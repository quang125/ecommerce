package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.dao.UserDao;
import com.example.ecommercebackendproject.model.dto.UserDto;
import com.example.ecommercebackendproject.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthService authService;

    @Override
    public UserDto updateInformation(UserDto userDto) throws Exception {
        try {
            User user = userDao.findById(userDto.getId()).get();
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setFullName(userDto.getFullName());
            user.setPhoneNumber(userDto.getPhoneNumber());
            userDao.save(user);
            return new UserDto(user.getId(),user.getEmail(), user.getPhoneNumber(), user.getUsername(), user.getPassword(), user.getRole().getRole(), user.getFullName());
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UserDto> findAllByRole(Long id) {
        List<User>userList=userDao.findAllByRoleId(id);
        List<UserDto>userDtos=new ArrayList<>();
        for(User x:userList) userDtos.add(new UserDto(x.getId(),x.getEmail(),x.getPhoneNumber(), x.getPassword(), x.getUsername(),x.getRole().getRole(),x.getFullName()));
        return userDtos;
    }


}
