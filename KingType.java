package src.src.checker;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static src.src.checker.CheckersApp.TILE_SIZE;

public class KingType extends StackPane {


    public KingType(PieceType type, int x, int y)
    {

        Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        bg.setFill(Color.WHITE);
        bg.setStroke(Color.RED);
        bg.setStrokeWidth(TILE_SIZE * 0.03);
        bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.07);

        Ellipse ellipse = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        ellipse.setFill(type == PieceType.RED_KING ? Color.valueOf("#4e0001") : Color.valueOf("#333333"));
        ellipse.setStroke(Color.LIGHTSLATEGRAY);
        ellipse.setStrokeWidth(TILE_SIZE * 0.015);
        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2);

        getChildren().addAll(bg, ellipse);
    }

}
