package Final.Controllers;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Final.Entities.auditoriaInventarioEntity;
import Final.Services.auditoriaInventarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.data.domain.Pageable;



@RestController
@RequestMapping("/auditoria")
@CrossOrigin
public class auditoriaInventarioController {

    @Autowired
    auditoriaInventarioService auditoriaInventarioService;

    @PostMapping("/crear")
    public String crear (@RequestBody auditoriaInventarioEntity entity) {
        return auditoriaInventarioService.crear(entity);
    }

    @GetMapping("/auditorias")
    public ResponseEntity<Page<auditoriaInventarioEntity>> listarAuditorias(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {
        
        Page<auditoriaInventarioEntity> auditorias = auditoriaInventarioService.findAll(page, size);
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<Page<auditoriaInventarioEntity>> filtrarAuditorias(
            @RequestParam(required = false) String producto,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<auditoriaInventarioEntity> resultado = auditoriaInventarioService.filtrarAuditorias(producto, tipo, fechaInicio, fechaFin, pageable);
        return ResponseEntity.ok(resultado);
    }
    
    
}
