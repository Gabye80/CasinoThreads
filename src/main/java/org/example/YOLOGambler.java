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
    public void playRound(int houseNumber) {
        int number = Main.azar();
        if (!casino.checkCasinoBalance(bet, 36)) {
            System.out.println("The casino can't pay " + name + ".");
            return;
        }

        if (Main.checkWin(houseNumber, number)) {
            int payout = bet * 36;
            increaseBalance(36);
            casino.payToGambler(payout);
            bet = 10; // reinicia la apuesta tras ganar
            System.out.println(name + " won " + payout + " dollas!");
        } else {
            decreaseBalance();
            casino.winFromGambler(bet);
            bet *= 2; // estrategia a la martingala
            System.out.println(name + " lost " + bet / 2 + " dollas. New bet: " + bet);
        }
    }
}
