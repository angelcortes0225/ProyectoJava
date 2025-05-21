/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendaelectro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SistemaGestionInventario sistema = new SistemaGestionInventario();

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n===== MENÚ DE INVENTARIO =====");
            System.out.println("1. Agregar producto al inventario");
            System.out.println("2. Registrar venta");
            System.out.println("3. Ver informe de ventas");
            System.out.println("4. Ver factura");
            System.out.println("5. Ver alertas de stock");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    registrarVenta();
                    break;
                case 3:
                    mostrarInformeVentas();
                    break;
                case 4:
                    generarFactura();
                    break;
                case 5:
                    mostrarAlertasStock();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void agregarProducto() {
        System.out.print("Código del producto: ");
        String codigo = scanner.nextLine();

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();

        System.out.print("Cantidad en stock: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        sistema.getInventario().agregarProducto(new Producto(codigo, nombre, precio), cantidad);
        System.out.println("Producto agregado al inventario.");
    }

    private static void registrarVenta() {
        System.out.print("Código de la venta: ");
        String codigoVenta = scanner.nextLine();

        List<ElementoVenta> elementos = new ArrayList<>();
        String continuar;

        do {
            System.out.print("Código del producto: ");
            String codigo = scanner.nextLine();

            Producto producto = sistema.getInventario().obtenerProducto(codigo);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();

            elementos.add(new ElementoVenta(producto, cantidad));

            System.out.print("¿Agregar otro producto a esta venta? (s/n): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        Venta venta = new Venta(codigoVenta, new Date(), elementos);
        boolean registrada = sistema.registrarVenta(venta);
        if (registrada) {
            System.out.println("Venta registrada exitosamente.");
        } else {
            System.out.println("No se pudo registrar la venta. Verifique el stock.");
        }
    }

    private static void mostrarInformeVentas() {
        Map<String, Object> informe = sistema.generarInformeVentas();
        System.out.println("Informe de Ventas:");
        System.out.println(informe);
    }

    private static void generarFactura() {
        System.out.print("Ingrese el código de la venta: ");
        String codigo = scanner.nextLine();

        Map<String, Object> factura = sistema.generarFactura(codigo);
        if (factura != null) {
            System.out.println("Factura:");
            System.out.println(factura);
        } else {
            System.out.println("Venta no encontrada.");
        }
    }

    private static void mostrarAlertasStock() {
        List<Map<String, Object>> alertas = sistema.obtenerAlertasStock();
        System.out.println("Productos con stock bajo:");
        for (Map<String, Object> alerta : alertas) {
            System.out.println(alerta);
        }
    }
}
