package com.ceica.Controladores;

import com.ceica.Modelos.Usuario;

public class LoginControlle {
    public static boolean login(String usuario, String contraseña){
        return Usuario.getUsuario(usuario, contraseña);
    }
}
