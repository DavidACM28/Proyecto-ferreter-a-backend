package Final.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Final.Entities.cotizacionEntity;

@Repository
public interface cotizacionRepository extends JpaRepository<cotizacionEntity, Integer> {

}
