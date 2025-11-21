import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventario implements Cloneable {
    private List<Item> itens;

    public Inventario() {
        itens = new ArrayList<>();
    }

    // Construtor de cópia
    public Inventario(Inventario outro) {
        itens = new ArrayList<>();
        for (Item i : outro.itens) {
            itens.add(i.clone());
        }
    }

    public void adicionarItem(Item item) {
        int idx = itens.indexOf(item);
        if (idx >= 0) {
            itens.get(idx).adicionar(item.getQuantidade());
        } else {
            itens.add(item.clone());
        }
    }

    public void removerItem(Item item, int qtd) {
        int idx = itens.indexOf(item);
        if (idx >= 0) {
            Item existente = itens.get(idx);
            existente.adicionar(-qtd);
            if (existente.getQuantidade() <= 0)
                itens.remove(idx);
        }
    }

    public void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("Inventário vazio!");
            return;
        }
        Collections.sort(itens);
        System.out.println("Itens no inventário:");
        for (Item i : itens)
            System.out.println("- " + i);
    }

    public Item buscarItem(String nome) {
        for (Item i : itens) {
            if (i.getNome().equalsIgnoreCase(nome))
                return i;
        }
        return null;
    }

    public List<Item> getItens() {
        return new ArrayList<>(itens);
    }

    @Override
    public Inventario clone() {
        return new Inventario(this);
    }

    // Inventario inimigos
    public void loot(Inventario inimigo) {
        for (Item i : inimigo.itens) {
            this.adicionarItem(i);
        }
    }
}