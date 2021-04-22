public class StateManager {

    private int round;
    private String[] results;

    public StateManager(int lenght) {
        this.round = 0;
        this.results = new String[lenght + 1];
        // Iniciar a primeira posição
        System.out.print(" Tabuleiro inicial:");
        this.results[0] = "  PPP  \n  PPP  \nPPPPPPP\nPPP-PPP\nPPPPPPP\n  PPP  \n  PPP  \n";
    }

    public void nextRound() {
        this.round += 1;
    }

    public void generateNewState() {
        if (this.round != 0) {
            this.results[this.round] = this.results[this.round - 1];
        }
    }

    public char getPositionValue(int position) {
        char positionValue;
        positionValue = this.results[this.round].charAt(position);
        return positionValue;
    }

    public void turnPiece(int position) {
        String temp = this.results[this.round];
        char[] tempArray = temp.toCharArray();
        tempArray[position] = 'P';
        this.results[this.round] = String.valueOf(tempArray);
    }

    public void turnSpace(int position) {
        String temp = this.results[this.round];
        char[] tempArray = temp.toCharArray();
        tempArray[position] = '-';
        this.results[this.round] = String.valueOf(tempArray);
    }

    public String[] getState() {
        return this.results;
    }

    public void printState() {

        int line = 7;

        System.out.print("\n ");
        for (int i = 0; i < 7; i++) {
            System.out.print(line + " ");
            line--;
            for (int j = 0; j < 8; j++) {
                System.out.print(results[this.round].charAt(i * 8 + j) + " ");
            }
        }
        System.out.print("  a b c d e f g\n");

    }
}
