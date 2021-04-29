public class EmptyPiece extends Piece{
    EmptyPiece(MatrixPosition pos){
        this.pieceColor = '-';
        this.pos = pos;
    }

    @Override
    public boolean validateMove(MatrixPosition target) {
        return false;
    }

    @Override
    public boolean validateCapture(MatrixPosition target, Piece pieceToCapture) {
        return false;
    }
}
