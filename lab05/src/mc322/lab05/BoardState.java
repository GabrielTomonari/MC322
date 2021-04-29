public class BoardState {

    private Piece[][] state;
    private String[] history;
    private int round;

    public BoardState(int numberOfCommands) {
        this.state = new Piece[8][8];
        this.history = new String[numberOfCommands];
        this.round = 0;

        // Iniciar a primeira posição
        System.out.println(" Tabuleiro inicial:");
        this.history[0] = "-p-p-p-p\np-p-p-p-\n-p-p-p-p\n--------\n--------\nb-b-b-b-\n-b-b-b-b\nb-b-b-b-\n";

        for (int i = 1; i < 8; i = i + 2) {
            this.state[0][i] = new Man('p', new MatrixPosition(0, i));
            this.state[2][i] = new Man('p', new MatrixPosition(2, i));
            this.state[6][i] = new Man('b', new MatrixPosition(6, i));
        }
        for (int i = 0; i < 8; i = i + 2) {
            this.state[1][i] = new Man('p', new MatrixPosition(1, i));
            this.state[5][i] = new Man('b', new MatrixPosition(5, i));
            this.state[7][i] = new Man('b', new MatrixPosition(7, i));
        }
        this.printState();
    }

    public Piece getPieceAt(MatrixPosition position) {
        return this.state[position.lin][position.col];
    }

    public void removePieceAt(MatrixPosition position) {
        this.state[position.lin][position.col] = null;
    }

    public void placePieceAt(Piece piece, MatrixPosition position) {
        this.state[position.lin][position.col] = piece;
    }

    private String covertStateToString() {
        // Converte a matriz de estado para string
        StringBuilder resultString = new StringBuilder();
        char color;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (this.state[i][j] != null) {
                    color = state[i][j].pieceColor;
                    resultString.append(color);
                } else {
                    resultString.append('-');
                }

            }
            resultString.append('\n');
        }

        String finalString = resultString.toString();
        return finalString;
    }

    private void saveStateOnHistory() {
        // Converte a matriz de estado para string e insere o resultado no histórico
        String result;
        result = this.covertStateToString();
        history[this.round] = result;
    }

    public void printState() {
        int line = 8;
        // System.out.print("\n");
        for (int i = 0; i < 8; i++) {
            System.out.print(line + " ");
            line--;
            for (int j = 0; j < 8; j++) {
                if (this.state[i][j] != null) {
                    System.out.print(this.state[i][j].pieceColor + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("  a b c d e f g h\n");

    }

    public String[] returnStateAsArrayofString() {
        return this.history;
    }

}
