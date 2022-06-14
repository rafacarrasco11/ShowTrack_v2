package com.example.showtrack.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase estatica que se usa para comprobar la validez de las cadenas de texto de los campos del usuario mediante expresiones regulares.
 */
public class CommonUtils {
    static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?!.*\\s)(?=.*[ !\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]).{8,20}$";
    static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    static final String USERNAME_PATTERN = "^[a-zA-Z0-9._-]{3,20}$";

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

    public static String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public static Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
