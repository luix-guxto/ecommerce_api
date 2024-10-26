package dio.api.ecommerce.controller;

import dio.api.ecommerce.model.mysql.entity.Produto;
import dio.api.ecommerce.model.mysql.service.ProdutoService;
import dio.api.ecommerce.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> findAll() {
        return produtoService.findAll();
    }

    @PostMapping
    public String save(@RequestBody Produto produto) {
        try{
            return Response.getResponse(produtoService.save(produto).toString(),200);
        }catch (Exception e){
            return Response.getResponse(e.getMessage(),500);
        }
    }


    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        if(produtoService.findById(id) != null){
            produtoService.deleteById(id);
            return Response.getResponse("Produto deletado com sucesso!",200);
        }else{
            return Response.getResponse("Produto não encontrado!",404);
        }
    }

    @DeleteMapping
    public String deleteAll() {
        if(!findAll().isEmpty()){
            produtoService.deleteAll();
            return Response.getResponse("Todos os produtos foram deletados com sucesso!",200);
        }else{
            return Response.getResponse("Nenhum produto encontrado!",404);
        }
    }
}
