import java.lang.Math;

public class Man extends Piece {
    public Man(char color, MatrixPosition pos) {
        this.pieceColor = color;
        this.pos = pos;
    }

    @Override
    public boolean validateMove(MatrixPosition target) {
        if (Math.abs(target.lin - this.pos.lin) > 1) {
            return false;
        }
        if (Math.abs(target.col - this.pos.col) > 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validateCapture(MatrixPosition target, Piece pieceToCapture) {
        if (Math.abs(target.lin - this.pos.lin) > 2) {
            return false;
        }
        if (Math.abs(target.col - this.pos.col) > 2) {
            return false;
        }
        if (this.pieceColor == pieceToCapture.pieceColor){
            return false;
        }
        return true;
    }
}
