public class Arqueiro extends Personagem {
    public Arqueiro(String nome) {
        super(nome, 70, 18, 6, 1);
    }

    public Arqueiro(Arqueiro outro) {
        super(outro);
    }

    @Override
    public void ataqueEspecial() {
        System.out.println(nome + " dispara uma chuva de flechas!");
    }
}