package src.src.checker;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static src.src.checker.CheckersApp.TILE_SIZE;


public class Piece extends StackPane
{
    private PieceType type;
    private double mouseX, mouseY;
    private double oldX, oldY;
    private boolean turnBLACK = true;

    public PieceType getType()
    {
        return type;
    }
    public KingType King()
    {
        return King();
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }
//Editing the pieces color
    public Piece(PieceType type, int x, int y)
    {
        this.type = type;

        move(x, y);

        Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        bg.setFill(Color.WHITE);
        bg.setStroke(Color.DARKSLATEBLUE);
        bg.setStrokeWidth(TILE_SIZE * 0.03);
        bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.07);

        Ellipse ellipse = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        ellipse.setFill(type == PieceType.RED ? Color.valueOf("#4e0001") : Color.valueOf("#333333"));
        ellipse.setStroke(Color.LIGHTSLATEGRAY);
        ellipse.setStrokeWidth(TILE_SIZE * 0.015);
        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2);

        Ellipse bg_King = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        bg_King.setFill(Color.WHITE);
        bg_King.setStroke(Color.RED);
        bg_King.setStrokeWidth(TILE_SIZE * 0.03);
        bg_King.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        bg_King.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.07);

        Ellipse King_ellipse = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        King_ellipse.setFill(type == PieceType.RED_KING ? Color.valueOf("#4e0001") : Color.valueOf("#333333"));
        King_ellipse.setStroke(Color.LIGHTSLATEGRAY);
        King_ellipse.setStrokeWidth(TILE_SIZE * 0.015);
        King_ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        King_ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2);

        getChildren().addAll(bg, ellipse);


        setOnMousePressed(e ->
        {
                mouseX = e.getSceneX();
                mouseY = e.getSceneY();
        });
        setOnMouseDragged(e ->
        {
            relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
        });
    }
//keeping the look of the piece when moved
    public void move(int x, int y)
    {
        oldX = x * TILE_SIZE;
        oldY = y * TILE_SIZE;
        relocate(oldX, oldY);
        checkKing(y);
    }

    public void abortMove()
    {
        relocate(oldX, oldY);
    }
    public void switchPlayer() {
        turnBLACK = !turnBLACK;
    }

    public void checkKing(int y){
        if(y==7 && type==PieceType.RED){
            type=PieceType.RED_KING;
            System.out.println("red king");
            System.out.println("x= " + oldX/100);
            System.out.println("y= " + oldY/100);

        }
        else if (y==0 && type== PieceType.BLACK)
        {
            type=PieceType.BLACK_KING;
            System.out.println("Black King");
            System.out.println("x= " + oldX/100);
            System.out.println("y= " + oldY/100);

        }

    }

}
