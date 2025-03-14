package edu.com.bookwarehousejunior.Repo;

import edu.com.bookwarehousejunior.Model.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {
}
