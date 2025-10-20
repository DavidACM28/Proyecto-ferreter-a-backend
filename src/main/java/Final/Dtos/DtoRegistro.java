package Final.Dtos;

import Final.Entities.tipoTrabajadorEntity;
import lombok.Data;

@Data
public class DtoRegistro {
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private boolean estado;
    tipoTrabajadorEntity tipoTrabajador;

    public boolean getEstado() {
        return estado;
    }
}
