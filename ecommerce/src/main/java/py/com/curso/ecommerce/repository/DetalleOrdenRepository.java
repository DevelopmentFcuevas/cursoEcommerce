package py.com.curso.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import py.com.curso.ecommerce.model.DetalleOrden;
@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Long> {
}
