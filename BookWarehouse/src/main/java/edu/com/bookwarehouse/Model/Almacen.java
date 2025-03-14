package edu.com.bookwarehouse.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlmacen;

    @Column(nullable = false, length = 20)
    private String numeroAlmacen;

    @Column(nullable = false)
    private Integer espacioAlmacen;
}
