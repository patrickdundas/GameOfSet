/**
 * Patrick Dundas CS110
 */

import java.util.Scanner;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.TimeUnit;

public class GUI extends Application
{
    private Game game;
    private Pane headerPane;
    private Pane menuPane;
    private GridPane cardGrid;
    private BorderPane generalLayout;
    private VBox cardBox;
    private Button add3Button;
    private Pane statusPane;

    private final int DEF_CARD_VGAP = 20;
    private final int DEF_CARD_HGAP = 20;
    private final int DEF_CARD_GRID_PADDING = 20;
    private final int DEF_WINDOW_WIDTH = 900;
    private final int DEF_WINDOW_HEIGHT = 850;

    private final int DEF_MAX_CARDS = 18;

    private int defaultNumCards;

    private int currentNumCards;

    private final int DEF_MENUPANE_HEIGHT = 45;

    private Label cardsRemaining;



    @Override
    public void start(Stage primaryStage) throws Exception {


        // create game
        game = new Game();

        generalLayout = new BorderPane();
        cardGrid = new GridPane();

        statusPane = new Pane();


        defaultNumCards = game.getBoard().getCols() * game.getBoard().getRows();

        currentNumCards = defaultNumCards;

        System.out.println(defaultNumCards + " cards by default");


        //HEADER PANE
        headerPane = new Pane();
        Rectangle headerBackground = new Rectangle(0,0,DEF_WINDOW_WIDTH,45);
        headerBackground.setFill(Color.LIGHTBLUE);


        Text text = new Text(0,35,"Game of Set");
        text.setFont(Font.font("Courier",FontWeight.BOLD,35));

        headerPane.getChildren().addAll(headerBackground,text);

        //MENU PANE
        menuPane = new Pane();
        Rectangle menuBackground = new Rectangle(0,0,200,DEF_WINDOW_HEIGHT);
        menuBackground.setFill(Color.LIGHTBLUE);


        cardsRemaining = new Label(String.format("Cards remaining: %d", game.getDeck().getSize()));
        cardsRemaining.setFont(Font.font("Courier", 18));
        cardsRemaining.setTranslateY(DEF_WINDOW_HEIGHT-250);

        add3Button = new Button("Add 3");
        add3Button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                if(currentNumCards < DEF_MAX_CARDS){
                    game.add3();
                    currentNumCards += 3;
                    System.out.println(currentNumCards+" cards now on board.");
                    showGameBoard();
                }
                else{
                    System.out.println("Not allowed to add more cards");
                }

            }
        });

        menuPane.getChildren().addAll(menuBackground,add3Button, cardsRemaining);

        //CARD GRID POSITIONING
        cardGrid.setHgap(DEF_CARD_HGAP); //positioning between the rows
        cardGrid.setVgap(DEF_CARD_VGAP); //positioning between the columns
        cardGrid.setPadding(new Insets(DEF_CARD_GRID_PADDING)); //padding around the entire cardGrid

        generalLayout.setTop(headerPane);
        generalLayout.setLeft(menuPane);
        generalLayout.setCenter(cardGrid);

        this.showGameBoard();

        Scene scene = new Scene(generalLayout,DEF_WINDOW_WIDTH,DEF_WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game of Set");
        primaryStage.getIcons().add(new Image("file:icon.png"));
        primaryStage.show();

    }
    public void showGameBoard() {
        cardGrid.getChildren().clear();
        Board board = game.getBoard();
        for (int currentRow = 0; currentRow < board.getRows(); currentRow++) {
            for (int currentCol = 0; currentCol < board.getCols(); currentCol++) {
                //System.out.println("Card");
                Pane cardPane = new CardPane(board.getBoardSquare(currentRow, currentCol));
                cardPane.setOnMouseClicked(this::cardClickEventHandler);
                cardGrid.add(cardPane, currentCol, currentRow);
                //pane.getChildren().add(cardPane);
            }
        }
        this.updateCardsRemaining();
        //menuPane.getChildren().add(statusPane);
        //showStatusPane();
    }
    public void updateCardsRemaining(){
        cardsRemaining.setText(String.format("Cards remaining: %d", game.getDeck().getSize()));
    }
    private void cardClickEventHandler(MouseEvent event){

        CardPane cardPane = (CardPane) event.getSource(); // get clicked CardPane

        Pane clickedPane = (Pane) event.getSource();

        BoardSquare boardSquare = cardPane.getBoardSquare(); // get the BoardSquare for that CardPane

        //the card is already selected

        System.out.println(boardSquare.getSelected());

        if(boardSquare.getSelected() == true){
            System.out.println("Already selected, unselected card!");
            game.removeSelected(boardSquare.getRow(),boardSquare.getCol());
            clickedPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        }
        else{
            System.out.println("Selected!" +boardSquare.getCard().toString());
            clickedPane.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            game.addToSelected(boardSquare.getRow(),boardSquare.getCol());

        }

        if (game.numSelected() == 3) {
            clickedPane.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));

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

            // re-display board
            this.showGameBoard();

        }
    }
}
