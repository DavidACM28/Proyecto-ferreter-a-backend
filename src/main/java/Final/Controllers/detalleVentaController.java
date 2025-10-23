package Final.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Final.Entities.detalleVentaEntity;
import Final.Services.detalleVentaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/detalleVenta")
@CrossOrigin
public class detalleVentaController {

    @Autowired
    detalleVentaService detalleVentaService;

    @PostMapping("/crear")
    public detalleVentaEntity crear(@RequestBody detalleVentaEntity entity) {
        return detalleVentaService.crearDetalleVenta(entity);
    }

    @GetMapping("/detalles/{idVenta}")
    public List<detalleVentaEntity> getMethodName(@PathVariable int idVenta) {
        return detalleVentaService.findByVentaId(idVenta);
    }
    
    
}
