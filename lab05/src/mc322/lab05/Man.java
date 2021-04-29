public class Man extends Piece {
    public Man(char color, Position pos) {
        this.pieceColor = color;
        this.pos = pos;
    }

    @Override
    public boolean validateMove(Position target){
        return true;
    }

    public boolean validateCapture(Position target){
        return true;
    }
}
