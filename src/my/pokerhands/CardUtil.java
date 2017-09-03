/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.pokerhands;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * CardUtil class
 * @author kkastner
 */
public class CardUtil {
    /**
     * integer ranking of the player's best hand
     * 0 = high card, 1 = one pair, 2 = two pairs, 3 = three of a kind,
     * 4 = straight, 5 = flush, 6 = full house, 7 = four of a kind,
     * 8 = straight flush, 9 = royal flush
     */
    static final int HIGH_CARD = 0;
    static final int ONE_PAIR = 1;
    static final int TWO_PAIRS = 2;
    static final int THREE_OF_A_KIND = 3;
    static final int STRAIGHT = 4;
    static final int FLUSH = 5;
    static final int FULL_HOUSE = 6;
    static final int FOUR_OF_A_KIND = 7;
    static final int STRAIGHT_FLUSH = 8;
    static final int ROYAL_FLUSH = 9;
    
    /**
     * Generates a hand of Cards based on the given card value and suit arrays
     * @param cardnums String array of the card values
     * @param cardsuits String array of the card suits
     * @return Card array of the generated hand of Cards
     */
    public static Card[] generateHand(String[] cardnums, String[] cardsuits) {
        Card[] hand = new Card[5];
        for (int i=0; i<hand.length; i++) {
            hand[i] = new Card(cardnums[i], cardsuits[i]);
        }
        return hand;
    }
    
    /**
     * Returns an array of randomly determined cards.
     * 
     * @return Card array of 10 randomly determined cards
     */
    public static Card[] getRandomCards() {
        final int HEARTS = 0;
        final int SPADES = 1;
        final int DIAMONDS = 2;
        final int CLUBS = 3;
        
        Random rand = new Random();
        HashSet<Integer> h = new HashSet<>();
        Integer n;
        Card[] cards = new Card[10];
        for (int i=0;i<cards.length;i++) {
            do {
                n = rand.nextInt(52);
            } while (h.contains(n));
            h.add(n);
            int cardnum = (n%13)+1;
            int cardsuitNum = n/13;
            String cardsuit;
            switch (cardsuitNum) {
                case HEARTS:
                    cardsuit = "H";
                    break;
                case SPADES:
                    cardsuit = "S";
                    break;
                case DIAMONDS:
                    cardsuit = "D";
                    break;
                case CLUBS:
                    cardsuit = "C";
                    break;
                default:
                    cardsuit = "";
                    break;
            }
            cards[i] = new Card(cardnum, cardsuit);
        }
        return cards;
    }
    
