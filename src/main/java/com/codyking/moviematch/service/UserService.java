package com.codyking.moviematch.service;

import com.codyking.moviematch.model.User;
import com.codyking.moviematch.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(Long id) {
    Optional<User> user = userRepository.findById(id);
    return user.orElse(null);
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public User updateUser(Long id, User user) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User not found");
    }

    user.setId(id);

    return userRepository.save(user);
  }

  public void deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User not found");
    }

    userRepository.deleteById(id);
  }
}
