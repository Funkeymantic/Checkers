package src.src.checker;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CheckersApp extends Application
{
    //Defining Tile size and board
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    //List<pieceId> pieceList = new ArrayList<pieceId>();


    private Parent createContent()
    {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup, pieceGroup);

        /*for (int i = 0; i < 24; i++) {
            pieceList.add(new pieceId(i));
            System.out.print("Variable id " + pieceId(i));
        }*/

        for(int y = 0; y < HEIGHT; y++)
        {
            for(int x =0; x < WIDTH; x++)
            {

                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);

                Piece piece = null;

                if(y <= 2 && (x + y) % 2 != 0)
                {
                    piece = makePiece(PieceType.RED, x, y);
                    //System.out.print(x + "\n");
                    //System.out.print(y + "\n");
                    //System.out.print("RED" + "\n");
                }
                if(y >= 5 && (x + y) % 2 != 0)
                {
                    piece = makePiece(PieceType.BLACK, x, y);
                    //System.out.print(x + "\n");
                    //System.out.print(y + "\n");
                    //System.out.print("BLACK" + "\n");
                }
                if (piece != null)
                {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
                //Log.d(" X id= ",x, "Y id= ",y, "Color is ", piece);

            }
        }

        return root;
    }

    private MoveResult tryMove(Piece piece, int newX, int newY)
    {
        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0)
        {
            return new MoveResult(MoveType.NONE);
        }

        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());
        if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {
            return new MoveResult((MoveType.NORMAL));
        }
        else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2)
        {
            int x1 = x0 + (newX - x0) /2;
            int y1 = y0 + (newY -y0) / 2;
            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType())
            {
                return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
            }
        }
        /*else if (newX >= WIDTH || newY <= 1)
        {
            board[x0][y0].setPiece(null);
            board[newX][newY].setPiece(piece);
            pieceGroup.getChildren().remove(piece);
            //makePiece(PieceType.RED_KING, newX, newY);
        }*/
        return new MoveResult(MoveType.NONE);
    }

    private int toBoard(double pixel)
    {
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Piece makePiece(PieceType type, int x, int y)
    {
        Piece piece = new Piece(type, x, y);
        piece.setOnMouseReleased(e ->
        {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());
            MoveResult result;
            if (newX < 0 || newY < 0|| newX >= WIDTH || newY >= HEIGHT)
            {
                result = new MoveResult(MoveType.NONE);
            }
            else
            {
                result = tryMove(piece, newX, newY);
            }
            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());
            if (newX >= WIDTH || newY <= 0) {
                board[x0][y0].setPiece(null);
                board[newX][newY].setPiece(piece);
                pieceGroup.getChildren().remove(piece);
                // = makePiece(PieceType.RED_KING, x, y);
            }
            else if (newX >= WIDTH || newY >= 7)
            {
                board[x0][y0].setPiece(piece);
                board[newX][newY].setPiece(piece);
                pieceGroup.getChildren().remove(piece);

            }
            switch (result.getType())
            {
                case NONE:
                    piece.abortMove();
                    break;
                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    break;
                case KILL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    Piece otherPiece = result.getPiece();
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);
                    break;

            }
        });
        return piece;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
