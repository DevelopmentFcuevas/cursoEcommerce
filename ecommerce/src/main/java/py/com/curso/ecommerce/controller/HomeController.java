package py.com.curso.ecommerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import py.com.curso.ecommerce.model.DetalleOrden;
import py.com.curso.ecommerce.model.Orden;
import py.com.curso.ecommerce.model.Producto;
import py.com.curso.ecommerce.service.ProductoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("productos", productoService.findAll());
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

        detalleOrdens.add(detalle);

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

}
