package dio.api.ecommerce.model.mysql.repository;

import dio.api.ecommerce.model.mysql.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    @Query(value = "SELECT e FROM estoque e WHERE e.id_produto = :id", nativeQuery = true)
    Estoque findByProdutoId(@Param("id") Long id);

    @Query(value = "SELECT e FROM estoque e LEFT FETCH produtos p WHERE p.nome_produto = :nome", nativeQuery = true)
    Estoque findByProdutoName(@Param("nome") String nome);

    @Query(value = "SELECT e FROM estoque e WHERE e.status = :status", nativeQuery = true)
    List<Estoque> findByStatus(@Param("status") String status);
}
