import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;

import java.util.ArrayList;


/**
 * CardPane assembles pieces of a card to create a visual element that is displayed by the gui
 * Uses JavaFX VBox
 */
public class CardPane extends VBox{

    //card attributes
    private Card.Color color;
    private Card.Fill fill;
    private Card.Number number;
    private Card.Shape shape;
    private int newXPosition;
    private int newYPosition;
    private int currentCol;
    private int currentRow;
    //private int newCenterX;
    //private int newCenterY;


    //CONSTANTS
    private final int DEF_CARD_HEIGHT = 225;
    private final int DEF_CARD_WIDTH = 150;

    private final int DEF_CARD_BORDER_WIDTH = 5;
    private final int DEF_SHAPE_HEIGHT = 35;


    private final Color DEF_CARD_BG_COLOR = Color.TRANSPARENT;
    private final String DEF_CARD_BORDER_COLOR = "black";

    private final int DEF_OVAL_HEIGHT = 20;
    private final int DEF_OVAL_WIDTH = 30;

    private final int DEF_TRIANGLE_HEIGHT = 40;
    private final int DEF_TRIANGLE_WIDTH = 45;

    private final int DEF_DIAMOND_HEIGHT = DEF_SHAPE_HEIGHT;
    private final int DEF_DIAMOND_WIDTH = 50;



    private final int DEF_SHAPE_STROKE_WIDTH = 3;

    //the currently accessed boardsquare
    BoardSquare boardSquare;


