package com.ceica.Controladores;

import com.ceica.Modelos.Pedidos;
import com.ceica.Modelos.Piezas;
import com.ceica.Modelos.Proveedores;

import java.util.ArrayList;
import java.util.List;

public class AlmacenControl {
    private List<Proveedores> proveedorLista;
    private List<Piezas> piezasLista;
    private  List<Pedidos> pedidosLista;

    public AlmacenControl() {
        proveedorLista=new ArrayList<>();
        piezasLista=new ArrayList<>();
        pedidosLista=new ArrayList<>();
    }
    public boolean nuevoProveedor(String cif, String nombre, String direccion, String localidad, String provincia){
        Proveedores proveedores=new Proveedores(cif,nombre);
        proveedores.setDireccion(direccion);
        proveedores.setLocalidad(localidad);
        proveedores.setProvincia(provincia);
        return proveedorLista.add(proveedores);

    }

    @Override
    public String toString() {
        return "AlmacenControl{" +
                "proveedorLista=" + proveedorLista +"\n"+
                ", piezasLista=" + piezasLista +"\n"+
                ", pedidosLista=" + pedidosLista +"\n"+
                '}';
    }
}
