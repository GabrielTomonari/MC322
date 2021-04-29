public class BoardController {
    BoardState state;
    
    BoardController(BoardState state){
        this.state = state;
    }

    private boolean isValidCommand(String cmd) {
        //ToDo
        return true;
    }

    private Position extractSourceFromCmd(String cmd) {
        char letter = cmd.charAt(0);
        char num = cmd.charAt(1);

        return new Position(letter, num);
    }

    private Position extractTargetFromCmd(String cmd) {
        char letter = cmd.charAt(3);
        char num = cmd.charAt(4);

        return new Position(letter, num);
    }

    public Boolean isCapture() {
        return false;
    }
    
    public void executeCommand(String cmd) {
        if(this.isValidCommand(cmd)){
            Position source = extractSourceFromCmd(cmd);
            Position target = extractTargetFromCmd(cmd);
            
            if(this.isCapture()){
                executeCapture();
            }else{
                executeMovement(source, target);
            }
        }
    }

    public void executeMovement(Position source, Position target) {
        Piece piece = this.state.getPieceAt(source);
        
        if(piece.validateMove(target)){
            this.state.removePieceAt(source);
            Position newPos = piece.executeMove(target);
            this.state.placePieceAt(piece, newPos);
        }
    }

    public void executeCapture() {
        // ToDo
    }
}