    /**
     * Determines the best hand that a player has.
     * 
     * @param hand Card array representing the player's hand
     * @return integer array with the following attributes:
     * Element 0 contains the ranking of the player's best hand
     * Elements 1+ are used to break ties
     */
    public static int[] getBestHand(Card[] hand) {
        Arrays.sort(hand);
        int[] result;
        // Determine if flush
        boolean flush = false;
        String suit = hand[0].getCardsuit();
        if (suit.equals(hand[1].getCardsuit()) && suit.equals(hand[2].getCardsuit())
                && suit.equals(hand[3].getCardsuit()) && suit.equals(hand[4].getCardsuit())) {
            flush = true;
        }
        // Determine if straight and number of pairs
        boolean straight = true;
        int num_pairs = 0;
        int prevCard,currCard;
        int highPair=0,lowPair=0;
        for (int i=1; i<hand.length;i++) {
            prevCard = hand[i-1].getCardnum();
            currCard = hand[i].getCardnum();
            if (prevCard+1 != currCard) {
                straight = false;
                //break;
                if (prevCard == currCard) {
                    num_pairs++;
                    lowPair = highPair;
                    highPair = currCard;
                }
            }
        }
        // Special case straight (five-high straight)
        if (hand[0].getCardnum() == 2 && hand[1].getCardnum() == 3 && hand[2].getCardnum() == 4
                && hand[3].getCardnum() == 5 && (hand[4].getCardnumStr()).equals("A")) {
            straight = true;
        }
        
        if (flush && straight) {
            if ((hand[4].getCardnumStr()).equals("A")) {
                if (hand[0].getCardnum() == 2) {
                    result = new int[]{STRAIGHT_FLUSH, 5}; // five-high straight flush
                } else {
                    result = new int[]{ROYAL_FLUSH};
                }
            } else {
                result = new int[]{STRAIGHT_FLUSH, hand[4].getCardnum()};
            }
            return result;
        } else if (flush) {
            result = new int[6];
            result[0] = FLUSH;
            result[1] = hand[4].getCardnum();
            result[2] = hand[3].getCardnum();
            result[3] = hand[2].getCardnum();
            result[4] = hand[1].getCardnum();
            result[5] = hand[0].getCardnum();
            return result;
        } else if (straight) {
            if (hand[0].getCardnum() == 2 && (hand[4].getCardnumStr()).equals("A")) {
                return new int[]{STRAIGHT, 5}; // five-high straight
            } else {
                return new int[]{STRAIGHT, hand[4].getCardnum()};
            }
        }
        
        // Determine if four of a kind
        if (hand[0].getCardnum() == hand[3].getCardnum() ||
                hand[1].getCardnum() == hand[4].getCardnum()) {
            result = new int[3];
            result[0] = FOUR_OF_A_KIND;
            result[1] = hand[3].getCardnum();
            if (hand[0].getCardnum() == hand[3].getCardnum()) {
                result[2] = hand[4].getCardnum();
            } else {
                result[2] = hand[0].getCardnum();
            }
            return result;
        }
        
        // Determine if full house or three of a kind
        if (hand[0].getCardnum() == hand[2].getCardnum()) {
            if (hand[3].getCardnum() == hand[4].getCardnum()) {
                result = new int[3];
                result[0] = FULL_HOUSE;
                result[1] = hand[2].getCardnum();
                result[2] = hand[4].getCardnum();
                return result;
            } else {
                result = new int[4];
                result[0] = THREE_OF_A_KIND;
                result[1] = hand[2].getCardnum();
                result[2] = hand[4].getCardnum();
                result[3] = hand[3].getCardnum();
                return result;
            }
        } else if (hand[2].getCardnum() == hand[4].getCardnum()) {
            if (hand[0].getCardnum() == hand[1].getCardnum()) {
                result = new int[3];
                result[0] = FULL_HOUSE;
                result[1] = hand[2].getCardnum();
                result[2] = hand[1].getCardnum();
                return result;
            } else {
                result = new int[4];
                result[0] = THREE_OF_A_KIND;
                result[1] = hand[2].getCardnum();
                result[2] = hand[1].getCardnum();
                result[3] = hand[0].getCardnum();
                return result;
            }
        } else if (hand[1].getCardnum() == hand[3].getCardnum()) {
            result = new int[4];
            result[0] = THREE_OF_A_KIND;
            result[1] = hand[3].getCardnum();
            result[2] = hand[4].getCardnum();
            result[3] = hand[1].getCardnum();
            return result;
        }
        
        // None of the higher ranking hands, so return number of pairs
        switch (num_pairs) {
            case 2:
                result = new int[4];
                result[0] = TWO_PAIRS;
                result[1] = highPair;
                result[2] = lowPair;
                for (int i=0;i<hand.length;i++) {
                    int cardnum = hand[i].getCardnum();
                    if (cardnum != highPair && cardnum != lowPair) {
                        result[3] = cardnum;
                        break;
                    } else {
                        i++;
                    }
                }
                break;
            case 1:
                result = new int[5];
                result[0] = ONE_PAIR;
                result[1] = highPair;
                int resItr = 4;
                for (int i=0;i<hand.length;i++) {
                    int cardnum = hand[i].getCardnum();
                    if (cardnum != highPair) {
                        result[resItr--] = cardnum;
                    } else {
                        i++;
                    }
                }
                break;
            default:
                result = new int[6];
                result[0] = HIGH_CARD;
                int n = hand.length;
                for (int i=1;i<=n;i++) {
                    result[i] = hand[n-i].getCardnum();
                }
                break;
        }
        return result;
    }
    
