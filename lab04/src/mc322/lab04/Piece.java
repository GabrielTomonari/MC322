public class Piece {
    private char pieceValue;

    Piece(char init) {
        this.pieceValue = init;
    }
    
    public void changeValue(char piece) {
        this.pieceValue = piece;
    }    
}
