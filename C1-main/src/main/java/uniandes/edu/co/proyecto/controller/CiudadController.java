package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Ciudad;
import uniandes.edu.co.proyecto.repositorio.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping
    public Collection<Ciudad> getCiudades() {
        return ciudadRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> getCiudadById(@PathVariable Integer id) {
        Optional<Ciudad> ciudad = ciudadRepository.findById(id);
        return ciudad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public ResponseEntity<String> crearCiudad(@RequestBody Ciudad ciudad) {
        try {
            ciudadRepository.save(ciudad);
            return new ResponseEntity<>("Ciudad creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<String> actualizarCiudad(@PathVariable Integer id, @RequestBody Ciudad ciudad) {
        if (!ciudadRepository.existsById(id)) {
            return new ResponseEntity<>("Ciudad no encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            ciudad.setId(id);
            ciudadRepository.save(ciudad);
            return new ResponseEntity<>("Ciudad actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarCiudad(@PathVariable Integer id) {
        if (!ciudadRepository.existsById(id)) {
            return new ResponseEntity<>("Ciudad no encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            ciudadRepository.deleteById(id);
            return new ResponseEntity<>("Ciudad eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
