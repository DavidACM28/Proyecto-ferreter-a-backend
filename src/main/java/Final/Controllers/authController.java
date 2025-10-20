package Final.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Final.Entities.tipoTrabajadorEntity;
import Final.Entities.trabajadorEntity;
import Final.Repositories.trabajadorRepository;
import Final.Security.JwtGenerador;
import Final.Dtos.DtoAuthResponse;
import Final.Dtos.DtoLogin;
import Final.Dtos.DtoRegistro;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class authController {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private trabajadorRepository trabajadorRepository;
    private JwtGenerador jwtGenerador;

    public authController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, trabajadorRepository trabajadorRepository, JwtGenerador jwtGenerador) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.trabajadorRepository = trabajadorRepository;
        this.jwtGenerador = jwtGenerador;
    }

    @PostMapping("/registrarTrabajador")
    public ResponseEntity<String> registrar(@RequestBody DtoRegistro dtoRegistro) {
        if(trabajadorRepository.existsByUsuarioTrabajador(dtoRegistro.getUsername())) {
            return new ResponseEntity<>("El usuario ya existe", HttpStatus.BAD_REQUEST);
        }else {
            trabajadorEntity trabajador = new trabajadorEntity();
            trabajador.setUsuarioTrabajador(dtoRegistro.getUsername());
            trabajador.setContraseñaTrabajador(passwordEncoder.encode(dtoRegistro.getPassword()));
            trabajador.setNombreTrabajador(dtoRegistro.getNombre());
            trabajador.setApellidoTrabajador(dtoRegistro.getApellido());
            trabajador.setEstadoTrabajador(dtoRegistro.getEstado());
            tipoTrabajadorEntity tipoTrabajador = dtoRegistro.getTipoTrabajador(); 
            trabajador.setTipoTrabajador(tipoTrabajador);
            trabajadorRepository.save(trabajador);
            return new ResponseEntity<>("Registro de trabajador exitoso", HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<DtoAuthResponse> login(@RequestBody DtoLogin dtoLogin) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoLogin.getUsername(), dtoLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerador.generarToken(authentication);
        trabajadorEntity trabajador = trabajadorRepository.findByUser(dtoLogin.getUsername());
        return new ResponseEntity<>(new DtoAuthResponse(token, trabajador), HttpStatus.OK);
    }

}
