package dio.api.ecommerce.model.mysql.entity;

import jakarta.persistence.*;

//usar mysql ao inves de mongodb


@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto", nullable = false, updatable = false, insertable = false)
    private Long _id;

    @Column(name = "nome_produto", nullable = false)
    private String nome;

    @Column(name = "descricao_produto")
    private String descricao;

    @Column(name = "preco_produto", nullable = false)
    private Double preco;

    @Column(name = "status", nullable = false)
    private String status;

    @Override
    public String toString() {
        return "{"+
                "\"_id\":" + _id +
                ", \"nome\":\"" + nome + '\"' +
                ", \"descricao\":\"" + descricao + '\"' +
                ", \"preco\":" + preco +
                ", \"status\":\"" + status + '\"' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long get_id() {
        return _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
