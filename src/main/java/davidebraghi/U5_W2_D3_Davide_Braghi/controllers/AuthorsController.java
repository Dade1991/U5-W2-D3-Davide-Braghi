package davidebraghi.U5_W2_D4_Davide_Braghi.controllers;

import davidebraghi.U5_W2_D3_Davide_Braghi.entities.Author;
import davidebraghi.U5_W2_D3_Davide_Braghi.payloads.NewAuthorPayload;
import davidebraghi.U5_W2_D3_Davide_Braghi.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    AuthorsService authorsService;

    // 1. - GET http://localhost:3001/authors
    @GetMapping
    public Page<Author> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return this.authorsService.findAll(page, size, sortBy);
    }

    // 2. - POST http://localhost:3001/authors (+ req.body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Author saveAuthor(@RequestBody NewAuthorPayload payload) {
        return this.authorsService.save(payload);
    }

    // 3. - GET http://localhost:3001/authors/{id}
    @GetMapping("/{authorId}")
    public Author findById(@PathVariable int authorId) {
        return this.authorsService.findById(authorId);
    }

    // 4. - PUT http://localhost:3001/authors/{id} (+ req.body)
    @PutMapping("/{authorId}")
    public Author findAndUpdate(@PathVariable int authorId, @RequestBody NewAuthorPayload payload) {
        return this.authorsService.findByIdAndUpdate(authorId, payload);
    }

    // 5. - DELETE http://localhost:3001/authors/{id}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int authorId) {
        this.authorsService.findByIdAndDelete(authorId);
    }
}
