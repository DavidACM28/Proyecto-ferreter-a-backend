package Final.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import Final.Entities.categoriaEntity;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface categoriaRepository extends JpaRepository<categoriaEntity, Integer> {

    @Query("SELECT c FROM categoriaEntity c WHERE c.estadoCategoria = true")
    public List<categoriaEntity> findAllTrue();

    //Hace busqueda por el nombre de la categoria y trae una categoria
    @Query("SELECT c FROM categoriaEntity c WHERE c.nombreCategoria = :categoria")
    public categoriaEntity traerPorCategoria(String categoria);

    @Query("SELECT c FROM categoriaEntity c WHERE c.idCategoria = :id")
    public categoriaEntity getPorId(int id);

    @Modifying
    @Transactional
    @Query("UPDATE categoriaEntity c SET c.estadoCategoria = false WHERE c.idCategoria = :id")
    public void deshabilitar(int id);

    @Modifying
    @Transactional
    @Query("UPDATE categoriaEntity c SET c.estadoCategoria = true WHERE c.idCategoria = :id")
    public void habilitar(int id);

}
