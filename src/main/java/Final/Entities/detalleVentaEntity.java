package Final.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "detalleventa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class detalleVentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetalleventa")
    private int idDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "idproducto")
    private productoEntity producto;

    @ManyToOne
    @JoinColumn(name = "idventa")
    private ventaEntity venta;

    @Column(name = "cantidadproducto")
    private int cantidadProducto;

    @Column(name = "precioproducto")
    private double precioProducto;

    public detalleVentaEntity(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }
}
