package edu.com.bookwarehouse.Controller;


import edu.com.bookwarehouse.Model.DTO.LibroRequest;
import edu.com.bookwarehouse.Model.DTO.LibroRequestIndices;
import edu.com.bookwarehouse.Model.Libro;
import edu.com.bookwarehouse.Service.LibroService;
import edu.com.bookwarehouse.Service.LibroServiceIndices;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libros")

@RequiredArgsConstructor
public class LibroController {

    //ioc
    private final LibroService libroService;

    private final LibroServiceIndices libroServiceIndices;

    //const



    //  metodo post para ingresar datos
    @PostMapping("/crear")
    public ResponseEntity<String> crearLibro(@RequestBody LibroRequest request) {
        libroService.crearLibroConTransaccion(
                request.getNombreEditorial(),
                request.getNombreAutor(),
                request.getApellidoAutor(),
                request.getNumeroAlmacen(),
                request.getEspacioAlmacen(),
                request.getNombreLibro(),
                request.getCantidadLibro()
        );

        return ResponseEntity.ok("Libro creado correctamente con transacción");
    }

    // metodo con indices

    @PostMapping("/crear2")
    public ResponseEntity<String> crearlibro2(@RequestBody LibroRequestIndices libroRequestIndices) {
        libroServiceIndices.crearLibro(libroRequestIndices.getNombreLibro()
        , libroRequestIndices.getCantidadLibro()
                ,libroRequestIndices.getIdAlmacen()
                , libroRequestIndices.getIdAutor());

        return ResponseEntity.ok("Libro creado correctamente con ID");
    }
}
