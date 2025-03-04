package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    private boolean handCreated = false;
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){
        if (!handCreated) {
            for (Card card : communityCards) {
                allCards.add(card);
            }
            for (Card card : hand) {
                allCards.add(card);
            }
            handCreated = true;
        }
        sortAllCards();
        // check for royal flush
        if (royal() && flush()) {return "Royal Flush";}
        // check for straight flush
        if (straight() && flush()) {return "Straight Flush";}
        // check for four
        if (four()) {return "Four of a Kind";}
        // check for full house
        if (three() && getNumPairs() == 1) {return "Full House";}
        // check for flush
        if (flush()) {return "Flush";}
        // check for straight
        if (straight()) {return "Straight";}
        // check for three of a kind
        if (three()) {return "Three of a Kind";}
        // check for pairs
        if (getNumPairs() > 0) {
            if (getNumPairs() == 2) {return "Two Pair";}
            if (getNumPairs() == 1) {return "A Pair";}
        }
        // high card
        if (!nothing()) {return "High Card";}
        return "Nothing";
    }

<<<<<<< HEAD
    public void sortAllCards(){
        for (int i = 0; i < allCards.size() ; i++) {
            for (int j = i; j < allCards.size() ; j++) {
                if (Utility.getRankValue(allCards.get(j).getRank()) < Utility.getRankValue(allCards.get(i).getRank())) {
                    allCards.set(i, allCards.set(j, allCards.get(i)));
                }
            }
        }
    } 
=======
    public void sortAllCards(){} 
>>>>>>> upstream/main

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> freqList = new ArrayList<>();
        for (int i = 0 ; i < ranks.length ; i++ ) {
            freqList.add(0);
        }
        for (int i = 0; i < ranks.length ; i++ ) {
            for (Card card : allCards) {
                if (card.getRank().equals(ranks[i])) {
                    freqList.set(i, freqList.get(i) + 1);
                }
            }
        }
        return freqList; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> freqList = new ArrayList<>();
        for (int i = 0 ; i < suits.length ; i++ ) {
            freqList.add(0);
        }
        for (int i = 0; i < suits.length ; i++ ) {
            for (Card card : allCards) {
                if (card.getSuit().equals(suits[i])) {
                    freqList.set(i, freqList.get(i) + 1);
                }
            }
        }
        return freqList; 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }


    // PRIVATE METHODS
    // check royal
    private boolean royal() {
        if (straight()) {
            if (Utility.getRankValue(allCards.get(4).getRank()) == 14) {
                return true;
            }
        }
        return false;
    }
    // check four
    private boolean four() {
        for (int i : findRankingFrequency()) {
            if (i == 4) {
                return true;
            }
        }
        return false;
    }
    // get high flush
    public int getHighFlush() {
        String flushSuit = "false";
        for (int i = 0 ; i < findSuitFrequency().size() ; i ++ ) {
            if (findSuitFrequency().get(i) == 5) {
                flushSuit = suits[i];
            }
        }
        for (int i = allCards.size() - 1; i >= 0 ; i -- ) {
            if (allCards.get(i).getSuit().equals(flushSuit)) {
                return Utility.getRankValue(allCards.get(i).getRank());
            }
        }
        return 0;
    }
    // check flush
    private boolean flush() {
        for (int i : findSuitFrequency()) {
            if (i == 5) {
                return true;
            }
        }
        return false;
    }
    // get straight
    public int getHighStraight() {
        int inARow = 0;
        for (int i = 0 ; i < findRankingFrequency().size() ; i ++ ) {
            if (findRankingFrequency().get(i) == 1) {
                inARow++;
            } else {
                inARow = 0;
            }
            if (inARow == 5) {
                return Utility.getRankValue(ranks[i]);
            }
        }
        return 0;
    }
    // check straight
    private boolean straight() {
        int inARow = 0;
        for (int i = 0 ; i < findRankingFrequency().size() ; i ++ ) {
            if (findRankingFrequency().get(i) == 1) {
                inARow++;
            } else {
                inARow = 0;
            }
            if (inARow == 5) {
                return true;
            }
        }
        return false;
    }
    // get three 
    public int getThree() {
        for (int i = 0 ; i < findRankingFrequency().size() ; i ++) {
            if (findRankingFrequency().get(i) == 3) {
                return Utility.getRankValue(ranks[i]);
            }
        }
        return 0;
    }
    // check three
    private boolean three() {
        for (int i : findRankingFrequency()) {
            if (i == 3) {
                return true;
            }
        }
        return false;
    }
    // get the higher pair
    public String getHighPair() {
        if (getNumPairs() == 2) {
            ArrayList<String> pairList = getPair();
            if (Utility.getRankValue(pairList.get(0)) > Utility.getRankValue(pairList.get(1))) {
                return pairList.get(0);
            }
            return pairList.get(1);
        }
        return "";
        
    }
    // check for num pairs
    private int getNumPairs() {
        ArrayList<String> pairList = getPair();
        return pairList.size();
    }
    // for pair
    public ArrayList<String> getPair() {
        ArrayList<Integer> freqList = findRankingFrequency();
        ArrayList<String> pairList = new ArrayList<>();
        for (int i  = 0 ; i < freqList.size() ; i ++ ) {
            if (freqList.get(i) == 2) {
                pairList.add(ranks[i]);
            }
        }
        return pairList;
    } 
    // for high card
    public Card getHigh() {
        sortAllCards();
        return allCards.get(4);
    }
    // check for nothing
    private boolean nothing() {
        int highestValue = 0;
        for (Card i : hand) { 
            if (Utility.getRankValue(i.getRank()) > highestValue) {
                highestValue = Utility.getRankValue(i.getRank());
            }
        }
        if (highestValue < Utility.getRankValue(allCards.get(4).getRank()))  {
            return true;
        }
        return false;
    }

    public int findNothingHighCard() {
        int highCard = 0;
        for (Card c : hand) {
            if (Utility.getRankValue(c.getRank()) > highCard) {
                highCard = Utility.getRankValue(c.getRank());
            }
        }
        return highCard;
    }
}
