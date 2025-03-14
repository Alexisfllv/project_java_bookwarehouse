package edu.com.bookwarehousejunior.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "almacenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlmacen;

    @Column(name = "nombre_almacen", nullable = false, length = 40)
    private String nombreAlmacen;

    @Column(nullable = false)
    private Integer capacidad;
}
