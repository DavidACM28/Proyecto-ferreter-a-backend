package Final.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

}
