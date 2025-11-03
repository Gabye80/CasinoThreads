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

    public void checkBrokeness(int casinoBalance, boolean brokeCasino) {
        if (casinoBalance < 1000) {
            brokeCasino = true;
        }
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
        return false;
    }

    public static boolean checkCasinoBalance(int casinoBalance, int bet) {
        return ((casinoBalance - bet) >= 1000);
    }

    public static void main(String[] args) {

        // Casino tiene 50000, nunca paga más de lo que tiene si lo gana alguien.
        Integer casinoBalance = 5000;
        boolean casinoBroke = false;

        Gambler g1 = new Gambler(1000, 10); //Random
        Gambler g2 = new Gambler(1000, 10); //Odd Even
        Gambler g3 = new Gambler(1000, 10); //Random YOLO

        while (!casinoBroke) {
//        for (int i = 0; i < 1000; i++) {
            int houseWinnings;
            int houseNumber = azar();

            int g1num = azar();
            int g2num = betOddEven();
            int g3num = azar();

//            //G1 BETTING
//            if (checkWin(houseNumber, g1num)) {
//                g1.increaseBalance();
//                casinoBalance = casinoBalance - g1.getBet();
//                System.out.println("Gambler 1 won " + g1.getBet() + " dollas!");
//            } else {
//                g1.decreaseBalance();
//                casinoBalance = casinoBalance + g1.getBet();
//                System.out.println("Gambler 1 lost " + g1.getBet() + " dollas!");
//            }
//
//            //G2 BETTING
//            if (checkWinOE(houseNumber, g2num)) {
//                g2.increaseBalanceOE();
//                casinoBalance = casinoBalance - g2.getBet();
//                System.out.println("Gambler 2 won " + g2.getBet() + " dollas!");
//            } else {
//                g2.decreaseBalance();
//                casinoBalance = casinoBalance + g2.getBet();
//                System.out.println("Gambler 2 lost " + g2.getBet() + " dollas!");
//            }

            //G3 BETTING
            if (!g3.checkBalance(g3.getBet())) {

                if (checkCasinoBalance(casinoBalance, g3.getBet())) {

                    if (checkWin(houseNumber, g3num)) {
                        g3.increaseBalance();
                        casinoBalance = casinoBalance - (g3.getBet()*36);
                        System.out.println("Gambler 3 won " + g3.getBet() + " dollas!");
                    } else {
                        g3.decreaseBalance();
                        casinoBalance = casinoBalance + g3.getBet();
                        g3.increaseBetYOLO();
                        System.out.println("Gambler 3 lost " + g3.getBet() + " dollas!");
                    }
                } else {
                    casinoBroke = true;
                    System.out.println("Casino is broke!");
                }
            } else {
                casinoBroke = true;
                System.out.println("Casino is broke!");
            }

//        System.out.println(g1.getBalance());
//        System.out.println(g2.getBalance());
            System.out.println(g3.getBalance());
            System.out.println(casinoBalance); // HOUSE IS NOT LOSING BALANCE WHEN GAMBLER WINS X36!!!!

            // Los hilos empiezan con 1000 euros.
            // 4 jugadores que juegan par/impar. Apuestan 10, Ganan 20.
            // 4 jugadores que juegan al azar. Apuestan 10, Ganan x36.
            // 4 jugadores que juegan al azar. Apuestan 10, Ganan x36. SI PIERDEN, x2 a la apuesta.

            // Números del 0 al 36. Si toca el 0, gana la casa ytodo dinero apostado se lo lleva la casa.


        }
    }
}