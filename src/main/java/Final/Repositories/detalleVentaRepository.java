package Final.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Final.Entities.detalleVentaEntity;

@Repository
public interface detalleVentaRepository extends JpaRepository<detalleVentaEntity, Integer> {

    @Query(value = """
            SELECT p.idProducto AS idProducto,
                   p.nombreProducto AS nombreProducto,
                   SUM(d.cantidadProducto) AS totalVendido
            FROM detalleventa d
            JOIN producto p ON d.idProducto = p.idProducto
            GROUP BY p.idProducto, p.nombreProducto
            ORDER BY totalVendido DESC
            LIMIT 6
            """, nativeQuery = true)
    List<Object[]> findTop6ProductosMasVendidosNative();

    @Query(value = """
            SELECT p.idProducto AS idProducto,
                   p.nombreProducto AS nombreProducto,
                   SUM(d.cantidadProducto) AS totalVendido
            FROM detalleVenta d
            JOIN producto p ON d.idProducto = p.idProducto
            GROUP BY p.idProducto, p.nombreProducto
            ORDER BY totalVendido DESC
            """, nativeQuery = true)
    List<Object[]> findProductosMasVendidosAll();

    @Query("SELECT dv FROM detalleVentaEntity dv where dv.venta.idVenta = :idVenta")
    List<detalleVentaEntity> findByIdVenta(int idVenta);

    @Query(value = """
                SELECT p.nombreProducto, SUM(d.cantidadProducto)
                FROM detalleVenta d
                INNER JOIN producto p ON p.idProducto = d.idProducto
                INNER JOIN venta v ON v.idVenta = d.idVenta
                WHERE MONTH(v.fechaVenta) = MONTH(CURDATE())
                  AND YEAR(v.fechaVenta) = YEAR(CURDATE())
                GROUP BY p.nombreProducto
                ORDER BY SUM(d.cantidadProducto) DESC
                LIMIT 1
            """, nativeQuery = true)
    List<Object[]> findProductoMasVendidoDelMes();

}
