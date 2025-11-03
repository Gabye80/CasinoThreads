package org.example;

/**
 * Jugador que aplica la estrategia "YOLO" (martingala).
 * Duplica su apuesta cada vez que pierde hasta que gana, luego vuelve a apostar 10.
 */
public class YOLOGambler extends Gambler {

    public YOLOGambler(String name, Casino casino) {
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
            bet = 10; // reinicia la apuesta tras ganar
            System.out.println(name + " ganó " + payout + " dólares!");
        } else {
            decreaseBalance();
            casino.winFromGambler(bet);
            bet *= 2; // estrategia YOLO
            System.out.println(name + " perdió " + bet / 2 + " dólares. Nueva apuesta: " + bet);
        }
    }
}
