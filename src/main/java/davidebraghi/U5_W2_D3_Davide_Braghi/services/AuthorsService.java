package davidebraghi.U5_W2_D3_Davide_Braghi.services;

import davidebraghi.U5_W2_D3_Davide_Braghi.entities.Author;
import davidebraghi.U5_W2_D3_Davide_Braghi.exceptions.NotFoundException;
import davidebraghi.U5_W2_D3_Davide_Braghi.payloads.NewAuthorPayload;
import davidebraghi.U5_W2_D3_Davide_Braghi.repositories.AuthorsRepository;
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
public class AuthorsService {
    @Autowired
    private AuthorsRepository authorsRepository;

    public Page<Author> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.authorsRepository.findAll(pageable);
    }

    public Author save(NewAuthorPayload payload) {
        this.authorsRepository.findByEmail(payload.getEmail()).ifPresent(author -> {
                    try {
                        throw new BadRequestException("The e-mail " + author.getEmail() + " is already in use.");
                    } catch (BadRequestException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        Author newAuthor = new Author(payload.getName(),
                payload.getSurname(),
                payload.getEmail(),
                payload.getDateOfBirth());
        newAuthor.setAvatar("https://ui-avatars.com/api/?name=" + payload.getName() + "+" + payload.getSurname());

        Author savedAuthor = this.authorsRepository.save(newAuthor);

        log.info("The user with ID: " + savedAuthor.getId() + " was duly saved.");

        return savedAuthor;
    }

    public Author findById(int id) {
        return this.authorsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Author findByIdAndUpdate(int id, NewAuthorPayload payload) {
        Author found = this.findById(id);

        if (!found.getEmail().equals(payload.getEmail())) {
            this.authorsRepository.findByEmail(payload.getEmail()).ifPresent(author -> {
                        try {
                            throw new BadRequestException("The e-mail " + author.getEmail() + " is already in use.");
                        } catch (BadRequestException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }

        found.setName(payload.getName());
        found.setSurname(payload.getSurname());
        found.setEmail(payload.getEmail());
        found.setDateOfBirth(payload.getDateOfBirth());
        found.setAvatar("https://ui-avatars.com/api/?name=" + payload.getName() + "+" + payload.getSurname());

        Author modifiedAuthor = this.authorsRepository.save(found);

        log.info("The user with ID: " + modifiedAuthor.getId() + " was duly saved.");

        return modifiedAuthor;
    }

    public void findByIdAndDelete(int id) {
        Author found = this.findById(id);
        this.authorsRepository.delete(found);
    }
}