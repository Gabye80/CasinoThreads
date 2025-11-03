package org.example;

/**
 * Clase abstracta que representa un jugador genérico del casino.
 * Define los métodos comunes a todos los tipos de jugadores.
 */
public abstract class Gambler {

    protected String name;
    protected int balance;
    protected int bet;
    protected Casino casino;

    public Gambler(String name, Casino casino) {
        this.name = name;
        this.casino = casino;
        this.balance = 1000;
        this.bet = 10;
    }

    /**
     * Comprueba si el jugador aún puede apostar.
     */
    public boolean canBet() {
        return balance >= bet;
    }

    /**
     * Resta el monto de la apuesta del saldo.
     */
    public void decreaseBalance() {
        balance -= bet;
    }

    /**
     * Aumenta el saldo según el multiplicador de la apuesta ganada.
     */
    public void increaseBalance(int multiplier) {
        balance += bet * multiplier;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Método abstracto que define el comportamiento de apuesta de cada tipo de jugador.
     */
    protected abstract void playRound(int houseNumber);
}
