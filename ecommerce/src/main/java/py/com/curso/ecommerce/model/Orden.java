package py.com.curso.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Date;

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
}
