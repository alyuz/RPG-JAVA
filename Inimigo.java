public class Inimigo extends Personagem {
    public Inimigo(String nome, int hp, int atk, int def, int nivel) {
        super(nome, hp, atk, def, nivel);
        // Adiciona itens aleatórios para loot
        inventario.adicionarItem(new Item("Moeda de Ouro", "Dinheiro do inimigo", "moeda", 1));
        inventario.adicionarItem(new Item("Poção Pequena", "Recupera HP", "cura", 1));
    }

    public Inimigo(Inimigo outro) {
        super(outro);
    }

    @Override
    public void ataqueEspecial() {
        System.out.println(nome + " usa ataque especial!");
    }
}
