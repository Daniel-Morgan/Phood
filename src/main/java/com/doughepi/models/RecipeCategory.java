package com.doughepi.models;

/**
 * Created by ajreicha on 3/29/17.
 */
public enum RecipeCategory {

    BEEF("Beef"), CHICKEN("Chicken"), PASTA("Pasta"), PORK("Pork"), SALMON("Salmon"),
    GLUTEN_FREE("Gluten Free"), BREADS("Breads"), CAKES("Cakes"), SALADS("Salads"),
    SMOOTHIES("Smoothies"), SOUPS("Soups"), OTHER("Other");

    private final String enumText;

    RecipeCategory(String enumText) {
        this.enumText = enumText;
    }

    public static RecipeCategory mapFrom(String categoryName) {
        for (RecipeCategory recipeCategory : RecipeCategory.values()) {
            if (categoryName.equalsIgnoreCase(recipeCategory.getEnumText())) {
                return recipeCategory;
            }
        }
        return OTHER;
    }

    public String getEnumText() {
        return enumText;
    }
}
