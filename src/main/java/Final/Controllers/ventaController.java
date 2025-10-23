package Final.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Final.Services.ventaService;

import java.time.LocalDate;
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

    @GetMapping("/ventasMesActual")
    public Long ventasMesActual() {
        return ventaService.contarVentasMesActual();
    }
    @GetMapping("/ventasPage")
    public ResponseEntity<Page<ventaEntity>> listarVentas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {
        
        Page<ventaEntity> ventas = ventaService.findAll(page, size);
        return ResponseEntity.ok(ventas);
    }
    @GetMapping("/filtrar")
    public ResponseEntity<Page<ventaEntity>> filtrarVentas(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) String cliente,
            @RequestParam(required = false) String medioPago,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ventaEntity> resultado = ventaService.filtrarVentas(nombre, apellido, cliente, medioPago, fechaInicio, fechaFin, pageable);
        return ResponseEntity.ok(resultado);
    }
    
}
