/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendaelectro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
class Inventario {
    // Mapa para almacenar productos y su cantidad: {codigo_producto: [producto, cantidad]}
    private Map<String, Object[]> productos;
    
    public Inventario() {
        productos = new HashMap<>();
    }
    
    /**
     * Añade un nuevo producto al inventario o actualiza su cantidad si ya existe
     */
    public boolean agregarProducto(Producto producto, int cantidad) {
        if (productos.containsKey(producto.getCodigo())) {
            Object[] actual = productos.get(producto.getCodigo());
            int cantidadActual = (int) actual[1];
            productos.put(producto.getCodigo(), new Object[]{producto, cantidadActual + cantidad});
        } else {
            productos.put(producto.getCodigo(), new Object[]{producto, cantidad});
        }
        return true;
    }
    
    /**
     * Actualiza directamente la cantidad de un producto
     */
    public boolean actualizarCantidad(String codigoProducto, int nuevaCantidad) {
        if (productos.containsKey(codigoProducto)) {
            Object[] actual = productos.get(codigoProducto);
            productos.put(codigoProducto, new Object[]{actual[0], nuevaCantidad});
            return true;
        }
        return false;
    }
    
    /**
     * Reduce el stock de un producto (usado en ventas)
     */
    public boolean reducirStock(String codigoProducto, int cantidad) {
        if (productos.containsKey(codigoProducto)) {
            Object[] actual = productos.get(codigoProducto);
            int cantidadActual = (int) actual[1];
            
            if (cantidadActual >= cantidad) {
                productos.put(codigoProducto, new Object[]{actual[0], cantidadActual - cantidad});
                return true;
            }
        }
        return false;
    }
    
    /**
     * Devuelve la cantidad disponible de un producto
     */
    public int obtenerCantidad(String codigoProducto) {
        if (productos.containsKey(codigoProducto)) {
            Object[] actual = productos.get(codigoProducto);
            return (int) actual[1];
        }
        return 0;
    }
    
    /**
     * Devuelve un producto por su código
     */
    public Producto obtenerProducto(String codigoProducto) {
        if (productos.containsKey(codigoProducto)) {
            Object[] actual = productos.get(codigoProducto);
            return (Producto) actual[0];
        }
        return null;
    }
    
    /**
     * Lista todos los productos con sus cantidades
     */
    public List<Map<String, Object>> listarProductos() {
        List<Map<String, Object>> resultado = new ArrayList<>();
        
        for (Map.Entry<String, Object[]> entry : productos.entrySet()) {
            Object[] valor = entry.getValue();
            Producto producto = (Producto) valor[0];
            int cantidad = (int) valor[1];
            
            Map<String, Object> info = producto.toMap();
            info.put("cantidad", cantidad);
            resultado.add(info);
        }
        
        return resultado;
    }
    
    /**
     * Lista productos con stock por debajo del umbral de alerta
     */
    public List<Map<String, Object>> productosBajoUmbral() {
        List<Map<String, Object>> alertas = new ArrayList<>();
        
        for (Map.Entry<String, Object[]> entry : productos.entrySet()) {
            Object[] valor = entry.getValue();
            Producto producto = (Producto) valor[0];
            int cantidad = (int) valor[1];
            
            if (cantidad <= producto.getUmbralAlerta()) {
                Map<String, Object> alerta = new HashMap<>();
                alerta.put("producto", producto.toMap());
                alerta.put("cantidadActual", cantidad);
                alerta.put("umbral", producto.getUmbralAlerta());
                alertas.add(alerta);
            }
        }
        
        return alertas;
    }
}