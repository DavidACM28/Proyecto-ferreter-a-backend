package Final.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

import Final.Repositories.trabajadorRepository;
import Final.Security.JwtGenerador;
import Final.Entities.trabajadorEntity;

@Service
public class trabajadorService {

    @Autowired
    trabajadorRepository trabajadorRepository;

    @Autowired
    JwtGenerador jwtGenerador;

    public List<trabajadorEntity> listarTodos() {
        return trabajadorRepository.findAll();
    }

    public Page<trabajadorEntity> findAllPage(PageRequest pageable) {
        return trabajadorRepository.findAll(pageable);
    }

    public String crearTrabajador(trabajadorEntity trabajadorEntity) { 
        trabajadorRepository.save(trabajadorEntity);
        return "Trabajador creado con exito";
    }       
    
    public String editarTrabajador(trabajadorEntity trabajadorEntity) { 
        trabajadorRepository.save(trabajadorEntity);
        return "Trabajador editado con exito";
    }
    
    public String deshabilitarTrabajador(int id) { 
        trabajadorRepository.deshabilitarTrabajador(id);
        return "Trabajador deshabilitado con exito";
    }

    public String habilitarTrabajador(int id) { 
        trabajadorRepository.habilitarTrabajador(id);
        return "Trabajador habilitado con exito";
    }

    public trabajadorEntity findByToken(String token){
        String username = jwtGenerador.obtenerUsernameDeJwt(token);
        return trabajadorRepository.findByUser(username);
    }
}
