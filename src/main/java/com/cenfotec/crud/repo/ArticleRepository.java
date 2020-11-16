package com.cenfotec.crud.repo;


import com.cenfotec.crud.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
