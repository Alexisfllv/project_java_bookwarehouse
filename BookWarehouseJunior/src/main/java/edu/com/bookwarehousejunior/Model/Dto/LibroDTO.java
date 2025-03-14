package edu.com.bookwarehousejunior.Model.Dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class LibroDTO {
    private String codigoLibro;
    private String titulo;
    private BigDecimal peso;
    private int numeroPaginas;
    private LocalDate fechaPublicacion;
    private BigDecimal precio;
    private int idEditorial;
    private int idAlmacen;
    private int idCategoria;
    private List<Integer> idAutores;

    // Getters y Setters
}
