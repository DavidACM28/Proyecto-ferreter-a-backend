package Final.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class categoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategoria")
    private int idCategoria;

    @Column(name = "nombrecategoria")
    private String nombreCategoria;

    @JsonBackReference
    @OneToMany(mappedBy = "categoria")
    private List<productoEntity> productos;

    @Column(name = "estadocategoria")
    private boolean estadoCategoria;

    public categoriaEntity(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public categoriaEntity(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
