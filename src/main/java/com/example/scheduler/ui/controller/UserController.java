package com.example.scheduler.ui.controller;

import com.example.scheduler.service.UserService;
import com.example.scheduler.shared.UserDto;
import com.example.scheduler.ui.model.request.UserDetailsRequestModel;
import com.example.scheduler.ui.model.response.ErrorMessages;
import com.example.scheduler.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")

public class UserController {

  @Autowired
  UserService userService;

  @GetMapping(path = "/{userId}")
  public UserRest getUser(@PathVariable String userId) {
    UserRest returnValue = new UserRest();
    UserDto userDto = userService.getUserByUserId(Long.valueOf(userId));
    BeanUtils.copyProperties(userDto, returnValue);
    return returnValue;
  }

  @PostMapping
  public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
    UserRest returnValue = new UserRest();
    validateInput(userDetails);
    UserDto userDto = new UserDto();
    BeanUtils.copyProperties(userDetails, userDto);
    UserDto createdUser = userService.createUser(userDto);
    BeanUtils.copyProperties(createdUser, returnValue);
    return returnValue;
  }

  private void validateInput(UserDetailsRequestModel userDetails) {
    try {
      if (userDetails.getEmail().isEmpty()
          || userDetails.getFirstName().isEmpty()
          || userDetails.getLastName().isEmpty()) {
        throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
      }
    } catch (Exception e) {
      throw new RuntimeException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    }

  }

}
