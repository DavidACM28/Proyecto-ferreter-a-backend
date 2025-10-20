package Final.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Final.Entities.ingresoProducto;

@Repository
public interface ingresoProductoRepository extends JpaRepository<ingresoProducto, Integer> {

}
