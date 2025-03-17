package edu.com.bookwarehousejunior.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroGlobalDto {
    private String libro;
    private BigDecimal peso;
    private Integer numeroPaginas;
    private LocalDate fechaPublicacion;
    private BigDecimal precio;
    private String editorial;
    private String almacen;
    private String categoria;
    private String autores;

    public Double getPeso() {
        return peso != null ? peso.doubleValue() : null;
    }

    public Double getPrecio() {
        return precio != null ? precio.doubleValue() : null;
    }
}

