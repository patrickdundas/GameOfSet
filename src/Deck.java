//Patrick Dundas cs110

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;
    private final int MAX_CARDS = 81;

    /**
     * Create a new deck holding MAX_CARDS amount of cards, and all possible combinations of the card features
     */
    public Deck(){
        deck = new ArrayList<>(MAX_CARDS);
        int count = 0;
        for(int n = 0; n <= Card.Number.THREE.ordinal(); n++){
            //System.out.println(n);

            for(int f = 0; f <= Card.Fill.STRIPED.ordinal(); f++){
                //System.out.println(f);

                for(int s = 0; s <= Card.Shape.TRIANGLE.ordinal(); s++){
                    //System.out.println(s);

                    for(int c = 0; c <= Card.Color.GREEN.ordinal(); c++){
                        deck.add(new Card(Card.Color.values()[c], Card.Fill.values()[f],Card.Shape.values()[s],Card.Number.values()[n]));
                    }
                }
            }
        }

    }

    /**
     * Get the top card in the deck
     * @return the top card of the deck (index 0)
     */
    public Card getTopCard(){

        Card topCard = deck.get(0);
        deck.remove(0);
        return topCard;
    }

    /**
     * Reorder (shuffle) the cards in the deck in a random order
     */

    public void shuffle(){
        int rand;
        Random r = new Random();
        Card tempCard;
        for(int i = 0; i< deck.size(); i++){
            rand = r.nextInt(deck.size());
            tempCard = deck.get(i);
            deck.set(i, deck.get(rand));
            deck.set(rand, tempCard);
        }

    }

    /**
     * Check if a deck is empty
     * @return true if the deck is empty, false if there are cards in the deck
     */
    public boolean isEmpty(){
        //will return true if size is 0, false otherwise
        return deck.size() == 0;
    }

    /**
     * Get the size of the deck
     * @return the size of the deck
     */
    public int getSize(){

        return deck.size();
    }

    /**
     * The toString method for returning a deck as a string
     * @return the deck as a string, all cards in the deck printed out as strings using each card's toString
     */
    @Override
    public String toString() {
        String deckString = "";
        for(int i = 0; i < deck.size(); i++){
            deckString += deck.get(i).toString()+", ";
        }
        return deckString;
    }
}
