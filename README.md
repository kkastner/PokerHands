# PokerHands
Developed by Kevin Kastner
A small Native GUI application that determines the winner of two poker players.

##############################################

PokerHands is a small program which determines the winner of a poker game between two players based on the cards that they possess.

Each of the player's names can be entered in their associated text boxes, if desired.

Each card for each player can be entered using the drop-down boxes. The left one sets the card's value (2-10, J, Q, K, A),
while the right one sets the card's suit (H, S, D, C).

In addition, both a Randomize command and button have been added which will randomly pick cards from the deck for both players.

##############################################

Written in Java 8 using the Netbeans IDE version 8.2.

Everything that is needed to build and run the project should be included in this directory.
While it was created using the Netbeans IDE, it should work with any IDE as long as you import all of the files located
within into the IDE, though this has not been tested.
The main Project source files are PokerHandsUI.java, Card.java, and CardUtil.java.
PokerHandsUI creates the actual GUI, while calling upon the Card class and methods from the CardUtil class
to enable its functionality. The actual GUI appearance was created using the Netbeans GUI Builder.

The project also includes JUnit tests for the Card and CardUtil class methods, and have all passed for the existing implementation.
