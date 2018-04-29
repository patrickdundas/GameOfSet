/**
 * Patrick Dundas CS110
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Main GUI Class for JavaFX and Game class
 */

public class GUI extends Application
{
    //Nodes and Objects
    private Game game;
    private Pane headerPane;
    private Pane menuPane;
    private GridPane cardGrid;
    private BorderPane generalLayout;
    private VBox cardBox;
    private Button add3Button;
    private Button exitButton;
    private Pane statusPane;
    private Label cardsRemaining;


    //Positioning variables
    private final int DEF_CARD_VGAP = 20;
    private final int DEF_CARD_HGAP = 20;
    private final int DEF_CARD_GRID_PADDING = 20;
    private final int DEF_WINDOW_WIDTH = 900;
    private final int DEF_WINDOW_HEIGHT = 850;
    private final int DEF_MENUPANE_HEIGHT = 45;

    //Maximum number of cards on board at one time
    private final int DEF_MAX_CARDS = 18;

    //Keeps track of the number of cards
    private int defaultNumCards;
    private int currentNumCards;

    /**
     * Start the GUI and display nodes and objects
     * @param primaryStage javafx stage
     * @throws Exception exception if throne
     */
    @Override
    public void start(Stage primaryStage) throws Exception {


        // create game
        game = new Game();

        //layout and panes
        generalLayout = new BorderPane();
        cardGrid = new GridPane();
        statusPane = new Pane();


        //set the default number of cards to the amount of cards loaded to the board originally
        defaultNumCards = game.getBoard().getCols() * game.getBoard().getRows();
        //set the current number of cards
        currentNumCards = defaultNumCards;

        //debug
        System.out.println(defaultNumCards + " cards by default");


        //HEADER PANE
        headerPane = new Pane();
        Rectangle headerBackground = new Rectangle(0,0,DEF_WINDOW_WIDTH,45);
        headerBackground.setFill(Color.LIGHTBLUE);

        //Set the heading text
        Text text = new Text(0,35,"Game of Set");
        text.setFont(Font.font("Courier",FontWeight.BOLD,35));
        text.setTranslateX(10);

        //add the text and header pane
        headerPane.getChildren().addAll(headerBackground,text);

        //MENU PANE
        menuPane = new Pane();
        Rectangle menuBackground = new Rectangle(0,0,DEF_WINDOW_WIDTH,100);
        menuBackground.setFill(Color.LIGHTBLUE);

        //ADD 3 BUTTON
        add3Button = new Button("Add 3 Cards");
        add3Button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                if(currentNumCards < DEF_MAX_CARDS){
                    game.add3();
                    currentNumCards += 3;
                    System.out.println(currentNumCards+" cards now on board.");
                    showGameBoard();

                    //if we have now hit the limit
                    if(currentNumCards >= DEF_MAX_CARDS){
                        add3Button.setDisable(true);
                    }

                }
                else{
                    System.out.println("Not allowed to add more cards");
                }

            }
        });

        //EXIT BUTTON
        exitButton = new Button("Quit Game");
        exitButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                System.exit(0);
            }
        });

        //BUTTON POSITIONING
        add3Button.setTranslateX(10);
        add3Button.setTranslateY(10);

        exitButton.setTranslateX(10);
        exitButton.setTranslateY(45);

        //CARDS REMAINING LABEL
        cardsRemaining = new Label(String.format("%d cards left", game.getDeck().getSize()));
        cardsRemaining.setFont(Font.font("Courier", 30));
        cardsRemaining.setTranslateX(DEF_WINDOW_WIDTH/2 - 100);
        cardsRemaining.setTranslateY(15);

        //Add all children to menu pane
        menuPane.getChildren().addAll(menuBackground,add3Button, cardsRemaining,exitButton);

        //CARD GRID POSITIONING
        cardGrid.setHgap(DEF_CARD_HGAP); //positioning between the rows
        cardGrid.setVgap(DEF_CARD_VGAP); //positioning between the columns
        cardGrid.setPadding(new Insets(DEF_CARD_GRID_PADDING)); //padding around the entire cardGrid

        generalLayout.setTop(headerPane);
        generalLayout.setBottom(menuPane);

        //cardGrid.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        //margin and alignment
        generalLayout.setMargin(cardGrid, new Insets(12,12,12,12));
        generalLayout.setAlignment(cardGrid, Pos.CENTER);

        //set padding and center
        cardGrid.setPadding(new Insets(10));
        generalLayout.setCenter(cardGrid);

        //now draw the board
        this.showGameBoard();

        //set the scene, title, and icon
        Scene scene = new Scene(generalLayout,DEF_WINDOW_WIDTH,DEF_WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game of Set");
        primaryStage.getIcons().add(new Image("file:icon.png"));

        //show the stage
        primaryStage.show();

    }

    /**
     * Generates the child cardpanes in the cardgrid and reloads the board
     * Also updates the number of remaining cards
     */
    public void showGameBoard() {
        //clear the cardgrid
        cardGrid.getChildren().clear();

        //load the board
        Board board = game.getBoard();

        //loop through all rows and columns
        for (int currentRow = 0; currentRow < board.getRows(); currentRow++) {
            for (int currentCol = 0; currentCol < board.getCols(); currentCol++) {
                //load a new cardpane for each card
                Pane cardPane = new CardPane(board.getBoardSquare(currentRow, currentCol));
                //set click event
                cardPane.setOnMouseClicked(this::cardClickEventHandler);

                //add the cardpane to the grid
                cardGrid.add(cardPane, currentCol, currentRow);
            }
        }

        //update the number of remaining cards
        this.updateCardsRemaining();
    }

    /**
     * Update the number of cards remaining
     * Updates the text of the label
     */
    public void updateCardsRemaining(){
        cardsRemaining.setText(String.format("%d cards left", game.getDeck().getSize()));
    }

    /**
     * Handle clicks on a card
     * @param event the event that has occurred
     */
    private void cardClickEventHandler(MouseEvent event){

        CardPane cardPane = (CardPane) event.getSource(); // get clicked CardPane as a CardPane

        Pane clickedPane = (Pane) event.getSource(); //get the clicked cardpane as a PAne

        BoardSquare boardSquare = cardPane.getBoardSquare(); // get the BoardSquare for that CardPane

        //if the card is already selected
        System.out.println(boardSquare.getSelected());
        if(boardSquare.getSelected() == true){
            //unselect the card since this is the second click
            System.out.println("Already selected, unselected card!");
            game.removeSelected(boardSquare.getRow(),boardSquare.getCol());
            clickedPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        }
        //if the card hasn't been selected yet
        else{
            //select the card
            System.out.println("Selected!" +boardSquare.getCard().toString());
            clickedPane.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            game.addToSelected(boardSquare.getRow(),boardSquare.getCol());

        }

        //if 3 cards are selected
        if (game.numSelected() == 3) {
            //change the pane background color
            clickedPane.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));

            //re-draw the board
            this.showGameBoard();

            //if there are more than the default number of cards
            if(currentNumCards > defaultNumCards){
                //EXTRA CREDIT: CHANGE replaceCardsIfSet TO false and modify test selected to replace the cards

                //test if the cards are a set, if they are a set then don't replace the cards since there are extra on the board
                game.testSelected(true);
            }
            //if there is the default number of cards
            else{
                //test if the cards are a set. If they are a set, replace the cards with new cards
                //if they aren't a set, they will be unselected by this method
                game.testSelected(true);
            }

            //disable the add 3 button if there are 3 or less cards free
            if(game.getDeck().getSize() < 3){
                add3Button.setDisable(true);
            }

            // re-display board
            this.showGameBoard();

        }
    }
}
