/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendaelectro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

class Venta {
    private String codigo;
    private LocalDateTime fecha;
    private List<ElementoVenta> elementos;
    
    public Venta(String codigoVenta, Date date, List<ElementoVenta> elementos1) {
        this.codigo = UUID.randomUUID().toString();
        this.fecha = LocalDateTime.now();
        this.elementos = new ArrayList<>();
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    public void agregarElemento(ElementoVenta elemento) {
        elementos.add(elemento);
    }
    
    public List<ElementoVenta> getElementos() {
        return elementos;
    }
    
    public double getTotal() {
        double total = 0;
        for (ElementoVenta elemento : elementos) {
            total += elemento.getSubtotal();
        }
        return total;
    }
    
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("codigo", codigo);
        map.put("fecha", fecha.toString());
        
        List<Map<String, Object>> elementosMap = new ArrayList<>();
        for (ElementoVenta elemento : elementos) {
            elementosMap.add(elemento.toMap());
        }
        
        map.put("elementos", elementosMap);
        map.put("total", getTotal());
        return map;
    }
}
