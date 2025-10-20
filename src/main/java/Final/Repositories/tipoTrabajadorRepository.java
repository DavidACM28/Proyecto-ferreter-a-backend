package Final.Repositories;

import org.springframework.stereotype.Repository;
import Final.Entities.tipoTrabajadorEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface tipoTrabajadorRepository extends JpaRepository<tipoTrabajadorEntity, Integer> {

    Optional<tipoTrabajadorEntity> findByDescripcionTipoTrabajador(String descripcionTipoTrabajador);

    @Query("SELECT tt FROM tipoTrabajadorEntity tt WHERE tt.descripcionTipoTrabajador = :descripcion")
    tipoTrabajadorEntity findPorDescripcion(String descripcion);

}
