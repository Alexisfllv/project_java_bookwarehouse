package edu.com.bookwarehouse.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroRequest {
    private String nombreEditorial;
    private String nombreAutor;
    private String apellidoAutor;
    private String numeroAlmacen;
    private int espacioAlmacen;
    private String nombreLibro;
    private int cantidadLibro;
}

