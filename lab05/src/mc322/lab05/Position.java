public class Position {
    public int lin;
    public int col;

    public static void main(String[] args) {
        Position pos = new Position('c', '4');
        System.out.println("linha " + pos.lin);
        System.out.println("coluna " + pos.col);
    }

    public Position(char letter, char num) {
        this.col = turnLetterToCol(letter);
        this.lin = turnNumToLine(num);

        return;
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
