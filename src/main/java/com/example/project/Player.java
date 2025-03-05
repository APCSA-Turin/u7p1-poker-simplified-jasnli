package com.example.project;
import java.util.ArrayList;


public class Player{
    // intialize the hand, the combination, and the suits and ranks
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    private boolean handCreated = false;
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    // constructor
    public Player(){
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    // getter methods
    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    // add a card to your hand
    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){
        // deals the hand first, and adds it to allCards
        if (!handCreated) {
            for (Card card : communityCards) {
                allCards.add(card);
            }
            for (Card card : hand) {
                allCards.add(card);
            }
            handCreated = true;
        }

        // sort
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
            // check for which pair
            if (getNumPairs() == 2) {return "Two Pair";}
            if (getNumPairs() == 1) {return "A Pair";}
        }
        // high card
        if (!nothing()) {return "High Card";}
        // last resort nothing
        return "Nothing";
    }

    // sorts all cards
    public void sortAllCards(){
        // go through everything (BUBBLE SORT)
        for (int i = 0; i < allCards.size() ; i++) {
            for (int j = i; j < allCards.size() ; j++) {
                // if it is lower, put it at that spot
                if (Utility.getRankValue(allCards.get(j).getRank()) < Utility.getRankValue(allCards.get(i).getRank())) {
                    allCards.set(i, allCards.set(j, allCards.get(i)));
                }
            }
        }
    } 

    // gives a list of how often each card is in your combined hand, corresponding to the ranks array
    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> freqList = new ArrayList<>();
        // add a zero for each first
        for (int i = 0 ; i < ranks.length ; i++ ) {
            freqList.add(0);
        }
        // goes through every rank and checks if that's in your deck
        for (int i = 0; i < ranks.length ; i++ ) {
            for (Card card : allCards) {
                if (card.getRank().equals(ranks[i])) {
                    // adds +1 if it is
                    freqList.set(i, freqList.get(i) + 1);
                }
            }
        }
        return freqList; 
    }

    // same as above but for the suits list
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
        // if it is straight
        if (straight()) {
            // and if the highest rank is 14
            if (Utility.getRankValue(allCards.get(4).getRank()) == 14) {
                return true;
            }
        }
        return false;
    }
    // check four
    private boolean four() {
        // if there are any fours in the frequency
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
        // find the suit that the flush is part of
        for (int i = 0 ; i < findSuitFrequency().size() ; i ++ ) {
            if (findSuitFrequency().get(i) == 5) {
                flushSuit = suits[i];
            }
        }
        // get the number in your hand that is the highest value in that suit
        for (int i = allCards.size() - 1; i >= 0 ; i -- ) {
            if (allCards.get(i).getSuit().equals(flushSuit)) {
                return Utility.getRankValue(allCards.get(i).getRank());
            }
        }
        return 0;
    }
    // check flush
    private boolean flush() {
        // if the suit frequency is ever 5
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
        // check for 5 in a row, if it gets disrupted at any point, reset the counter to 0
        for (int i = 0 ; i < findRankingFrequency().size() ; i ++ ) {
            if (findRankingFrequency().get(i) == 1) {
                inARow++;
            } else {
                inARow = 0;
            }
            // if the fifth in a row is detected, return that value as the highest straight
            if (inARow == 5) {
                return Utility.getRankValue(ranks[i]);
            }
        }
        return 0;
    }
    // check straight
    private boolean straight() {
        int inARow = 0;
        // checks for 5 in a row, if it gets disrupted, reset the counter
        for (int i = 0 ; i < findRankingFrequency().size() ; i ++ ) {
            if (findRankingFrequency().get(i) == 1) {
                inARow++;
            } else {
                inARow = 0;
            }
            // if it reaches 5 without disruption, then it is a straight
            if (inARow == 5) {
                return true;
            }
        }
        return false;
    }
    // get three 
    public int getThree() {
        // check for a frequency of three and find that rank
        for (int i = 0 ; i < findRankingFrequency().size() ; i ++) {
            if (findRankingFrequency().get(i) == 3) {
                return Utility.getRankValue(ranks[i]);
            }
        }
        return 0;
    }
    // check three
    private boolean three() {
        // check for a frequency of 3
        for (int i : findRankingFrequency()) {
            if (i == 3) {
                return true;
            }
        }
        return false;
    }
    // get the higher pair
    public String getHighPair() {
        // check if the number of pairs is 2 with the other method
        if (getNumPairs() == 2) {
            ArrayList<String> pairList = getPair();
            // find the higher of the two pairs
            if (Utility.getRankValue(pairList.get(0)) > Utility.getRankValue(pairList.get(1))) {
                return pairList.get(0);
            }
            return pairList.get(1);
        }
        return "";
        
    }
    // check for num pairs
    private int getNumPairs() {
        // get the number of pairs by using the list size
        ArrayList<String> pairList = getPair();
        return pairList.size();
    }
    // for pair
    public ArrayList<String> getPair() {
        // will return all occurances of 2 in the frequency list but the ranks of them
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
        // returns the last card, which is the biggest
        sortAllCards();
        return allCards.get(4);
    }
    // check for nothing
    private boolean nothing() {
        int highestValue = 0;
        for (Card i : hand) { 
            // check if your hand has a card higher than the one in the community cards
            if (Utility.getRankValue(i.getRank()) > highestValue) {
                highestValue = Utility.getRankValue(i.getRank());
            }
        }
        if (highestValue < Utility.getRankValue(allCards.get(4).getRank()))  {
            return true;
        }
        return false;
    }

    // high card for when both players have nothing
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
