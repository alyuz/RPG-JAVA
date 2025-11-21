import java.util.Objects;

public class Item implements Comparable<Item>, Cloneable {
    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;

    public Item(String nome, String descricao, String efeito, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;
    }

    // Construtor de cópia
    public Item(Item outro) {
        this.nome = outro.nome;
        this.descricao = outro.descricao;
        this.efeito = outro.efeito;
        this.quantidade = outro.quantidade;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEfeito() {
        return efeito;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void adicionar(int qtd) {
        this.quantidade += qtd;
    }

    public void setQuantidade(int qtd) {
        this.quantidade = qtd;
    }

    @Override
    public String toString() {
        return nome + " (" + quantidade + "x) - " + descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Item))
            return false;
        Item item = (Item) o;
        return nome.equalsIgnoreCase(item.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase());
    }

    @Override
    public int compareTo(Item outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public Item clone() {
        return new Item(this);
    }
}