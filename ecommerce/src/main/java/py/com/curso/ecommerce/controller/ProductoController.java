package py.com.curso.ecommerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import py.com.curso.ecommerce.model.Producto;
import py.com.curso.ecommerce.model.Usuario;
import py.com.curso.ecommerce.service.ProductoService;

@Controller
@RequestMapping("/productos")
@Slf4j
public class ProductoController {
    @Autowired
    private ProductoService service;

    @GetMapping("")
    public String show() {
        return "productos/show";
    }

    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto) {
        log.info("Este es el objeto producto: {}", producto);
        Usuario usuario = new Usuario();
        Long identificador = 1L;
        usuario.setId(identificador);
        producto.setUsuario(usuario);
        
        service.save(producto);
        return "redirect:/productos";
    }

}
