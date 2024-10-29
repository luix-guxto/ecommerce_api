package dio.api.ecommerce.controller;

import dio.api.ecommerce.model.mysql.entity.Estoque;
import dio.api.ecommerce.model.mysql.service.EstoqueService;
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

@Tag(name = "Estoque", description = "Gestão de estoque")
@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @Operation(
            summary = "Listar todos os estoques",
            description = "Retorna uma lista com todos os estoques cadastrados"
    )
    @GetMapping
    public List<Estoque> findAll() {
        return estoqueService.findAll();
    }

    @Operation(
            summary = "Salvar um estoque",
            description = "Salva um estoque no banco de dados",
            responses = {
                @ApiResponse(
                    responseCode = "200",
                    description = "Sucesso",
                    content = @Content(
                        schema = @Schema(implementation = Estoque.class)
                    )
                )
            }
    )
    @PostMapping
    public String save(@RequestBody Estoque estoque) {
        try {
            return Response.getResponse(estoqueService.save(estoque), 200);
        } catch (Exception e) {
            return Response.getResponse(e.getMessage(), 500);
        }
    }


    @Operation(
            summary = "Listar estoques por status",
            description = "Retorna uma lista com todos os estoques cadastrados com o status informado"
    )
    @GetMapping("/status/{status}")
    public List<Estoque> findByStatus(@PathVariable String status) {
        return estoqueService.findByStatus(status);
    }

    @Operation(
            summary = "Buscar estoque por ID",
            description = "Retorna o estoque cadastrado com o ID informado"
    )
    @GetMapping("/produto/{id}")
    public Estoque findByProdutoId(@PathVariable Long id) {
        return estoqueService.findByProdutoId(id);
    }

    @Operation(
            summary = "Buscar estoque por nome",
            description = "Retorna o estoque cadastrado com o nome informado"
    )
    @GetMapping("/produto/nome/{nome}")
    public Estoque findByProdutoName(@PathVariable String nome) {
        return estoqueService.findByProdutoName(nome);
    }


    @Operation(
            summary = "Deletar um estoque",
            description = "Deleta um estoque do banco de dados",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID do estoque",
                            required = true,
                            example = "1",
                            allowEmptyValue = false
                    )
            }
    )
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        if(estoqueService.findById(id) != null){
            estoqueService.deleteById(id);
            return Response.getResponse("Estoque deletado com sucesso!",200);
        }else{
            return Response.getResponse("Estoque não encontrado!",404);
        }
    }

    @Operation(
            summary = "Deletar todos os estoques",
            description = "Deleta todos os estoques cadastrados"
    )
    @DeleteMapping
    public String deleteAll() {
        if(!findAll().isEmpty()){
            estoqueService.deleteAll();
            return Response.getResponse("Todos os estoques foram deletados com sucesso!",200);
        }else{
            return Response.getResponse("Nenhum estoques encontrado!",404);
        }
    }
}
