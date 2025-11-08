package Final.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import Final.Entities.productoEntity;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface productoRepository extends JpaRepository<productoEntity, Integer> {

    long countByCategoria_IdCategoria(int idCategoria);
    long count();

    @Query("SELECT p FROM productoEntity p WHERE p.idProducto = :id")
    public productoEntity traerPorId(int id);

    @Query("SELECT p FROM productoEntity p WHERE p.nombreProducto = :nombre")
    public List<productoEntity> traerPorNombre(String nombre);

    @Query("SELECT p FROM productoEntity p WHERE p.categoria.nombreCategoria = :categoria")
    public List<productoEntity> traerPorCategoria(String categoria);

    @Query(value = """
        SELECT p.* 
        FROM producto p
        JOIN detalleventa dv ON p.idProducto = dv.idProducto
        JOIN categoria c on p.idCategoria = c.idCategoria
        WHERE p.estadoProducto = true
        AND c.estadoCategoria = true
        GROUP BY p.idProducto
        ORDER BY SUM(dv.cantidadProducto) DESC
        LIMIT 6
        """, nativeQuery = true)
    List<productoEntity> findTop6ProductosMasVendidos();

    @Query("SELECT p FROM productoEntity p WHERE p.estadoProducto = true AND p.categoria.estadoCategoria = true")
    public List<productoEntity> traerHabilitados();

    @Query("SELECT p FROM productoEntity p WHERE p.estadoProducto = false")
    public List<productoEntity> traerDeshabilitados();


    @Modifying
    @Transactional
    @Query("UPDATE productoEntity p set p = :producto where p.idProducto = :id")
    public void actualizar(int id, productoEntity producto);

    @Modifying
    @Transactional
    @Query("UPDATE productoEntity p set p.estadoProducto = false where p.idProducto = :id")
    public void desHabilitarProducto(int id);

    @Modifying
    @Transactional
    @Query("UPDATE productoEntity p set p.estadoProducto = true where p.idProducto = :id")
    public void habilitarProducto(int id);

}
