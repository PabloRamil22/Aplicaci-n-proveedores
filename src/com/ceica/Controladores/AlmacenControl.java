package com.ceica.Controladores;

import com.ceica.Modelos.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlmacenControl {
    private List<Proveedores> proveedorLista;
    private List<Piezas> piezasLista;
    private List<Pedidos> pedidosLista;
    private List<Categoria> categorias;


    public AlmacenControl() {
        proveedorLista = new ArrayList<>();
        piezasLista = new ArrayList<>();
        pedidosLista = new ArrayList<>();
        categorias= new ArrayList<>();
        proveedorLista=Proveedores.getProveedores();
        categorias=Categoria.getCategoria();
        piezasLista=Piezas.getPiezas();

    }

    /**
     * @param nombre
     * @param color
     * @param precio
     * @param idcategoria
     * @return Se añade una nueva pieza a la lista de piezas.
     */
    public boolean nuevaPieza(String nombre, Color color, double precio, int idcategoria ){
        Piezas piezas= new Piezas(nombre, color.toString(), precio);
        piezas.setCategoria(getCategoriaById(idcategoria));
        piezasLista.add(piezas);
        return true;
    }

    /**
     * @param id
     * @return Incrusta el tipo de categoría mediante el id.
     */
    public Categoria getCategoriaById(int id){
        return categorias.stream().filter(c->c.getId()==id).findFirst().get();
    }


    /**
     * @param nombre
     * @return elimina una pieza de la lista de piezas.
     */
    public boolean eliminarPieza(String nombre) {
        for (int i = 0; i < piezasLista.size(); i++) {
            if (nombre.equals(piezasLista.get(i).getNombre())) {
                piezasLista.remove(i);
                return true;
            }
        }
    return false;
    }

    /**
     * @param id
     * @param precio
     * @return edita el precio de una pieza
     */
    public boolean editarPrecioPieza(int id, double precio){
        return piezasLista.stream().filter(pieza ->pieza.getId()==id).findFirst().map(pieza -> {
            pieza.setPrecio(precio);
            return true;
        }).orElse(false);
    }

    /**
     * Metodo para crear un nuevo pedido.
     * @param cif
     * @param idPieza
     * @param cantidad
     * @return Si creo bien el pedido o tinene algún error.
     */
    public String nuevoPedido(String cif, int idPieza, int cantidad){
        Pedidos pedidos= new Pedidos();
        Proveedores proveedores=buscarPorCif(cif);
        if (proveedores!=null) {
            Piezas piezas=getPiezasByID(idPieza);
            if (piezas!=null) {
                Pedidos pedidos1=new Pedidos(proveedores,piezas);
                pedidos1.setCantidad(cantidad);
                pedidos1.setFechaPedido(LocalDate.now());
                pedidosLista.add(pedidos1);
                return "Pedido creado con fecha";
            }else{
                return "Error al crear un pedido, proveedor no existe";
            }
        }else{
            return "Error al crear un pedido, proveedor no existe";
        }
    }

    /**
     * @param id
     * @return Te entrega la pieza en cuestión buscando por ID.
     */
    private Piezas getPiezasByID(int id){
        for (int i = 0; i < piezasLista.size(); i++) {
            if (piezasLista.get(i).getId()==id);{
                return piezasLista.get(i);
            }
        }
        return null;
    }

    /**
     * @param cif
     * @param nombre
     * @param direccion
     * @param localidad
     * @param provincia
     * @return Añade un nuevo proveedor.
     */
    public boolean nuevoProveedor(String cif, String nombre, String direccion, String localidad, String provincia) {
        Proveedores proveedores = new Proveedores(cif, nombre);
        proveedores.setDireccion(direccion);
        proveedores.setLocalidad(localidad);
        proveedores.setProvincia(provincia);
        return proveedorLista.add(proveedores);

    }

    /**
     * @param cif
     * @return Elimina un nuevo proveedor.
     */
    public boolean eliminarProveedor(String cif) {
        for (int i = 0; i < proveedorLista.size(); i++) {
            if (cif.equals(proveedorLista.get(i).getCif())) {
                proveedorLista.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * @param cif
     * @return Busca entre todos los proveedores mediante el cif.
     */
    public Proveedores buscarPorCif(String cif) {
        for (int i = 0; i < proveedorLista.size(); i++) {
            if (proveedorLista.get(i) != null)
            if (cif.equals(proveedorLista.get(i).getCif())) {
                return proveedorLista.get(i);
            }
        }
        return null;
    }

    /**
     * Modifica el nombre.
     * @param nuevoNombre
     * @param cif
     */
    public void editarNombre(String cif, String nuevoNombre) {
        Proveedores proveedores = buscarPorCif(cif);
        if (proveedores != null)
            proveedores.setNombre(nuevoNombre);
    }

    /**
     * Modifica la dirección.
     * @param cif
     * @param nuevaDireccion
     */
    public void editarDireccion(String cif, String nuevaDireccion) {
        Proveedores proveedores = buscarPorCif(cif);
        if (proveedores != null)
            proveedores.setDireccion(nuevaDireccion);
    }

    /**
     * Modifica la localidad.
     * @param cif
     * @param nuevaLocalidad
     */
    public void editarLocalidad(String cif, String nuevaLocalidad){
        Proveedores proveedores = buscarPorCif(cif);
        if (proveedores != null)
            proveedores.setLocalidad(nuevaLocalidad);
    }

    /**
     * Modifica la provincia.
     * @param cif
     * @param nuevaProvincia
     */
    public void editarProvincia(String cif, String nuevaProvincia){
        Proveedores proveedores = buscarPorCif(cif);
        if (proveedores != null)
            proveedores.setLocalidad(nuevaProvincia);
    }

    /**
     * @param idPieza
     * @return Te dá el pedido que has realizado mediante el id de la pieza.
     */
    public String getPedidosByPieza(int idPieza){
        List<Pedidos> pedidosByPieza=new ArrayList<>();
        for (Pedidos pedidos : pedidosLista) {
            if (pedidos.getPieza().getId()==idPieza){
                pedidosByPieza.add(pedidos);
            }
        }
        if (pedidosByPieza.size()>0) {
            return pedidosByPieza.toString();
        }else{
            return "No hay pedidos de esa pieza";
        }
    }

    /**
     * @param cif
     * @return Busca el pedido mediante el cif del proveedor.
     */
    public String getPedidosByProveedor(String cif){
        List<Proveedores> pedidosByProveedores=new ArrayList<>();
        for (Proveedores proveedores : proveedorLista){
            if (proveedores.getCif().equals(cif));
            pedidosByProveedores.add(proveedores);
        }
        return "No hay pedidos con este proveedor";
    }

    @Override
    public String toString() {
        return "AlmacenControl{" +
                "proveedorLista=" + proveedorLista +
                ", piezasLista=" + piezasLista +
                ", pedidosLista=" + pedidosLista +
                '}';
    }

    /**
     * @return Lista de proveedores.
     */
    public String verProveedores() {

        return proveedorLista.toString();
    }

    public String categoriasDisponibles() {
        return categorias.toString();
    }

    public boolean categoriaValida(int categoria) {
        for (Categoria cat: categorias){
            if (cat.getId()==categoria){
                return true;
            }
        }
        return false;
    }

    public String verPiezas() {
        return piezasLista.toString();
    }
}
