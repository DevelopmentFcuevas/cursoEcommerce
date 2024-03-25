package py.com.curso.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import py.com.curso.ecommerce.model.Orden;
import py.com.curso.ecommerce.model.Usuario;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

    //Buscar todas las ordenes por usuario.
    List<Orden> findByUsuario(Usuario usuario);
}
