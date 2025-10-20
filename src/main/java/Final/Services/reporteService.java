package Final.Services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import Final.Repositories.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class reporteService {
    private final ventaRepository ventaRepository;
    private final productoRepository productoRepository;
    private final detalleVentaRepository detalleVentaRepository;

    public Map<String, Object> obtenerResumen() {
        Double ventasTotales = ventaRepository.sumTotalVentas();
        Long totalProductos = productoRepository.count();
        Map<String, Object> res = new HashMap<>();
        res.put("ventasTotales", ventasTotales != null ? ventasTotales : 0.0);
        res.put("totalProductos", totalProductos);
        return res;
    }

    public List<Map<String, Object>> obtenerTop6ProductosMasVendidos() {
        List<Object[]> rows = detalleVentaRepository.findTop6ProductosMasVendidosNative();
        List<Map<String,Object>> result = new ArrayList<>();
        for(Object[] r : rows){
            Map<String,Object> m = new HashMap<>();
            m.put("idProducto", ((Number) r[0]).intValue());
            m.put("nombreProducto", r[1]);
            m.put("totalVendido", ((Number) r[2]).longValue());
            result.add(m);
        }
        return result;
    }

    public List<Map<String,Object>> ventasPorMes(int meses) {
        List<Object[]> rows = ventaRepository.findVentasPorMes(meses);
        List<Map<String,Object>> out = new ArrayList<>();
        for(Object[] r : rows) {
            Integer year = ((Number) r[0]).intValue();
            Integer month = ((Number) r[1]).intValue();
            Double total = ((Number) r[2]).doubleValue();
            Map<String,Object> m = new HashMap<>();
            m.put("year", year);
            m.put("month", month);
            m.put("total", total);
            out.add(m);
        }
        return out;
    }
}
