package Final.Dtos;

import Final.Entities.trabajadorEntity;
import lombok.Data;

@Data
public class DtoAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer ";
    private trabajadorEntity trabajador;

    public DtoAuthResponse(String accessToken, trabajadorEntity user) {
        this.accessToken = accessToken;
        this.trabajador = user;
    }

    public DtoAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
