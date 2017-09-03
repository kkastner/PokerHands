/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.pokerhands;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

/**
 *
 * @author lizho
 */
public class CardUtilTest {
    
    public CardUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of generateHand method, of class CardUtil.
     */
    @Test
    public void testGenerateHand() {
        System.out.println("generateHand");
        String[] cardnums = new String[] {"5", "A", "K", "7", "2"};
        String[] cardsuits = new String[] {"D", "C", "C", "H", "S"};
        Card[] expResult = new Card[5];
        expResult[0] = new Card("5", "D");
        expResult[1] = new Card("A", "C");
        expResult[2] = new Card("K", "C");
        expResult[3] = new Card("7", "H");
        expResult[4] = new Card("2", "S");
        Card[] result = CardUtil.generateHand(cardnums, cardsuits);
        for (int i=0; i<expResult.length; i++) {
            assertEquals(expResult[i].getCardnumStr(), result[i].getCardnumStr());
            assertEquals(expResult[i].getCardnum(), result[i].getCardnum());
            assertEquals(expResult[i].getCardsuit(), result[i].getCardsuit());
        }
        System.out.println("--Test passed--");
    }
    /**
     * Test of getRandomCards method, of class CardUtil.
     */
    @Test
    public void testGetRandomCards() {
        System.out.println("getRandomCards");
        int expLength = 10;
        Object[] result = CardUtil.getRandomCards();
        assertEquals(expLength, result.length);
        for (Object c : result) {
            assertThat(c, instanceOf(Card.class));
        }
        System.out.println("--Test passed--");
    }
    
    

    /**
     * Test of getBestHand method, of class CardUtil.
     */
    @Test
    public void testGetBestHandRoyalFlush() {
        System.out.println("getBestHandRoyalFlush");
        String[] cardnums =  new String[] {"A", "Q", "K", "10", "J"};
        String[] cardsuits = new String[] {"H", "H", "H", "H", "H"};
        Card[] hand = CardUtil.generateHand(cardnums,cardsuits);
        int[] expResult = new int[] {CardUtil.ROYAL_FLUSH};
        int[] result = CardUtil.getBestHand(hand);
        assertArrayEquals(expResult, result);
        System.out.println("--Test passed--");
    }
    
    

    /**
     * Test of getBestHand method, of class CardUtil.
     */
    @Test
    public void testGetBestHandStraightFlush() {
        System.out.println("getBestHandStraightFlush");
        String[] cardnums =  new String[] {"3", "7", "4", "5", "6"};
        String[] cardsuits = new String[] {"D", "D", "D", "D", "D"};
        Card[] hand = CardUtil.generateHand(cardnums,cardsuits);
        int[] expResult = new int[] {CardUtil.STRAIGHT_FLUSH, 7};
        int[] result = CardUtil.getBestHand(hand);
        assertArrayEquals(expResult, result);
        System.out.println("--Test passed--");
    }
    
    

    /**
     * Test of getBestHand method, of class CardUtil.
     */
    @Test
    public void testGetBestHandStraightFlushFiveHigh() {
        System.out.println("getBestHandStraightFlushFiveHigh");
        String[] cardnums =  new String[] {"3", "5", "A", "2", "4"};
        String[] cardsuits = new String[] {"S", "S", "S", "S", "S"};
        Card[] hand = CardUtil.generateHand(cardnums,cardsuits);
        int[] expResult = new int[] {CardUtil.STRAIGHT_FLUSH, 5};
        int[] result = CardUtil.getBestHand(hand);
        assertArrayEquals(expResult, result);
        System.out.println("--Test passed--");
    }

    /**
     * Test of getBestHand method, of class CardUtil.
     */
    @Test
    public void testGetBestHandFourOfAKind() {
        System.out.println("getBestHandFourOfAKind");
        String[] cardnums =  new String[] {"Q", "2", "Q", "Q", "Q"};
        String[] cardsuits = new String[] {"D", "H", "H", "S", "C"};
        Card[] hand = CardUtil.generateHand(cardnums,cardsuits);
        int[] expResult = new int[] {CardUtil.FOUR_OF_A_KIND, 12, 2};
        int[] result = CardUtil.getBestHand(hand);
        assertArrayEquals(expResult, result);
        System.out.println("--Test passed--");
    }

