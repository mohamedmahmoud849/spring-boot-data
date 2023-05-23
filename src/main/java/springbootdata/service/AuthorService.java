package springbootdata.service;


import springbootdata.model.Author;

public interface AuthorService {
    Author getAuthorById(Long id);

    Author addAuthor(Author author);
}
