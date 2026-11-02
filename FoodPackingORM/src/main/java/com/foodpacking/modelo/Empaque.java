package com.foodpacking.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "empaques")
public class Empaque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;

    @Column(name = "tipo_material")
    private String tipoMaterial; // Ej: Cartón biodegradable, Plástico reciclable

    @Column(name = "stock")
    private int stock;

    // Constructores
    public Empaque() {}

    public Empaque(String nombreProducto, String tipoMaterial, int stock) {
        this.nombreProducto = nombreProducto;
        this.tipoMaterial = tipoMaterial;
        this.stock = stock;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public String getTipoMaterial() { return tipoMaterial; }
    public void setTipoMaterial(String tipoMaterial) { this.tipoMaterial = tipoMaterial; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "Empaque [ID=" + id + ", Producto=" + nombreProducto + ", Material=" + tipoMaterial + ", Stock=" + stock + "]";
    }
}
