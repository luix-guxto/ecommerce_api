package dio.api.ecommerce.model.mysql.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estoque")
public class Estoque {
    @Id
    @Column(name = "id_estoque", nullable = false, updatable = false, insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "valor_inventario", nullable = false)
    private Double invcost;

    public Double getInvcost() {
        this.updateInvcost();
        return invcost;
    }

    public void updateInvcost() {
        this.invcost = this.quantidade * this.produto.getPreco();
    }

    @OneToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto", nullable = false, updatable = false, unique = true)
    private Produto produto;

    @Override
    public String toString() {
        this.updateInvcost();
        return "{" +
                "\"_id\":" + _id +
                ", \"quantidade\":" + quantidade +
                ", \"status\":\"" + status + '\"' +
                ", \"invcost\":" + invcost +
                ", \"id_produto\":" + produto.get_id() +
                '}';
    }

    public Long get_id() {
        return _id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        this.updateInvcost();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
