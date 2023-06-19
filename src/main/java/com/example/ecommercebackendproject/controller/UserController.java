package com.example.ecommercebackendproject.controller;

import com.example.ecommercebackendproject.model.dto.UserDto;
import com.example.ecommercebackendproject.model.entity.User;
import com.example.ecommercebackendproject.service.IUserService;
import com.example.ecommercebackendproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/customers")
    public List<UserDto> findAllCustomer(){
        return userService.findAllByRole(3L);
    }
    @GetMapping("/sellers")
    public List<UserDto> findAllSeller(){
        return userService.findAllByRole(2L);
    }
    @GetMapping("/admins")
    public List<UserDto> findAllAdmin(){
        return userService.findAllByRole(1L);
    }
    @PutMapping("/{userId}/password")
    public UserDto changePassword(@RequestBody UserDto userDto, @PathVariable String userId) throws Exception {
        return userService.updateInformation(userDto);
    }
    @PutMapping("/{userId}/phone")
    public UserDto changePhone(@RequestBody UserDto userDto, @PathVariable String userId) throws Exception {
        return userService.updateInformation(userDto);
    }
    @PutMapping("/{userId}/name")
    public UserDto changeName(@RequestBody UserDto userDto, @PathVariable String userId) throws Exception {
        return userService.updateInformation(userDto);
    }
}
