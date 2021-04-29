public class AppDama {

    public static String[] executaJogo(String caminho) {

        // CSV Reader
        CSVReader csv = new CSVReader();
        csv.setDataSource(caminho);
        String commands[] = csv.requestCommands();

        // Instances
        BoardState boardState = new BoardState(1);
        BoardController boardController = new BoardController(boardState);

        // Main Logic
        for (int i = 0; i < commands.length; i++) {
            boardController.executeCommand(commands[i]);
        }

        return boardState.returnStateAsArrayofString();
    }
}
