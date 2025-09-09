package com.asli.demo.controller;

import com.asli.demo.dto.UserDto;
import com.asli.demo.entity.User;
import com.asli.demo.mapper.UserMapper;
import com.asli.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper; // Mapper eklendi

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    // CREATE
    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User saved = userService.createUser(user);
        return userMapper.toDto(saved);
    }

    // READ - all
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    // READ - by id
    @GetMapping("/{id}")
    public Optional<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(userMapper::toDto);
    }

    // UPDATE
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User updated = userService.updateUser(id, user);
        return userMapper.toDto(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
