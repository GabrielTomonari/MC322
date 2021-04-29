public class Piece {
    public char pieceColor;
    Position pos;

    public Position executeMove(Position target){
        this.pos = target;
        return this.pos;
    }

    public boolean validateMove(Position target){
        return true;
    }
}
