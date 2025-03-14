package edu.com.bookwarehouse.Repo;

import edu.com.bookwarehouse.Model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepo extends JpaRepository<Autor, Integer> {

}
