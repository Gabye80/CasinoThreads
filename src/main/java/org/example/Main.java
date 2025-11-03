package org.example;

public class Main {

    public static int azar() {
        return (int) (Math.floor(Math.random() * 37));
    }

    public static int betOddEven() {
        if (azar() % 2 == 0) {
            return 2;
        } else {
            return 3;
        }
    }

    public static boolean checkBrokeness(int casinoBalance) {
        return casinoBalance < 1000;
    }

    public static boolean isEven(int houseNumber) {
        if (houseNumber % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkWin(int houseNumber, int betterNumber) {
        if (houseNumber == betterNumber) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkWinOE(int houseNumber, int betterOE) {
        if (houseNumber % 2 == 0 && betterOE == 2) {
            return true;
        }
        if (houseNumber % 2 != 0 && betterOE == 3) {
            return true;
        }
        return false;
    }

    public static boolean checkCasinoBalance(int casinoBalance, int bet, int multiplier) {
        return casinoBalance >= bet * multiplier;
    }

    public static void main(String[] args) throws InterruptedException {

        // Casino tiene 50000, nunca paga más de lo que tiene si lo gana alguien.
        Integer casinoBalance = 50000;
        boolean casinoBroke = false;

        Gambler g1 = new Gambler(1000, 10); //Random
        Gambler g2 = new Gambler(1000, 10); //Odd Even
        Gambler g3 = new Gambler(1000, 10); //Random YOLO (martingala)

        while (!casinoBroke && (g1.canBet() || g2.canBet() || g3.canBet())) {
            int houseNumber = azar();

            if (houseNumber == 0) {
                System.out.println("House wins! 0 came out!");
                casinoBalance += g1.getBalance() + g2.getBalance() + g3.getBalance();
                g1.setBalance(0);
                g2.setBalance(0);
                g3.setBalance(0);
                break;
            }

            // G1 - apuesta a número aleatorio (pago x36)
            if (g1.canBet()) {
                int g1num = azar();
                int g1Bet = g1.getBet();
                if (!checkCasinoBalance(casinoBalance, g1Bet, 36)) {
                    System.out.println("Casino can’t pay G1 bet, da casino is broke.");
                    casinoBroke = true;
                    break;
                }
                if (checkWin(houseNumber, g1num)) {
                    int payout = g1Bet * 36;
                    g1.increaseBalance();
                    casinoBalance -= payout;
                    System.out.println("Gambler 1 won " + payout + " dollas!");
                } else {
                    g1.decreaseBalance();
                    casinoBalance += g1Bet;
                    System.out.println("Gambler 1 lost " + g1Bet + " dollas!");
                }
            } else {
                System.out.println("Gambler 1 can’t bet anymore...");
            }

            // G2 - par/impar (pago x2)
            if (g2.canBet()) {
                int g2num = betOddEven();
                int g2Bet = g2.getBet();
                if (!checkCasinoBalance(casinoBalance, g2Bet, 2)) {
                    System.out.println("Casino can’t pay G2 bet, da casino is broke.");
                    casinoBroke = true;
                    break;
                }
                if (checkWinOE(houseNumber, g2num)) {
                    int payout = g2Bet * 2;
                    g2.increaseBalanceOE();
                    casinoBalance -= payout;
                    System.out.println("Gambler 2 won " + payout + " dollas!");
                } else {
                    g2.decreaseBalance();
                    casinoBalance += g2Bet;
                    System.out.println("Gambler 2 lost " + g2Bet + " dollas!");
                }
            } else {
                System.out.println("Gambler 2 can’t bet anymore...");
            }

            // G3 - martingala jugando a número (pago x36)
            if (g3.canBet()) {
                int g3num = azar();
                int g3Bet = g3.getBet();
                if (!checkCasinoBalance(casinoBalance, g3Bet, 36)) {
                    System.out.println("AYO, CASINO CANT PAY THAT SHIT FAM! PACK IT UP!");
                    casinoBroke = true;
                    break;
                }
                if (checkWin(houseNumber, g3num)) {
                    int payout = g3Bet * 36;
                    g3.increaseBalance();
                    casinoBalance -= payout;
                    g3.setBet(10); // reinicia la apuesta tras ganar
                    System.out.println("Gambler 3 won " + payout + " dollas!");
                } else {
                    g3.decreaseBalance();
                    casinoBalance += g3Bet;
                    g3.increaseBetYOLO();
                    System.out.println("Gambler 3 lost " + g3Bet + " dollas!");
                }
            } else {
                System.out.println("Gambler 3 can do this no more...");
            }

            System.out.println(g1.getBalance() + " | " + g2.getBalance() + " | " + g3.getBalance());
            System.out.println(casinoBalance);

            // 4 jugadores que juegan par/impar. Apuestan 10, Ganan 20.
            // 4 jugadores que juegan al azar. Apuestan 10, Ganan x36.
            // 4 jugadores que juegan al azar. Apuestan 10, Ganan x36. SI PIERDEN, x2 a la apuesta.

            // Números del 0 al 36. Si toca el 0, gana la casa ytodo dinero apostado se lo lleva la casa.

            Thread.sleep(1000);

            if (checkBrokeness(casinoBalance)) {
                System.out.println("Casino is outta dolla dollas.");
                casinoBroke = true;
            }
        }
    }
}
