package edu.com.bookwarehousejunior.Repo;

import edu.com.bookwarehousejunior.Model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Integer> {
}
