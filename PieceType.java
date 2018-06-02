package src.src.checker;
//tells what piece goes to what player
public enum PieceType
{
    RED(1), BLACK(-1), RED_KING(0), BLACK_KING(0);
    final int moveDir;

    PieceType(int moveDir)
    {
        this.moveDir = moveDir;
    }
}
