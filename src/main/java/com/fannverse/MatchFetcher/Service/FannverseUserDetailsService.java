package com.fannverse.MatchFetcher.Service;

import com.fannverse.MatchFetcher.Models.User;
import com.fannverse.MatchFetcher.Models.UserPrincipal;
import com.fannverse.MatchFetcher.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FannverseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println(userId);
        Optional<User> user = repo.findById(userId);
        if(user.isEmpty()) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user.get());
    }

}