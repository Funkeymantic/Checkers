package src.src.checker;
//Tells what kind of move a certain piece is making
public class MoveResult
{
    private MoveType type;

    public MoveType getType()
    {
        return type;
    }

    private Piece piece;

    public Piece getPiece()
    {
        return piece;
    }

    public MoveResult(MoveType type)
    {
        this(type, null);
    }

    public MoveResult(MoveType type, Piece piece)
    {
        this.type = type;
        this.piece = piece;
    }
}
