/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.pokerhands;

/**
 * Card class which contains the value and suit of a card
 * @author kkastner
 */
public class Card implements Comparable<Card> {
    private final int cardnum;
    private final String cardnumStr;
    private final String cardsuit;

    /**
     * Constructor for the Card class
     * @param cardnum String card value
     * @param cardsuit String card suit
     */
    public Card(String cardnum, String cardsuit) {
        this.cardnum = getNumericalCardValue(cardnum);
        this.cardnumStr = cardnum;
        this.cardsuit = cardsuit;
    }

    /**
     * Constructor for the Card class
     * @param cardnum integer card value representation
     * @param cardsuit String card suit
     */
    public Card(int cardnum, String cardsuit) {
        if (cardnum == 1) {
            this.cardnum = 14;
        } else {
            this.cardnum = cardnum;
        }
        this.cardnumStr = getStringCardValue(this.cardnum);
        this.cardsuit = cardsuit;
    }

    /**
     * Gets the String card value based on its integer representation.
     * @param cardnum integer card representation
     * @return String card value
     */
    private String getStringCardValue(int cardnum) {
        switch (cardnum) {
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            case 14:
                return "A";
            default:
                return Integer.toString(cardnum);
        }
    }

    /**
     * Gets the integer card value representation based on the String card value
     * @param cardnum String card value
     * @return integer card value representation
     */
    private int getNumericalCardValue(String cardnum) {
        switch (cardnum) {
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return Integer.parseInt(cardnum);
        }
    }

    /**
     * Compares two card value representations.
     * Positive indicates this card value
     * is greater than the compared card's value.
     * @param o Card to compare with
     * @return integer representing the difference between the two card values
     */
    @Override
    public int compareTo(Card o){
        return (int)(this.cardnum - o.cardnum);
    }

    /**
     * Returns the card value representation
     * @return integer
     */
    public int getCardnum() {
        return this.cardnum;
    }

    /**
     * Returns the card value
     * @return String
     */
    public String getCardnumStr() {
        return this.cardnumStr;
    }

    /**
     * Returns the card value and suit
     * @return String concatenation of the card value and suit
     */
    public String getCardStr() {
        return (this.cardnumStr + this.cardsuit);
    }

    /**
     * Returns the card suit
     * @return String
     */
    public String getCardsuit() {
        return this.cardsuit;
    }
}
