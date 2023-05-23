package springbootdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import springbootdata.contoller.ArticlesController;
import springbootdata.contoller.AuthorController;
import springbootdata.errorhandlling.NotFoundException;
import springbootdata.model.Article;
import springbootdata.model.Links;
import springbootdata.repository.ArticleDataRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    public ArticleDataRepo repo;

    @Override
    public List<Article> getAllArticles() {
        Iterable<Article> iterable = repo.findAll();
        return Streamable.of(iterable).toList();

    }

    @Override
    public Article getArticleById(Long id) {
        Optional<Article> article = repo.findById(id);
        return article.orElseThrow(() -> new NotFoundException(String.format("The Article with id '%s' was not found", id)));
    }

    @Override
    public List<Article> getArticlesByAuthorName(String authorName) {


        List<Article> articleList = new ArrayList<>();
        for (Article article : repo.findAllByName(authorName)) {
            if (authorName.equals(article.getAuthor())) {
                Article articleWithLinks = addLinks(article);
                articleList.add(articleWithLinks);
            }
        }
        return articleList;
    }

    @Override
    public Article addArticle(Article article) {
        repo.save(article);
        return addLinks(article);
    }

    @Override
    public void deleteArticle(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Article updateArticle(Long id, Article article) {
        return repo.save(article);
    }

    private Article addLinks(Article article) {
        List<Links> links = new ArrayList<>();
        Links self = new Links();

        Link selfLink = linkTo(methodOn(ArticlesController.class)
                .getArticle(article.getId())).withRel("self");

        self.setRel("self");
        self.setHref(selfLink.getHref());

        Links authorLink = new Links();
        Link authLink = linkTo(methodOn(AuthorController.class)
                .getAuthorById(article.getAuthorId())).withRel("author");
        authorLink.setRel("author");
        authorLink.setHref(authLink.getHref());

        links.add(self);
        links.add(authorLink);
        article.setLinks(links);
        return article;
    }
}
