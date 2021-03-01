package com.example.scheduler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.example.scheduler.service.UserServiceImpl;
import com.example.scheduler.shared.UserDto;
import com.example.scheduler.ui.controller.UserController;
import com.example.scheduler.ui.model.response.UserRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserControllerTests {

  final String USER_ID = "1234";
  @InjectMocks
  UserController userController;
  @Mock
  UserServiceImpl userService;
  UserDto userDto;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    createUserDto();

  }

  private void createUserDto() {
    userDto = new UserDto();
    userDto.setUserId(new Long(USER_ID));
    userDto.setFirstName("Test");
    userDto.setLastName("User");
    userDto.setEmail("test_user2@intuit.com");
  }

  @Test
  final void testGetUser() {
    when(userService.getUserByUserId(userDto.getUserId())).thenReturn(userDto);
    UserRest userRest = userController.getUser(USER_ID);
    assertNotNull(userRest);
    assertEquals(USER_ID, userRest.getUserId().toString());
  }

}
