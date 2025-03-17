package edu.com.bookwarehousejunior.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VistaLibroDTO2 {
    private Integer idLibro;
    private String libro;
    private BigDecimal peso;
    private Integer numeroPaginas;
    private LocalDate fechaPublicacion;
    private BigDecimal precio;
    private String editorial;
    private String almacen;
    private String categoria;
    private List<String> autores; // Ahora es una lista, no un String
}
