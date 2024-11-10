package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.proyecto.modelo.DocumentoIngreso;

import java.util.List;

public interface DocumentoIngresoRepository extends JpaRepository<DocumentoIngreso, Long> {

    // Método para RFC6 con nivel de aislamiento SERIALIZABLE
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Query(value = "SELECT s.nombre AS sucursal_nombre, " +
                   "       b.nombre AS bodega_nombre, " +
                   "       d.numero_documento, " +
                   "       d.fecha, " +
                   "       p.nombre AS proveedor_nombre " +
                   "FROM DOCUMENTOS_INGRESO d " +
                   "JOIN BODEGAS b ON d.bodega_id = b.id " +
                   "JOIN SUCURSALES s ON b.sucursal = s.id " +
                   "JOIN PROVEEDORES p ON d.proveedor_id = p.id " +
                   "WHERE d.fecha >= SYSDATE - 30 " +
                   "  AND s.id = :sucursalId " +
                   "  AND b.id = :bodegaId",
           nativeQuery = true)
    List<Object[]> findDocumentosIngresoRecientes(@Param("sucursalId") Long sucursalId,
                                                  @Param("bodegaId") Long bodegaId);

    // Método para RFC7 con nivel de aislamiento READ COMMITTED
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Query(value = "SELECT s.nombre AS sucursal_nombre, " +
                   "       b.nombre AS bodega_nombre, " +
                   "       d.numero_documento, " +
                   "       d.fecha, " +
                   "       p.nombre AS proveedor_nombre " +
                   "FROM DOCUMENTOS_INGRESO d " +
                   "JOIN BODEGAS b ON d.bodega_id = b.id " +
                   "JOIN SUCURSALES s ON b.sucursal = s.id " +
                   "JOIN PROVEEDORES p ON d.proveedor_id = p.id " +
                   "WHERE d.fecha >= SYSDATE - 30 " +
                   "  AND s.id = :sucursalId " +
                   "  AND b.id = :bodegaId",
           nativeQuery = true)
    List<Object[]> findDocumentosIngresoRecientesReadCommitted(@Param("sucursalId") Long sucursalId,
                                                               @Param("bodegaId") Long bodegaId);
}
