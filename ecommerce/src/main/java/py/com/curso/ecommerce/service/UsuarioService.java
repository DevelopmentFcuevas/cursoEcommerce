package py.com.curso.ecommerce.service;

import py.com.curso.ecommerce.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
    Optional<Usuario> findByEmail(String email);
}
