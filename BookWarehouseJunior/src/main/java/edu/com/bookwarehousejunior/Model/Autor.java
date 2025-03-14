package edu.com.bookwarehousejunior.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "autores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAutor;

    @Column(name = "nombre_autor", nullable = false, length = 40)
    private String nombreAutor;

    @Column(name = "apellido_autor", nullable = false, length = 40)
    private String apellidoAutor;
}