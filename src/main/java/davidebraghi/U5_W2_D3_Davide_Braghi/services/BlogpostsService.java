package davidebraghi.U5_W2_D3_Davide_Braghi.services;

import davidebraghi.U5_W2_D3_Davide_Braghi.entities.Blogpost;
import davidebraghi.U5_W2_D3_Davide_Braghi.exceptions.NotFoundException;
import davidebraghi.U5_W2_D3_Davide_Braghi.payloads.NewBlogpostPayload;
import davidebraghi.U5_W2_D3_Davide_Braghi.repositories.BlogpostsRepositoty;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BlogpostsService {
    @Autowired
    private BlogpostsRepositoty blogpostsRepositoty;

    public Page<Blogpost> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.blogpostsRepositoty.findAll(pageable);
    }

    public Blogpost save(NewBlogpostPayload payload) {
        this.blogpostsRepositoty.findByTitle(payload.getTitle()).ifPresent(blogpost -> {
                    try {
                        throw new BadRequestException("The Title " + blogpost.getTitle() + " is already saved into the system.");
                    } catch (BadRequestException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        Blogpost newBlogpost = new Blogpost(payload.getCategory(),
                payload.getTitle(),
                payload.getCover(),
                payload.getContent(),
                payload.getReadingTime());

        Blogpost savedBlogpost = this.blogpostsRepositoty.save(newBlogpost);

        return savedBlogpost;
    }

    public Blogpost findById(int id) {
        return this.blogpostsRepositoty.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Blogpost findByIdAndUpdate(int id, NewBlogpostPayload payload) {
        Blogpost found = this.findById(id);

        if (!found.getTitle().equals(payload.getTitle())) {
            this.blogpostsRepositoty.findByTitle(payload.getTitle()).ifPresent(blogpost -> {
                        try {
                            throw new BadRequestException("The Title " + blogpost.getTitle() + " is already saved into the system.");
                        } catch (BadRequestException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }

        found.setCategory(payload.getCategory());
        found.setTitle(payload.getTitle());
        found.setCover(payload.getCover());
        found.setContent(payload.getContent());
        found.setReadingTime(payload.getReadingTime());

        Blogpost modifiedBlogpost = this.blogpostsRepositoty.save(found);

        log.info("The Blogpost with Title " + modifiedBlogpost.getTitle() + " was duly saved.");

        return modifiedBlogpost;
    }


    public void findByIdAndDelete(int id) {
        Blogpost found = this.findById(id);
        this.blogpostsRepositoty.delete(found);
    }
}