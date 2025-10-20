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
@Table(name = "cotizacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class cotizacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcotizacion")
    private int idCotizacion;
    
    @ManyToOne
    @JoinColumn(name = "idtrabajador")
    private trabajadorEntity trabajador;

    @Column(name = "totalcotizacion")
    private double totalCotizacion;

    @Column(name = "fechacotizacion")
    private String fechaCotizacion;

    public cotizacionEntity(int idCotizacion) {
        this.idCotizacion = idCotizacion;
    }
}
