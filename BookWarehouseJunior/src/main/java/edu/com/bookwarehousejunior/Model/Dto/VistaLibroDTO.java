package edu.com.bookwarehousejunior.Model.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VistaLibroDTO {
    private String libro;
    private BigDecimal peso;
    private Integer numeroPaginas;
    private LocalDate fechaPublicacion;
    private BigDecimal precio;
    private String editorial;
    private String almacen;
    private String categoria;
    private String autores;

    // Constructor
    public VistaLibroDTO(String libro, BigDecimal peso, Integer numeroPaginas, LocalDate fechaPublicacion,
                         BigDecimal precio, String editorial, String almacen, String categoria, String autores) {
        this.libro = libro;
        this.peso = peso;
        this.numeroPaginas = numeroPaginas;
        this.fechaPublicacion = fechaPublicacion;
        this.precio = precio;
        this.editorial = editorial;
        this.almacen = almacen;
        this.categoria = categoria;
        this.autores = autores;
    }

    // Getters y Setters
    public String getLibro() { return libro; }
    public BigDecimal getPeso() { return peso; }
    public Integer getNumeroPaginas() { return numeroPaginas; }
    public LocalDate getFechaPublicacion() { return fechaPublicacion; }
    public BigDecimal getPrecio() { return precio; }
    public String getEditorial() { return editorial; }
    public String getAlmacen() { return almacen; }
    public String getCategoria() { return categoria; }
    public String getAutores() { return autores; }
}

