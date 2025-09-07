package com.asli.demo.service.impl;

import com.asli.demo.entity.User;
import com.asli.demo.repository.UserRepository;
import com.asli.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // REQUIRED (default)
    @Transactional(propagation = Propagation.REQUIRED)
    public User createUserRequired(User user) {
        return userRepository.save(user);
    }

    // REQUIRES_NEW → her zaman yeni bir transaction açar
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User updateUserRequiresNew(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    // SUPPORTS → transaction varsa kullanır, yoksa kullanmaz
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<User> getUserSupports(Long id) {
        return userRepository.findById(id);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public User createUserWithRollback(User user, boolean throwError) {
        User savedUser = userRepository.save(user);
        if (throwError) {
            throw new RuntimeException("Rollback Test Exception");
        }
        return savedUser;
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public User updateUserReadCommitted(Long id, User user) {
        return updateUser(id, user);
    }


    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public User updateUserRepeatableRead(Long id, User user) {
        return updateUser(id, user);
    }
}
