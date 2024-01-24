package com.ceica.bbdd;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conexion {

    public static Connection conectar(){
        String url;
        String usuario;
        String contraseña;
        // Cargar la configuración desde el archivo de propiedades
        Properties propiedades = new Properties();
        try (FileInputStream entrada = new FileInputStream("config.properties")) {
            propiedades.load(entrada);
            // Obtener la configuración de la base de datos
            url = propiedades.getProperty("db.url");
            usuario = propiedades.getProperty("db.usuario");
            contraseña = propiedades.getProperty("db.contraseña");
        } catch (Exception e) {
            url = "jdbc:mysql://localhost:3306/proveedores";
            usuario = "root";
            contraseña = "1234";

        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            return conexion;
        } catch (Exception e) {
            return null;
        }

    }
}

