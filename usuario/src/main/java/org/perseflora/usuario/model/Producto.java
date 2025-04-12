package org.perseflora.usuario.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(length = 50,nullable = false,unique = true)
    private String nombreProducto;

    @Column(length = 50,nullable = false,unique = true)
    private String nombreCientifico;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false )
    private Double precio;

    @Column(length = 70, nullable = false )
    private String categoria;

    //Constructor vacio
    public Producto(){

    }

    public Producto(Long idProducto, String nombreProducto, String nombreCientifico, int cantidad, Double precio, String categoria) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.nombreCientifico = nombreCientifico;
        this.cantidad = cantidad;
        this.precio = precio;
        this.categoria = categoria;
    }

    //Getters and Setters

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }
    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", nombreCientifico='" + nombreCientifico + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Producto producto)) return false;
        return cantidad == producto.cantidad && Objects.equals(idProducto, producto.idProducto) && Objects.equals(nombreProducto, producto.nombreProducto) && Objects.equals(nombreCientifico, producto.nombreCientifico) && Objects.equals(precio, producto.precio) && Objects.equals(categoria, producto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, nombreProducto, nombreCientifico, cantidad, precio, categoria);
    }
}
