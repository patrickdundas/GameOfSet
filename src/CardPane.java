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

public class CardPane extends VBox{

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

    BoardSquare boardSquare;




    public CardPane(BoardSquare boardSquare){

        super();

        //GridPane gp = new GridPane();
        this.boardSquare = boardSquare;
        Card card = boardSquare.getCard();
        this.color = card.getColor();
        this.fill = card.getFill();
        this.number = card.getNumber();
        this.shape = card.getShape();

        this.currentCol = boardSquare.getCol();
        this.currentRow = boardSquare.getRow();



        ArrayList shapePositions = new ArrayList<Integer>();

        this.setBackground(new Background(new BackgroundFill(DEF_CARD_BG_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        String cssLayout = "-fx-border-color: "+DEF_CARD_BORDER_COLOR+";\n" +
                "-fx-border-insets: 0;\n" +
                "-fx-border-width: "+DEF_CARD_BORDER_WIDTH+";\n" +
                "-fx-border-style: solid;\n";


        this.setStyle(cssLayout);

        this.setAlignment(Pos.BOTTOM_CENTER);
        this.setPrefHeight(DEF_CARD_HEIGHT);
        this.setPrefWidth(DEF_CARD_WIDTH);
        this.setSpacing(1);

        //variables local to this card
        Color newFillColor;
        Paint newFillPattern;
        Color newFillOutline;
        int newShapeQuantity;

        int testposition = -10;
        switch(this.number){
            case ONE:
                newShapeQuantity = 1;
                shapePositions.add(shapePositions.add(testposition));
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

        //now draw the shape (loop through the number of shapes so that the right amount are drawn)
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

    public Ellipse drawOval(int newShapePosition,Paint newFillPattern, Color newFillOutline){

        Ellipse e1 = new Ellipse(DEF_CARD_WIDTH/2,newShapePosition,DEF_OVAL_WIDTH,DEF_OVAL_HEIGHT);
        e1.setFill(newFillPattern);
        e1.setStroke(newFillOutline);
        e1.setStrokeWidth(DEF_SHAPE_STROKE_WIDTH);

        return e1;
    }

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

    public BoardSquare getBoardSquare(){
        return boardSquare;
    }

    public void setFill(Card.Fill newFill){
        this.fill = newFill;
    }

}
