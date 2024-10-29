package dio.api.ecommerce.model.mysql.service;

import dio.api.ecommerce.controller.ProdutoController;
import dio.api.ecommerce.model.mysql.entity.Estoque;
import dio.api.ecommerce.model.mysql.repository.EstoqueRepository;
import dio.api.ecommerce.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private ProdutoController produtoController;

    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<Estoque> findAll() {
        return estoqueRepository.findAll();
    }

    public Estoque findById(Long id) {
        return estoqueRepository.findById(id).orElse(null);
    }

    public List<Estoque> findByStatus(String status){
        return estoqueRepository.findByStatus(status);
    }

    public Estoque findByProdutoId(Long id){
        return estoqueRepository.findByProdutoId(id);
    }

    public Estoque findByProdutoName(String nome){
        return estoqueRepository.findByProdutoName(nome);
    }

    public void deleteById(Long id) {
        estoqueRepository.deleteById(id);
    }

    public void deleteAll() {
        estoqueRepository.deleteAll();
    }

    public String save(Estoque estoque) {
        try{
            if(produtoController.findById(estoque.getProduto().get_id()) == null){
                throw  new IllegalArgumentException("Produto nao encontrado!");
            }
            if(!produtoController.findById(estoque.getProduto().get_id()).getStatus().equals(Status.ACTIVE)){
                throw  new IllegalArgumentException("Produto inativo!");
            }
            if(!Status.STATUS.contains(estoque.getStatus())){
                throw  new IllegalArgumentException("Status invalido!");
            }
            estoqueRepository.save(estoque);
            return estoque.toString();
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
