public abstract class Personagem implements Cloneable {
    protected String nome;
    protected int pontosVida;
    protected int ataque;
    protected int defesa;
    protected int nivel;
    protected Inventario inventario;

    public Personagem(String nome, int hp, int atk, int def, int nivel) {
        this.nome = nome;
        this.pontosVida = hp;
        this.ataque = atk;
        this.defesa = def;
        this.nivel = nivel;
        this.inventario = new Inventario();
    }

    // Construtor de cópia
    public Personagem(Personagem outro) {
        this.nome = outro.nome;
        this.pontosVida = outro.pontosVida;
        this.ataque = outro.ataque;
        this.defesa = outro.defesa;
        this.nivel = outro.nivel;
        this.inventario = outro.inventario.clone();
    }

    public String getNome() {
        return nome;
    }

    public int getPontosVida() {
        return pontosVida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public boolean estaVivo() {
        return pontosVida > 0;
    }

    public void receberDano(int dmg) {
        pontosVida -= dmg;
        if (pontosVida < 0)
            pontosVida = 0;
    }

    public abstract void ataqueEspecial();
}