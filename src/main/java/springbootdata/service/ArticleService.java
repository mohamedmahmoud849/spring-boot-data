package springbootdata.service;


import springbootdata.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles();

    Article getArticleById(Long id);

    List<Article> getArticlesByAuthorName(String authorName);

    Article addArticle(Article article);

    void deleteArticle(Long id);

    Article updateArticle(Long id, Article article);
}
