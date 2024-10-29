package dio.api.ecommerce.model.mysql.service;

import dio.api.ecommerce.model.mysql.entity.Produto;
import dio.api.ecommerce.model.mysql.repository.ProdutoRepository;
import dio.api.ecommerce.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto findByName(String nome){
        return produtoRepository.findByName(nome);
    }

    public List<Produto> findByStatus(String status){
        return produtoRepository.findByStatus(status);
    }

    public String save(Produto produto) {
        try{
            if(!Status.STATUS.contains(produto.getStatus())){
                throw  new IllegalArgumentException("Status invalido!");
            }
            if(produto.getPreco() <= 0){
                throw  new IllegalArgumentException("Preço invalido!");
            }
            produtoRepository.save(produto);
            return produto.toString();
        }
        catch (Exception e){
            return e.getMessage();
        }


    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    public void deleteAll() {
        produtoRepository.deleteAll();
    }
}
