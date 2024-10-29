package dio.api.ecommerce.model.mysql.repository;


import dio.api.ecommerce.model.mysql.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(value = "SELECT * FROM produtos WHERE nome_produto=?",nativeQuery = true)
    Produto findByName(String nome);

    //from status
    @Query(value = "SELECT p FROM produtos p WHERE p.status = :status", nativeQuery = true)
    List<Produto> findByStatus(@Param("status") String status);
}
