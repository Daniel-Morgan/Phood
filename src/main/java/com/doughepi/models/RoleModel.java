package com.doughepi.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * Created by dough on 1/30/2017.
 */
@Entity
@Table(name = "role")
public class RoleModel
{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "roleid", length = 16)
    private UUID roleID;

    @Column(name = "rolename")
    private String roleName;

    @ManyToMany(mappedBy = "roleSet")
    private Set<UserModel> userModelSet;


    public UUID getRoleID()
    {
        return roleID;
    }

    public void setRoleID(UUID roleID)
    {
        this.roleID = roleID;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public Set<UserModel> getUserModelSet()
    {
        return userModelSet;
    }

    public void setUserModelSet(Set<UserModel> userModelSet)
    {
        this.userModelSet = userModelSet;
    }
}
