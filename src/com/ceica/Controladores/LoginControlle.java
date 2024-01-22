package com.ceica.Controladores;

public class LoginControlle {
    public static boolean login(String usuario, String contraseña){
        if ("admin".equals(usuario) & "1234".equals(contraseña)) {
            return true;
        }else{
            return false;
        }
    }
}
