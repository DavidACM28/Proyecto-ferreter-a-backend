package Final.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import Final.Entities.trabajadorEntity;
import jakarta.transaction.Transactional;

@Repository
public interface trabajadorRepository extends JpaRepository<trabajadorEntity, Integer> {

    Optional<trabajadorEntity> findByUsuarioTrabajador(String usuarioTrabajador);

    Boolean existsByUsuarioTrabajador(String usuarioTrabajador);

    @Query("select t from trabajadorEntity t where t.usuarioTrabajador = :usuarioTrabajador")
    trabajadorEntity findByUser(String usuarioTrabajador);

    @Modifying
    @Transactional
    @Query("UPDATE trabajadorEntity t SET t.estadoTrabajador = false WHERE t.idTrabajador = :id")
    void deshabilitarTrabajador(int id);

    @Modifying
    @Transactional
    @Query("UPDATE trabajadorEntity t SET t.estadoTrabajador = true WHERE t.idTrabajador = :id")
    void habilitarTrabajador(int id);

}
