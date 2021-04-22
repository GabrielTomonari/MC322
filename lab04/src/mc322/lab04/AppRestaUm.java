public class AppRestaUm {

    public static void executaJogo(String caminho) {

        // CSV Reader
        CSVReader csv = new CSVReader();
        csv.setDataSource(caminho);
        String commands[] = csv.requestCommands();

        // Instances
        StateManager stateManager = new StateManager(commands.length);
        LogicController logicController = new LogicController(stateManager);

        // Main logic
        stateManager.printState();
        for (int i = 0; i < commands.length; i++) {
            logicController.setCommand(commands[i]);
            if (logicController.validateCommand()) {
                stateManager.nextRound();
                int moves[] = logicController.generateMove();
                logicController.executeMove(moves);

                String[] coord = commands[i].split(":");
                System.out.println("\nSource: " + coord[0]);
                System.out.print("Target: " + coord[1]);
                stateManager.printState();
            } else {
                String[] coord = commands[i].split(":");
                System.out.println("\nSource: " + coord[0]);
                System.out.print("Target: " + coord[1]);
                System.out.println("Comando Invalido!");
            }

        }
    }
}