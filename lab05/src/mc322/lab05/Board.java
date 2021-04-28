import java.lang.*;

public class Board {

    private char[][] stateMen;
    private char[][] stateKings;
    private String[] history;
    private int round;

    public Board(int numberOfCommands) {
        this.stateMen = new char[7][7];
        this.stateKings = new char[7][7];
        this.history = new String[numberOfCommands];
        this.round = 0;

        // Iniciar a primeira posição
        System.out.print(" Tabuleiro inicial:");
        this.history[0] = "-p-p-p-p\np-p-p-p-\n-p-p-p-p\n--------\n--------\nb-b-b-b-\n-b-b-b-b\nb-b-b-b-\n";
    }

    public String covertStateToString() {

        // Faz a união das duas matrizes de estado e converte o resultado para uma string
        char[][] result = new char[7][7];
        StringBuilder resultString = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (this.stateMen[i][j] != '-') {
                    result[i][j] = stateMen[i][j];
                } else if (this.stateKings[i][j] != '-') {
                    result[i][j] = stateMen[i][j];
                }
                resultString.append(result[i][j]);
            }
            resultString.append('\n');
        }

        System.out.println(resultString);
        return resultString;
    }

    public String[] saveStateOnHistory() {

    }

    public void executeCommand() {

    }

    public void printState() {

    }

    public String[] returnStateAsArrayofString() {

    }

    public Boolean isCapture() {

    }

    public void executeMovement() {

    }

    public void executeCapture() {

    }

}
