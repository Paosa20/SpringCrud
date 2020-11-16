package com.cenfotec.crud.service;

import com.cenfotec.crud.domain.Article;
import com.cenfotec.crud.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleRepository articleRepo;

    @Override
    public void save(Article article) {

            articleRepo.save(article);
    }
}
