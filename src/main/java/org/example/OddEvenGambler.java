package org.example;

/**
 * Jugador que apuesta si el número será par o impar.
 * Si acierta, gana el doble de lo apostado.
 */
public class OddEvenGambler extends Gambler {

    public OddEvenGambler(String name, Casino casino) {
        super(name, casino);
    }

    @Override
    protected void playRound(int houseNumber) {
        int choice = Main.betOddEven();
        if (!casino.checkCasinoBalance(bet, 2)) {
            System.out.println("Casino no puede pagar a " + name + ".");
            return;
        }

        if (Main.checkWinOE(houseNumber, choice)) {
            int payout = bet * 2;
            increaseBalance(2);
            casino.payToGambler(payout);
            System.out.println(name + " ganó " + payout + " dólares!");
        } else {
            decreaseBalance();
            casino.winFromGambler(bet);
            System.out.println(name + " perdió " + bet + " dólares!");
        }
    }
}
