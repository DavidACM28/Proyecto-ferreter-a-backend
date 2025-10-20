package Final.Services;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import Final.Entities.auditoriaInventarioEntity;
import Final.Repositories.auditoriaInventarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.stream.Collectors;
import java.util.Comparator;

@Service
public class auditoriaInventarioService {


    @Autowired
    private auditoriaInventarioRepository auditoriaInventarioRepository;

    public String crear(auditoriaInventarioEntity auditoriaInventarioEntity) {
        auditoriaInventarioRepository.save(auditoriaInventarioEntity);
        return "Auditoria Inventario creada con exito";
    }

    public Page<auditoriaInventarioEntity> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idAuditoriaInventario").descending());
        return auditoriaInventarioRepository.findAll(pageable);
    }

    

public Page<auditoriaInventarioEntity> filtrarAuditorias(
        String producto,
        String tipo,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        Pageable pageable
) {
    List<auditoriaInventarioEntity> auditorias = auditoriaInventarioRepository.findAll();

    // 🔍 Filtro por producto
    if (producto != null && !producto.isEmpty()) {
        auditorias = auditorias.stream()
            .filter(a -> a.getProducto().getNombreProducto().toLowerCase().contains(producto.toLowerCase()))
            .collect(Collectors.toList());
    }

    // 🔍 Filtro por tipo
    if (tipo != null && !tipo.isEmpty()) {
        auditorias = auditorias.stream()
            .filter(a -> {
                boolean esEntrada = a.getCantidadNueva() > a.getCantidadAnterior();
                return tipo.equalsIgnoreCase("entrada") ? esEntrada : !esEntrada;
            })
            .collect(Collectors.toList());
    }

    // 🔍 Filtro por fecha
    if (fechaInicio != null && fechaFin != null) {
        auditorias = auditorias.stream()
            .filter(a -> {
                LocalDate fecha = a.getFecha().toLocalDate();
                return !fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin);
            })
            .collect(Collectors.toList());
    }

    // 🔄 Ordenar por fecha descendente (más recientes primero)
    auditorias.sort(Comparator.comparing(auditoriaInventarioEntity::getFecha).reversed());

    // 📄 Paginación manual
    int start = (int) pageable.getOffset();
    int end = Math.min((start + pageable.getPageSize()), auditorias.size());
    List<auditoriaInventarioEntity> paginated = auditorias.subList(start, end);

    return new PageImpl<>(paginated, pageable, auditorias.size());
}

}
