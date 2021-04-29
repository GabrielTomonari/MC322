public class Man extends Piece {
    public Man(char color, MatrixPosition pos) {
        this.pieceColor = color;
        this.pos = pos;
    }

    @Override
    public boolean validateMove(MatrixPosition target) {
        return true;
    }

    public boolean validateCapture(MatrixPosition target) {
        return true;
    }
}
