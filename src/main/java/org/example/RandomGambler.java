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
    public void playRound(int houseNumber) {
        int number = Main.azar();
        if (!casino.checkCasinoBalance(bet, 36)) {
            System.out.println("The casino can't pay " + name);
            return;
        }

        if (Main.checkWin(houseNumber, number)) {
            int payout = bet * 36;
            increaseBalance(36);
            casino.payToGambler(payout);
            System.out.println(name + " won " + payout + " dollas!");
        } else {
            decreaseBalance();
            casino.winFromGambler(bet);
            System.out.println(name + " lost " + bet + " dollas!");
        }
    }
}
