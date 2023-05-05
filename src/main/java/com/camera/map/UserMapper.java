package com.camera.map;

import org.springframework.stereotype.Component;

import com.camera.dto.UserDTO;
import com.camera.entity.UserEntity;

@Component
public class UserMapper {

    public UserDTO toDto(UserEntity user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