    /**
     * Test of getBestHand method, of class CardUtil.
     */
    @Test
    public void testGetBestHandFullHouse() {
        System.out.println("getBestHandFullHouse");
        String[] cardnums =  new String[] {"3", "7", "3", "3", "7"};
        String[] cardsuits = new String[] {"D", "H", "H", "S", "C"};
        Card[] hand = CardUtil.generateHand(cardnums,cardsuits);
        int[] expResult = new int[] {CardUtil.FULL_HOUSE, 3, 7};
        int[] result = CardUtil.getBestHand(hand);
        assertArrayEquals(expResult, result);
        System.out.println("--Test passed--");
    }
    
    /**
     * Test of getBestHand method, of class CardUtil.
     */
    @Test
    public void testGetBestHandFlush() {
        System.out.println("getBestHandFlush");
        String[] cardnums =  new String[] {"3", "7", "10", "2", "9"};
        String[] cardsuits = new String[] {"C", "C", "C", "C", "C"};
        Card[] hand = CardUtil.generateHand(cardnums,cardsuits);
        int[] expResult = new int[] {CardUtil.FLUSH, 10, 9, 7, 3, 2};
        int[] result = CardUtil.getBestHand(hand);
        assertArrayEquals(expResult, result);
        System.out.println("--Test passed--");
    }
    
    

    /**
     * Test of getBestHand method, of class CardUtil.
     */
    @Test
    public void testGetBestHandStraight() {
        System.out.println("getBestHandStraight");
        String[] cardnums =  new String[] {"9", "J", "10", "K", "Q"};
        String[] cardsuits = new String[] {"D", "H", "H", "S", "C"};
        Card[] hand = CardUtil.generateHand(cardnums,cardsuits);
        int[] expResult = new int[] {CardUtil.STRAIGHT, 13};
        int[] result = CardUtil.getBestHand(hand);
        assertArrayEquals(expResult, result);
        System.out.println("--Test passed--");
    }
    
    /**
     * Test of getBestHand method, of class CardUtil.
     */
    @Test
    public void testGetBestHandStraightFiveHigh() {
        System.out.println("getBestHandStraightFiveHigh");
        String[] cardnums =  new String[] {"4", "2", "3", "A", "5"};
        String[] cardsuits = new String[] {"D", "H", "H", "S", "C"};
        Card[] hand = CardUtil.generateHand(cardnums,cardsuits);
        int[] expResult = new int[] {CardUtil.STRAIGHT, 5};
        int[] result = CardUtil.getBestHand(hand);
        assertArrayEquals(expResult, result);
        System.out.println("--Test passed--");
    }

    /**
     * Test of getBestHand method, of class CardUtil.
     */
    @Test
    public void testGetBestHandThreeOfAKind() {
        System.out.println("getBestHandThreeOfAKind");
        String[] cardnums =  new String[] {"3", "10", "3", "3", "A"};
        String[] cardsuits = new String[] {"D", "H", "H", "S", "C"};
        Card[] hand = CardUtil.generateHand(cardnums,cardsuits);
        int[] expResult = new int[] {CardUtil.THREE_OF_A_KIND, 3, 14, 10};
        int[] result = CardUtil.getBestHand(hand);
        assertArrayEquals(expResult, result);
        System.out.println("--Test passed--");
    }
    
    /**
     * Test of getBestHand method, of class CardUtil.
     */
    @Test
    public void testGetBestHandTwoPairs() {
        System.out.println("getBestHandTwoPairs");
        String[] cardnums =  new String[] {"3", "7", "3", "Q", "7"};
        String[] cardsuits = new String[] {"D", "H", "H", "S", "C"};
        Card[] hand = CardUtil.generateHand(cardnums,cardsuits);
        int[] expResult = new int[] {CardUtil.TWO_PAIRS, 7, 3, 12};
        int[] result = CardUtil.getBestHand(hand);
        assertArrayEquals(expResult, result);
        System.out.println("--Test passed--");
    }
    
    

