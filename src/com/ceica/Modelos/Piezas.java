package com.ceica.Modelos;

import com.ceica.bbdd.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Piezas {
    private static int idPieza=0;
    private int id;
    private String nombre;
    private String color;
    private Double precio;
    private Categoria categoria;

    public Piezas(String nombre, String color, Double precio) {
        this.id=idPieza++;
        this.nombre = nombre;
        this.color = color;
        this.precio = precio;
        this.categoria= categoria;
    }

    public Piezas() {

    }
    public static boolean insertar(Piezas piezas) {
        Connection conn=Conexion.conectar();
        String sql="insert into piezas (nombre)" + " values (?)";
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, piezas.getNombre());
            if(pst.executeUpdate()<0){
                return false;
            }else{
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean eliminarPiezas(int idPieza) {
        Connection conn=Conexion.conectar();
        String sql="delete from piezas where idpiezas=?";
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, String.valueOf(idPieza));
            if(pst.executeUpdate()>0){
                conn.close();
                return true;
            }else{
                conn.close();
                return false;
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return false;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public static List<Piezas> getPiezas(){
        List<Piezas> piezasLista=new ArrayList<>();
        Connection conn= Conexion.conectar();
        String sql="SELECT P.idpiezas,P.nombre,P.color,P.precio,C.idcategoria,C.nombre as nombre_categoria " +
                "FROM proveedores.piezas as P inner join categoria as C on P.categoria=C.idcategoria;";
        try {
            Statement stm=conn.createStatement();
            ResultSet respuesta=stm.executeQuery(sql);
            while(respuesta.next()){
                Categoria categoria=new Categoria();
                Piezas piezas=new Piezas();
                piezas.setId(respuesta.getInt("idpiezas"));
                categoria.setId(respuesta.getInt("idcategoria"));
                categoria.setNombre(respuesta.getString("nombre_categoria"));
                piezas.setNombre(respuesta.getString("nombre"));
                piezas.setColor(respuesta.getString("color"));
                piezas.setPrecio(respuesta.getDouble("precio"));
                piezas.setCategoria(categoria);
                piezasLista.add(piezas);

            }
        }catch (SQLException e){
            return piezasLista;
        }
        return piezasLista;
    }

    @Override
    public String toString() {
        return "Piezas{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                ", precio=" + precio +"\n"+
                ", categoria=" + categoria +
                '}';
    }
}
