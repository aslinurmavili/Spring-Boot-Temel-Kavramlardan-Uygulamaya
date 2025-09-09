package com.asli.demo.mapper;

import com.asli.demo.dto.UserDto;
import com.asli.demo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User toEntity(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }
}
