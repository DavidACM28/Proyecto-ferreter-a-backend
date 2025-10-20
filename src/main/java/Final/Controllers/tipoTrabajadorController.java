package Final.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Final.Services.tipoTrabajadorService;
import Final.Entities.tipoTrabajadorEntity;

import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tipoTrabajador")
@CrossOrigin
public class tipoTrabajadorController {

    @Autowired
    private tipoTrabajadorService tipoTrabajadorService;

    @GetMapping("/tipoTrabajadores")
    public List<tipoTrabajadorEntity> getAll() {
        return tipoTrabajadorService.findAll();
    }

    @PostMapping("/nuevoTipoTrabajador")
    public String crear(@RequestBody tipoTrabajadorEntity tipoTrabajador) {
        return tipoTrabajadorService.crearTipoTrabajador(tipoTrabajador);
    }

    @PostMapping("/eliminarTipoTrabajador/{id}")
    public String eliminarTipoTrabajador(@PathVariable int id) {
        return tipoTrabajadorService.eliminarTipoTrabajador(id);
    }

    @GetMapping("/tipoTrabajador/{descripcion}")
    public tipoTrabajadorEntity getByDescripcion(@PathVariable String descripcion) {
        return tipoTrabajadorService.getByDescripcion(descripcion);
    }
}
