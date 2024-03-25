package py.com.curso.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.curso.ecommerce.model.Orden;
import py.com.curso.ecommerce.model.Usuario;
import py.com.curso.ecommerce.repository.OrdenRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImpl implements OrdenService {
    @Autowired
    private OrdenRepository repository;

    //Con este metodo obtenemos todas las ordenes, lo que nos va a servir para ir
    //mirando cual es el ultimo numero secuencial de la orden.
    @Override
    public List<Orden> findAll() {
        return repository.findAll();
    }

    @Override
    public Orden save(Orden orden) {
        return repository.save(orden);
    }

    @Override
    public String generarNumeroOrden() {
        int numero = 0;
        String numeroConcatenado = "";

        List<Orden> ordenes = repository.findAll();
        List<Integer> numeros = new ArrayList<Integer>();

        ordenes.stream().forEach(orden -> numeros.add(Integer.parseInt( orden.getNumero() ) ));

        if (ordenes.isEmpty()) {
            numero = 1;
        } else {
            //Con esto lo que hace es obtenernos el mayor numero de toda esa lista
            numero = numeros.stream().max(Integer::compare).get();  //La lista donde hemos anhadido todos los numeros.
            numero++;
        }

        //Ejemplo para armar numero: 00000000010..
        if (numero < 10) {
            numeroConcatenado = "000000000" + String.valueOf(numero);
        } else if (numero < 100) {
            numeroConcatenado = "00000000" + String.valueOf(numero);
        } else if (numero < 1000) {
            numeroConcatenado = "0000000" + String.valueOf(numero);
        } else if (numero < 10000) {
            numeroConcatenado = "000000" + String.valueOf(numero);
        } else if (numero < 100000) {
            numeroConcatenado = "00000" + String.valueOf(numero);
        }

        return numeroConcatenado;
    }

    @Override
    public List<Orden> findByUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }

    @Override
    public Optional<Orden> findById(Long id) {
        return repository.findById(id);
    }

}
