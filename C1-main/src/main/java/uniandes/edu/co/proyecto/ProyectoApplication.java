package uniandes.edu.co.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repositorio.ProductoRepository;

import java.util.Collection;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProyectoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Obtener todos los productos y mostrarlos en la consola
        Collection<Producto> productos = productoRepository.findAll();
        productos.forEach(System.out::println);
    }
}
