public class AppDama {

    public static String[] executaJogo(String caminho) {

        // CSV Reader
        CSVReader csv = new CSVReader();
        csv.setDataSource(caminho);
        String commands[] = csv.requestCommands();

        // Instances
        BoardState board = new BoardState(1);

        // Main Logic
        for (int i = 0; i < commands.length; i++) {

        }

    }
}
