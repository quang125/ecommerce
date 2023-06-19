package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.model.dto.UserDto;
import com.example.ecommercebackendproject.model.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    UserDto updateInformation(UserDto userDto) throws Exception;

    public List<UserDto> findAllByRole(Long id);
}
