package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.repositorio.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/bodegas")
public class BodegaController {

    @Autowired
    private BodegaRepository bodegaRepository;

    // Obtener todas las bodegas
    @GetMapping
    public Collection<Bodega> getBodegas() {
        return bodegaRepository.findAll();
    }

    // Obtener una bodega por ID
    @GetMapping("/{id}")
    public ResponseEntity<Bodega> getBodegaById(@PathVariable Integer id) {
        Optional<Bodega> bodega = bodegaRepository.findById(id);
        return bodega.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva bodega
    @PostMapping("/new")
    public ResponseEntity<String> crearBodega(@RequestBody Bodega bodega) {
        try {
            bodegaRepository.save(bodega);
            return new ResponseEntity<>("Bodega creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una bodega existente
    @PutMapping("/{id}/edit")
    public ResponseEntity<String> actualizarBodega(@PathVariable Integer id, @RequestBody Bodega bodega) {
        if (!bodegaRepository.existsById(id)) {
            return new ResponseEntity<>("Bodega no encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            bodega.setId(id); // Asegura que la entidad tenga el ID correcto
            bodegaRepository.save(bodega);
            return new ResponseEntity<>("Bodega actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una bodega
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarBodega(@PathVariable Integer id) {
        if (!bodegaRepository.existsById(id)) {
            return new ResponseEntity<>("Bodega no encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            bodegaRepository.deleteById(id);
            return new ResponseEntity<>("Bodega eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // RFC1: Obtener el índice de ocupación de cada bodega de una sucursal
    @GetMapping("/ocupacion")
    public ResponseEntity<?> obtenerIndiceOcupacion(@RequestParam Integer sucursalId) {
        try {
            Collection<Object[]> resultados = bodegaRepository.calcularIndiceOcupacion(sucursalId);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener el índice de ocupación: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
