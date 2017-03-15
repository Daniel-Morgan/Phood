package com.doughepi.repositories;

import com.doughepi.models.RecipeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by ajreicha on 2/28/17.
 */
public interface RecipeRepository extends JpaRepository<RecipeModel, UUID> {
}
