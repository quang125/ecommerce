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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@Service
public class AuthService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<User> findAll(){return userDao.findAll();};
    public Optional<User> findById(Long id){
        return userDao.findById(id);
    }
    public Optional<User>findByEmail(String email){
        return userDao.findByEmail(email);
    }
    public Optional<User>findByUsername(String username){
        return userDao.findByUsername(username);
    }
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationForm registrationForm) throws Exception {
        try {
            if (!userDao.findByUsername(registrationForm.getUsername()).isPresent() && !userDao.findByEmail(registrationForm.getEmail()).isPresent()) {
                User user = new User();
                user.setFullName(registrationForm.getFullName());
                user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
                user.setEmail(registrationForm.getEmail());
                user.setUsername(registrationForm.getUsername());
                user.setPhoneNumber(registrationForm.getPhoneNumber());
                Optional<Role> role = roleDao.findByRole(registrationForm.getRole());
                user.setRole(role.get());
                userDao.save(user);
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(registrationForm.getUsername(), registrationForm.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
                User currentUser = userDao.findByUsername(registrationForm.getUsername()).get();
                return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), currentUser.getUsername(), currentUser.getFullName()));
            }
            throw new Exception("Đã tồn tại người dùng, vui lòng chọn tên đăng nhập khác");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm){
        try {
            Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            User currentUser = userDao.findByUsername(loginForm.getUsername()).get();
            return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), currentUser.getUsername(), currentUser.getFullName()));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sai email hoặc mật khẩu!");
        }
    }
}
