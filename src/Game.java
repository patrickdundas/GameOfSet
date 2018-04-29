/**
 * Patrick Dundas CS110
 */

import java.util.ArrayList;

/**
 * Class game creates a new game containing a board and deck, as well as an arraylist of boardsquares
 */
public class Game {
    private Deck d = new Deck();
    private Board b;
    private ArrayList<BoardSquare> selectedSquares = new ArrayList<>(3);

    /**
     * Default for creating a new game and initializing variables and method calls
     * Do any game creation tasks here, such as shuffling
     */
    public Game(){

        d.shuffle();
        b = new Board(d);

    }

    /**
     * Checks if the board is out of cards or not
     * @return boolean value. True if out of cards, false if more cards are available
     */
    public boolean outOfCards(){

        return d.isEmpty();

    }

    /**
     * Selects a boardsquare to the arraylist of selected squares
     * @param row the row of the square to select
     * @param col the column of the square to select
     */
    public void addToSelected(int row, int col){
        if(row < b.getRows() && col < b.getCols()){
            selectedSquares.add(b.getBoardSquare(row,col));
            b.getBoardSquare(row,col).setSelected(true);

            //System.out.println(row+" clicked. Rows on board: "+b.getRows());
            //System.out.println(col+" clicked. Cols on board: "+b.getCols());
        }
        else{
            System.out.println("Index out of range! Please select a square on the board. ");
            //System.out.println(row+" clicked. Rows on board: "+b.getRows());
            //System.out.println(col+" clicked. Cols on board: "+b.getCols());
        }
    }

    /**
     * Removes a card from the selectedsquares arraylist
     * @param row the row of the boardsquare to unselect
     * @param col the column of the boardsquare to unselect
     */
    public void removeSelected(int row, int col){
        b.getBoardSquare(row,col).setSelected(false);
        selectedSquares.remove(b.getBoardSquare(row,col));
    }

    /**
     * Gets the number of selected boardsquares
     * @return the number of selected boardsquares as an int
     */
    public int numSelected(){

        return selectedSquares.size();

    }

    /**
     * Test the selected cards to see if they are a set.
     */
    public void testSelected(boolean replaceCardsIfSet){

        //if they are a set
        if(Card.checkSet(selectedSquares.get(0).getCard(),selectedSquares.get(1).getCard(),selectedSquares.get(2).getCard())){
            for(int i=0; i<3;i++){
                //unselect the boardsquares
                b.getBoardSquare(selectedSquares.get(i).getRow(),selectedSquares.get(i).getCol()).setSelected(false);

                //should the cards be replaced if they are a set
                if(replaceCardsIfSet == true){
                    //replace the cards in those boardsquares
                    b.replaceCard(d.getTopCard(),selectedSquares.get(i).getRow(),selectedSquares.get(i).getCol());
                }
                else{
                    //replace the cards in the empty squares with cards in the extra row
                }

            }
            System.out.println("Those cards are a set");

        }
        else{
            for(int i=0; i<3;i++){
                //unselect the boardsquares
                b.getBoardSquare(selectedSquares.get(i).getRow(),selectedSquares.get(i).getCol()).setSelected(false);
            }
            System.out.println("Those cards aren't a set");
        }
        //clear selected squares
        selectedSquares.remove(2);
        selectedSquares.remove(1);
        selectedSquares.remove(0);

    }

    /**
     * Add 3 cards to the board (new column)
     */
    public void add3(){
        b.add3(d);
    }

    /**
     * Get the arraylist of selected boardsquares
     * @return the arraylist of selected boardsquares
     */
    public ArrayList getSelected(){

        return selectedSquares;

    }

    /**
     * Get the board
     * @return the board
     */
    public Board getBoard(){
        return b;
    }

    /**
     * Get the deck
     * @return the deck
     */
    public Deck getDeck(){
        return d;
    }

    /**
     * The overridden toString for printing out the board as a string
     * @return the board as a string
     */
    @Override
    public String toString(){
        return b.toString();
    }
}
