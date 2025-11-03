package org.example;

/**
 * Jugador que apuesta a un número aleatorio en cada ronda.
 * Si gana, recibe 36 veces su apuesta.
 */
public class RandomGambler extends Gambler {

    public RandomGambler(String name, Casino casino) {
        super(name, casino);
    }

    @Override
    protected void playRound(int houseNumber) {
        int number = Main.azar();
        if (!casino.checkCasinoBalance(bet, 36)) {
            System.out.println("Casino no puede pagar a " + name + ".");
            return;
        }

        if (Main.checkWin(houseNumber, number)) {
            int payout = bet * 36;
            increaseBalance(36);
            casino.payToGambler(payout);
            System.out.println(name + " ganó " + payout + " dólares!");
        } else {
            decreaseBalance();
            casino.winFromGambler(bet);
            System.out.println(name + " perdió " + bet + " dólares!");
        }
    }
}
