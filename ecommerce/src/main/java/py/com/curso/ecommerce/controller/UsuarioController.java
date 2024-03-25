package py.com.curso.ecommerce.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import py.com.curso.ecommerce.model.Orden;
import py.com.curso.ecommerce.model.Usuario;
import py.com.curso.ecommerce.service.OrdenService;
import py.com.curso.ecommerce.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {
    @Autowired
    private UsuarioService service; //usuarioService

    @Autowired
    private OrdenService ordenService;

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

    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session) {
        log.info("Accesos: {}", usuario);

        Optional<Usuario> user = service.findByEmail(usuario.getEmail());


        //Para que nos guarde la session.
        if (user.isPresent()) {
            log.info("Usuario de db: {}", user.get().getEmail());

            session.setAttribute("idUsuario", user.get().getId());

            if (user.get().getTipo().equals("ADMIN")) {
                return "redirect:/administrador";
            } else {
                return "redirect:/";
            }
        } else {
            log.info("Usuario no existe");
        }

        return "redirect:/";
    }

    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session) {
        model.addAttribute("session", session.getAttribute("idUsuario"));

        Usuario usuario = service.findById( Long.parseLong(session.getAttribute("idUsuario").toString()) ).get();
        List<Orden> ordenes = ordenService.findByUsuario(usuario);
        model.addAttribute("ordenes", ordenes);

        return "usuario/compras";
    }

    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Long id, HttpSession session, Model model) {
        log.info("Id de la orden: {}", id);

        Optional<Orden> orden = ordenService.findById(id);
        model.addAttribute("detalles", orden.get().getDetalleOrden());

        //session
        model.addAttribute("session", session.getAttribute("idUsuario"));
        return "usuario/detallecompra";
    }

}
