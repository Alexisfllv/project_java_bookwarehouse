package edu.com.bookwarehouse.Service;

import edu.com.bookwarehouse.Model.Almacen;
import edu.com.bookwarehouse.Model.Autor;
import edu.com.bookwarehouse.Model.Libro;
import edu.com.bookwarehouse.Repo.AlmacenRepo;
import edu.com.bookwarehouse.Repo.AutorRepo;
import edu.com.bookwarehouse.Repo.LibroRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LibroServiceIndices {

    private final LibroRepo libroRepository;
    private final AlmacenRepo almacenRepository;
    private final AutorRepo autorRepository;


    // transaccion
    @Transactional
    public void crearLibro(String nombreLibro, int cantidadLibro, int idAlmacen, int idAutor) {

        // Buscar entidades existentes
        Almacen almacen = almacenRepository.findById(idAlmacen)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));
        Autor autor = autorRepository.findById(idAutor)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        // libro y relaciones

        Libro libro = new Libro();
        libro.setNombreLibro(nombreLibro);
        libro.setCantidadLibro(cantidadLibro);
        libro.setAlmacen(almacen);
        libro.setAutor(autor);

        //Guardar en la BD
        libroRepository.save(libro);
    }
}