    /**
     * Test of getBestHand method, of class CardUtil.
     */
    @Test
    public void testGetBestHandOnePair() {
        System.out.println("getBestHandOnePair");
        String[] cardnums =  new String[] {"8", "7", "8", "2", "A"};
        String[] cardsuits = new String[] {"D", "H", "H", "S", "C"};
        Card[] hand = CardUtil.generateHand(cardnums,cardsuits);
        int[] expResult = new int[] {CardUtil.ONE_PAIR, 8, 14, 7, 2};
        int[] result = CardUtil.getBestHand(hand);
        assertArrayEquals(expResult, result);
        System.out.println("--Test passed--");
    }
    
    

    /**
     * Test of getBestHand method, of class CardUtil.
     */
    @Test
    public void testGetBestHandHighCard() {
        System.out.println("getBestHandHighCard");
        String[] cardnums =  new String[] {"3", "10", "9", "2", "K"};
        String[] cardsuits = new String[] {"D", "H", "H", "S", "C"};
        Card[] hand = CardUtil.generateHand(cardnums,cardsuits);
        int[] expResult = new int[] {CardUtil.HIGH_CARD, 13, 10, 9, 3, 2};
        int[] result = CardUtil.getBestHand(hand);
        assertArrayEquals(expResult, result);
        System.out.println("--Test passed--");
    }

    /**
     * Test of verifyUniqueCards method, of class CardUtil.
     */
    @Test
    public void testVerifyUniqueCards() {
        System.out.println("verifyUniqueCards");
        String[] cardnums =  new String[] {"3", "5", "8", "3", "3"};
        String[] cardsuits = new String[] {"D", "S", "H", "S", "C"};
        Card[] player1cards = CardUtil.generateHand(cardnums,cardsuits);
        cardnums =  new String[] {"7", "3", "8", "5", "A"};
        cardsuits = new String[] {"H", "H", "S", "S", "C"};
        Card[] player2cards = CardUtil.generateHand(cardnums,cardsuits);
        boolean expResult = false;
        boolean result = CardUtil.verifyUniqueCards(player1cards, player2cards);
        assertEquals(expResult, result);
        System.out.println("--Test duplicate passed--");
        
        player2cards[3] = new Card("5", "D");
        expResult = true;
        result = CardUtil.verifyUniqueCards(player1cards, player2cards);
        assertEquals(expResult, result);
        System.out.println("--Test unique passed--");
    }

    /**
     * Test of determineWinner method, of class CardUtil.
     */
    @Test
    public void testDetermineWinner() {
        System.out.println("determineWinner");
        String[] cardnums =  new String[] {"3", "5", "3", "3", "3"};
        String[] cardsuits = new String[] {"D", "D", "H", "S", "C"};
        Card[] player1cards = CardUtil.generateHand(cardnums,cardsuits);
        cardnums =  new String[] {"7", "8", "6", "5", "4"};
        cardsuits = new String[] {"H", "H", "S", "S", "C"};
        Card[] player2cards = CardUtil.generateHand(cardnums,cardsuits);
        String player1name = "Jason";
        String player2name = "George";
        String expResult = "Player 1 (Jason) won the game with Four of a Kind!";
        String result = CardUtil.determineWinner(player1cards, player2cards, player1name, player2name);
        assertEquals(expResult, result);
        System.out.println("--Test Player 1 wins passed--");
        
        player1cards[2] = new Card("4", "H");
        expResult = "Player 2 (George) won the game with a Straight!";
        result = CardUtil.determineWinner(player1cards, player2cards, player1name, player2name);
        assertEquals(expResult, result);
        System.out.println("--Test Player 2 wins passed--");
        
        cardnums =  new String[] {"6", "7", "4", "5", "8"};
        cardsuits = new String[] {"D", "S", "S", "H", "S"};
        player1cards = CardUtil.generateHand(cardnums,cardsuits);
        expResult = "Both players have equal hands.";
        result = CardUtil.determineWinner(player1cards, player2cards, player1name, player2name);
        assertEquals(expResult, result);
        System.out.println("--Test Draw passed--");
    }

    
}
