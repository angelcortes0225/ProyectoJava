/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendaelectro;

import java.util.HashMap;
import java.util.Map;

class ElementoVenta {
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    
    public ElementoVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    
    public Producto getProducto() {
        return producto;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public double getPrecioUnitario() {
        return precioUnitario;
    }
    
    public double getSubtotal() {
        return cantidad * precioUnitario;
    }
    
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("codigoProducto", producto.getCodigo());
        map.put("nombreProducto", producto.getNombre());
        map.put("cantidad", cantidad);
        map.put("precioUnitario", precioUnitario);
        map.put("subtotal", getSubtotal());
        return map;
    }
}