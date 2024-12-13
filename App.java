import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // As outras classes estão nos arquivos deste mesmo repositório
        PilhaReferencia cartasJogadorA = new PilhaReferencia(), cartasJogadorB = new PilhaReferencia(),
                pilhaAux = new PilhaReferencia();
        boolean parouA = false, parouB = false;
        int vez = 0;
        Scanner teclado = new Scanner(System.in);

        for (int i = 0; i < 2; i++) {
            int c = new Random().nextInt(10) + 1;
            System.out.println("Jogador A - carta sorteada: " + c);
            cartasJogadorA.push(new Carta(c));
        }

        for (int i = 0; i < 2; i++) {
            int c = new Random().nextInt(10) + 1;
            System.out.println("Jogador B - carta sorteada: " + c);
            cartasJogadorB.push(new Carta(c));
        }

        System.out.println("Soma das cartas do jogador A:" + cartasJogadorA.soma);
        System.out.println("Soma das cartas do jogador B:" + cartasJogadorB.soma);

        String jogador;
        while (true) {
            jogador = "A";
            if (vez == 0) {
                System.out.println("Jogador " + jogador + ", quer mais carta? Opções: [S,N]");
                String op = teclado.nextLine();
                op = op.toUpperCase();

                if (op.equals("S")) {
                    int c = new Random().nextInt(10) + 1;
                    cartasJogadorA.push(new Carta(c));
                    System.out.println("Carta tirada: " + "[" + cartasJogadorA.topo.valor + "]");
                    System.out.println("Soma acumulada: " + cartasJogadorA.soma);

                    if (cartasJogadorA.soma == 21) {
                        System.out.println("Jogador " + jogador + ", você venceu, sortudo do carai");
                        break;
                    } else {
                        if (cartasJogadorA.soma > 21) {
                            parouA = true;
                            System.out.println("Estourou. Soma das cartas:" + cartasJogadorA.soma);

                            Carta ultima = cartasJogadorA.pop();
                            cartasJogadorA.soma -= 5;
                            System.out.println("Penalidade: -5. Carta retirada: " + ultima.valor
                                    + ". Soma das cartas depois de estourar: " + cartasJogadorA.soma);
                            System.out.print("Cartas na mão - Jogador " + jogador + ": [");

                            int tamanhoBaralho = cartasJogadorA.tamanho;
                            for (int i = 0; i < tamanhoBaralho; i++) {
                                Carta carta = cartasJogadorA.pop();
                                System.out.print(carta.valor + " |");
                                pilhaAux.push(carta);
                            }
                            System.out.println("]");

                            cartasJogadorA = pilhaAux;
                            pilhaAux = new PilhaReferencia();

                            vez = 1;
                        }
                    }
                } else {
                    vez = 1;
                }
            } else {
                jogador = "B";

                System.out.println("Jogador " + jogador + ", quer mais carta? Opções: [S,N]");
                String op = teclado.nextLine();
                op = op.toUpperCase();

                if (op.equals("S")) {
                    int c = new Random().nextInt(10) + 1;
                    cartasJogadorB.push(new Carta(c));
                    System.out.println("Carta tirada: " + "[" + cartasJogadorB.topo.valor + "]");
                    System.out.println("Soma acumulada: " + cartasJogadorB.soma);

                    if (cartasJogadorB.soma == 21) {
                        System.out.println("Jogador " + jogador + ", você venceu, sortudo do carai");
                        break;
                    } else {
                        if (cartasJogadorB.soma > 21) {
                            System.out.println("Estourou. Soma das cartas:" + cartasJogadorB.soma);

                            Carta ultima = cartasJogadorB.pop();
                            cartasJogadorB.soma -= 5;
                            System.out.println("Penalidade: -5. Carta retirada: " + ultima.valor
                                    + ". Soma das cartas depois de estourar: " + cartasJogadorB.soma);
                            System.out.print("Cartas na mão - Jogador " + jogador + ": [");

                            int tamanhoBaralho = cartasJogadorB.tamanho;
                            for (int i = 0; i < tamanhoBaralho; i++) {
                                Carta carta = cartasJogadorB.pop();
                                System.out.print(carta.valor + " |");
                                pilhaAux.push(carta);
                            }
                            System.out.println("]");

                            cartasJogadorB = pilhaAux;
                            pilhaAux = new PilhaReferencia();

                            break;
                        }
                    }
                } else {
                    break;
                }
            }

        }

        System.out.println("Fim do jogo.");
        if (cartasJogadorA.soma > cartasJogadorB.soma) {
            System.out.println("Jogador A venceu.");
        } else {
            System.out.println("Jogador B venceu.");
        }
    }
}
