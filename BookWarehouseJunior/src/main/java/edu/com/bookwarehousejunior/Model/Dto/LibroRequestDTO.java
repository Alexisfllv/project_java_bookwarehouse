package edu.com.bookwarehousejunior.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroRequestDTO {
    private String codigoLibro;
    private String titulo;
    private BigDecimal peso;
    private Integer numeroPaginas;
    private LocalDate fechaPublicacion;
    private BigDecimal precio;

    private String nombreEditorial;
    private String nombreAlmacen;
    private Integer capacidadAlmacen;
    private String nombreCategoria;

    private List<AutorDTO> autores;
}