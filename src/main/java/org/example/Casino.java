package org.example;

/**
 * Representa el casino (la banca). Controla el saldo y los pagos.
 * Es thread-safe para evitar conflictos al acceder al saldo desde múltiples hilos.
 */
public class Casino {

    private int casinoBalance;
    private boolean casinoBroke = false;

    public Casino(int balance) {
        this.casinoBalance = balance;
    }

    /**
     * Comprueba si el casino puede pagar una apuesta determinada.
     * @param bet cantidad apostada
     * @param multiplier multiplicador del pago (por ejemplo 36x o 2x)
     * @return true si el casino tiene suficiente saldo, false si no.
     */
    public synchronized boolean checkCasinoBalance(int bet, int multiplier) {
        return casinoBalance >= bet * multiplier;
    }

    /**
     * Incrementa el saldo del casino cuando un jugador pierde.
     */
    public synchronized void winFromGambler(int amount) {
        casinoBalance += amount;
    }

    /**
     * Disminuye el saldo del casino cuando paga a un jugador.
     * Si baja de 1000, se considera que el casino está en bancarrota.
     */
    public synchronized void payToGambler(int amount) {
        casinoBalance -= amount;
        if (casinoBalance < 1000) {
            casinoBroke = true;
            System.out.println("Casino se quedó sin dinero.");
        }
    }

    /**
     * Indica si el casino está en bancarrota.
     */
    public synchronized boolean isBroke() {
        return casinoBroke;
    }

    /**
     * Devuelve el saldo actual del casino.
     */
    public synchronized int getBalance() {
        return casinoBalance;
    }
}
