package Final.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import Final.Services.reporteService;
import java.util.*;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class reporteController {
    private final reporteService reporteService;

    @GetMapping("/resumen")
    public Map<String,Object> obtenerResumen() {
        return reporteService.obtenerResumen();
    }

    @GetMapping("/productos-mas-vendidos")
    public List<Map<String,Object>> productosMasVendidos() {
        return reporteService.obtenerTop6ProductosMasVendidos();
    }

    @GetMapping("/ventas-por-mes")
    public List<Map<String,Object>> ventasPorMes(@RequestParam(defaultValue = "6") int meses) {
        return reporteService.ventasPorMes(meses);
    }
}
