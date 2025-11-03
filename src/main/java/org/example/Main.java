package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que simula el funcionamiento de un casino con una ruleta francesa.
 * Cada ronda ocurre una vez por segundo y participan 12 jugadores divididos en 3 tipos:
 * - 4 jugadores aleatorios (RandomGambler)
 * - 4 jugadores que apuestan a par/impar (OddEvenGambler)
 * - 4 jugadores que usan la estrategia YOLO (YOLOGambler)
 *
 * Si sale el número 0, todos los jugadores pierden y el casino gana sus saldos.
 */
public class Main {

    /**
     * Genera un número aleatorio entre 0 y 36 (inclusive), simulando el resultado de la ruleta.
     */
    public static int azar() {
        return (int) (Math.floor(Math.random() * 37));
    }
    /**
     * Genera una apuesta aleatoria a par o impar.
     */
    public static int betOddEven() {
        return azar() % 2 == 0 ? 2 : 3;
    }

    /**
     * Comprueba si el número apostado es igual al número de la casa.
     */
    public static boolean checkWin(int houseNumber, int betterNumber) {
        return houseNumber == betterNumber;
    }

    /**
     * Comprueba si una apuesta par/impar es ganadora.
     */
    public static boolean checkWinOE(int houseNumber, int betterOE) {
        if (houseNumber % 2 == 0 && betterOE == 2) return true;
        if (houseNumber % 2 != 0 && betterOE == 3) return true;
        return false;
    }

    public static void main(String[] args) throws InterruptedException {

        Casino casino = new Casino(50000);
        List<Gambler> gamblers = new ArrayList<>();

        // Se crean 4 jugadores de cada tipo
        for (int i = 0; i < 4; i++)
            gamblers.add(new RandomGambler("RandomGambler-" + (i + 1), casino));

        for (int i = 0; i < 4; i++)
            gamblers.add(new OddEvenGambler("OddEvenGambler-" + (i + 1), casino));

        for (int i = 0; i < 4; i++)
            gamblers.add(new YOLOGambler("YOLOGambler-" + (i + 1), casino));

        int round = 1;

        // Comienzo de apuestas
        while (!casino.isBroke() && gamblers.stream().anyMatch(Gambler::canBet)) {
            System.out.println("\n========== ROUND " + round + " ==========");

            int houseNumber = azar();
            if (houseNumber == 0) {
                System.out.println("0!!! House wins!");
                for (Gambler g : gamblers) {
                    casino.winFromGambler(g.getBalance());
                    g.setBalance(0);
                }
                break;
            }

            List<Thread> threads = new ArrayList<>();

            // Los jugadores que pueden apostar juegan esta ronda
            for (Gambler g : gamblers) {
                if (g.canBet()) {
                    Thread t = new Thread(() -> g.playRound(houseNumber));
                    threads.add(t);
                    t.start();
                }
            }

            // Espera que todos terminen
            for (Thread t : threads) t.join();

            System.out.println("====== CASINO BALANCE : " + casino.getBalance() + " ======");

            round++;
            Thread.sleep(1000); // una ronda por segundo (3 segundos por ronda me muero del asco xd)
        }

        System.out.println("End of simulation :P");
    }
}
