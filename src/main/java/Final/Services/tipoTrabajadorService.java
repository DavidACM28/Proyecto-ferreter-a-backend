package Final.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import Final.Repositories.tipoTrabajadorRepository;
import Final.Entities.tipoTrabajadorEntity;

@Service    
public class tipoTrabajadorService {

    @Autowired
    tipoTrabajadorRepository tipoTrabajadorRepository;

    public List<tipoTrabajadorEntity> findAll() {
        return tipoTrabajadorRepository.findAll();
    }

    public String crearTipoTrabajador(tipoTrabajadorEntity tipoTrabajadorEntity) { 
        tipoTrabajadorRepository.save(tipoTrabajadorEntity);
        return "TipoTrabajador creado con exito";
    }

    public String editarTipoTrabajador(tipoTrabajadorEntity tipoTrabajadorEntity) { 
        tipoTrabajadorRepository.save(tipoTrabajadorEntity);
        return "TipoTrabajador editado con exito";
    }

    public String eliminarTipoTrabajador (int id) { 
        tipoTrabajadorRepository.deleteById(id);
        return "TipoTrabajador eliminado con exito";
    }

    public tipoTrabajadorEntity getByDescripcion(String descripcion){
        return tipoTrabajadorRepository.findPorDescripcion(descripcion);
    }
}
