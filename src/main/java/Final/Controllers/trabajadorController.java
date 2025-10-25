package Final.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Final.Entities.trabajadorEntity;

import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import Final.Services.trabajadorService;

@RestController
@RequestMapping("/trabajador")
@CrossOrigin
public class trabajadorController {

    @Autowired
    private trabajadorService trabajadorService;
    private PasswordEncoder passwordEncoder;

    public trabajadorController(trabajadorService trabajadorService, PasswordEncoder passwordEncoder) {
        this.trabajadorService = trabajadorService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/trabajadores")
    public List<trabajadorEntity> listarTodos() {
        return trabajadorService.listarTodos();
    }

    @PostMapping("/editarTrabajador")
    public String editar(@RequestBody trabajadorEntity trabajador) {
        trabajadorEntity trabajador1 = new trabajadorEntity();
        trabajador1.setIdTrabajador(trabajador.getIdTrabajador());
        trabajador1.setTipoTrabajador(trabajador.getTipoTrabajador());
        trabajador1.setNombreTrabajador(trabajador.getNombreTrabajador());
        trabajador1.setApellidoTrabajador(trabajador.getApellidoTrabajador());
        trabajador1.setUsuarioTrabajador(trabajador.getUsuarioTrabajador());
        trabajador1.setContraseñaTrabajador(passwordEncoder.encode(trabajador.getContraseñaTrabajador()));
        trabajador1.setEstadoTrabajador(trabajador.isEstadoTrabajador());
        return trabajadorService.editarTrabajador(trabajador1);
    }

    @PostMapping("/deshabilitarTrabajador/{id}")
    public String deshabilitarTrabajador(@PathVariable int id) {
        return trabajadorService.deshabilitarTrabajador(id);
    }
    
    @PostMapping("/habilitarTrabajador/{id}")
    public String habilitarTrabajador(@PathVariable int id) {
        return trabajadorService.habilitarTrabajador(id);
    }
    

    @GetMapping("/trabajador/{token}")
    public trabajadorEntity getByToken(@PathVariable String token) {
        return trabajadorService.findByToken(token);
    }

    @GetMapping("/trabajador/trabajadores/{page}")
    public Page<trabajadorEntity> getAll(@PathVariable int page) {
        final PageRequest pageable = PageRequest.of(page, 7);
        return trabajadorService.findAllPage(pageable);
    }

    

}
