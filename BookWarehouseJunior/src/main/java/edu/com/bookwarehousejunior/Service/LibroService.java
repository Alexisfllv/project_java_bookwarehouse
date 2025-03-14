package edu.com.bookwarehousejunior.Service;

import edu.com.bookwarehousejunior.Model.*;
import edu.com.bookwarehousejunior.Model.Dto.LibroDTO;
import edu.com.bookwarehousejunior.Model.Dto.LibroRequestDTO;
import edu.com.bookwarehousejunior.Repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.com.bookwarehousejunior.Model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepo;
    private final EditorialRepository editorialRepo;
    private final AlmacenRepository almacenRepo;
    private final CategoriaRepository categoriaRepo;
    private final AutorRepository autorRepo;

    @Autowired
    public LibroService(LibroRepository libroRepo, EditorialRepository editorialRepo, AlmacenRepository almacenRepo, CategoriaRepository categoriaRepo, AutorRepository autorRepo) {
        this.libroRepo = libroRepo;
        this.editorialRepo = editorialRepo;
        this.almacenRepo = almacenRepo;
        this.categoriaRepo = categoriaRepo;
        this.autorRepo = autorRepo;
    }

    @Transactional
    public void registrarLibro(Libro libro) {
        // Guardar Editorial
        Editorial editorial = libro.getEditorial();
        editorialRepo.save(editorial);

        // Guardar Almacén
        Almacen almacen = libro.getAlmacen();
        almacenRepo.save(almacen);

        // Guardar Categoría
        Categoria categoria = libro.getCategoria();
        categoriaRepo.save(categoria);

        // Guardar Autores y actualizar lista en el libro
        List<Autor> autores = libro.getAutores();
        for (Autor autor : autores) {
            autorRepo.save(autor);
        }

        // Guardar Libro con las relaciones
        libroRepo.save(libro);
    }
}