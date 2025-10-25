package Final.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import Final.Repositories.productoRepository;
import Final.Entities.productoEntity;

@Service
public class productoService {

    @Autowired
    productoRepository productoRepository;

    public productoEntity crearProducto(productoEntity productoEntity) { 
        return productoRepository.save(productoEntity);
    }

    public String editarProducto(productoEntity productoEntity){
        productoRepository.save(productoEntity);
        return "Producto editado con exito";
    }

    public List<productoEntity> findAll() {
        return productoRepository.traerHabilitados();
    }

    public List<productoEntity> finDeshabilitados() {
        return productoRepository.traerDeshabilitados();
    }

    public List<productoEntity> obtenerTop6ProductosMasVendidos() {
        return productoRepository.findTop6ProductosMasVendidos();
    }

    public List<productoEntity> traerPorCategoria(String categoria) {
        return productoRepository.traerPorCategoria(categoria);
    }

    public List<productoEntity> traerPorNombre(String nombre) {
        return productoRepository.traerPorNombre(nombre);
    }

    public productoEntity traerPorId(int id){
        return productoRepository.traerPorId(id);
    }

    public String deshabilitarProducto(int id){
        productoRepository.desHabilitarProducto(id);
        return "Producto deshabilitado con exito";
    }

    public String habilitarProducto(int id){
        productoRepository.habilitarProducto(id);
        return "Producto habilitado con exito";
    }
}
