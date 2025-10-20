package Final.Repositories;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Final.Entities.ventaEntity;


@Repository
public interface ventaRepository extends JpaRepository<ventaEntity, Integer> {

    
    @Query("SELECT COALESCE(SUM(v.totalVenta),0) FROM ventaEntity v")
    Double sumTotalVentas();

    // ventas por mes (últimos N meses) - usamos native query for grouping month/year
    @Query(value = """
        SELECT YEAR(v.fechaVenta) AS yr, MONTH(v.fechaVenta) AS mo, COALESCE(SUM(v.totalVenta),0) AS total
        FROM venta v
        WHERE v.fechaVenta >= DATE_SUB(CURDATE(), INTERVAL ?1 MONTH)
        GROUP BY yr, mo
        ORDER BY yr ASC, mo ASC
        """, nativeQuery = true)
    List<Object[]> findVentasPorMes(int meses);

    @Query("SELECT v FROM ventaEntity v WHERE v.fechaVenta >= :inicio AND v.fechaVenta < :fin")
    List<ventaEntity> findVentasBetweenDates(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);



    @Query("""
        SELECT c.nombreCategoria AS categoria, p.nombreProducto AS producto, SUM(dv.cantidadProducto) AS cantidadVendida
        FROM detalleVentaEntity dv
        JOIN dv.producto p
        JOIN p.categoria c
        GROUP BY c.nombreCategoria, p.nombreProducto
        ORDER BY cantidadVendida DESC
    """)
    List<Map<String, Object>> findProductosMasVendidosPorCategoria();

    @Query(value = """
    SELECT DISTINCT v.* 
    FROM venta v
    WHERE v.fechaventa >= :inicio
      AND v.fechaventa < :fin
      AND (
          :productoId IS NULL
          OR EXISTS (
              SELECT 1 
              FROM detalleventa d
              WHERE d.idventa = v.idventa
              AND d.idproducto = :productoId
          )
      )
      AND (
          :categoriaId IS NULL
          OR EXISTS (
              SELECT 1
              FROM detalleventa d2
              JOIN producto p2 ON p2.idproducto = d2.idproducto
              WHERE d2.idventa = v.idventa
              AND p2.idcategoria = :categoriaId
          )
      )
""", nativeQuery = true)
List<ventaEntity> findVentasFiltradas(
    @Param("inicio") LocalDateTime inicio,
    @Param("fin") LocalDateTime fin,
    @Param("productoId") Long productoId,
    @Param("categoriaId") Long categoriaId
);




}
