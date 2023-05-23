package springbootdata.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;
import springbootdata.model.Article;

import java.util.List;


@Repository
public interface ArticleDataRepo extends CrudRepository<Article, Long> {


    List<Article> findAllByName(String name);

}
