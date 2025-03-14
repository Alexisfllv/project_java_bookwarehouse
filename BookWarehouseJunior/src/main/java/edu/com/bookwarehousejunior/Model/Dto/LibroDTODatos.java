package edu.com.bookwarehousejunior.Model.Dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class LibroDTODatos {
    private String codigoLibro;
    private String titulo;
    private BigDecimal peso;
    private Integer numeroPaginas;
    private LocalDate fechaPublicacion;
    private BigDecimal precio;

    private Integer idEditorial;
    private Integer idAlmacen;
    private Integer idCategoria;
    private List<Integer> idAutores;
}
