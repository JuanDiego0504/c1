package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Inventario;
import uniandes.edu.co.proyecto.modelo.InventarioPK;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.repositorio.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventarios")
public class InventarioController {

    @Autowired
    private InventarioRepository inventarioRepository;

    // Obtener todos los inventarios
    @GetMapping
    public Collection<Inventario> getInventarios() {
        return inventarioRepository.findAll();
    }

    // Obtener inventario por ID de producto y bodega
    @GetMapping("/{id_producto}/{id_bodega}")
    public ResponseEntity<Inventario> getInventarioById(@PathVariable Integer id_producto, @PathVariable Integer id_bodega) {
        InventarioPK pk = new InventarioPK();
        pk.setId_producto(new Producto(id_producto)); // Asegúrate de que Producto tenga un constructor que acepte id
        pk.setId_bodega(new Bodega(id_bodega));       // Asegúrate de que Bodega tenga un constructor que acepte id
        Optional<Inventario> inventario = inventarioRepository.findById(pk);
        return inventario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo inventario
    @PostMapping("/new")
    public ResponseEntity<String> crearInventario(@RequestBody Inventario inventario) {
        try {
            inventarioRepository.save(inventario);
            return new ResponseEntity<>("Inventario creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el inventario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un inventario existente
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

    // Eliminar un inventario
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

    // Endpoint para RFC3: Obtener el inventario de productos en una bodega de una sucursal específica
    @GetMapping("/bodega")
    public ResponseEntity<List<Inventario>> getInventarioPorSucursalYBodega(
            @RequestParam("sucursalId") Integer sucursalId,
            @RequestParam("bodegaId") Integer bodegaId) {
        List<Inventario> inventario = inventarioRepository.findInventarioPorSucursalYBodega(sucursalId, bodegaId);
        if (inventario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(inventario, HttpStatus.OK);
    }

    // Endpoint para RFC4: Obtener sucursales con disponibilidad de un producto por id o nombre
    @GetMapping("/disponibilidad")
    public ResponseEntity<List<Sucursal>> getSucursalesConDisponibilidadDeProducto(
            @RequestParam(value = "idProducto", required = false) Integer idProducto,
            @RequestParam(value = "nombreProducto", required = false) String nombreProducto) {
        List<Sucursal> sucursales = inventarioRepository.findSucursalesConDisponibilidadDeProducto(idProducto, nombreProducto);
        if (sucursales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sucursales, HttpStatus.OK);
    }
}