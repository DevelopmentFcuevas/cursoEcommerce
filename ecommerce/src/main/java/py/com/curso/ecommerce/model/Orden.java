package py.com.curso.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orden")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("Identificador único del registro.")
    private Long id;

    @Column(name = "numero", nullable = false)
    @Comment("Numero de orden")
    private String numero;

    @Column(name = "fecha_recibida")
    @Comment("Fecha que se recibio la orden")
    private String fechaRecibida;

    @Column(name = "fecha_creacion")
    @Comment("Registro de la fecha y hora en que se creó el registro.")
    private Date fechaCreacion;

    @Column(name = "total")
    @Comment("Total de la orden")
    private double total;

    //Vamos a crear un atributo que nos permita identificar que usuarios estan registrados a una orden.
    //Aqui lo que va hacer internamente el framework es a crear un campo en la tabla "Producto"
    //para mandar el IdUsuario o un Usuario como Objeto y que se mapee directamente a la clase "Usuario".
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detalleOrden;
}
