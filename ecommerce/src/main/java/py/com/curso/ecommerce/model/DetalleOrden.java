package py.com.curso.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "detalle_orden")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("Identificador único del registro.")
    private Long id;

    @Column(name = "nombre", nullable = false)
    //@Comment("")
    private String nombre;

    @Column(name = "cantidad")
    @Comment("Cantidad en el detalle")
    private double cantidad;

    @Column(name = "precio")
    @Comment("Precio en el detalle")
    private double precio;

    @Column(name = "total")
    @Comment("Total del detalle")
    private double total;

    @OneToOne
    private Orden orden;

    //@OneToOne
    @ManyToOne
    private Producto producto;
}
