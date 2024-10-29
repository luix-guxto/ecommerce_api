package dio.api.ecommerce.controller;

import dio.api.ecommerce.model.mysql.entity.Produto;
import dio.api.ecommerce.model.mysql.service.ProdutoService;
import dio.api.ecommerce.util.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Produto", description = "Gestão de protutos")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(
            summary = "Listar todos os produtos",
            description = "Retorna uma lista com todos os produtos cadastrados"
    )
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @GetMapping
    public List<Produto> findAll() {
        return produtoService.findAll();
    }

    @Operation(
            summary = "Listar produtos por status",
            description = "Retorna uma lista com todos os produtos cadastrados com o status informado"
    )
    @GetMapping("/status/{status}")
    public List<Produto> findByStatus(@PathVariable String status) {
        return produtoService.findByStatus(status);
    }

    @Operation(
            summary = "Buscar produto por ID",
            description = "Retorna o produto cadastrado com o ID informado"
    )
    @GetMapping("/id/{id}")
    public Produto findById(@PathVariable Long id) {
        return produtoService.findById(id);
    }

    @Operation(
            summary = "Buscar produto por nome",
            description = "Retorna o produto cadastrado com o nome informado"
    )
    @GetMapping("/nome/{nome}")
    public Produto findByName(@PathVariable String nome) {
        return produtoService.findByName(nome);
    }

    @Operation(
            summary = "Salvar ou atualizar um produto",
            description = "Cria ou atualiza um produto no banco de dados",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = Produto.class)
                            )
                    )
            }
    )
    @PostMapping
    public String save(@RequestBody Produto produto) {
        try{
            return Response.getResponse(produtoService.save(produto),200);
        }catch (Exception e){
            return Response.getResponse(e.getMessage(),500);
        }
    }


    @Operation(
            summary = "Deletar um produto",
            description = "Deleta um produto do banco de dados",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID do produto",
                            required = true,
                            example = "1",
                            allowEmptyValue = false
                    )
            }
    )
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        if(produtoService.findById(id) != null){
            produtoService.deleteById(id);
            return Response.getResponse("Produto deletado com sucesso!",200);
        }else{
            return Response.getResponse("Produto não encontrado!",404);
        }
    }

    @Operation(
            summary = "Deletar todos os produtos",
            description = "Deleta todos os produtos do banco de dados"
    )
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
