package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){    
        for (Card card : communityCards) {
            allCards.add(card);
        }
        for (Card card : hand) {
            allCards.add(card);
        }
        SortCards();
        for (int i = 0 ; i < allCards.size() ; i ++ ) {
            if (allCards.get(i) )
        }
        

        
        return "Nothing";
    }

    public void SortCards(){
        for (int i = 0; i < allCards.size() ; i++) {
            for (j = i; j < allCards.size() ; j++) {
                if (allCards.get(j) < allCards.get(i)) {
                    allCards.set(i, allCards.set(j, allCards.get(i)));
                }
            }
        }
    } 

    public ArrayList<Integer> findRankingFrequency(){
        return new ArrayList<>(); 
    }

    public ArrayList<Integer> findSuitFrequency(){
        return new ArrayList<>(); 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }

    private Card getHigh() {
        Card highCard = new Card(ranks[0], suits[3]);
        for (Card card : allCards) {
            if (Utility.getRankValue(card) > Utility.getRankValue(highCard)) {
                highCard = card;
            }
        }
        return highCard;
    }

    private boolean checkForDupes() {
        for (Card card : )
    }

    private void removeDupes() {
        
    }




}
