public class Mago extends Personagem {
    public Mago(String nome) {
        super(nome, 50, 20, 5, 1);
    }

    public Mago(Mago outro) {
        super(outro);
    }

    @Override
    public void ataqueEspecial() {
        System.out.println(nome + " lança uma bola de fogo poderosa!");
    }
}