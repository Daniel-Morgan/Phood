package com.doughepi.services;

import com.doughepi.models.UserModel;
import com.doughepi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by dough on 1/30/2017.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserModel userModel = userRepository.findByUserUsername(username);
        if (userModel == null)
        {
            throw new UsernameNotFoundException("The supplied username does not exist in the database.");
        }
        Set<GrantedAuthority> grantedAuthorities = userModel.getRoleSet().stream().map(roleModel -> new
                SimpleGrantedAuthority(roleModel.getRoleName())).collect(Collectors.toSet());
        return new User(userModel.getUserUsername(), userModel.getUserPassword(), grantedAuthorities);
    }
}
