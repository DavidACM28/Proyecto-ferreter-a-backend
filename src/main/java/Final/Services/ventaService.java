package Final.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Final.Repositories.ventaRepository;
import Final.Entities.ventaEntity;

@Service
public class ventaService {

    @Autowired
    ventaRepository ventaRepository;

    public List<ventaEntity> findAll() {
        return ventaRepository.findAll();
    }

    public ventaEntity crearVenta(ventaEntity ventaEntity) {
        return ventaRepository.save(ventaEntity);
    }

    public Long contarVentasMesActual() {
        return ventaRepository.contarVentasMesActual();
    }
    public Page<ventaEntity> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idVenta").descending());
        return ventaRepository.findAll(pageable);
    }

    public Page<ventaEntity> filtrarVentas(
            String nombre,
            String apellido,
            String cliente,
            String medioPago,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            Pageable pageable) {
        List<ventaEntity> ventas = ventaRepository.findAll();

        // 🔍 Filtro por producto
        if (nombre != null && !nombre.isEmpty()) {
            ventas = ventas.stream()
                    .filter(v -> v.getTrabajador().getNombreTrabajador().toLowerCase().contains(nombre.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (apellido != null && !apellido.isEmpty()) {
            ventas = ventas.stream()
                    .filter(v -> v.getTrabajador().getApellidoTrabajador().toLowerCase().contains(apellido.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (cliente != null && !cliente.isEmpty()) {
            ventas = ventas.stream()
                    .filter(v -> v.getClienteVenta().toLowerCase().contains(cliente.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (medioPago != null && !medioPago.isEmpty()) {
            ventas = ventas.stream()
                    .filter(v -> v.getMedioPagoVenta().toLowerCase().contains(medioPago.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // 🔍 Filtro por fecha
        if (fechaInicio != null && fechaFin != null) {
            ventas = ventas.stream()
                    .filter(v -> {
                        LocalDate fecha = v.getFechaVenta().toLocalDate();
                        return !fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin);
                    })
                    .collect(Collectors.toList());
        }

        // 🔄 Ordenar por fecha descendente (más recientes primero)
        ventas.sort(Comparator.comparing(ventaEntity::getFechaVenta).reversed());

        // 📄 Paginación manual
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), ventas.size());
        List<ventaEntity> paginated = ventas.subList(start, end);

        return new PageImpl<>(paginated, pageable, ventas.size());
    }

}
