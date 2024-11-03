package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;
import uniandes.edu.co.proyecto.repositorio.OrdenDeCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/ordenesDeCompra")
public class OrdenDeCompraController {

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    // Listar todas las órdenes de compra
    @GetMapping
    public Collection<OrdenDeCompra> getOrdenesDeCompra() {
        return ordenDeCompraRepository.findAll();
    }

    // Obtener una orden de compra por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenDeCompra> getOrdenDeCompraById(@PathVariable Integer id) {
        Optional<OrdenDeCompra> ordenDeCompra = ordenDeCompraRepository.findById(id);
        return ordenDeCompra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva orden de compra
    @PostMapping("/new")
    public ResponseEntity<String> crearOrdenDeCompra(@RequestBody OrdenDeCompra ordenDeCompra) {
        try {
            ordenDeCompra.setEstado("vigente"); // Estado inicial
            ordenDeCompraRepository.save(ordenDeCompra);
            return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de compra", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una orden de compra existente
    @PutMapping("/{id}/edit")
    public ResponseEntity<String> actualizarOrdenDeCompra(@PathVariable Integer id, @RequestBody OrdenDeCompra ordenDeCompra) {
        if (!ordenDeCompraRepository.existsById(id)) {
            return new ResponseEntity<>("Orden de compra no encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            ordenDeCompra.setId(id);
            ordenDeCompraRepository.save(ordenDeCompra);
            return new ResponseEntity<>("Orden de compra actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la orden de compra", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una orden de compra
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarOrdenDeCompra(@PathVariable Integer id) {
        if (!ordenDeCompraRepository.existsById(id)) {
            return new ResponseEntity<>("Orden de compra no encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            ordenDeCompraRepository.deleteById(id);
            return new ResponseEntity<>("Orden de compra eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la orden de compra", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Anular una orden de compra (cambio de estado a "anulada")
    @PutMapping("/{id}/anular")
    public ResponseEntity<String> anularOrdenDeCompra(@PathVariable Integer id) {
        Optional<OrdenDeCompra> ordenDeCompraOptional = ordenDeCompraRepository.findById(id);
        if (ordenDeCompraOptional.isEmpty()) {
            return new ResponseEntity<>("Orden de compra no encontrada", HttpStatus.NOT_FOUND);
        }
        
        OrdenDeCompra ordenDeCompra = ordenDeCompraOptional.get();
        if ("entregada".equalsIgnoreCase(ordenDeCompra.getEstado())) {
            return new ResponseEntity<>("La orden de compra ya fue entregada y no se puede anular", HttpStatus.BAD_REQUEST);
        }
        
        ordenDeCompra.setEstado("anulada");
        ordenDeCompraRepository.save(ordenDeCompra);
        return new ResponseEntity<>("Orden de compra anulada exitosamente", HttpStatus.OK);
    }
}
