package com.example.carsharingservice.service;

import com.example.carsharingservice.dto.UserRegistrationRequestDto;
import com.example.carsharingservice.dto.UserResponseDto;
import com.example.carsharingservice.exception.RegistrationException;
import com.example.carsharingservice.mapper.UserMapper;
import com.example.carsharingservice.model.Role;
import com.example.carsharingservice.model.User;
import com.example.carsharingservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration.");
        }
        User user = userMapper.toModel(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role role = roleService.findByName(Role.RoleName.ROLE_CUSTOMER);
        user.getRoles().add(role);
        return userMapper.toDto(userRepository.save(user));
    }
}
