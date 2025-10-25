package Final.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Final.Services.categoriaService;
import Final.Entities.categoriaEntity;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin
public class categoriaController {

    @Autowired
    private categoriaService categoriaProductoService;

    @PostMapping("/crearCategoria")
    public String crear(@RequestBody categoriaEntity categoriaProductoEntity) {
        return categoriaProductoService.crearCategoria(categoriaProductoEntity);
    }

    @PostMapping("/editarCategoria")
    public String editar(@RequestBody categoriaEntity categoriaProductoEntity) {
        return categoriaProductoService.editarCategoria(categoriaProductoEntity);
    }

    @GetMapping("/categoriasHabilitadas")
    public List<categoriaEntity> getAll() {
        return categoriaProductoService.findAllTrue();
    }
    @GetMapping("/categorias")
    public List<categoriaEntity> findAll() {
        return categoriaProductoService.findAll();
    }

    @PostMapping("/eliminar/{id}")
    public String postMethodName(@PathVariable int id) {
        try{
            categoriaProductoService.eliminarCategoria(id);
            return "Categoria eliminada con exito";
        } catch (IllegalArgumentException e) {
            return "No se puede eliminar una categoria con productos";
        }
    }
    
    @GetMapping("/categorias/{page}")
    public Page<categoriaEntity> getAll(@PathVariable int page) {
        final PageRequest pageable = PageRequest.of(page, 7);
        return categoriaProductoService.findAllPage(pageable);
    }

    @GetMapping("/categoria/id/{id}")
    public categoriaEntity getById(@PathVariable int id) {
        return categoriaProductoService.getById(id);
    }

    @GetMapping("/categoria/{categoria}")
    public categoriaEntity buscarPorCategoria(@PathVariable String categoria){
        return categoriaProductoService.traerPorCategoria(categoria);
    }

    @PostMapping("/habilitar/{id}")
    public void habilitar(@PathVariable int id) {
        categoriaProductoService.habilitar(id);
    }

    

    @PostMapping("/deshabilitar/{id}")
    public void deshabilitar(@PathVariable int id) {
        categoriaProductoService.deshabilitar(id);
    }
    

}
