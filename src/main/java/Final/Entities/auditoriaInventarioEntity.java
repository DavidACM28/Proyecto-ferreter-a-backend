package Final.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auditoriainventario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class auditoriaInventarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idauditoria")
    private int idAuditoriaInventario;

    @ManyToOne
    @JoinColumn(name = "idproducto")
    private productoEntity producto;

    @Enumerated(EnumType.STRING)
    private Accion accion;

    @Column(name = "cantidadanterior")
    private int cantidadAnterior;

    @Column(name = "cantidadnueva")
    private int cantidadNueva;

    @ManyToOne
    @JoinColumn(name = "idtrabajador")
    private trabajadorEntity trabajador;

    @Column(name = "referencia")
    private String referencia;

    @Column(name = "fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP") 
    private LocalDateTime fecha;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    } 
}
