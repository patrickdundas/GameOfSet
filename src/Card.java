//Patrick Dundas cs110
public class Card {
    enum Color{RED, PURPLE, GREEN}
    enum Fill{OUTLINE, SOLID, STRIPED}
    enum Shape{OVAL, DIAMOND, TRIANGLE}
    enum Number{ONE, TWO, THREE}

    private Color cardColor;
    private Fill cardFill;
    private Shape cardShape;
    private Number cardNumber;

    //construct a card

    /**
     * Create a new card with given properties
     * @param color the color for the card
     * @param fill the fill for the card
     * @param shape the shape for the card
     * @param number the number of shapes for the card
     */
    public Card(Color color, Fill fill, Shape shape, Number number ){
        cardColor = color;
        cardFill = fill;
        cardShape = shape;
        cardNumber = number;
    }

    /**
     * Get the number of shapes on the card
     * @return the number of shapes
     */
    public Number getNumber(){
        return this.cardNumber;
    }

    /**
     * Get the fill of the card
     * @return fill of the card
     */
    public Fill getFill(){
        return this.cardFill;
    }

    /**
     * Get the shape of the card
     * @return shape of card
     */
    public Shape getShape(){
        return this.cardShape;
    }

    /**
     * Get the color of card
     * @return the color of the card
     */
    public Color getColor(){
        return this.cardColor;
    }

    /**
     * Check if three cards are a set
     * @param card1 first card to compare
     * @param card2 second to compare
     * @param card3 third to compare
     * @return true if set, false otherwise
     */
    public static boolean checkSet(Card card1, Card card2, Card card3){
        if(!((card1.cardNumber == card2.cardNumber) && (card2.cardNumber == card3.cardNumber) || (card1.cardNumber != card2.cardNumber) && (card2.cardNumber != card3.cardNumber) &&(card2.cardNumber != card3.cardNumber))){
            return false;
        }
        if(!((card1.cardShape == card2.cardShape) && (card2.cardShape == card3.cardShape) || (card1.cardShape != card2.cardShape) && (card2.cardShape != card3.cardShape) &&(card2.cardShape != card3.cardShape))){
            return false;
        }
        if(!((card1.cardColor == card2.cardColor) && (card2.cardColor == card3.cardColor) || (card1.cardColor != card2.cardColor) && (card2.cardColor != card3.cardColor) &&(card2.cardColor != card3.cardColor))){
            return false;
        }
        if(!((card1.cardFill == card2.cardFill) && (card2.cardFill == card3.cardFill) || (card1.cardFill != card2.cardFill) && (card2.cardFill != card3.cardFill) &&(card2.cardFill != card3.cardFill))){
            return false;
        }

        return true;
    }

    /**
     * Tostring method for the card
     * @return the card as a string
     */
    @Override
    public String toString() {
        return this.getNumber() + " " + this.getColor() + " " + this.getFill() + " " + this.getShape();
    }
}
