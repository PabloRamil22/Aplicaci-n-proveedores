package com.ceica;

import com.ceica.Controladores.AlmacenControl;

public class Main {
    public static void main(String[] args) {
        AlmacenControl almacen=new AlmacenControl();
        almacen.nuevoProveedor("A", "Paco", "Plaza de la republica", "Redondela", "Pontevedra");
        System.out.println(almacen.toString());

    }
}