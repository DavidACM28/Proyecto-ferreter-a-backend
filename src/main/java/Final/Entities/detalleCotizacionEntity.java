package Final.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detallevotizacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class detalleCotizacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetallecotizacion")
    private int idDetalleCotizacion;

    @ManyToOne
    @JoinColumn(name = "idcotizacion")
    private cotizacionEntity cotizacion;

    @ManyToOne
    @JoinColumn(name = "idproducto")
    private productoEntity producto;

    @Column(name = "cantidadproducto")
    private int cantidadProducto;

    @Column(name = "precioproducto")
    private double precioProducto;

    public detalleCotizacionEntity(int idDetalleCotizacion) {
        this.idDetalleCotizacion = idDetalleCotizacion;
    }
}
