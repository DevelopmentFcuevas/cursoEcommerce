package py.com.curso.ecommerce.service;

import py.com.curso.ecommerce.model.Orden;
import py.com.curso.ecommerce.model.Usuario;

import java.util.List;

public interface OrdenService {
    List<Orden> findAll();
    Orden save(Orden orden);
    String generarNumeroOrden();
    List<Orden> findByUsuario(Usuario usuario);
}
