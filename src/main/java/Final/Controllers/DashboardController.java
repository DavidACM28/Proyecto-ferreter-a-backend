package Final.Controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Final.Services.DashboardService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/ventas-por-rango")
    public Map<String, Object> getVentasPorRango(
            @RequestParam String inicio,
            @RequestParam String fin
    ) {
        LocalDateTime startDateTime = LocalDate.parse(inicio).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(fin).atTime(23, 59, 59, 999_999_999);
        return dashboardService.getVentasPorRango(startDateTime, endDateTime);
    }


    @GetMapping("/productos-mas-vendidos")
    public List<Map<String, Object>> getProductosMasVendidosPorCategoria() {
        return dashboardService.getProductosMasVendidosPorCategoria();
    }
    @GetMapping("/ventas-filtradas")
    public Map<String, Object> getVentasFiltradas(
            @RequestParam(required = false) String inicio,
            @RequestParam(required = false) String fin,
            @RequestParam(required = false) Long productoId,
            @RequestParam(required = false) Long categoriaId
    ) {
        LocalDateTime fechaInicio = null;
        LocalDateTime fechaFin = null;

        if (inicio != null && !inicio.isEmpty()) {
            fechaInicio = LocalDate.parse(inicio).atStartOfDay();
        }

        if (fin != null && !fin.isEmpty()) {
            // Incluye TODO el día final hasta 23:59:59.999999999
            fechaFin = LocalDate.parse(fin).atTime(23, 59, 59, 999_999_999);
        }

        return dashboardService.getVentasFiltradas(fechaInicio, fechaFin, productoId, categoriaId);
    }
}
