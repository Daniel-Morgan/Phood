package com.doughepi.repositories;

import com.doughepi.models.IngredientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by ajreicha on 2/28/17.
 */
public interface IngredientRepository extends JpaRepository<IngredientModel, UUID>{
}
