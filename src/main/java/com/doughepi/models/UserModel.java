package com.doughepi.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by dough on 1/30/2017.
 */
@Entity
@Table(name = "\"user\"")
public class UserModel
{
    //Primary key for Hibernate.
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "userID")
    private UUID userID;

    //User login information.
    @Column(name = "userUsername")
    private String userUsername;

    @Column(name = "userPassword")
    private String userPassword;

    @Transient
    private String userConfirmationPassword;

    //User personal information.
    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "userFirstName")
    private String userFirstName;

    @Column(name = "userMiddleInitial")
    private String userMiddleInitial;

    @Column(name = "userLastName")
    private String userLastName;

    //Used for testing.
    @Column(name = "other")
    private UUID other;

    //User assigned roles.
    @ManyToMany
    @JoinTable(name = "roles", joinColumns = @JoinColumn(name = "userID"), inverseJoinColumns = @JoinColumn(name = "roleID"))
    private Set<RoleModel> roleSet;

    // Foreign key linking recipes to the user
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userID")
    private List<RecipeModel> recipeModels;


    public String getUserUsername()
    {
        return userUsername;
    }

    public void setUserUsername(String userUsername)
    {
        this.userUsername = userUsername;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public String getUserConfirmationPassword()
    {
        return userConfirmationPassword;
    }

    public void setUserConfirmationPassword(String userConfirmationPassword)
    {
        this.userConfirmationPassword = userConfirmationPassword;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public String getUserFirstName()
    {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName)
    {
        this.userFirstName = userFirstName;
    }

    public String getUserMiddleInitial()
    {
        return userMiddleInitial;
    }

    public void setUserMiddleInitial(String userMiddleInitial)
    {
        this.userMiddleInitial = userMiddleInitial;
    }

    public String getUserLastName()
    {
        return userLastName;
    }

    public void setUserLastName(String userLastName)
    {
        this.userLastName = userLastName;
    }

    public Set<RoleModel> getRoleSet()
    {
        return roleSet;
    }

    public void setRoleSet(Set<RoleModel> roleSet)
    {
        this.roleSet = roleSet;
    }

    public UUID getOther()
    {
        return other;
    }

    public void setOther(UUID other)
    {
        this.other = other;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof UserModel && ((UserModel) obj).getUserID().equals(getUserID());
    }

    public UUID getUserID()
    {
        return userID;
    }

    public void setUserID(UUID userID)
    {
        this.userID = userID;
    }

    public List<RecipeModel> getRecipeModels() {
        return recipeModels;
    }

    public void setRecipeModels(List<RecipeModel> recipeModels) {
        this.recipeModels = recipeModels;
    }

    @Override
    public String toString() {
        return String.format("[UUID: %s, Username: %s, Email: %s, FirstName: %s, LastName: %s]",
                getUserID(), getUserUsername(), getUserEmail(), getUserFirstName(), getUserLastName());
    }
}
