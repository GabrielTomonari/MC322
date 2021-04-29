public class MatrixPosition {
    public int lin;
    public int col;

    public MatrixPosition(int line, int col) {
        this.col = col;
        this.lin = line;

        return;
    }

    static MatrixPosition createFromLetterAndNum(char letter, char num) {
        int line = MatrixPosition.turnNumToLine(num);
        int col = MatrixPosition.turnLetterToCol(letter);

        return new MatrixPosition(line, col);
    }

    static private int turnNumToLine(char num) {
        final int offset = 8;

        int value = Character.getNumericValue(num);
        int line = (value - offset) * -1;

        return line;
    }

    static private int turnLetterToCol(char letter) {
        final int offset = 97;

        int value = letter;
        int col = value - offset;

        return col;
    }
}
