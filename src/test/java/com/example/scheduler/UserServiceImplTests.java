package com.example.scheduler;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.example.scheduler.io.entity.UserEntity;
import com.example.scheduler.service.UserServiceImpl;
import com.example.scheduler.shared.UserDto;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTests {

  final String USER_ID = "1234";
  @InjectMocks
  UserServiceImpl userService;
  @Mock
  UserRepository userRepository;
  UserDto userDto;
  Optional<UserEntity> userEntity;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    createUserDto();
    createUserEntity();
  }

  private void createUserEntity() {
    userEntity = Optional.of(new UserEntity());
    userEntity.get().setUserId(new Long(USER_ID));
    userEntity.get().setFirstName("Test");
    userEntity.get().setLastName("User");
    userEntity.get().setEmail("test_user2@intuit.com");
  }

  private void createUserDto() {
    userDto = new UserDto();
    userDto.setUserId(new Long(USER_ID));
    userDto.setFirstName("Test");
    userDto.setLastName("User");
    userDto.setEmail("test_user2@intuit.com");
  }

  @Test
  final void testGetUserByUserId() {
    when(userRepository.findById(userDto.getUserId())).thenReturn(userEntity);
    UserDto returnedValue = userService.getUserByUserId(userDto.getUserId());
    assertNotNull(returnedValue);
  }

  @Test
  final void testGetUserByEmail() {
    when(userRepository.findByEmail(userDto.getEmail())).thenReturn(userEntity.get());
    UserDto returnedValue = userService.getUserByEmail(userDto.getEmail());
    assertNotNull(returnedValue);
  }


}
