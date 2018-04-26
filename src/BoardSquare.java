//Patrick Dundas cs110

public class BoardSquare {
    //instance vars
    private int row;
    private int col;
    private Card c;
    private boolean selected=false;

    /**
     * Create a board square with a card and position
     * @param c the card to place
     * @param row the row to place the card at
     * @param col the column to place the card at
     */

    public BoardSquare(Card c, int row, int col){
        this.c = c;
        this.row = row;
        this.col = col;
    }

    /**
     * Set the row for the boardsquare
     * @param rowNumber the row to set the boardsquare to
     */
    public void setRow(int rowNumber){
        row = rowNumber;
    }

    /**
     * Get the row of a boardsquare
     * @return the row of the boardsquare
     */
    public int getRow(){
        return row;
    }

    /**
     * Set the column for the boardsquare
     * @param colNumber the column to set the boardsquare to
     */
    public void setCol(int colNumber){
        col = colNumber;
    }

    /**
     * Get the column for the boardsquare
     * @return
     */
    public int getCol(){
        return col;
    }

    /**
     * Set the card of the boardsquare
     * @param otherCard the card to place on the square
     */
    public void setCard(Card otherCard){
        c = otherCard;
    }

    /**
     * Get the card in the boardsquare
     * @return the card in the boardsquare
     */
    public Card getCard(){
        return c;
    }

    /**
     * Get the currently selected boardsquare
     * @return the currently selected boardsquare
     */
    public boolean getSelected(){
        return selected;
    }

    /**
     * Set the selected boardsquare
     * @param newSelection the boardsquare to select
     */
    public void setSelected(boolean newSelection){
        selected=newSelection;
    }

    @Override
    public String toString(){
        return c.toString();
    }

}
