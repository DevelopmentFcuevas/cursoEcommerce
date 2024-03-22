package py.com.curso.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import py.com.curso.ecommerce.model.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
