package com.example.showtrack.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?!.*\\s)(?=.*[ !\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]).{8,20}$";
    static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    static final String USERNAME_PATTERN = "^[a-zA-Z0-9._-]{3,}$";

    /**
     * Metodo que comprueba que la contrasena es valida:
     * - Debe contener al menos un digito
     * - Debe contener al menos un caracter mayuscula
     * - Debe contener al menos un caracter minuscula
     * - Debe contener al menos un caracter especial
     * - Debe tener como minimo 8 caracteres y max 20
     */
    public static boolean isPasswordValid(String password) {
        Pattern pattern =Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isUserNameValid(String userName) {
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }
}
