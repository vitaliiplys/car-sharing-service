package com.example.carsharingservice.dto;

public record UserResponseDto(
        Long id,
        String email,
        String firstName,
        String lastName
) {
}