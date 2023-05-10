package com.cyansnbrst.EliteMed.services;

import com.cyansnbrst.EliteMed.entities.User;
import com.cyansnbrst.EliteMed.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private final UserService userService;

    @Mock
    private UserRepository userRepository;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("user1");
        User user2 = new User();
        user2.setId(2);
        user2.setUsername("user2");
        List<User> expectedUsers = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(expectedUsers);
        List<User> actualUsers = userService.getAllUsers();
        verify(userRepository, times(1)).findAll();
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testGetUserByPrincipal() {
        User expectedUser = new User();
        expectedUser.setId(1);
        expectedUser.setUsername("user1");
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("user1");
        when(userRepository.findByUsername("user1")).thenReturn(expectedUser);
        User actualUser = userService.getUserByPrincipal(principal);
        verify(userRepository, times(1)).findByUsername("user1");
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testGetUserByUsername() {
        User expectedUser = new User();
        expectedUser.setId(1);
        expectedUser.setUsername("user1");
        when(userRepository.findByUsername("user1")).thenReturn(expectedUser);
        User actualUser = userService.getUserByUsername("user1");
        verify(userRepository, times(1)).findByUsername("user1");
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testAddUser() {
        User userToAdd = new User();
        userToAdd.setUsername("newUser");
        userService.addUser(userToAdd);
        verify(userRepository, times(1)).save(userToAdd);
    }

    @Test
    public void testDeleteUser() {
        Integer idToDelete = 1;
        when(userRepository.existsById(idToDelete)).thenReturn(true);
        userService.deleteUser(idToDelete);
        verify(userRepository, times(1)).existsById(idToDelete);
        verify(userRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    public void testFindUserById() {
        User expectedUser = new User();
        expectedUser.setId(1);
        expectedUser.setUsername("user1");
        when(userRepository.findUserById(1)).thenReturn(expectedUser);
        User actualUser = userService.findUserById(1);
        verify(userRepository, times(1)).findUserById(1);
        assertEquals(expectedUser, actualUser);
    }
}