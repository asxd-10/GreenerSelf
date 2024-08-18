package com.greenerself.sustainability.globalchanges.repo;


import com.greenerself.sustainability.globalchanges.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
