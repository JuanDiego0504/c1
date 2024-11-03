package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.repositorio.ProductoRepository;
import uniandes.edu.co.proyecto.repositorio.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Obtener todos los productos
    @GetMapping
    public Collection<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<String> addProducto(@RequestBody Producto producto) {
        // Verificar si la categoría existe
        if (producto.getCategoria() != null && !categoriaRepository.existsById(producto.getCategoria().getId())) {
            return new ResponseEntity<>("Categoría no existe", HttpStatus.BAD_REQUEST);
        }
        productoRepository.save(producto);
        return new ResponseEntity<>("Producto añadido exitosamente", HttpStatus.CREATED);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        if (!productoRepository.existsById(id)) {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
        
        // Verificar si la categoría existe
        if (producto.getCategoria() != null && !categoriaRepository.existsById(producto.getCategoria().getId())) {
            return new ResponseEntity<>("Categoría no existe", HttpStatus.BAD_REQUEST);
        }

        producto.setId(id); // Establecer el ID del producto existente
        productoRepository.save(producto);
        return new ResponseEntity<>("Producto actualizado exitosamente", HttpStatus.OK);
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        if (!productoRepository.existsById(id)) {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
        productoRepository.deleteById(id);
        return new ResponseEntity<>("Producto eliminado exitosamente", HttpStatus.OK);
    }
}
