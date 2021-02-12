package com.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChangePasswordDTO {

    private String currentPassword;

    private String newPassword;
}
