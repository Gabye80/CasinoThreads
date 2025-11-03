package org.example;

public class Gambler {

    private Integer balance;
    private Integer bet;

    public Gambler(Integer balance, Integer bet) {
        this.balance = balance;
        this.bet = bet;
    }

    public Integer getBalance() {
        return balance;
    }

    public Integer getBet() {
        return bet;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public void increaseBetYOLO() {
        this.bet = this.bet * 2;
    }

    public void decreaseBalance() {
        this.balance = this.balance - this.bet;
    }

    public void increaseBalance() {
        this.balance = this.balance + this.bet * 36;
    }

    public void increaseBalanceOE() {
        this.balance = this.balance + this.bet * 2;
    }

    public boolean checkBalance(int bet) {
        return this.balance <= bet;
    }

    public boolean canBet() {
        return this.balance >= this.bet;
    }
}
