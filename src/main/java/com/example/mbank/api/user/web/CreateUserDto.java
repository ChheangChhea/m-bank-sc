package com.example.mbank.api.user.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDto(String uuid,
                            @NotBlank
                          String name,

                            @NotBlank
                            String gender,
                            @NotBlank
                            @Email
                            String email,

                            @NotBlank
                            @Size
                            String password) {



}
