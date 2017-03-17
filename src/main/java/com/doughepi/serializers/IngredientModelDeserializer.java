package com.doughepi.serializers;

import com.doughepi.models.IngredientModel;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by ajreicha on 3/16/17.
 */
public class IngredientModelDeserializer implements JsonDeserializer<IngredientModel> {

    @Override
    public IngredientModel deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject ingredientJson = jsonElement.getAsJsonObject();

        IngredientModel ingredientModel = new IngredientModel();

        String ingredientQuantity = ingredientJson.get("ingredientQuantity").getAsString();
        String ingredientUnit = ingredientJson.get("ingredientUnit").getAsString();
        String ingredientName = ingredientJson.get("ingredientName").getAsString();

        ingredientModel.setIngredientQuantity(Double.parseDouble(ingredientQuantity));
        ingredientModel.setIngredientUnit(ingredientUnit);
        ingredientModel.setIngredientName(ingredientName);

        return ingredientModel;

    }
}