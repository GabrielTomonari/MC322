public class BoardController {
    BoardState state;

    BoardController(BoardState state) {
        this.state = state;
    }

    private boolean isValidCommand(String cmd) {
        // ToDo
        return true;
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

        MatrixPosition pos = new MatrixPosition(source.lin + linInc, source.col + colInc);
        while (pos.lin != target.lin && pos.col != target.col) {
            if (this.state.getPieceAt(pos) != null) {
                return true;
            }
            pos.lin += linInc;
            pos.col += colInc;
        }

        return false;
    }

    public void executeCommand(String cmd) {
        if (this.isValidCommand(cmd)) {
            MatrixPosition source = extractSourceFromCmd(cmd);
            MatrixPosition target = extractTargetFromCmd(cmd);

            if (this.isCapture(source, target)) {
                executeCapture(source, target);
            } else {
                executeMovement(source, target);
            }
        }

        this.state.printState();
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

        MatrixPosition pos = new MatrixPosition(source.lin, source.col);
        while (pos.lin != target.lin && pos.col != target.col) {
            Piece currentPiece = this.state.getPieceAt(pos);
            if (currentPiece != null) {
                return currentPiece;
            }
            pos.lin += linInc;
            pos.col += colInc;
        }

        System.out.println("Algum erro no isCaptured ocorreu");
        return null;
    }

    public void executeCapture(MatrixPosition source, MatrixPosition target) {
        Piece piece = this.state.getPieceAt(source);

        if (piece.validateCapture(target)) {
            // move the source piece
            this.state.removePieceAt(source);
            MatrixPosition newPos = piece.executeMove(target);
            this.state.placePieceAt(piece, newPos);

            // remove the captured piece
            Piece pieceToRemove = this.getCapturedPiece(source, target);
            this.state.removePieceAt(pieceToRemove.pos);
        }
    }
}
