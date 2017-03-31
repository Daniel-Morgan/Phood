package com.doughepi.services;

import com.doughepi.models.RecipeModel;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by pjdoughe on 3/23/17.
 */
@Service
public class StartupService {

    Logger logger = Logger.getLogger(getClass().getName());

    @PersistenceContext
    EntityManager entityManager;


    @Autowired
    RecipeService recipeService;

    @Scheduled(fixedRate = Long.MAX_VALUE)
    public void indexExisting() {

        logger.info("Indexing now, son.");

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for (List<RecipeModel> recipeModels : recipeService.getTopRecipeforCategories()) {
            for (RecipeModel recipeModel : recipeModels) {
                logger.info(recipeModel.getRecipeName());
            }
        }
    }

}
