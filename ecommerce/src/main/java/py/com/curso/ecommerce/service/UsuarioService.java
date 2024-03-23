package py.com.curso.ecommerce.service;

import py.com.curso.ecommerce.model.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
}
