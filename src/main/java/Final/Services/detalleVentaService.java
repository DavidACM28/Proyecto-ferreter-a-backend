package Final.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Final.Entities.detalleVentaEntity;
import Final.Repositories.detalleVentaRepository;

@Service
public class detalleVentaService {

    @Autowired
    detalleVentaRepository detalleVentaRepository;

    public detalleVentaEntity crearDetalleVenta(detalleVentaEntity detalleVentaEntity) {
        return detalleVentaRepository.save(detalleVentaEntity);
    }
}
