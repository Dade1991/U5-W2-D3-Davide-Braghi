package davidebraghi.U5_W2_D4_Davide_Braghi.controllers;

import davidebraghi.U5_W2_D3_Davide_Braghi.entities.Blogpost;
import davidebraghi.U5_W2_D3_Davide_Braghi.payloads.NewBlogpostPayload;
import davidebraghi.U5_W2_D3_Davide_Braghi.services.BlogpostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogpostsController {
    @Autowired
    private BlogpostsService blogpostsService;

    // 1. - GET http://localhost:3001/blogs
    @GetMapping
    public Page<Blogpost> findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sortBy) {
        return this.blogpostsService.findAll(page, size, sortBy);
    }

    // 2. - POST http://localhost:3001/blogs (+ req.body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Blogpost saveBlog(@RequestBody NewBlogpostPayload payload) {
        return this.blogpostsService.save(payload);
    }

    // 3. - GET http://localhost:3001/blogs/{id}
    @GetMapping("/{blogId}")
    public Blogpost findById(@PathVariable int blogId) {
        return this.blogpostsService.findById(blogId);
    }

    // 4. - PUT http://localhost:3001/blogs/{id} (+ req.body)
    @PutMapping("/{blogId}")
    public Blogpost findAndUpdate(@PathVariable int blogId, @RequestBody NewBlogpostPayload payload) {
        return blogpostsService.findByIdAndUpdate(blogId, payload);
    }

    // 5. - DELETE http://localhost:3001/blogs/{id
    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int blogId) {
        this.blogpostsService.findByIdAndDelete(blogId);
    }
}

