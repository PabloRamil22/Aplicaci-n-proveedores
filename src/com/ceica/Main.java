package com.ceica;

import com.ceica.Controladores.AlmacenControl;
import com.ceica.Controladores.LoginControlle;
import com.ceica.Modelos.Categoria;
import com.ceica.Modelos.Color;
import com.ceica.Modelos.Proveedores;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String usuario;
        String contraseña;
        Scanner leer = new Scanner(System.in);
        AlmacenControl almacen = new AlmacenControl();
        System.out.println("Bienvenido a AppAlmacen");
        System.out.println("... Enter para empezar");
        leer.nextLine();
        do {
            System.out.println("Login AppAlmacen");
            System.out.print("Introduce usuario: ");
            usuario = leer.next();
            System.out.print("Introduce la contraseña: ");
            contraseña = leer.next();
            if (LoginControlle.login(usuario, contraseña)) {
                System.out.println("Estoy en la AppAlmacen");
                leer.nextLine();
            } else {
                System.out.println("Usuario o contraseña incorrecta");
            }
            menuPrincipalAlmacen(leer, almacen);

            break;
        } while (true);

    }

    private static void menuPrincipalAlmacen(Scanner leer, AlmacenControl almacen) {
        String op = "";
        String menuPrincipal = """
                1. Proveedores
                2. Piezas
                3. Pedidos
                4. Salir
                """;
        do {
            System.out.println(menuPrincipal);
            op = leer.nextLine();
            switch (op) {
                case "1":
                    subMenuProveedores(leer, almacen);
                    break;

                case "2":
                    subMenuPiezas(leer, almacen);
                    break;

                case "3":

                case "4":
                    System.out.println("Saliendo de la aplicación");
                default:
                    System.out.println("opción no valida");
            }

        } while (!"4".equals(op));

    }

    private static void subMenuPiezas(Scanner leer, AlmacenControl almacen) {
        String id, nombre, color;
        Double precio;
        Categoria categoria;
        String op;
        String menuPiezas = """
                1. Nueva pieza
                2. Editar precio piezas
                3. Borrar pieza
                4. Ver piezas
                5. Volver al menu principal 
                """;
        do {
            System.out.println(menuPiezas);
            op = leer.nextLine();
            switch (op) {
                case "1":
                    nuevaPieza(leer, almacen);
                    break;
                case "2":
                    break;
                case "3":
                    //System.out.println(almacen.eliminarPieza(nombre));
                    break;
                case "4":
                    System.out.println(almacen.verPiezas());
                    break;
                case "5":
                    System.out.println("Volver al menú principal");
                    break;
            }
        } while (!"5".equals(op));

    }

    private static void nuevaPieza(Scanner leer, AlmacenControl almacen) {
        String nombre, colorPieza;
        double precio = 0;
        Color color = null;
        boolean colorValido = false, categoriaValida = false, precioValido = false;
        int categoria;
        System.out.print("Nombre de la pieza: ");
        nombre = leer.nextLine();
        do {
            System.out.print("Precio: ");
            try {
                precio = leer.nextDouble();
                leer.nextLine();
                precioValido = true;
            } catch (Exception e) {
                leer.nextLine();
                System.out.println("Formato de precio no válido");
                precioValido = false;
            }
        } while (!precioValido);
        do {
            System.out.println("Color de la pieza (Colores disponibles)");
            System.out.println(Arrays.stream(Color.values()).toList());
            colorPieza = leer.nextLine();
            try {
                color = Color.valueOf(colorPieza.toUpperCase());
                colorValido = true;
            } catch (Exception e) {
                colorValido = false;
            }
        } while (!colorValido);
        do {
            System.out.println(almacen.categoriasDisponibles());
            categoria = leer.nextInt();
            leer.nextLine();
            if (almacen.categoriaValida(categoria)) {
                categoriaValida = true;
            } else {
                System.out.println("Categoría no válida");
            }
        } while (!categoriaValida);
        almacen.nuevaPieza(nombre, color, precio, categoria);

    }

    private static void subMenuProveedores(Scanner leer, AlmacenControl almacen) {
        String cif;
        String nombre, direccion, localidad, provincia;
        String eliminarProveedor;
        String op;
        String menuProveedores = """
                1. Nuevo proveedor
                2. Editar proveedor
                3. Buscar proveedores
                4. Eliminar proveedor
                5. Volver al menu principal
                """;
        do {
            System.out.println(menuProveedores);
            op = leer.nextLine();
            switch (op) {
                case "1":
                    System.out.println("CIF: ");
                    cif = leer.nextLine();
                    System.out.println("Nombre: ");
                    nombre = leer.nextLine();
                    System.out.println("Dirección: ");
                    direccion = leer.nextLine();
                    System.out.println("Localidad: ");
                    localidad = leer.nextLine();
                    System.out.println("Provincia: ");
                    provincia = leer.nextLine();
                    almacen.nuevoProveedor(cif, nombre, direccion, localidad, provincia);
                    break;

                /*case "2":
                    System.out.println("Introduzca el cif del proveedor: ");
                    cif = leer.nextLine();
                    if (almacen.buscarPorCif(cif)) {
                        editarDatos(leer, almacen);

                    } else {
                        System.out.println("Este proveedor no existe");
                    }
                    break;

                 */

                case "3":
                    System.out.println(almacen.verProveedores());
                    break;
                case "4":
                    System.out.println("Introduce el cif del proveedor: ");
                    cif = leer.nextLine();
                    System.out.println("El proveedor que quieres eliminar es: " + almacen.buscarPorCif(cif));
                    eliminarProveedor = leer.nextLine();
                    almacen.eliminarProveedor(cif);
                    break;

                case "5":
                    System.out.println("Volviendo al menú principal");
                    break;

                default:
                    System.out.println("opción no valida");


            }
        } while (!"5".equals(op));
    }

    private static void editarDatos(Scanner leer, AlmacenControl almacen) {
        String nuevoNombre, nuevaDireccion, nuevaLocalidad, nuevaProvincia;
        String cif;
        String op;
        String menuEdicon = """
                1. Editar nombre
                2. Editar dirección
                3. Editar Localidad
                4. Editar provincia
                5. Salir
                """;
        do {
            System.out.println(menuEdicon);
            op = leer.nextLine();

            switch (op) {
                case "1":
                    System.out.println("Introduce el cif del proveedor: ");
                    cif = leer.nextLine();
                    System.out.println("El proveedor que vas a editar es: " + almacen.buscarPorCif(cif));
                    System.out.println("Nuevo nombre: ");
                    nuevoNombre = leer.nextLine();
                    almacen.editarNombre(cif, nuevoNombre);
                    break;

                case "2":
                    System.out.println("Introduce el cif del proveedor: ");
                    cif = leer.nextLine();
                    System.out.println("El proveedor que vas a editar es: " + almacen.buscarPorCif(cif));
                    System.out.println("Nueva Dirección: ");
                    nuevaDireccion = leer.nextLine();
                    almacen.editarDireccion(cif, nuevaDireccion);
                    break;


                case "3":
                    System.out.println("Introduce el cif del proveedor: ");
                    cif = leer.nextLine();
                    System.out.println("El proveedor que vas a editar es: " + almacen.buscarPorCif(cif));
                    System.out.println("Nueva localidad: ");
                    nuevaLocalidad = leer.nextLine();
                    almacen.editarLocalidad(cif, nuevaLocalidad);
                    break;

                case "4":
                    System.out.println("Introduce el cif del proveedor: ");
                    cif = leer.nextLine();
                    System.out.println("El proveedor que vas a editar es: " + almacen.buscarPorCif(cif));
                    System.out.println("Nueva provincia: ");
                    nuevaProvincia = leer.nextLine();
                    almacen.editarProvincia(cif, nuevaProvincia);
                    break;

                case "5":
                    System.out.println("Saliendo del menú edición");
                    break;
                default:
                    System.out.println("Opción no valida");

            }
        } while (!"5".equals(op));
    }
}