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

/**
 *
 * @author lizho
 */
public class CardTest {
    
    public CardTest() {
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
     * Test of compareTo method, of class Card.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Card o = new Card("2", "H");
        Card instance = new Card("5", "D");
        int expResult = 3;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCardnum method, of class Card.
     */
    @Test
    public void testGetCardnum() {
        System.out.println("getCardnum");
        Card instance = new Card("A", "C");
        int expResult = 14;
        int result = instance.getCardnum();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCardnumStr method, of class Card.
     */
    @Test
    public void testGetCardnumStr() {
        System.out.println("getCardnumStr");
        Card instance = new Card("K", "D");
        String expResult = "K";
        String result = instance.getCardnumStr();
        assertEquals(expResult, result);
        instance = new Card("2", "C");
        expResult = "2";
        result = instance.getCardnumStr();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCardStr method, of class Card.
     */
    @Test
    public void testGetCardStr() {
        System.out.println("getCardStr");
        Card instance = new Card("4", "H");
        String expResult = "4H";
        String result = instance.getCardStr();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCardsuit method, of class Card.
     */
    @Test
    public void testGetCardsuit() {
        System.out.println("getCardsuit");
        Card instance = new Card("8", "D");
        String expResult = "D";
        String result = instance.getCardsuit();
        assertEquals(expResult, result);
    }
    
}
