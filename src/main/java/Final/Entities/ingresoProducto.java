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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingresoproducto")
public class ingresoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idingresoproducto")
    private int idIngreso;

    @ManyToOne
    @JoinColumn(name = "idproducto")
    private productoEntity producto;

    @ManyToOne
    @JoinColumn(name = "idtrabajador")
    private trabajadorEntity trabajador;

    @Column(name = "cantidadingreso")
    private int cantidadIngreso;

    @Column(name = "fechaingreso")
    private String fechaIngreso;

    public ingresoProducto(int idIngreso) {
        this.idIngreso = idIngreso;
    }
}
