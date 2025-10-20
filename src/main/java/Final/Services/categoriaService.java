package Final.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import Final.Repositories.categoriaRepository;
import Final.Entities.categoriaEntity;
import Final.Repositories.productoRepository;

@Service
public class categoriaService {

    @Autowired
    categoriaRepository categoriaRepository;

    @Autowired
    productoRepository productoRepository;

    public List<categoriaEntity> findAllTrue() {
        return categoriaRepository.findAllTrue();
    }

    public Page<categoriaEntity> findAllPage(PageRequest pageable) {
        return categoriaRepository.findAll(pageable);
    }

    public String crearCategoria(categoriaEntity categoriaProductoEntity) { 
        categoriaRepository.save(categoriaProductoEntity);
        return "Categoria creada con exito";
    }

    public String editarCategoria(categoriaEntity categoriaProductoEntity){
        categoriaRepository.save(categoriaProductoEntity);
        return "Categoria editada con exito";
    }

    public void eliminarCategoria(int idCategoria){
        long productos = productoRepository.countByCategoria_IdCategoria(idCategoria);
        if (productos > 0) {
            throw new IllegalArgumentException("No se puede eliminar una categoria con productos");
        }
        categoriaRepository.deleteById(idCategoria);
    }

    public categoriaEntity traerPorCategoria(String categoria) {
        return categoriaRepository.traerPorCategoria(categoria);
    }

    public categoriaEntity getById(int id){
        return categoriaRepository.getPorId(id);
    }

    public void deshabilitar(int id){
        categoriaRepository.deshabilitar(id);
    }

    public void habilitar(int id){
        categoriaRepository.habilitar(id);
    }

    
    
}
