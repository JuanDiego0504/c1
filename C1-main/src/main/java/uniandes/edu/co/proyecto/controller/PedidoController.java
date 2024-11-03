package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Pedido;
import uniandes.edu.co.proyecto.modelo.PedidoPK;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;
import uniandes.edu.co.proyecto.repositorio.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.sql.Date;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public Collection<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id_producto}/{id_ordenDeCompra}/{fechaEntrega}/{estado}")
    public ResponseEntity<Pedido> getPedidoById(
            @PathVariable Integer id_producto, 
            @PathVariable Integer id_ordenDeCompra, 
            @PathVariable Date fechaEntrega, 
            @PathVariable String estado) {

        PedidoPK pk = new PedidoPK(new Producto(id_producto), new OrdenDeCompra(id_ordenDeCompra), fechaEntrega, estado);
        Optional<Pedido> pedido = pedidoRepository.findById(pk);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public ResponseEntity<String> crearPedido(@RequestBody Pedido pedido) {
        try {
            pedidoRepository.save(pedido);
            return new ResponseEntity<>("Pedido creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el pedido", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id_producto}/{id_ordenDeCompra}/{fechaEntrega}/{estado}/edit")
    public ResponseEntity<String> actualizarPedido(
            @PathVariable Integer id_producto,
            @PathVariable Integer id_ordenDeCompra,
            @PathVariable Date fechaEntrega,
            @PathVariable String estado,
            @RequestBody Pedido pedido) {

        PedidoPK pk = new PedidoPK(new Producto(id_producto), new OrdenDeCompra(id_ordenDeCompra), fechaEntrega, estado);

        if (!pedidoRepository.existsById(pk)) {
            return new ResponseEntity<>("Pedido no encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            pedido.setPk(pk);
            pedidoRepository.save(pedido);
            return new ResponseEntity<>("Pedido actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el pedido", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id_producto}/{id_ordenDeCompra}/{fechaEntrega}/{estado}/delete")
    public ResponseEntity<String> eliminarPedido(
            @PathVariable Integer id_producto,
            @PathVariable Integer id_ordenDeCompra,
            @PathVariable Date fechaEntrega,
            @PathVariable String estado) {

        PedidoPK pk = new PedidoPK(new Producto(id_producto), new OrdenDeCompra(id_ordenDeCompra), fechaEntrega, estado);

        if (!pedidoRepository.existsById(pk)) {
            return new ResponseEntity<>("Pedido no encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            pedidoRepository.deleteById(pk);
            return new ResponseEntity<>("Pedido eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el pedido", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
