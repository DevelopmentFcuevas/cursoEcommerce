package py.com.curso.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.curso.ecommerce.model.Producto;
import py.com.curso.ecommerce.repository.ProductoRepository;

import java.util.Optional;
@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    ProductoRepository repository;

    @Override
    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public Optional<Producto> get(Long id) {
        //return Optional.empty();
        return repository.findById(id);
    }

    @Override
    public void update(Producto producto) {
        repository.save(producto);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
