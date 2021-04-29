public class Piece {
    public char pieceColor;
    MatrixPosition pos;

    public MatrixPosition executeMove(MatrixPosition target) {
        this.pos = target;
        return this.pos;
    }

    public boolean validateMove(MatrixPosition target) {
        return true;
    }

    public boolean validateCapture(MatrixPosition target, Piece pieceToCapture) {
        return true;
    }
}