    /**
     * Create a cardpane from a boardsquare
     * @param boardSquare the boardsquare to read data from
     */
    public CardPane(BoardSquare boardSquare){

        super();


        //save the current boardsquare to the class scope
        this.boardSquare = boardSquare;

        //get the data from the boardsquare and card
        Card card = boardSquare.getCard();
        this.color = card.getColor();
        this.fill = card.getFill();
        this.number = card.getNumber();
        this.shape = card.getShape();

        this.currentCol = boardSquare.getCol();
        this.currentRow = boardSquare.getRow();

        //positioning for shapes in the card
        ArrayList shapePositions = new ArrayList<Integer>();

        //set the card background
        this.setBackground(new Background(new BackgroundFill(DEF_CARD_BG_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        //css for card
        String cssLayout = "-fx-border-color: "+DEF_CARD_BORDER_COLOR+";\n" +
                "-fx-border-insets: 0;\n" +
                "-fx-border-width: "+DEF_CARD_BORDER_WIDTH+";\n" +
                "-fx-border-style: solid;\n";
        this.setStyle(cssLayout);

        //card height, alignment, spacing, etc.
        this.setAlignment(Pos.CENTER);
        this.setPrefHeight(DEF_CARD_HEIGHT);
        this.setPrefWidth(DEF_CARD_WIDTH);
        this.setSpacing(1);

        //variables local to this card
        Color newFillColor;
        Paint newFillPattern;
        Color newFillOutline;
        int newShapeQuantity;

        //int testposition = -10;

        //detect the number of shapes from the card
        //save as an integer and save shape positioning
        switch(this.number){
            case ONE:
                newShapeQuantity = 1;
                shapePositions.add(shapePositions.add(0));
                break;
            case TWO:
                newShapeQuantity = 2;

                shapePositions.add(0);
                shapePositions.add(0);

                break;
            case THREE:
                newShapeQuantity = 3;

                shapePositions.add(0);
                shapePositions.add(0);
                shapePositions.add(0);
                break;
            default:
                newShapeQuantity = 1;
                shapePositions.add(0);
        }

        //detect card color and set the newFillColor
        switch(this.color){
            case GREEN:
                newFillColor = Color.GREEN;
                break;
            case RED:
                newFillColor = Color.RED;
                break;
            case PURPLE:
                newFillColor = Color.PURPLE;
                break;
            default:
                newFillColor = Color.TRANSPARENT;
        }

        //detect the card fill type and set newFillPattern and newFillOutline
        switch(this.fill){
            case OUTLINE:
                newFillPattern = Color.TRANSPARENT;
                newFillOutline = newFillColor;
                break;
            case SOLID:
                newFillPattern = newFillColor;
                newFillOutline = newFillColor;
                break;
            case STRIPED:
                newFillPattern = new ImagePattern(drawNewStripeFill(newFillColor), 0, 0, 20, 20, false);
                newFillOutline = newFillColor;
                break;
            default:
                newFillPattern = Color.TRANSPARENT;
                newFillOutline = Color.TRANSPARENT;
        }

        //now generate the shape (loop through the number of shapes so that the right amount are drawn)
        Node newShape;
        for(int i = 0; i < newShapeQuantity; i++) {
            switch(this.shape){
                case OVAL:
                    newShape = drawOval((int)(shapePositions.get(i)), newFillPattern, newFillOutline);
                    break;
                case DIAMOND:
                    newShape = drawDiamond((int)(shapePositions.get(i)), newFillPattern, newFillOutline);
                    break;
                    //drawDiamonds();
                case TRIANGLE:
                    newShape = drawTriangle((int)(shapePositions.get(i)), newFillPattern, newFillOutline);
                    break;
                    //drawTriangles();
                default:
                    newShape = drawOval((int)(shapePositions.get(i)), newFillPattern, newFillOutline);
            }

            this.getChildren().add(newShape);
        }

        //System.out.println(card.toString());

    }

    /**
     * Generate an ellipse with given position, pattern, and filloutline
     * @param newShapePosition the new position for the ellipse
     * @param newFillPattern the nwe pattern for the ellipse
     * @param newFillOutline the new fill outline for the ellipse
     * @return the ellipse
     */
    public Ellipse drawOval(int newShapePosition,Paint newFillPattern, Color newFillOutline){

        Ellipse e1 = new Ellipse(DEF_CARD_WIDTH/2,newShapePosition,DEF_OVAL_WIDTH,DEF_OVAL_HEIGHT);
        e1.setFill(newFillPattern);
        e1.setStroke(newFillOutline);
        e1.setStrokeWidth(DEF_SHAPE_STROKE_WIDTH);

        return e1;
    }

    /**
     * Generate a triangle with given position, pattern, and filloutline
     * @param newShapePosition the new position for the triangle
     * @param newFillPattern the new pattern for the triangle
     * @param newFillOutline the new fill outline for the triangle
     * @return the triangle
     */
    public Polygon drawTriangle(int newShapePosition, Paint newFillPattern, Color newFillOutline){
        /*int newCenterX = newXPosition + (DEF_CARD_WIDTH / 2);
        int newCenterY;

        newCenterY = newYPosition + (DEF_CARD_HEIGHT / 2);*/
        Polygon triangle1 = new Polygon();
        triangle1.getPoints().addAll(new Double[]{
                (double)(DEF_TRIANGLE_WIDTH/2), 0.0,
                0.0, (double)(DEF_TRIANGLE_HEIGHT),
                (double)(DEF_TRIANGLE_WIDTH), (double)(DEF_TRIANGLE_HEIGHT) });
        triangle1.setFill(newFillPattern);
        triangle1.setStroke(newFillOutline);
        triangle1.setStrokeWidth(DEF_SHAPE_STROKE_WIDTH);

        return triangle1;
    }

    /**
     * Generate a diamond with given position, pattern, and filloutline
     * @param newShapePosition the new position for the diamond
     * @param newFillPattern the nwe pattern for the diamond
     * @param newFillOutline the new fill outline for the diamond
     * @return the diamond
     */
    public Polygon drawDiamond(int newShapePosition, Paint newFillPattern, Color newFillOutline){

        Polygon diamond1 = new Polygon();
        diamond1.getPoints().addAll(new Double[]{
                (double)(DEF_DIAMOND_WIDTH/2), 0.0,
                0.0, (double)(DEF_DIAMOND_HEIGHT/2),
                (double)(DEF_DIAMOND_WIDTH/2), (double)(DEF_DIAMOND_HEIGHT),
                (double)(DEF_DIAMOND_WIDTH), (double)(DEF_DIAMOND_HEIGHT/2),
                (double)(DEF_DIAMOND_WIDTH/2), 0.0,
                 });

        diamond1.setFill(newFillPattern);
        diamond1.setStroke(newFillOutline);
        diamond1.setStrokeWidth(DEF_SHAPE_STROKE_WIDTH);

        return diamond1;
    }

    /**
     * Generate a new stripe fill for a shape
     * @param newColor the color for this fill
     * @return an Image object for the stripe fill
     */
    private Image drawNewStripeFill(Color newColor){
        Pane drawPane = new Pane();
        drawPane.setPrefSize(2,2);
        Line stripeLine = new Line (0,0,30,30);
        stripeLine.setStrokeWidth(5);
        stripeLine.setStroke(newColor);
        stripeLine.setRotate(90);
        drawPane.getChildren().addAll(stripeLine);
        new Scene(drawPane);
        return drawPane.snapshot(null,null);

    }

    /**
     * Get the currently selected boardsquare
     * @return the boardsquare
     */
    public BoardSquare getBoardSquare(){
        return boardSquare;
    }

    /**
     * Set the fill for the cardpane
     * @param newFill the new fill of type Card.Fill
     */
    public void setFill(Card.Fill newFill){
        this.fill = newFill;
    }

}