    /**
     * Verifies that the cards possessed by both players are unique,
     * as in there are no duplicate cards in play.
     * 
     * @param player1cards Card array representing player 1's hand
     * @param player2cards Card array representing player 2's hand
     * @return boolean of weather all cards in play are unique
     */
    public static boolean verifyUniqueCards(Card[] player1cards, Card[] player2cards) {
        HashSet<String> h = new HashSet<>();
        String cardStr;
        Card[] cards = player1cards;
        int itr=1;
        do {
        for (Card c : cards) {
            cardStr = c.getCardStr();
            if (h.contains(cardStr)) {
                return false;
            }
            h.add(cardStr);
        }
        cards = player2cards;
        ++itr;
        } while (itr < 3);
        return true;
    }
    
    /**
     * Determines the winner of two poker players based
     * on what is currently in their hand.
     * 
     * @param player1cards Card array representing player 1's hand
     * @param player2cards Card array representing player 2's hand
     * @param player1name String which contains player 1's name
     * @param player2name String which contains player 2's name
     * @return String message of which player won and what their winning
     * hand was. Or, if no player won, then the message will state so.
     */
    public static String determineWinner(Card[] player1cards, Card[] player2cards, String player1name, String player2name) {
        final int DRAW = 0;
        final int PLAYER1 = 1;
        final int PLAYER2 = 2;
        
        int[] player1BestHand = getBestHand(player1cards);
        int[] player2BestHand = getBestHand(player2cards);
        int winner = DRAW;
        if (player1BestHand[0] > player2BestHand[0]) {
            winner = PLAYER1;
        } else if (player1BestHand[0] < player2BestHand[0]) {
            winner = PLAYER2;
        } else {
            // Players have same ranked hands, so determine winner by high card
            for (int i=1;i<player1BestHand.length;i++) {
                if (player1BestHand[i] > player2BestHand[i]) {
                    winner = PLAYER1;
                    break;
                } else if (player1BestHand[i] < player2BestHand[i]) {
                    winner = PLAYER2;
                    break;
                }
            }
        }
        
        // Create winner message
        String message = "Both players have equal hands.";
        if (winner != DRAW) {
            message = "Player " + Integer.toString(winner) + " ";
            int besthand;
            if (winner == PLAYER1) {
                String name = player1name;
                if (!name.isEmpty()) {
                    message += "(" + name + ") ";
                }
                besthand = player1BestHand[0];
            } else {
                String name = player2name;
                if (!name.isEmpty()) {
                    message += "(" + name + ") ";
                }
                besthand = player2BestHand[0];
            }
            String method;
            switch (besthand) {
                case HIGH_CARD:
                    method = "a High Card";
                    break;
                case ONE_PAIR:
                    method = "a Pair";
                    break;
                case TWO_PAIRS:
                    method = "Two Pairs";
                    break;
                case THREE_OF_A_KIND:
                    method = "Three of a Kind";
                    break;
                case STRAIGHT:
                    method = "a Straight";
                    break;
                case FLUSH:
                    method = "a Flush";
                    break;
                case FULL_HOUSE:
                    method = "a Full House";
                    break;
                case FOUR_OF_A_KIND:
                    method = "Four of a Kind";
                    break;
                case STRAIGHT_FLUSH:
                    method = "a Straight Flush!";
                    break;
                case ROYAL_FLUSH:
                    method = "a ROYAL FLUSH!!";
                    break;
                default: // Should never reach
                    method = "an impossible method";
                    break;
            }
            message += "won the game with " + method + "!";
        }
        return message;
    }
}
