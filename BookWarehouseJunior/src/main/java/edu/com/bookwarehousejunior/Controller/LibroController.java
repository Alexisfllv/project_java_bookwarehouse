package edu.com.bookwarehousejunior.Controller;


import edu.com.bookwarehousejunior.Model.*;
import edu.com.bookwarehousejunior.Model.Dto.LibroDTO;
import edu.com.bookwarehousejunior.Model.Dto.LibroDTODatos;
import edu.com.bookwarehousejunior.Model.Dto.LibroRequestDTO;
import edu.com.bookwarehousejunior.Service.LibroService;
import edu.com.bookwarehousejunior.Service.LibroServiceLibro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/libros")

@RequiredArgsConstructor
public class LibroController {

    private final LibroService libroService;

    private final LibroServiceLibro libroServiceLibro;

//    @Autowired
//    public LibroController(LibroService libroService) {
//        this.libroService = libroService;
//    }

    @PostMapping("/registro")
    public ResponseEntity<String> registrarLibro(@RequestBody LibroRequestDTO libroDTO) {
        // Convertir DTO en Entidades
        Editorial editorial = new Editorial(null, libroDTO.getNombreEditorial());
        Almacen almacen = new Almacen(null, libroDTO.getNombreAlmacen(), libroDTO.getCapacidadAlmacen());
        Categoria categoria = new Categoria(null, libroDTO.getNombreCategoria());

        List<Autor> autores = libroDTO.getAutores().stream()
                .map(a -> new Autor(null, a.getNombreAutor(), a.getApellidoAutor()))
                .collect(Collectors.toList());

        // Crear objeto Libro
        Libro libro = new Libro(
                null, libroDTO.getCodigoLibro(), libroDTO.getTitulo(), libroDTO.getPeso(),
                libroDTO.getNumeroPaginas(), libroDTO.getFechaPublicacion(), libroDTO.getPrecio(),
                editorial, almacen, categoria, autores
        );

        // Guardar en Base de Datos
        libroService.registrarLibro(libro);

        return ResponseEntity.ok("Libro registrado correctamente");
    }

    // libro 2
    @PostMapping("/registro2")
    public ResponseEntity<String> registrarLibro2(@RequestBody LibroDTODatos libroRequest) {
        try {
            Libro libro = new Libro();
            libro.setCodigoLibro(libroRequest.getCodigoLibro());
            libro.setTitulo(libroRequest.getTitulo());
            libro.setPeso(libroRequest.getPeso());
            libro.setNumeroPaginas(libroRequest.getNumeroPaginas());
            libro.setFechaPublicacion(libroRequest.getFechaPublicacion());
            libro.setPrecio(libroRequest.getPrecio());

            // Llamar al servicio con los IDs de las relaciones
            libroServiceLibro.registrarLibroConDatosExistentes(
                    libro,
                    libroRequest.getIdEditorial(),
                    libroRequest.getIdAlmacen(),
                    libroRequest.getIdCategoria(),
                    libroRequest.getIdAutores()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body("Libro registrado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}