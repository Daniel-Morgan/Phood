package com.doughepi.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by ajreicha on 2/20/17.
 */
@Entity
@Table(name = "image")
public class ImageModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "image_id", length = 16)
    private UUID imageID;


    @ManyToOne(cascade = CascadeType.MERGE)
    private RecipeModel recipeModel;

    @Column(name = "image_data")
    private Byte[] imageData;


    public UUID getImageID() {
        return imageID;
    }

    public void setImageID(UUID imageID) {
        this.imageID = imageID;
    }




    public RecipeModel getRecipeModel() {
        return recipeModel;
    }

    public void setRecipeModel(RecipeModel recipeModel) {
        this.recipeModel = recipeModel;
    }



    public Byte[] getImageData() {
        return imageData;
    }

    public void setImageData(Byte[] imageData) {
        this.imageData = imageData;
    }
}
