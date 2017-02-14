package com.doughepi.services;

import com.doughepi.models.UserModel;
import com.doughepi.repositories.RoleRepository;
import com.doughepi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by dough on 2017-02-06.
 */
@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    UserRepository userRepository;


    @Override
    public void printAllUserData()
    {
        List<UserModel> userModelList = userRepository.findAll().stream().sorted(
                Comparator.comparing(UserModel::getUserLastName)
        ).collect(Collectors.toList());


        for (UserModel userModel : userModelList)
        {
            logger.log(Level.INFO, String.format(
                    "%s %s %s %s %s %s",
                    userModel.getUserID(),
                    userModel.getUserUsername(),
                    userModel.getUserEmail(),
                    userModel.getUserFirstName(),
                    userModel.getUserMiddleInitial(),
                    userModel.getUserLastName()));
        }

    }

    @Override
    public UserModel findByUsername(String username)
    {
        return userRepository.findByUserUsername(username);
    }

    @Override
    public UserModel findByEmail(String email)
    {
        return userRepository.findByUserEmail(email);
    }

    @Override
    public UserModel save(UserModel userModel)
    {
        userModel.setUserPassword(bCryptPasswordEncoder.encode(userModel.getUserPassword()));
        userModel.setRoleSet(new HashSet<>());
        return userRepository.save(userModel);
    }
}
