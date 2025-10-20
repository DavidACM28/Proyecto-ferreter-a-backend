package Final.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Final.Entities.detalleVentaEntity;
import Final.Services.detalleVentaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    
}
