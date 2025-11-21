public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        super(nome, 100, 15, 8, 1);
    }

    public Guerreiro(Guerreiro outro) {
        super(outro);
    }

    @Override
    public void ataqueEspecial() {
        System.out.println(nome + " executa um golpe devastador com a espada!");
    }
}