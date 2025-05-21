/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tiendaelectro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Enumeración de categorías de productos electrónicos
 */
enum CategoriaProducto {
    SMARTPHONE("Smartphone"),
    LAPTOP("Laptop"),
    TABLET("Tablet"),
    TELEVISOR("Televisor"),
    ACCESORIO("Accesorio"),
    COMPONENTE("Componente"),
    OTRO("Otro");
    
    private final String valor;
    
    CategoriaProducto(String valor) {
        this.valor = valor;
    }
    
    public String getValor() {
        return valor;
    }
}

/**
 * Clase para representar un producto electrónico
 */
class Producto {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private CategoriaProducto categoria;
    private int umbralAlerta;
    
    public Producto(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.umbralAlerta = umbralAlerta;
    }
    
    // Constructor con umbral por defecto
    public Producto(String codigo, String nombre, String descripcion, 
                   double precio, CategoriaProducto categoria) {
        this(codigo, nombre, precio);
    }
    
    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public CategoriaProducto getCategoria() {
        return categoria;
    }
    
    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }
    
    public int getUmbralAlerta() {
        return umbralAlerta;
    }
    
    public void setUmbralAlerta(int umbralAlerta) {
        this.umbralAlerta = umbralAlerta;
    }
    
    @Override
    public String toString() {
        return nombre + " - " + precio + "€ (Código: " + codigo + ")";
    }
    
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("codigo", codigo);
        map.put("nombre", nombre);
        map.put("descripcion", descripcion);
        map.put("precio", precio);
        map.put("categoria", categoria.getValor());
        map.put("umbralAlerta", umbralAlerta);
        return map;
    }
}

