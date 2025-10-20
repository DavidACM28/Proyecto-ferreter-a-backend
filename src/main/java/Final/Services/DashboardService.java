package Final.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Final.Entities.ventaEntity;
import Final.Repositories.ventaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ventaRepository ventaRepository;

    public Map<String, Object> getVentasPorRango(LocalDateTime inicio, LocalDateTime fin) {
        // ✅ Ajuste: sumar un día al fin para incluir todo el último día
        LocalDateTime finAjustado = fin.plusDays(1);

        List<ventaEntity> ventas = ventaRepository.findVentasBetweenDates(inicio, finAjustado);

        double totalVentas = ventas.stream()
                .mapToDouble(ventaEntity::getTotalVenta)
                .sum();

        int cantidadVentas = ventas.size();

        // Agrupamos ventas por día
        Map<LocalDate, Double> ventasPorDia = ventas.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getFechaVenta().toLocalDate(),
                        Collectors.summingDouble(ventaEntity::getTotalVenta)
                ));

        // Convertimos a lista ordenada para el frontend
        List<Map<String, Object>> detalleVentas = ventasPorDia.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("fecha", entry.getKey().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    map.put("total", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("totalVentas", totalVentas);
        response.put("cantidadVentas", cantidadVentas);
        response.put("detalleVentas", detalleVentas);

        return response;
    }

    public List<Map<String, Object>> getProductosMasVendidosPorCategoria() {
        return ventaRepository.findProductosMasVendidosPorCategoria();
    }

    public Map<String, Object> getVentasFiltradas(LocalDateTime inicio, LocalDateTime fin, Long productoId, Long categoriaId) {
        // ✅ Mismo ajuste para los filtros
        //LocalDateTime finAjustado = fin != null ? fin.plusDays(1) : null;
        System.out.println("Parámetro FIN enviado al repositorio: " + fin);
        List<ventaEntity> ventas = ventaRepository.findVentasFiltradas(inicio, fin, productoId, categoriaId);

        double totalVentas = ventas.stream().mapToDouble(v -> v.getTotalVenta()).sum();

        Map<LocalDate, Double> ventasPorDia = ventas.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getFechaVenta().toLocalDate(),
                        Collectors.summingDouble(v -> v.getTotalVenta())
                ));

        List<Map<String, Object>> detalleVentas = ventasPorDia.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("fecha", entry.getKey().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    map.put("total", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());

        return Map.of(
                "totalVentas", totalVentas,
                "detalleVentas", detalleVentas
        );
    }
}
