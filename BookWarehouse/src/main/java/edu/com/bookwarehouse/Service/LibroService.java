package edu.com.bookwarehouse.Service;

import edu.com.bookwarehouse.Model.Almacen;
import edu.com.bookwarehouse.Model.Autor;
import edu.com.bookwarehouse.Model.Editorial;
import edu.com.bookwarehouse.Model.Libro;
import edu.com.bookwarehouse.Repo.AlmacenRepo;
import edu.com.bookwarehouse.Repo.AutorRepo;
import edu.com.bookwarehouse.Repo.EditorialRepo;
import edu.com.bookwarehouse.Repo.LibroRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroService {


    // ioc
    private final EditorialRepo editorialRepo;
    private final AutorRepo autorRepo;
    private final AlmacenRepo almacenRepo;
    private final LibroRepo libroRepo;

    // constructor para la ioc
    @Autowired
    public LibroService(EditorialRepo editorialRepo, AlmacenRepo almacenRepo,AutorRepo autorRepo, LibroRepo libroRepo) {
        this.editorialRepo = editorialRepo;
        this.autorRepo = autorRepo;
        this.almacenRepo = almacenRepo;
        this.libroRepo = libroRepo;
    }

    @Transactional
    public void crearLibroConTransaccion
            (String nombreEditorial,
             String nombreAutor,
             String apellidoAutor,
             String numeroAlmacen,
             int  espacioAlmacen,
             String nombreLibro,
             int cantidadLibro
            ) {

        // Insertar Editorial
        Editorial editorial =  new Editorial();
        editorial.setNombreEditorial(nombreEditorial);
        //guardar
        editorialRepo.save(editorial);

        // Insertar Autores
        Autor autor =  new Autor();
        autor.setNombreAutor(nombreAutor);
        autor.setApellidoAutor(apellidoAutor);
        //fks
        autor.setEditorial(editorial);
        //guardar
        autorRepo.save(autor);

        //Insertar Almacen
        Almacen almacen =  new Almacen();
        almacen.setNumeroAlmacen(numeroAlmacen);
        almacen.setEspacioAlmacen(espacioAlmacen);
        //guardar
        almacenRepo.save(almacen);

        //Insertar Libro
        Libro libro = new Libro();
        libro.setNombreLibro(nombreLibro);
        libro.setCantidadLibro(cantidadLibro);
        //fks
        libro.setAlmacen(almacen);
        libro.setAutor(autor);
        //guardar
        libroRepo.save(libro);
    }






}
