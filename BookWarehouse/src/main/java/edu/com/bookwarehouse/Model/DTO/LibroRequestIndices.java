package edu.com.bookwarehouse.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroRequestIndices {

    private String nombreLibro;
    private int cantidadLibro;
    private int idAlmacen;  // Enviar solo el ID
    private int idAutor;    // Enviar solo el ID

}
