public class AppDama {

    public static String[] executaJogo(String caminho) {

        // CSV Reader
        CSVReader csv = new CSVReader();
        // csv.setDataSource(caminho);
        String commands[] = { "c3:d4", "f6:e5", "d4:f6" };

        // Instances
        BoardState boardState = new BoardState(1);
        BoardController boardController = new BoardController(boardState);

        // Main Logic
        for (int i = 0; i < commands.length; i++) {
            String[] coord = commands[i].split(":");
            System.out.println("\nSource: " + coord[0]);
            System.out.println("Target: " + coord[1]);
            boardController.executeCommand(commands[i]);
        }

        return boardState.returnStateAsArrayofString();
    }
}
