package Final.Repositories;


import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Final.Entities.auditoriaInventarioEntity;


@Repository
public interface auditoriaInventarioRepository extends JpaRepository<Final.Entities.auditoriaInventarioEntity, Integer> {

    @Query("""
        SELECT a FROM auditoriaInventarioEntity a
        WHERE (:producto IS NULL OR LOWER(a.producto.nombreProducto) LIKE LOWER(CONCAT('%', :producto, '%')))
        AND (
        :tipo IS NULL OR 
        (:tipo = 'entrada' AND a.cantidadNueva > a.cantidadAnterior) OR 
        (:tipo = 'salida' AND a.cantidadNueva < a.cantidadAnterior)
        )
        AND (:fechaInicio IS NULL OR a.fecha >= :fechaInicio)
        AND (:fechaFin IS NULL OR a.fecha <= :fechaFin)
        ORDER BY idAuditoriaInventario DESC
        """)
    Page<auditoriaInventarioEntity> filtrarAuditorias(
        @Param("producto") String producto,
        @Param("tipo") String tipo,
        @Param("fechaInicio") LocalDateTime fechaInicio,
        @Param("fechaFin") LocalDateTime fechaFin,
        Pageable pageable
    );
}
