package py.com.curso.ecommerce.service;

import py.com.curso.ecommerce.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public Producto save(Producto producto);

    /* ¿Porque se utiliza Optional?
     * - Por que Optional nos da la opcion de poder validar
     * si es que el objeto que obtenemos existe o no.
     */
    public Optional<Producto> get(Long id);

    public void update(Producto producto);

    public void delete(Long id);

    public List<Producto> findAll();
}
