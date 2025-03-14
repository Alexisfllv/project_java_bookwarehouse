package edu.com.bookwarehouse.Repo;

import edu.com.bookwarehouse.Model.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepo extends JpaRepository<Editorial, Integer> {

}
