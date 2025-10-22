package Final.Entities;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ventaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idventa")
    private int idVenta;

    @ManyToOne
    @JoinColumn(name = "idtrabajador")
    private trabajadorEntity trabajador;

    @Column(name = "fechaventa")
    private LocalDateTime fechaVenta;

    @Column(name = "totalventa")
    private double totalVenta;

    @Column(name = "clienteventa")
    private String clienteVenta;

    @Column(name = "mediopagoventa")
    private String medioPagoVenta;

    public ventaEntity(int idVenta) {
        this.idVenta = idVenta;
    }
    
    @JsonIgnore
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<detalleVentaEntity> detalleVentas;
    
    @PrePersist
    protected void onCreate() {
        this.fechaVenta = LocalDateTime.now();
    }
}
