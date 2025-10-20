package Final.Security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import Final.Entities.tipoTrabajadorEntity;
import Final.Entities.trabajadorEntity;
import Final.Repositories.trabajadorRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
@CrossOrigin
public class CustomUsersDetailsService implements UserDetailsService{

    @Autowired
    private trabajadorRepository trabajadorRepository;

    public CustomUsersDetailsService(trabajadorRepository trabajadorRepository) {
        this.trabajadorRepository = trabajadorRepository;
    }

    public Collection<GrantedAuthority> mapAuthorities(List<tipoTrabajadorEntity> tipoTrabajador) {
        return tipoTrabajador.stream().map(tipo -> new SimpleGrantedAuthority(tipo.getDescripcionTipoTrabajador())).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        trabajadorEntity trabajador = trabajadorRepository.findByUsuarioTrabajador(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        List<tipoTrabajadorEntity> tipoTrabajador = new ArrayList<tipoTrabajadorEntity>();
        tipoTrabajador.add(trabajador.getTipoTrabajador());
        return new User(trabajador.getUsuarioTrabajador(), trabajador.getContraseñaTrabajador(), mapAuthorities(tipoTrabajador));
    }

    
}
