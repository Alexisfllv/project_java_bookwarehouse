package edu.com.bookwarehousejunior.Repo;

import edu.com.bookwarehousejunior.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {


    //vista global
    @Query(value = "SELECT * FROM vista_libros_global", nativeQuery = true)
    List<Object[]> obtenerVistaLibros();

    @Query(value = "SELECT * FROM vista_libros_global2", nativeQuery = true)
    List<Object[]> obtenerLibrosDesdeVista();
}
