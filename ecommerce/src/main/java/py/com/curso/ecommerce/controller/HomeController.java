package py.com.curso.ecommerce.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import py.com.curso.ecommerce.model.DetalleOrden;
import py.com.curso.ecommerce.model.Orden;
import py.com.curso.ecommerce.model.Producto;
import py.com.curso.ecommerce.model.Usuario;
import py.com.curso.ecommerce.service.DetalleOrdenService;
import py.com.curso.ecommerce.service.OrdenService;
import py.com.curso.ecommerce.service.ProductoService;
import py.com.curso.ecommerce.service.UsuarioService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {
    @Autowired
    private ProductoService productoService;

    //Lista para almacenar los detalles de la orden.
    List<DetalleOrden> detalleOrdens = new ArrayList<DetalleOrden>(); //detalles

    //Para almacenar datos de la orden
    Orden orden = new Orden();

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private DetalleOrdenService detalleOrdenService;


    @GetMapping("")
    public String home(Model model, HttpSession session) {
        log.info("Sesion del usuario: {}", session.getAttribute("idUsuario"));
        model.addAttribute("productos", productoService.findAll());

        //session
        model.addAttribute("session", session.getAttribute("idUsuario"));

        return "usuario/home";
    }

    @GetMapping("productohome/{id}")
    public String productHome(@PathVariable Long id, Model model) {
        log.info("Id enviado como parametro: {}", id);
        Producto producto = new Producto();
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();
        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Long id, @RequestParam Integer cantidad, Model model) {
        DetalleOrden detalle = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;

        Optional<Producto> optionalProducto = productoService.get(id);
        log.info("Producto agregado: {}", optionalProducto.get().getId());
        log.info("Cantidad: {}", cantidad);

        producto = optionalProducto.get();
        detalle.setCantidad(cantidad);
        detalle.setNombre(producto.getNombre());
        detalle.setPrecio(producto.getPrecio());
        detalle.setTotal(producto.getPrecio() * cantidad);
        detalle.setProducto(producto);

        //validar que el producto no se anhada dos veces
        Long idProducto = producto.getId();
        //Si existe algun producto con ese id,sabremos si es que hay algo ingresado con ese codigo(idProducto)
        boolean ingresado = detalleOrdens.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

        if (!ingresado) {
            detalleOrdens.add(detalle);
        }


        //Sumar el total de lo que anhada el usuario al carrito.
        //dt-> : Se refiere a una funcion anonima.
        //basicamente lo que hace es sumarnos todos los totales de los productos
        //que esten en esa lista(dt).
        sumaTotal = detalleOrdens.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);

        model.addAttribute("cart", detalleOrdens);
        model.addAttribute("orden", orden);
        return "usuario/carrito";
    }

    @GetMapping("/delete/cart/{id}")
    public String deleteProductoCart(@PathVariable Long id, Model model) {

        //Lista nueva de productos
        List<DetalleOrden> ordenesNuevas = new ArrayList<DetalleOrden>();

        for (DetalleOrden detalleOrden: detalleOrdens) {
            if (detalleOrden.getProducto().getId() != id) {
                ordenesNuevas.add(detalleOrden);
            }
        }

        //Poner la nueva lista con los productos restantes
        detalleOrdens = ordenesNuevas;

        //Calcular nuevamente los productos
        double sumaTotal = 0;
        //Sumar el total de lo que anhada el usuario al carrito.
        //dt-> : Se refiere a una funcion anonima.
        //basicamente lo que hace es sumarnos todos los totales de los productos
        //que esten en esa lista(dt).
        sumaTotal = detalleOrdens.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);

        model.addAttribute("cart", detalleOrdens);
        model.addAttribute("orden", orden);

        return "usuario/carrito";

    }

    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session) {
        model.addAttribute("cart", detalleOrdens);
        model.addAttribute("orden", orden);
        model.addAttribute("session", session.getAttribute("idUsuario"));
        return "/usuario/carrito";
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session) {

        //Usuario usuario = usuarioService.findById(1L).get();
        //Usuario usuario = usuarioService.findById(Long.parseLong((String) session.getAttribute("idUsuario"))).get();
        Usuario usuario = usuarioService.findById(Long.parseLong(session.getAttribute("idUsuario").toString())).get();

        model.addAttribute("cart", detalleOrdens);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);

        return "usuario/resumenorden";
    }

    //Guardar la orden
    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session) {
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());

        //usuario
        //Usuario usuario = usuarioService.findById(1L).get();
        Usuario usuario = usuarioService.findById(Long.parseLong(session.getAttribute("idUsuario").toString())).get();
        orden.setUsuario(usuario);

        ordenService.save(orden);
        log.info("ORDEN: {}", orden.getId());

        //guardar detalles
        for (DetalleOrden dt:detalleOrdens) {
            dt.setOrden(orden);
            detalleOrdenService.save(dt);
            log.info("DETALLEORDEN: {}", orden);
            log.info("DETALLEORDEN_DT: ", dt);
        }

        //limpiar lista y orden
        orden = new Orden();
        detalleOrdens.clear();

        return "redirect:/";

    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model) {
        log.info("Nombre del producto: {}", nombre);
        //esta escrito en lambda, pasar con chatgpt a funcion normal.
        List<Producto> productos = productoService.findAll().stream().filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
        model.addAttribute("productos", productos);
        return "usuario/home";
    }

}
