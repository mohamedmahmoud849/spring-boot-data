package springbootdata.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springbootdata.model.Author;


@Repository
public interface AuthorDataRepo extends CrudRepository<Author, Long> {
}
