package py.com.curso.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("Identificador único del registro.")
    private Long id;

    @Column(name = "nombre", nullable = false)
    @Comment("Nombre del producto")
    private String nombre;

    @Column(name = "descripcion")
    @Comment("Descripcion del producto")
    private String descripcion;

    @Column(name = "imagen")
    @Comment("Imagen del producto")
    private String imagen;

    @Column(name = "precio")
    @Comment("Precio del producto")
    private double precio;

    @Column(name = "cantidad")
    @Comment("Cantidad del producto")
    private double cantidad;

    //Aqui lo que va hacer internamente el framework es a crear un campo en la tabla "Producto"
    //para mandar el IdUsuario o un Usuario como Objeto y que se mapee directamente a la clase "Usuario".
    @ManyToOne
    private Usuario usuario;
}
