package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Categoria;

public interface CategoriaRepository extends JpaRepository <Categoria, Integer> {

    @Query(value = "SELECT * FROM categorias", nativeQuery = true)
    Collection<Categoria> darCategorias();

    @Query(value = "SELECT * FROM categorias WHERE id= :id", nativeQuery = true)
    Categoria darCategoria(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categorias (id, codigo, nombre, descripcion, caracteristicasAlmacenamiento) VALUES (superandes_sequence.nextval,:codigo, :nombre, :descripcion, :caracteristicasAlmacenamiento)", nativeQuery = true)
    void insertarCategoria(@Param("codigo") Integer codigo, @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("caracteristicasAlmacenamiento") String caracteristicasAlmacenamiento);

    @Modifying
    @Transactional
    @Query(value = "UPDATE categorias SET codigo= :codigo, nombre= :nombre, descripcion= :descripcion, caracteristicasAlmacenamiento= :caracteristicasAlmacenamiento WHERE id =:id", nativeQuery = true)
    void actualizarCategoria(@Param("id") Integer id, @Param("codigo") Integer codigo, @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("caracteristicasAlmacenamiento") String caracteristicasAlmacenamiento);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM categorias WHERE id = :id", nativeQuery = true)
    void eliminarCategoria(@Param("id") Integer id);
}
