package com.ceica.Modelos;

import com.ceica.bbdd.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Proveedores {
    private int id;
    private String cif;
    private String nombre;
    private String direccion;
    private String localidad;
    private String provincia;

    public Proveedores() {
    }

    public Proveedores(String cif, String nombre) {
        this.cif = cif;
        this.nombre = nombre;
    }
    public static boolean insertar(Proveedores proveedor) {
        Connection conn=Conexion.conectar();
        String sql="insert into proveedores (nombre,direccion,localidad,provincia,cif)" +
                " values (?,?,?,?,?)";
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,proveedor.getNombre());
            pst.setString(2, proveedor.getDireccion());
            pst.setString(3,proveedor.getLocalidad());
            pst.setString(4,proveedor.getProvincia());
            pst.setString(5,proveedor.getCif());
            if(pst.executeUpdate()<0){
                return false;
            }else{
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean eliminarProveedor(String cif) {
        Connection conn=Conexion.conectar();
        String sql="delete from proveedores where cif=?";
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,cif);
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

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public static boolean editarNombreProveedor(String cif, String nombre){
        Connection conn=Conexion.conectar();
        String sql="update proveedores set nombre = ? where cif = ?";
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,nombre);
            pst.setString(2,cif);
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
    public static List<Proveedores>getProveedores(){
        List<Proveedores> proveedoresLista=new ArrayList<>();
        Connection conn= Conexion.conectar();
        String sql="select * from proveedor";
        try {
            Statement stm=conn.createStatement();
            ResultSet respuesta=stm.executeQuery(sql);
            while (respuesta.next()){
                Proveedores proveedores=new Proveedores();
                proveedores.setId(respuesta.getInt("idProveedor"));
                proveedores.setNombre(respuesta.getString("nombre"));
                proveedores.setDireccion(respuesta.getString("direccion"));
                proveedores.setLocalidad(respuesta.getString("localidad"));
                proveedores.setProvincia(respuesta.getString("provincia"));
                proveedoresLista.add(proveedores);
            }


        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return proveedoresLista;
        }
        return proveedoresLista;
    }

    @Override
    public String toString() {
        return "Proveedores{" +"\n"+
                "id=" + id +"\n"+
                ", cif='" + cif + '\'' +"\n"+
                ", nombre='" + nombre + '\'' +"\n"+
                ", direccion='" + direccion + '\'' +"\n"+
                ", localidad='" + localidad + '\'' +"\n"+
                ", provincia='" + provincia + '\'' +"\n"+
                '}';
    }
}
