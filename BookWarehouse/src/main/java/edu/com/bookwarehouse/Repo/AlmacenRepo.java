package edu.com.bookwarehouse.Repo;

import edu.com.bookwarehouse.Model.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlmacenRepo extends JpaRepository<Almacen, Integer> {

}
