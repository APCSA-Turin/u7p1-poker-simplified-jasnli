package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Game{
    public static void main(String[] args) {
        play();
    }
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        // create player hands
        String player1 = p1.playHand(communityCards);
        String player2 = p2.playHand(communityCards);
        // higher hand wins
        if (Utility.getHandRanking(player1) > Utility.getHandRanking(player2)) {
            return "Player 1 wins!";
        } else if (Utility.getHandRanking(player2) > Utility.getHandRanking(player1)) {
            return "Player 2 wins!";
        } else {
            // if the hands are the same
            // nothing will determine which player has the higher card from the nothing
            if (player1.equals("Nothing")) {
                if (p1.findNothingHighCard() > p2.findNothingHighCard()) {
                    return "Player 1 wins!";
                } else if (p1.findNothingHighCard() < p2.findNothingHighCard()) {
                    return "Player 2 wins!";
                }
                return "Tie!";
            }
            // check which player has a higher high card if they both have high card
            if (player1.equals("High Card")) {
                if (Utility.getRankValue(p1.getHigh().getRank()) > Utility.getRankValue(p2.getHigh().getRank())) {
                    return "Player 1 wins!";
                } else if (Utility.getRankValue(p1.getHigh().getRank()) < Utility.getRankValue(p2.getHigh().getRank())) {
                    return "Player 2 wins!";
                } else {
                    return "Tie!";
                }
            }
            // checks which player has the higher pair
            if (player1.equals("A Pair")) {
                // checks the rank value of the player's pair
                if (Utility.getRankValue(p1.getPair().get(0)) > Utility.getRankValue(p2.getPair().get(0))) {
                    return "Player 1 wins!";
                } else if (Utility.getRankValue(p1.getPair().get(0)) < Utility.getRankValue(p2.getPair().get(0))) {
                    return "Player 2 wins!";
                } else {
                    if (p1.findNothingHighCard() > p2.findNothingHighCard()) {
                        return "Player 1 wins!";
                    } else if (p1.findNothingHighCard() < p2.findNothingHighCard()) {
                        return "Player 2 wins!";
                    }
                    return "Tie!";
                }
            }
            // checks which player has the highest pair
            if (player1.equals("Two Pair")) {
                // get the highest pair with the method
                if (Utility.getRankValue(p1.getHighPair()) > Utility.getRankValue(p2.getHighPair())) {
                    return "Player 1 wins!";
                } else if (Utility.getRankValue(p1.getHighPair()) < Utility.getRankValue(p2.getHighPair())) {
                    return "Player 2 wins!";
                } else {
                    return "Tie!";
                }
            }
            // checks which player has the higher three of a kind
            if (player1.equals("Three of a Kind")) {
                if (p1.getThree() > p2.getThree())  {
                    return "Player 1 wins!";
                } else if (p2.getThree() < p1.getThree()) {
                    return "Player 2 wins!";
                } else {
                    return "Tie!";
                }
            }
            // checks which player has the bigger straight
            if (player1.equals("Straight")) {
                if (p1.getHighStraight() > p2.getHighStraight()) {
                    return "Player 1 wins!";
                } else if (p2.getHighStraight() > p1.getHighStraight()) {
                    return "Player 2 wins!";
                } else {
                    return "Tie!";
                }
            }
            // check which player has the highest card in a flush
            if (player1.equals("Flush")) {
                if (p1.getHighFlush() > p2.getHighFlush()) {
                    return "Player 1 wins!";
                } else if (p2.getHighFlush() > p1.getHighFlush()) {
                    return "Player 2 wins!";
                } else {
                    return "Tie!";
                }
            }
            // checks which player has a higher full house
            if (player1.equals("Full House")) {
                // which players three  is bigger
                if (p1.getThree() > p2.getThree()) {
                    return "Player 1 wins!";
                } else if (p1.getThree() < p2.getThree()) {
                    return "Player 2 wins!";
                } else {
                    // which player's pair is bigger if the threes are the same
                    if (Utility.getRankValue(p1.getPair().get(0)) > Utility.getRankValue(p2.getPair().get(0))) {
                        return "Player 1 wins!";
                    } else if (Utility.getRankValue(p1.getPair().get(0)) < Utility.getRankValue(p2.getPair().get(0))) {
                        return "Player 2 wins!";
                    } else {
                        return "Tie!";
                    }
                }
            }
        }
        return "Error";
    }

    public static void play(){ //simulate card playing
        // initialize players & deck
        Player playerOne = new Player();
        Player playerTwo = new Player();
        ArrayList<Card> communityCards = new ArrayList<>();
        Deck deck = new Deck();
        Scanner input = new Scanner(System.in);
        // start game
        System.out.println("-------- 🃏 Welcome to Simplified Poker! 🃏 --------");
        System.out.println("Press enter to start!");
        input.nextLine();
        // draw cards
        System.out.println("-----------------------------------------------------");
        System.out.println("Press enter to draw two cards.");
        input.nextLine();
        playerOne.addCard(deck.drawCard());
        playerTwo.addCard(deck.drawCard());
        playerOne.addCard(deck.drawCard());
        playerTwo.addCard(deck.drawCard());
        System.out.println("Hand: " + playerOne.getHand());    
        System.out.println();
        System.out.println("-----------------------------------------------------");
        // community cards
        System.out.println("Press Enter to See Community Cards");
        input.nextLine();
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                deck.drawCard(); // discard one
            } else {
                communityCards.add(deck.drawCard()); // draw three
            }
        }
        System.out.println("Community Cards: " + communityCards);
        // decide winner
        System.out.println("Press enter to see winner!");
        input.nextLine();
        String winner = determineWinner(playerOne, playerTwo, playerOne.playHand(communityCards), playerTwo.playHand(communityCards), communityCards);
        System.out.println("Player Two Hand: " + playerTwo.getHand());
        System.out.println("You play " + playerOne.playHand(communityCards));
        System.out.println("Player two plays " + playerTwo.playHand(communityCards));
        System.out.println(winner);


    }
        
        

}