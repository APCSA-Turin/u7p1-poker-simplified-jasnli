package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;


public class Game{
    public static void main(String[] args) {
        play();
    }
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        String player1 = p1.playHand(communityCards);
        String player2 = p2.playHand(communityCards);
        System.out.println(player1);
        System.out.println(player2);
        if (Utility.getHandRanking(player1) > Utility.getHandRanking(player2)) {
            return "Player 1 wins!";
        } else if (Utility.getHandRanking(player2) > Utility.getHandRanking(player1)) {
            return "Player 2 wins!";
        } else {
            if (player1.equals("Nothing")) {
                System.out.println(p1.findNothingHighCard());
                System.out.println(p2.findNothingHighCard());
                if (p1.findNothingHighCard() > p2.findNothingHighCard()) {
                    return "Player 1 wins!";
                } else if (p1.findNothingHighCard() < p2.findNothingHighCard()) {
                    return "Player 2 wins!";
                }
                return "Tie!";
            }
            if (player1.equals("High Card")) {
                System.out.println(p1.getHigh());
                System.out.println(p2.getHigh());
                if (Utility.getRankValue(p1.getHigh().getRank()) > Utility.getRankValue(p2.getHigh().getRank())) {
                    return "Player 1 wins!";
                } else if (Utility.getRankValue(p1.getHigh().getRank()) < Utility.getRankValue(p2.getHigh().getRank())) {
                    return "Player 2 wins!";
                } else {
                    return "Tie!";
                }
            }
            if (player1.equals("A Pair")) {
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
            if (player1.equals("Two Pair")) {
                if (Utility.getRankValue(p1.getHighPair()) > Utility.getRankValue(p2.getHighPair())) {
                    return "Player 1 wins!";
                } else if (Utility.getRankValue(p1.getHighPair()) < Utility.getRankValue(p2.getHighPair())) {
                    return "Player 2 wins!";
                } else {
                    return "Tie!";
                }
            }
            if (player1.equals("Three of a Kind")) {
                if (p1.getThree() > p2.getThree())  {
                    return "Player 1 wins!";
                } else if (p2.getThree() < p1.getThree()) {
                    return "Player 2 wins!";
                } else {
                    return "Tie!";
                }
            }
            if (player1.equals("Straight")) {
                if (p1.getHighStraight() > p2.getHighStraight()) {
                    return "Player 1 wins!";
                } else if (p2.getHighStraight() > p1.getHighStraight()) {
                    return "Player 2 wins!";
                } else {
                    return "Tie!";
                }
            }
            if (player1.equals("Flush")) {
                if (p1.getHighFlush() > p2.getHighFlush()) {
                    return "Player 1 wins!";
                } else if (p2.getHighFlush() > p1.getHighFlush()) {
                    return "Player 2 wins!";
                } else {
                    return "Tie!";
                }
            }
            if (player1.equals("Full House")) {
                if (p1.getThree() > p2.getThree()) {
                    return "Player 1 wins!";
                } else if (p1.getThree() < p2.getThree()) {
                    return "Player 2 wins!";
                } else {
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
        Player player1 = new Player();
        Player player2 = new Player();
        
        player1.addCard(new Card("7", "♠"));
        player1.addCard(new Card("10", "♠"));
  
        player2.addCard(new Card("A", "♠"));
        player2.addCard(new Card("3", "♠"));

        
        // Community cards that could help form the flush
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("J", "♠")); // Player 1 completes the flush with this card
        communityCards.add(new Card("J", "♥"));
        communityCards.add(new Card("Q", "♠"));
        
        // Player results after playing the hand
        String p1Result = player1.playHand(communityCards);
        String p2Result = player2.playHand(communityCards);
        
        // Determine the winner
        String winner = Game.determineWinner(player1, player2, p1Result, p2Result, communityCards);
        System.out.println(winner);
    }
        
        

}