package uniandes.edu.co.proyecto.repositorio;

import java.util.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Pedido;
import uniandes.edu.co.proyecto.modelo.PedidoPK;

public interface PedidoRepository extends JpaRepository<Pedido, PedidoPK> {

    @Query(value = "SELECT * FROM pedidos", nativeQuery = true)
    Collection<Pedido> darPedidos();

    @Query(value = "SELECT * FROM pedidos WHERE id = :id", nativeQuery = true)
    Pedido darPedido(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO pedidos (id, fechaPedido, fechaEntrega, cliente, producto) VALUES (superandes_sequence.nextval, :fechaPedido, :fechaEntrega, :cliente, :producto)", nativeQuery = true)
    void insertarPedido(
        @Param("fechaPedido") Date fechaPedido, 
        @Param("fechaEntrega") Date fechaEntrega, 
        @Param("cliente") Integer cliente, 
        @Param("producto") Integer producto
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE pedidos SET fechaPedido = :fechaPedido, fechaEntrega = :fechaEntrega, cliente = :cliente, producto = :producto WHERE id = :id", nativeQuery = true)
    void actualizarPedido(
        @Param("id") Integer id, 
        @Param("fechaPedido") Date fechaPedido, 
        @Param("fechaEntrega") Date fechaEntrega, 
        @Param("cliente") Integer cliente, 
        @Param("producto") Integer producto
    );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM pedidos WHERE id = :id", nativeQuery = true)
    void eliminarPedido(@Param("id") Integer id);
}
