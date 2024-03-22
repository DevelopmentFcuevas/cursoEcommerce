package py.com.curso.ecommerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import py.com.curso.ecommerce.service.ProductoService;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "usuario/home";
    }

    @GetMapping("productohome/{id}")
    public String productHome(@PathVariable Long id) {
        log.info("Id enviado como parametro: {}", id);
        return "usuario/productohome";
    }

}
