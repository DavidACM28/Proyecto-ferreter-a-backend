package Final.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Final.Entities.detalleCotizacionEntity;

@Repository
public interface detalleCotizacionRepository extends JpaRepository<detalleCotizacionEntity, Integer> {

}
