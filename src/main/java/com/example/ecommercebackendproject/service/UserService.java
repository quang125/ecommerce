package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.dao.RoleDao;
import com.example.ecommercebackendproject.dao.UserDao;
import com.example.ecommercebackendproject.jwt.JwtResponse;
import com.example.ecommercebackendproject.jwt.JwtTokenProvider;
import com.example.ecommercebackendproject.model.CustomUserDetails;
import com.example.ecommercebackendproject.model.dto.LoginForm;
import com.example.ecommercebackendproject.model.dto.RegistrationForm;
import com.example.ecommercebackendproject.model.entity.Role;
import com.example.ecommercebackendproject.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User>user=userDao.findByUsername(username);
        if(user.isPresent()){
            return new CustomUserDetails(user.get());
        }
        throw new UsernameNotFoundException(username);
    }

    public UserDetails loadUserById(Long userId) {
        Optional<User>user=userDao.findById(userId);
        if(user.isPresent()){
            return new CustomUserDetails(user.get());
        }
        throw new UsernameNotFoundException("");
    }
}
