package Final.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class productoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproducto")
    private int idProducto;

    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private categoriaEntity categoria;

    @Column(name = "nombreproducto")
    private String nombreProducto;

    @Column(name = "descripcionproducto")
    private String descripcionProducto;

    @Column(name = "precioproducto")
    private double precioProducto;

    @Column(name = "cantidadproducto")
    private int cantidadProducto;

    @Column(name = "estadoproducto")
    private Boolean estadoProducto;

    @JsonIgnore
    @OneToMany(mappedBy = "producto")
    private List<detalleVentaEntity> detallesVenta;

    @JsonIgnore
    @OneToMany(mappedBy = "producto")
    private List<detalleCotizacionEntity> detallesCotizacion;

    @JsonIgnore
    @OneToMany(mappedBy = "producto")
    private List<auditoriaInventarioEntity> auditorias;

    public productoEntity(int idProducto) {
        this.idProducto = idProducto;
    }

}
