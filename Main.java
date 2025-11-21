import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Bem-vindo ao RPG em Java!");

        System.out.print("Digite o nome do seu personagem: ");
        String nome = sc.nextLine();

        System.out.println("Escolha sua classe:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Arqueiro");

        int escolha;
        try {
            escolha = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            escolha = 1;
        }

        Personagem jogador;
        switch (escolha) {
            case 1:
                jogador = new Guerreiro(nome);
                break;
            case 2:
                jogador = new Mago(nome);
                break;
            case 3:
                jogador = new Arqueiro(nome);
                break;
            default:
                System.out.println("Classe inválida! Você será um Guerreiro.");
                jogador = new Guerreiro(nome);
        }

        // Itens iniciais padrão
        jogador.getInventario().adicionarItem(new Item("Poção de Cura", "Recupera HP", "cura", 1));
        jogador.getInventario().adicionarItem(new Item("Flecha Mágica", "Aumenta ataque", "buff", 1));
        jogador.getInventario().adicionarItem(new Item("Poção de Mana", "Recupera energia mágica", "mana", 1));

        boolean jogando = true;

        while (jogando) {
            System.out.println("\nO que deseja fazer?");
            System.out.println("1 - Explorar");
            System.out.println("2 - Usar Item");
            System.out.println("3 - Ver Inventário");
            System.out.println("4 - Sair");

            int opcao;
            try {
                opcao = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Opção inválida!");
                continue;
            }

            switch (opcao) {
                case 1:
                    explorar(jogador, sc, rand);
                    break;
                case 2:
                    usarItem(jogador, sc);
                    break;
                case 3:
                    jogador.getInventario().listarItens();
                    break;
                case 4:
                    System.out.println("Até a próxima aventura, " + jogador.getNome() + "!");
                    jogando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            if (!jogador.estaVivo()) {
                System.out.println(jogador.getNome() + " foi derrotado :( Fim de jogo!");
                jogando = false;
            }
        }

        sc.close();
    }

    // Exploração
    private static void explorar(Personagem jogador, Scanner sc, Random rand) {
        System.out.println("\nVocê está explorando a floresta misteriosa...");

        System.out.println("Você encontra uma bifurcação. O que fará?");
        System.out.println("1 - Caminho da esquerda");
        System.out.println("2 - Caminho da direita");
        System.out.println("3 - Abrir porta antiga");
        System.out.println("4 - Pegar bolsa no chão");
        System.out.print("Escolha: ");

        int escolha;
        try {
            escolha = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            escolha = 1;
        }

        switch (escolha) {
            case 1:
            case 2:
                int evento = rand.nextInt(3);
                if (evento == 0) {
                    System.out.println("Você encontrou um inimigo!");
                    Inimigo inimigo = new Inimigo("Inimigo chefe", 120, 8, 5, 1);
                    Batalhar(jogador, inimigo, sc, rand);
                } else if (evento == 1) {
                    System.out.println("Você achou uma poção misteriosa!");
                    jogador.getInventario().adicionarItem(new Item("Poção de Cura", "Recupera HP", "cura", 1));
                } else {
                    System.out.println("Você caiu em uma armadilha e perdeu 5 HP!");
                    jogador.receberDano(5);
                    System.out.println(jogador.getNome() + " agora tem " + jogador.getPontosVida() + " HP.");
                }
                break;
            case 3:
                System.out.println("Você abriu uma porta antiga e encontrou um inimigo!");
                Inimigo inimigo = new Inimigo("Anta Adversária", 40, 10, 4, 1);
                Batalhar(jogador, inimigo, sc, rand);
                break;
            case 4:
                System.out.println("Você pegou uma bolsa com moedas e uma poção!");
                jogador.getInventario().adicionarItem(new Item("Moeda de Ouro", "Dinheiro encontrado", "moeda", 5));
                jogador.getInventario().adicionarItem(new Item("Poção de Cura", "Recupera HP", "cura", 1));
                break;
            default:
                System.out.println("Você se perdeu e nada aconteceu.");
        }
    }

    // Usar item
    private static void usarItem(Personagem jogador, Scanner sc) {
        Inventario inv = jogador.getInventario();
        List<Item> itens = inv.getItens();

        if (itens.isEmpty()) {
            System.out.println("Seu inventário está vazio!");
            return;
        }

        System.out.println("\nItens no inventário:");
        for (int i = 0; i < itens.size(); i++)
            System.out.println((i + 1) + " - " + itens.get(i));

        System.out.print("Digite o número do item que deseja usar: ");
        int escolha;
        try {
            escolha = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            return;
        }

        if (escolha < 1 || escolha > itens.size()) {
            System.out.println("Escolha inválida!");
            return;
        }

        Item item = itens.get(escolha - 1);

        System.out.print("Quantas unidades deseja usar? ");
        int qtd;
        try {
            qtd = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            return;
        }

        if (qtd <= 0 || qtd > item.getQuantidade()) {
            System.out.println("Quantidade inválida!");
            return;
        }

        switch (item.getEfeito().toLowerCase()) {
            case "cura":
                jogador.pontosVida += 10 * qtd;
                System.out.println(jogador.getNome() + " usou " + qtd + "x " + item.getNome() + " e recuperou "
                        + (10 * qtd) + " HP!");
                break;
            case "buff":
                jogador.ataque += 2 * qtd;
                System.out.println(jogador.getNome() + " usou " + qtd + "x " + item.getNome() + " e aumentou ataque em "
                        + (2 * qtd) + "!");
                break;
            case "mana":
                System.out.println(jogador.getNome() + " recuperou energia mágica com " + item.getNome() + "!");
                break;
            case "moeda":
                System.out.println(jogador.getNome() + " pegou moedas: " + qtd);
                break;
            default:
                System.out.println("Item usado: " + item.getNome());
        }

        inv.removerItem(item, qtd);
    }

    // Batalhar
    private static void Batalhar(Personagem jogador, Inimigo inimigo, Scanner sc, Random rand) {
        System.out.println("\nBatalha iniciada: " + jogador.getNome() + " VS " + inimigo.getNome());

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\nHP de " + jogador.getNome() + ": " + jogador.getPontosVida());
            System.out.println("HP de " + inimigo.getNome() + ": " + inimigo.getPontosVida());

            System.out.println("\nEscolha sua ação:");
            System.out.println("1 - Ataque normal");
            System.out.println("2 - Ataque especial");
            System.out.println("3 - Usar item");
            System.out.println("4 - Tentar fugir");
            System.out.print("Digite sua escolha: ");

            int acao;
            try {
                acao = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                acao = 1;
            }

            switch (acao) {
                case 1:
                    System.out.print("Digite o valor do dado que deseja rolar (1-6): ");
                    int dado = rand.nextInt(6) + 1;
                    try {
                        dado = Integer.parseInt(sc.nextLine().trim());
                    } catch (Exception e) {
                    }
                    int dano = (jogador.getAtaque() + dado) - inimigo.getDefesa();
                    dano = Math.max(dano, 0);
                    inimigo.receberDano(dano);
                    System.out.println(jogador.getNome() + " causou " + dano + " de dano ao " + inimigo.getNome());
                    break;
                case 2:
                    jogador.ataqueEspecial();
                    int dadoEsp = rand.nextInt(6) + 1;
                    int danoEsp = (jogador.getAtaque() * 2 + dadoEsp) - inimigo.getDefesa();
                    danoEsp = Math.max(danoEsp, 0);
                    inimigo.receberDano(danoEsp);
                    System.out.println(jogador.getNome() + " usou ataque especial e causou " + danoEsp + " de dano!");
                    break;
                case 3:
                    usarItem(jogador, sc);
                    break;
                case 4:
                    int dadoFuga = rand.nextInt(6) + 1;
                    if (dadoFuga >= 4) {
                        System.out.println(jogador.getNome() + " conseguiu fugir com sucesso!");
                        return;
                    } else {
                        System.out.println(jogador.getNome() + " falhou ao tentar fugir!");
                    }
                    break;
                default:
                    System.out.println("Ação inválida! Você perdeu a vez.");
            }

            // Turno do inimigo
            if (inimigo.estaVivo()) {
                int dadoInimigo = rand.nextInt(6) + 1;
                int danoInimigo = (inimigo.getAtaque() + dadoInimigo) - jogador.getDefesa();
                danoInimigo = Math.max(danoInimigo, 0);
                jogador.receberDano(danoInimigo);
                System.out.println(
                        inimigo.getNome() + " atacou " + jogador.getNome() + " causando " + danoInimigo + " de dano!");
            }
        }

        // Resultado da batalha
        if (jogador.estaVivo()) {
            System.out.println("\n" + jogador.getNome() + " derrotou " + inimigo.getNome() + " :D !");
            jogador.getInventario().loot(inimigo.getInventario());
        } else {
            System.out.println("\n" + jogador.getNome() + " foi derrotado :(");
        }
    }
}
