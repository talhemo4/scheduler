package com.example.scheduler.service;

import com.example.scheduler.UserRepository;
import com.example.scheduler.UserRiskRepository;
import com.example.scheduler.io.entity.UserEntity;
import com.example.scheduler.io.entity.UserRiskEntity;
import com.example.scheduler.shared.UserDto;
import com.example.scheduler.ui.model.response.ErrorMessages;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserRiskRepository userRiskRepository;

  @Override
  public UserDto createUser(UserDto user) {
    if (userRepository.findByEmail(user.getEmail()) != null) {
      throw new RuntimeException(ErrorMessages.USER_EXISTS.getErrorMessage());
    }
    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(user, userEntity);
    UserEntity storedUserDetails = userRepository.save(userEntity);
    insertRandomUserRisk(storedUserDetails.getUserId());
    UserDto returnedValue = new UserDto();
    BeanUtils.copyProperties(storedUserDetails, returnedValue);
    return returnedValue;
  }

  private void insertRandomUserRisk(Long userId) {
    UserRiskEntity userRiskEntity = new UserRiskEntity();
    userRiskEntity.setUserId(userId);
    userRiskEntity.setRisk(generateRandomScore());
    userRiskRepository.save(userRiskEntity);
  }

  private double generateRandomScore() {
    Random r = new Random();
    double randomScore = 0 + (100 - 0) * r.nextDouble();
    return randomScore;
  }

  @Override
  public UserDto getUserByUserId(Long userId) {
    UserDto returnedValue = new UserDto();
    Optional<UserEntity> userEntity = userRepository.findById(userId);
    if (userEntity == null) {
      throw new RuntimeException(ErrorMessages.USER_NOT_EXISTS.getErrorMessage());
    }
    BeanUtils.copyProperties(userEntity.get(), returnedValue);
    return returnedValue;
  }

  @Override
  public UserDto getUserByEmail(String email) {
    UserDto returnedValue = new UserDto();
    UserEntity userEntity = userRepository.findByEmail(email);
    if (userEntity == null) {
      throw new RuntimeException(ErrorMessages.USER_NOT_EXISTS.getErrorMessage());
    }
    BeanUtils.copyProperties(userEntity, returnedValue);
    return returnedValue;
  }
}
