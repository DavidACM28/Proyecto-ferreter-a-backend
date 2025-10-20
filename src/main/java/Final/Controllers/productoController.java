package Final.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Final.Services.productoService;
import Final.Entities.productoEntity;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")
public class productoController {

    @Autowired
    private productoService productoService;

    @GetMapping("/producto/{id}")
    public productoEntity buscarPorId(@PathVariable int id) {
        return productoService.traerPorId(id);
    }

    @GetMapping("/productosCategoria/{categoria}")
    public List<productoEntity> traerPorCategoria(@PathVariable String categoria) {
        return productoService.traerPorCategoria(categoria);
    }

    @GetMapping("/productosNombre/{nombre}")
    public List<productoEntity> traerPorNombre(@PathVariable String nombre) {
        return productoService.traerPorNombre(nombre);
    }
    
    @GetMapping("/productos")
    public List<productoEntity> traerTodos() {
        return productoService.findAll();
    }

    @GetMapping("/masVendidos")
    public ResponseEntity<List<productoEntity>> obtenerTop6Productos() {
        List<productoEntity> productos = productoService.obtenerTop6ProductosMasVendidos();
        return ResponseEntity.ok(productos);
    }
    
    @PostMapping("/nuevoProducto")
    public productoEntity create(@RequestBody productoEntity producto) {
        return productoService.crearProducto(producto);
    }
    
    @PostMapping("actualizar")
    public String actualizar(@RequestBody productoEntity producto) {
        return productoService.editarProducto(producto);
    }

    @PostMapping("deshabilitar/{id}")
    public String deshabilitar(@PathVariable int id) {
        return productoService.deshabilitarProducto(id);
    }
    
    @PostMapping("habilitar/{id}")
    public String habilitar(@PathVariable int id) {
        return productoService.habilitarProducto(id);
    }
    
}
