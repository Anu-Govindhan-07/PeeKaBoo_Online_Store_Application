package com.peekaboo.user.service;

import com.peekaboo.user.dto.UserRegistrationRequestDto;
import com.peekaboo.user.dto.UserResponseDto;

public interface UserService {

    UserResponseDto registerUser(UserRegistrationRequestDto request);
}