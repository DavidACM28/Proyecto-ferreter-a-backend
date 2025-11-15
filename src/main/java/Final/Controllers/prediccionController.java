package Final.Controllers;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prediccion")
public class prediccionController {
    @GetMapping("/compras")
    public ResponseEntity<String> obtenerPredicciones() {
        try {
            // Crear cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Crear la solicitud GET
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://ventas-prediccion-flask.onrender.com/predict"))
                    .GET()
                    .build();

            // Enviar solicitud y obtener respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Retornar respuesta tal cual al frontend
            return ResponseEntity.ok(response.body());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Error al obtener predicciones: " + e.getMessage());
        }
    }
}
