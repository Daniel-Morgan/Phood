package com.doughepi.services;

import com.doughepi.models.RecipeModel;
import com.doughepi.models.SearchResults;
import com.doughepi.repositories.RecipeRepository;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pjdoughe on 3/23/17.
 */
@Service
public class SearchService {

    @Autowired
    RecipeRepository recipeRepository;

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    @Transactional
    public SearchResults<RecipeModel> getSearchResults(String query) {

        long start = System.currentTimeMillis();


        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(RecipeModel.class).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().fuzzy().onFields("recipeName",
                "recipeDescription", "categoryName").matching(query).createQuery();
        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, RecipeModel.class);
        List resultList = jpaQuery.getResultList();

        ArrayList<RecipeModel> recipeList = new ArrayList<>();
        for (Object o : resultList) {
            if (o instanceof RecipeModel) {
                recipeList.add(((RecipeModel) o));
            }
        }


        long end = System.currentTimeMillis();

        long total = end - start;
        return new SearchResults<>(query, recipeList, total, "ms");
    }
}
