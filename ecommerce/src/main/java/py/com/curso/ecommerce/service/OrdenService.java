package py.com.curso.ecommerce.service;

import py.com.curso.ecommerce.model.Orden;

import java.util.List;

public interface OrdenService {
    List<Orden> findAll();
    Orden save(Orden orden);
}
