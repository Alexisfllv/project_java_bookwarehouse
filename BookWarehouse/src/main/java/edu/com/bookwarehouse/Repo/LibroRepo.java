package edu.com.bookwarehouse.Repo;

import edu.com.bookwarehouse.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepo extends JpaRepository<Libro, Integer> {

}
