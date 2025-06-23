package com.codyking.moviematch.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.codyking.moviematch.model.User;
import com.codyking.moviematch.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
  @Mock private UserRepository userRepository;

  @InjectMocks private UserService userService;

  @Test
  void testGetAllUsers() {
    User fakeUser1 = new User();
    User fakeUser2 = new User();
    User fakeUser3 = new User();
    List<User> allFakeUsers = List.of(fakeUser1, fakeUser2, fakeUser3);

    when(userRepository.findAll()).thenReturn(allFakeUsers);

    List<User> result = userService.getAllUsers();

    assertEquals(allFakeUsers, result);

    verify(userRepository).findAll();
  }

  @Test
  void testGetUserById() {
    User fakeUser1 = new User();
    User fakeUser2 = new User();
    fakeUser1.setId(10L);
    fakeUser2.setId(20L);

    when(userRepository.findById(10L))
        .thenReturn(
            Optional.of(fakeUser1)); // Returning as optional since that is findByID return type

    User result = userService.getUserById(10L);
    User resultIfNoneFound = userService.getUserById(12L);

    assertEquals(fakeUser1, result);
    assertNull(resultIfNoneFound);

    verify(userRepository).findById(10L);
    verify(userRepository).findById(12L);
  }

  @Test
  void testCreateUser() {
    User fakeUser1 = new User();

    when(userRepository.save(fakeUser1)).thenReturn(fakeUser1);

    User result = userService.createUser(fakeUser1);

    assertEquals(result, fakeUser1);

    verify(userRepository).save(fakeUser1);
  }

  @Test
  void testUpdateUser() {
    User fakeUser1 = new User();
    fakeUser1.setId(1L);

    User updatedFakeUser1 = new User();
    updatedFakeUser1.setId(fakeUser1.getId());
    updatedFakeUser1.setEmail("fakeemail84@gmail.com");

    when(userRepository.existsById(fakeUser1.getId())).thenReturn(true);
    when(userRepository.save(updatedFakeUser1)).thenReturn(updatedFakeUser1);

    User result = userService.updateUser(fakeUser1.getId(), updatedFakeUser1);

    assertThrows(RuntimeException.class, () -> userService.updateUser(2L, updatedFakeUser1));
    assertEquals(result, updatedFakeUser1);

    verify(userRepository).save(updatedFakeUser1);
  }

  @Test
  void testDeleteUser() {
    User fakeUser1 = new User();
    fakeUser1.setId(1L);

    when(userRepository.existsById(1L)).thenReturn(true);

    userService.deleteUser(fakeUser1.getId());

    assertThrows(RuntimeException.class, () -> userService.deleteUser(2L));

    verify(userRepository).deleteById(fakeUser1.getId());
  }
}
