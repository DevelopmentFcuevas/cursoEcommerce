package py.com.curso.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.curso.ecommerce.model.Usuario;
import py.com.curso.ecommerce.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    UsuarioRepository repository;

    @Override
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

}
