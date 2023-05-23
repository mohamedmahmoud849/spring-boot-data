package springbootdata.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootdata.model.Author;
import springbootdata.service.AuthorService;

@RestController
@RequestMapping(value = "/v1")
public class AuthorController {

    @Autowired
    @Qualifier("authorServiceImpl")
    private AuthorService authorService;


    @GetMapping(value = "/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PostMapping(value = "/authors", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<Author> addArticle(@RequestBody Author author) {
        author = authorService.addAuthor(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

}
