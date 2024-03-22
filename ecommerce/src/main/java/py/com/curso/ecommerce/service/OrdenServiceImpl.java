package py.com.curso.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.curso.ecommerce.model.Orden;
import py.com.curso.ecommerce.repository.OrdenRepository;

@Service
public class OrdenServiceImpl implements OrdenService {
    @Autowired
    private OrdenRepository repository;

    @Override
    public Orden save(Orden orden) {
        return repository.save(orden);
    }
}
