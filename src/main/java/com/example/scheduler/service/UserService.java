package com.example.scheduler.service;

import com.example.scheduler.shared.UserDto;

public interface UserService {

  UserDto createUser(UserDto user);

  UserDto getUserByUserId(Long userId);

  UserDto getUserByEmail(String email);

}
