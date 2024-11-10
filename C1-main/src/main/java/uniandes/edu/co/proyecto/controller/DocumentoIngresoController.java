package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.DocumentoIngreso;
import uniandes.edu.co.proyecto.repositorio.DocumentoIngresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/documentos-ingreso")
public class DocumentoIngresoController {

    @Autowired
    private DocumentoIngresoRepository documentoIngresoRepository;

    // Endpoint para obtener documentos de ingreso según RFC6 (nivel de aislamiento SERIALIZABLE)
    @GetMapping
    public ResponseEntity<?> getDocumentosIngreso(@RequestParam Long sucursalId, @RequestParam Long bodegaId) {
        try {
            // Imprimir la fecha actual del servidor de aplicaciones
            System.out.println("Fecha actual del servidor de aplicaciones: " + new java.util.Date());

            // Temporizador de 30 segundos para simular pruebas de concurrencia
            Thread.sleep(30000);

            List<Object[]> resultados = documentoIngresoRepository.findDocumentosIngresoRecientes(sucursalId, bodegaId);

            if (resultados.isEmpty()) {
                return new ResponseEntity<>("No se encontraron documentos de ingreso para la sucursal y bodega especificadas en los últimos 30 días.", HttpStatus.NOT_FOUND);
            }

            // Convertir los resultados a una lista de mapas
            List<Map<String, Object>> documentos = new ArrayList<>();
            for (Object[] obj : resultados) {
                Map<String, Object> documento = new HashMap<>();
                documento.put("sucursalNombre", obj[0]);
                documento.put("bodegaNombre", obj[1]);
                documento.put("numeroDocumento", obj[2]);
                documento.put("fecha", obj[3]);
                documento.put("proveedorNombre", obj[4]);
                documentos.add(documento);
            }

            return new ResponseEntity<>(documentos, HttpStatus.OK);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new ResponseEntity<>("La operación fue interrumpida.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (UnexpectedRollbackException e) {
            // Manejo específico para el rollback en caso de error en la transacción
            return new ResponseEntity<>("No se pudo realizar la consulta debido a un conflicto de transacción. Por favor, inténtelo de nuevo.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Manejo general de excepciones
            return new ResponseEntity<>("Ocurrió un error al procesar la solicitud. Inténtelo de nuevo.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para obtener documentos de ingreso según RFC7 (nivel de aislamiento READ COMMITTED)
    @GetMapping("/read-committed")
    public ResponseEntity<?> getDocumentosIngresoReadCommitted(@RequestParam Long sucursalId, @RequestParam Long bodegaId) {
        try {
            // Imprimir la fecha actual del servidor de aplicaciones
            System.out.println("Fecha actual del servidor de aplicaciones: " + new java.util.Date());

            // Temporizador de 30 segundos para simular pruebas de concurrencia
            Thread.sleep(30000);

            List<Object[]> resultados = documentoIngresoRepository.findDocumentosIngresoRecientesReadCommitted(sucursalId, bodegaId);

            if (resultados.isEmpty()) {
                return new ResponseEntity<>("No se encontraron documentos de ingreso para la sucursal y bodega especificadas en los últimos 30 días.", HttpStatus.NOT_FOUND);
            }

            // Convertir los resultados a una lista de mapas
            List<Map<String, Object>> documentos = new ArrayList<>();
            for (Object[] obj : resultados) {
                Map<String, Object> documento = new HashMap<>();
                documento.put("sucursalNombre", obj[0]);
                documento.put("bodegaNombre", obj[1]);
                documento.put("numeroDocumento", obj[2]);
                documento.put("fecha", obj[3]);
                documento.put("proveedorNombre", obj[4]);
                documentos.add(documento);
            }

            return new ResponseEntity<>(documentos, HttpStatus.OK);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new ResponseEntity<>("La operación fue interrumpida.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (UnexpectedRollbackException e) {
            // Manejo específico para el rollback en caso de error en la transacción
            return new ResponseEntity<>("No se pudo realizar la consulta debido a un conflicto de transacción. Por favor, inténtelo de nuevo.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Manejo general de excepciones
            return new ResponseEntity<>("Ocurrió un error al procesar la solicitud. Inténtelo de nuevo.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint de prueba para verificar la conexión a la base de datos
    @GetMapping("/test")
    public ResponseEntity<?> testDatabaseConnection() {
        List<DocumentoIngreso> documentos = documentoIngresoRepository.findAll();
        System.out.println("Documentos de ingreso en la base de datos:");
        for (DocumentoIngreso doc : documentos) {
            System.out.println("ID: " + doc.getId() + ", Numero Documento: " + doc.getNumeroDocumento() + ", Fecha: " + doc.getFecha());
        }
        return new ResponseEntity<>("Consulta de prueba realizada.", HttpStatus.OK);
    }

    // Obtener todos los documentos de ingreso
    @GetMapping("/all")
    public ResponseEntity<List<DocumentoIngreso>> getAllDocumentosIngreso() {
        List<DocumentoIngreso> documentos = documentoIngresoRepository.findAll();
        if (documentos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(documentos, HttpStatus.OK);
    }

    // Crear un nuevo documento de ingreso
    @PostMapping("/new")
    public ResponseEntity<String> crearDocumentoIngreso(@RequestBody DocumentoIngreso documentoIngreso) {
        try {
            documentoIngresoRepository.save(documentoIngreso);
            return new ResponseEntity<>("Documento de ingreso creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el documento de ingreso", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un documento de ingreso existente
    @PutMapping("/{id}/edit")
    public ResponseEntity<String> actualizarDocumentoIngreso(
            @PathVariable Long id,
            @RequestBody DocumentoIngreso documentoIngreso) {

        if (!documentoIngresoRepository.existsById(id)) {
            return new ResponseEntity<>("Documento de ingreso no encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            documentoIngreso.setId(id);
            documentoIngresoRepository.save(documentoIngreso);
            return new ResponseEntity<>("Documento de ingreso actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el documento de ingreso", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un documento de ingreso
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarDocumentoIngreso(@PathVariable Long id) {
        if (!documentoIngresoRepository.existsById(id)) {
            return new ResponseEntity<>("Documento de ingreso no encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            documentoIngresoRepository.deleteById(id);
            return new ResponseEntity<>("Documento de ingreso eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el documento de ingreso", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
