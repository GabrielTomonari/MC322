import java.lang.Math;

public class Man extends Piece {
    public Man(char color, MatrixPosition pos) {
        this.pieceColor = color;
        this.pos = pos;
    }

    @Override
    public boolean validateMove(MatrixPosition target) {
        int linInc = 0;
        
        if(this.pieceColor == 'p'){
            linInc = 1;
        }else if(this.pieceColor == 'b'){
            linInc = -1;
        }
        
        if (target.lin - this.pos.lin != linInc) {
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
