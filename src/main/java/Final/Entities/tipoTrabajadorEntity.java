package Final.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "tipotrabajador")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class tipoTrabajadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipotrabajador")
    private int idTipoTrabajador;

    @Column(name = "descripciontipotrabajador")
    private String descripcionTipoTrabajador;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoTrabajador")
    private List<trabajadorEntity> trabajadores;

    public tipoTrabajadorEntity(int idTipoTrabajador) {
        this.idTipoTrabajador = idTipoTrabajador;
    }
}
