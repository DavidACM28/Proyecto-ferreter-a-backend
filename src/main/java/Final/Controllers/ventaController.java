package Final.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Final.Services.ventaService;
import java.util.List;
import Final.Entities.ventaEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/venta")
@CrossOrigin
public class ventaController {

    @Autowired
    private ventaService ventaService;

    @GetMapping("/ventas")
    public List<ventaEntity> getAll() {
        return ventaService.findAll();
    }

    @PostMapping("/nuevoVenta")
    public ventaEntity crear(@RequestBody ventaEntity venta) {
        return ventaService.crearVenta(venta);
    }
    
}
