package edu.com.bookwarehousejunior.Service;


import edu.com.bookwarehousejunior.Model.*;
import edu.com.bookwarehousejunior.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibroServiceLibro {

    private final LibroRepository libroRepo;
    private final EditorialRepository editorialRepo;
    private final AlmacenRepository almacenRepo;
    private final CategoriaRepository categoriaRepo;
    private final AutorRepository autorRepo;

    @Autowired
    public LibroServiceLibro(LibroRepository libroRepo, EditorialRepository editorialRepo, AlmacenRepository almacenRepo, CategoriaRepository categoriaRepo, AutorRepository autorRepo) {
        this.libroRepo = libroRepo;
        this.editorialRepo = editorialRepo;
        this.almacenRepo = almacenRepo;
        this.categoriaRepo = categoriaRepo;
        this.autorRepo = autorRepo;
    }

    @Transactional
    public void registrarLibroConDatosExistentes(Libro libro, Integer idEditorial, Integer idAlmacen, Integer idCategoria, List<Integer> idAutores) {
        // Buscar Editorial existente
        Editorial editorial = editorialRepo.findById(idEditorial)
                .orElseThrow(() -> new IllegalArgumentException("Editorial no encontrada"));

        // Buscar Almacén existente
        Almacen almacen = almacenRepo.findById(idAlmacen)
                .orElseThrow(() -> new IllegalArgumentException("Almacén no encontrado"));

        // Buscar Categoría existente
        Categoria categoria = categoriaRepo.findById(idCategoria)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

        // Buscar Autores existentes
        List<Autor> autores = autorRepo.findAllById(idAutores);
        if (autores.isEmpty()) {
            throw new IllegalArgumentException("Ningún autor encontrado con los IDs proporcionados");
        }

        // Asignar relaciones al Libro
        libro.setEditorial(editorial);
        libro.setAlmacen(almacen);
        libro.setCategoria(categoria);
        libro.setAutores(autores);

        // Guardar solo el libro con las referencias existentes
        libroRepo.save(libro);
    }
}
