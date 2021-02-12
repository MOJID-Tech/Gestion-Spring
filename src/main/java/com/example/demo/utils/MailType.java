package com.example.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MailType {
    RESET_PASSWORD("Réinitialisation du mot de passe", "Votre nouveau mot de passe est: %s");

    final String subject;
    final String content;
}
