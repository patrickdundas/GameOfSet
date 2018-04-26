//Patrick Dundas cs110

import java.util.ArrayList;

public class Board {
    ArrayList<ArrayList<BoardSquare>> board = new ArrayList<ArrayList<BoardSquare>>();

    /**
     * Create a new board from a deck of cards, 3 rows, 4 columns
     * @param deck the deck to create the board from
     */
    public Board(Deck deck){

        for(int row=0; row < 3; row++){
            board.add(new ArrayList<BoardSquare>());
            for(int column = 0; column < 4; column++){

                board.get(row).add(new BoardSquare(deck.getTopCard(), row, column));
            }
        }
    }

    /**
     * Replaces a card on the board, given the index, and the new card
     * @param newCard the new card to place at that position
     * @param row the position's row
     * @param column the position's column
     */
    public void replaceCard(Card newCard, int row, int column){
        board.get(row).set(column, new BoardSquare(newCard, row, column));
    }

    /**
     * Returns the board's boardsquare at the given index row and column
     * @param row the given row
     * @param column the given column
     * @return the boardsquare at that location
     */
    public BoardSquare getBoardSquare(int row, int column){
        return board.get(row).get(column);
    }

    /**
     * Add's three cards to the board
     * @param deck the deck to deal the cards from
     */
    public void add3(Deck deck){
        int foundCols = this.getCols();
        for(int i = 0; i <= 2; i++){
            board.get(i).add(new BoardSquare(deck.getTopCard(),i,foundCols));
        }
    }

    /**
     * Get the card object at a given location row and column
     * @param row the given row for the location
     * @param column the given column for the location
     * @return the card at that location
     */
    public Card getCard(int row, int column){
        return board.get(row).get(column).getCard();
    }

    /**
     * Tells you how many rows are on the board
     * @return the number of rows in the board
     */

    public int getRows(){
        return board.size();
    }

    /**
     * Tells you the number of columns on the board
     * @return the number of columns in the board
     */

    public int getCols(){
        int biggest=0;
        for(int i=0; i<= board.size()-1; i++){
            int columns = board.get(i).size();
            if(columns>biggest){
                biggest=columns;
            }
        }
        return biggest;
    }

    /**
     * The overridden toString method for board, to return the cards in a board-like format as strings
     * @return all of the cards in the board, based on position and cards as a string
     */

    @Override
    public String toString(){
        String boardString = "";
        for(int row = 0; row < board.size(); row++){
            for(int col = 0; col < board.get(row).size(); col++){
                boardString += board.get(row).get(col).getCard().toString() + "          ";
            }
            boardString += "\n";
        }

        return boardString;
    }

}
