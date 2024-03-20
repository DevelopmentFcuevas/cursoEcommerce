package py.com.curso.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("Identificador único del registro.")
    private Long id;

    @Column(name = "nombre", nullable = false)
    @Comment("Nombre del usuario")
    private String nombre;

    @Column(name = "username", nullable = false)
    @Comment("Username del usuario")
    private String username;

    @Column(name = "email", nullable = false)
    @Comment("Email del usuario")
    private String email;

    @Column(name = "direccion", nullable = false)
    @Comment("Direccion del usuario")
    private String direccion;

    @Column(name = "telefono", nullable = false)
    @Comment("Telefono del usuario")
    private String telefono;

    @Column(name = "tipo", nullable = false)
    //@Comment("")
    private String tipo;

    @Column(name = "password", nullable = false)
    @Comment("Password del usuario")
    private String password;

    //Para obtener una lista de productos para usuarios
    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;

    //Para obtener una lista de productos para ordenes
    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;
}
