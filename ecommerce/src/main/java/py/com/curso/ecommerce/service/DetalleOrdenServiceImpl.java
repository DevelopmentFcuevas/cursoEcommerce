package py.com.curso.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.curso.ecommerce.model.DetalleOrden;
import py.com.curso.ecommerce.repository.DetalleOrdenRepository;

@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService {
    @Autowired
    private DetalleOrdenRepository repository;
    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return repository.save(detalleOrden);
    }
}
