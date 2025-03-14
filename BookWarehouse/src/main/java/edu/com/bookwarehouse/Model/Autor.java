package edu.com.bookwarehouse.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAutor;

    @Column(nullable = false, length = 40)
    private String nombreAutor;

    @Column(nullable = false, length = 40)
    private String apellidoAutor;

    @ManyToOne
    @JoinColumn(name = "id_editorial")
    private Editorial editorial;
}