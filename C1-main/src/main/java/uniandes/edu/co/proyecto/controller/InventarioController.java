package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Inventario;
import uniandes.edu.co.proyecto.modelo.InventarioPK;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.repositorio.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/inventarios")
public class InventarioController {

    @Autowired
    private InventarioRepository inventarioRepository;

    @GetMapping
    public Collection<Inventario> getInventarios() {
        return inventarioRepository.findAll();
    }

    @GetMapping("/{id_producto}/{id_bodega}")
    public ResponseEntity<Inventario> getInventarioById(@PathVariable Integer id_producto, @PathVariable Integer id_bodega) {
        InventarioPK pk = new InventarioPK();
        pk.setId_producto(new Producto(id_producto)); // Asegúrate de que Producto tenga un constructor que acepte id
        pk.setId_bodega(new Bodega(id_bodega));       // Asegúrate de que Bodega tenga un constructor que acepte id
        Optional<Inventario> inventario = inventarioRepository.findById(pk);
        return inventario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public ResponseEntity<String> crearInventario(@RequestBody Inventario inventario) {
        try {
            inventarioRepository.save(inventario);
            return new ResponseEntity<>("Inventario creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el inventario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id_producto}/{id_bodega}/edit")
    public ResponseEntity<String> actualizarInventario(
            @PathVariable Integer id_producto,
            @PathVariable Integer id_bodega,
            @RequestBody Inventario inventario) {
        
        InventarioPK pk = new InventarioPK();
        pk.setId_producto(new Producto(id_producto));
        pk.setId_bodega(new Bodega(id_bodega));
        
        if (!inventarioRepository.existsById(pk)) {
            return new ResponseEntity<>("Inventario no encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            inventario.setPk(pk);
            inventarioRepository.save(inventario);
            return new ResponseEntity<>("Inventario actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el inventario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id_producto}/{id_bodega}/delete")
    public ResponseEntity<String> eliminarInventario(@PathVariable Integer id_producto, @PathVariable Integer id_bodega) {
        InventarioPK pk = new InventarioPK();
        pk.setId_producto(new Producto(id_producto));
        pk.setId_bodega(new Bodega(id_bodega));

        if (!inventarioRepository.existsById(pk)) {
            return new ResponseEntity<>("Inventario no encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            inventarioRepository.deleteById(pk);
            return new ResponseEntity<>("Inventario eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el inventario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
