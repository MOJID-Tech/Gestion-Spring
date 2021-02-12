package com.example.demo.exception;
import static java.lang.String.format;

import org.springframework.http.HttpStatus;


/**
 * Cette enum représente les codes d'erreur fonctionnels.
 */

public enum FunctionalErrorCode
{

    BAD_REQUEST(0, HttpStatus.BAD_REQUEST, "Requête mal formulée"),
    NOT_FOUND_ENTITY(1, HttpStatus.NOT_FOUND, "Aucun enregistrement de type %s et avec l'id %s n'est présent dans la base de donnée"),
    NOT_FOUND_ENTITY_BY_FIELD(1, HttpStatus.NOT_FOUND, "Aucun enregistrement de type %s et avec la propriété %s = %s n'est présent dans la base de donnée"),
    NOT_NULL_FIELD(2, HttpStatus.BAD_REQUEST, "Le champ suivant est obligatoire : %s"),
    NOT_NULL_FIELDS(2, HttpStatus.BAD_REQUEST, "Les champs suivants sont obligatoires : %s"),
    MUST_BE_NULL_FIELD(3, HttpStatus.BAD_REQUEST, "Le champ suivant doit être null : %s"),
    INSUFFICIENT_RIGHT(4, HttpStatus.UNAUTHORIZED, "Droits insuffisant"),
    USER_DEACTIVATED(5, HttpStatus.UNAUTHORIZED, "Utilisateur désactivé"),
    INVALID_JWT_TOKEN(6, HttpStatus.UNAUTHORIZED, "Token JWT expiré ou invalide"),
    PROPERTY_ALREADY_USED(7, HttpStatus.NOT_FOUND, "%s est deja utilisée"),
    NOTIF_ALREADY_RESPONDED(8, HttpStatus.FOUND, "L'utilisateur %s a déjà répondu à cette notification"),
    WRONG_PASSWORD(9, HttpStatus.UNAUTHORIZED, "Mot de passe incorrecte"),
    FORBIDDEN_ACTION(11, HttpStatus.FORBIDDEN, "Action non autorisée"),
    EMAIL_NOT_FOUND(15, HttpStatus.NOT_FOUND, "Email non trouvé"),
    SEND_EMAIL(10, HttpStatus.BAD_REQUEST, "Erreur lors de l'envoie de l'email"),
    BAD_CREDENTIALS(12, HttpStatus.UNAUTHORIZED, "Identifiant ou mot de passe incorrect"),
    DELETE_USED_EVENT(13, HttpStatus.FORBIDDEN, "Suppression impossible car cet événement est utilisé"),
    CREDENTIAL_ALREADY_USED(14, HttpStatus.BAD_REQUEST, "%s existe déjà");

    private final String code;
    private final HttpStatus httpStatus;
    private final String messageTemplate;

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    FunctionalErrorCode(int code, HttpStatus httpStatus, String messageTemplate) {
        this.code = format("%03d", code);
        this.httpStatus = httpStatus;
        this.messageTemplate = messageTemplate;
    }
}
