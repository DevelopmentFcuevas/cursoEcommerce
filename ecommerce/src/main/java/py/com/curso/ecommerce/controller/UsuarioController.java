package py.com.curso.ecommerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import py.com.curso.ecommerce.model.Usuario;
import py.com.curso.ecommerce.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {
    @Autowired
    private UsuarioService service; //usuarioService

    // /usuario/registro
    @GetMapping("/registro")
    public String create() {
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario) {
        log.info("Usuario registro: {}", usuario);
        usuario.setTipo("USER");
        service.save(usuario);
        return "redirect:/";
    }

}
