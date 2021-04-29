import java.lang.Math;

public class King extends Piece{

    public King(char color, MatrixPosition pos) {
        this.pieceColor = color;
        this.pos = pos;
    }

    @Override
    public boolean validateMove(MatrixPosition target) {
        if(Math.abs(target.lin - this.pos.lin) == Math.abs(target.col - this.pos.col)){
            return true;
        }
        return false;
    }

    @Override
    public boolean validateCapture(MatrixPosition target, Piece pieceToCapture) {
        if (this.pieceColor == pieceToCapture.pieceColor){
            return false;
        }
        if(Math.abs(target.lin - this.pos.lin) == Math.abs(target.col - this.pos.col)){
            return true;
        }
        return false;
    }
}
