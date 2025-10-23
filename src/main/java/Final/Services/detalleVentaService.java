package Final.Services;

import java.util.List;

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

    public List<detalleVentaEntity> findByVentaId(int idVenta){
        return detalleVentaRepository.findByIdVenta(idVenta);
    }
}
