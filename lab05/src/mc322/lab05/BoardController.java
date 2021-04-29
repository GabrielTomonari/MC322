import java.util.Arrays;

public class BoardController {
    BoardState state;

    BoardController(BoardState state) {
        this.state = state;
    }

    private boolean isValidCommand(String cmd) {

        // Valida as coordenadas
        char[] validLetters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
        char[] validNumbers = { '1', '2', '3', '4', '5', '6', '7', '8' };
        char firstLetter = cmd.charAt(0);
        char secondLetter = cmd.charAt(3);
        char firstNumber = cmd.charAt(1);
        char secondNumber = cmd.charAt(4);
        boolean validFirstLetter = false;
        boolean validSecondLetter = false;
        boolean validFirstNumber = false;
        boolean validSecondNumber = false;

        for (char c : validLetters) {
            if (c == firstLetter) {
                validFirstLetter = true;
            }
            if (c == secondLetter) {
                validSecondLetter = true;
            }
        }

        for (char c : validNumbers) {
            if (c == firstNumber) {
                validFirstNumber = true;
            }
            if (c == secondNumber) {
                validSecondNumber = true;
            }
        }

        if (validFirstLetter && validSecondLetter && validFirstNumber && validSecondNumber) {
            return true;
        } else {
            return false;
        }

    }

    private MatrixPosition extractSourceFromCmd(String cmd) {
        char letter = cmd.charAt(0);
        char num = cmd.charAt(1);

        return MatrixPosition.createFromLetterAndNum(letter, num);
    }

    private MatrixPosition extractTargetFromCmd(String cmd) {
        char letter = cmd.charAt(3);
        char num = cmd.charAt(4);

        return MatrixPosition.createFromLetterAndNum(letter, num);
    }

    private int extractIncrementer(int source, int target) {
        int inc;

        if (source < target) {
            inc = 1;
        } else {
            inc = -1;
        }

        return inc;
    }

    // return true if is a capture move, false otherwise
    public Boolean isCapture(MatrixPosition source, MatrixPosition target) {
        int linInc = this.extractIncrementer(source.lin, target.lin);
        int colInc = this.extractIncrementer(source.col, target.col);
        boolean foundPiece = false;

        MatrixPosition pos = new MatrixPosition(source.lin + linInc, source.col + colInc);
        while (pos.lin != target.lin && pos.col != target.col) {
            if (this.state.getPieceAt(pos).pieceColor != '-') {
                if(!foundPiece){
                    foundPiece = true;
                }else{
                    return false;
                }
            }
            pos.lin += linInc;
            pos.col += colInc;
        }

        return foundPiece;
    }

    private boolean isMove(MatrixPosition source, MatrixPosition target){
        int linInc = this.extractIncrementer(source.lin, target.lin);
        int colInc = this.extractIncrementer(source.col, target.col);

        MatrixPosition pos = new MatrixPosition(source.lin + linInc, source.col + colInc);
        while (pos.lin != target.lin && pos.col != target.col) {
            if (this.state.getPieceAt(pos).pieceColor != '-') {
                return false;
            }
            pos.lin += linInc;
            pos.col += colInc;
        }

        return true;
    }

    private void promotePieces() {
        Piece candidate;
        MatrixPosition posCandidate;

        // promote "b" men
        for (int i = 0; i < 8; i++) {
            posCandidate = new MatrixPosition(0, i);
            candidate = this.state.getPieceAt(posCandidate);
            if (candidate.pieceColor == 'b') {
                this.state.removePieceAt(posCandidate);
                Piece promotedCandidate = new King('B', posCandidate);
                this.state.placePieceAt(promotedCandidate, posCandidate);
            }
        }

        // promote "p" men
        for (int i = 0; i < 8; i++) {
            posCandidate = new MatrixPosition(7, i);
            candidate = this.state.getPieceAt(posCandidate);
            if (candidate.pieceColor == 'p') {
                this.state.removePieceAt(posCandidate);
                Piece promotedCandidate = new King('P', posCandidate);
                this.state.placePieceAt(promotedCandidate, posCandidate);
            }
        }

    }

    public void executeCommand(String cmd) {
        if (this.isValidCommand(cmd)) {
            MatrixPosition source = extractSourceFromCmd(cmd);
            MatrixPosition target = extractTargetFromCmd(cmd);

            if (this.isCapture(source, target)) {
                executeCapture(source, target);
            } else if(this.isMove(source, target)) {
                executeMovement(source, target);
            }

            this.promotePieces();
        }

        this.state.printState();
        this.state.saveStateOnHistory();
    }

    public void executeMovement(MatrixPosition source, MatrixPosition target) {
        Piece piece = this.state.getPieceAt(source);

        if (piece.validateMove(target)) {
            this.state.removePieceAt(source);
            MatrixPosition newPos = piece.executeMove(target);
            this.state.placePieceAt(piece, newPos);
        }
    }

    private Piece getCapturedPiece(MatrixPosition source, MatrixPosition target) {
        int linInc = this.extractIncrementer(source.lin, target.lin);
        int colInc = this.extractIncrementer(source.col, target.col);
        Piece currentPiece = new Piece();

        MatrixPosition pos = new MatrixPosition(source.lin + linInc, source.col + colInc);
        while (pos.lin != target.lin && pos.col != target.col) {
            currentPiece = this.state.getPieceAt(pos);
            if (currentPiece.pieceColor != '-') {
                return currentPiece;
            }
            pos.lin += linInc;
            pos.col += colInc;
        }

        System.out.println("Algum erro no isCaptured ocorreu");
        return currentPiece;
    }

    public void executeCapture(MatrixPosition source, MatrixPosition target) {
        Piece piece = this.state.getPieceAt(source);
        Piece pieceToRemove = this.getCapturedPiece(source, target);

        if (piece.validateCapture(target, pieceToRemove)) {
            // move the source piece
            this.state.removePieceAt(source);
            MatrixPosition newPos = piece.executeMove(target);
            this.state.placePieceAt(piece, newPos);

            // remove the captured piece
            this.state.removePieceAt(pieceToRemove.pos);
        }
    }
}
