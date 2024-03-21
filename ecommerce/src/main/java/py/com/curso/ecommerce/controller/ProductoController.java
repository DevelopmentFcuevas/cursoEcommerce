package py.com.curso.ecommerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import py.com.curso.ecommerce.model.Producto;
import py.com.curso.ecommerce.model.Usuario;
import py.com.curso.ecommerce.service.ProductoService;

import java.util.Optional;

@Controller
@RequestMapping("/productos")
@Slf4j
public class ProductoController {
    @Autowired
    private ProductoService service;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("productos", service.findAll());
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

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Producto producto = new Producto();
        Optional<Producto> optionalProducto = service.get(id);
        producto = optionalProducto.get();
        log.info("Producto buscado: {}", producto.getId());

        model.addAttribute("producto", producto);
        //service.save(producto);
        return "/productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto) {
        service.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/productos";
    }

}
