package com.example.demo.utils;

public class UtilisateurHelper {
    private UtilisateurHelper() {
    }

    public static final int PASSWORD_LENGTH = 10;

    // function to generate a random string of length n
    public static String generateRandomPassword() {
        // chose a Character random from this String
        String alphaNumericString =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (alphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(alphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
