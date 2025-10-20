package Final.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "trabajador")
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class trabajadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtrabajador")
    private int idTrabajador;

    @ManyToOne
    @JoinColumn(name = "idtipotrabajador")
    private tipoTrabajadorEntity tipoTrabajador;

    @Column(name = "nombretrabajador")
    private String nombreTrabajador;

    @Column(name = "apellidotrabajador")
    private String apellidoTrabajador;

    @Column(name = "usuariotrabajador")
    private String usuarioTrabajador;

    @Column(name = "contraseñatrabajador")
    private String contraseñaTrabajador;

    @Column(name = "estadotrabajador") 
    private boolean estadoTrabajador;

    @JsonIgnore
    @OneToMany(mappedBy = "trabajador")
    private List<cotizacionEntity> cotizaciones;

    @JsonIgnore
    @OneToMany(mappedBy = "trabajador")
    private List<ventaEntity> ventas;

    @JsonIgnore
    @OneToMany(mappedBy = "trabajador")
    private List<auditoriaInventarioEntity> auditorias;

    public trabajadorEntity(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }
}
