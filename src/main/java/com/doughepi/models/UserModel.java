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
    private UUID userID;

    //User login information.
    private String userUsername;
    private String userPassword;
    private String userConfirmationPassword;

    //User personal information.
    private String userEmail;
    private String userFirstName;
    private String userMiddleInitial;
    private String userLastName;


    //Used for testing.
    private UUID other;

    //User assigned roles.
    private Set<RoleModel> roleSet;

    // Foreign key linking recipes to the user
    private List<RecipeModel> recipeModels;

    @Column(name = "userUsername")
    public String getUserUsername()
    {
        return userUsername;
    }

    public void setUserUsername(String userUsername)
    {
        this.userUsername = userUsername;
    }

    @Column(name = "userPassword")
    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    @Transient
    public String getUserConfirmationPassword()
    {
        return userConfirmationPassword;
    }

    public void setUserConfirmationPassword(String userConfirmationPassword)
    {
        this.userConfirmationPassword = userConfirmationPassword;
    }

    @Column(name = "userEmail")
    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    @Column(name = "userFirstName")
    public String getUserFirstName()
    {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName)
    {
        this.userFirstName = userFirstName;
    }

    @Column(name = "userMiddleInitial")
    public String getUserMiddleInitial()
    {
        return userMiddleInitial;
    }

    public void setUserMiddleInitial(String userMiddleInitial)
    {
        this.userMiddleInitial = userMiddleInitial;
    }

    @Column(name = "userLastName")
    public String getUserLastName()
    {
        return userLastName;
    }

    public void setUserLastName(String userLastName)
    {
        this.userLastName = userLastName;
    }

    @ManyToMany
    @JoinTable(name = "roles", joinColumns = @JoinColumn(name = "userID"), inverseJoinColumns = @JoinColumn(name = "roleID"))
    public Set<RoleModel> getRoleSet()
    {
        return roleSet;
    }

    public void setRoleSet(Set<RoleModel> roleSet)
    {
        this.roleSet = roleSet;
    }

    @Column(name = "other")
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

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "userID")
    public UUID getUserID()
    {
        return userID;
    }

    public void setUserID(UUID userID)
    {
        this.userID = userID;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userID")
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
