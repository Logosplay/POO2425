import java.util.Scanner;

public class Main {
    private static Bound currentBound = null;
    private static String currentLocation = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServiceCollection sCollection = new ServiceCollection();

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Bye!");
                break;
            }

            String[] tokens = input.split("\\s+");
            if (tokens.length == 0) continue;

            String command = tokens[0].toLowerCase();

            switch (command) {
                case "bounds":
                    handleBounds(tokens);
                    break;
                case "help":
                    handleHelp();
                    break;
                case "eating":
                    handleEating(tokens);
                    break;
                case "lodging":
                case "leisure":
                case "services":
                case "student":
                case "students":
                case "leave":
                case "go":
                case "move":
                case "star":
                case "where":
                case "visited":
                case "ranking":
                case "ranked":
                default:
                    handleUnknownCommand();
                    break;
            }
        }

        scanner.close();
    }

    private static void handleBounds(String[] tokens) {
        if (tokens.length < 6) {
            System.out.println("Invalid bounds.");
            return;
        }
    
        try {
            long maxLat = Long.parseLong(tokens[1]);
            long minLon = Long.parseLong(tokens[2]);
            long minLat = Long.parseLong(tokens[3]);
            long maxLon = Long.parseLong(tokens[4]);
    
            if (maxLat <= minLat || minLon >= maxLon) {
                System.out.println("Invalid bounds.");
                return;
            }
    
            StringBuilder nameBuilder = new StringBuilder();
            for (int i = 5; i < tokens.length; i++) {
                nameBuilder.append(tokens[i]).append(" ");
            }
            String name = nameBuilder.toString().trim();
    
            if (name.isEmpty()) {
                System.out.println("Invalid bounds.");
                return;
            }
    
            // Clear the system data and set the new bound
            BoundManager.setBound(new Bound(maxLat, minLon, minLat, maxLon));
            System.out.println(name + " created.");
    
        } catch (NumberFormatException e) {
            System.out.println("Invalid bounds.");
        }
    }

    private static void handleHelp() {
        System.out.println("Available commands: bounds, help, exit, eating, lodging, leisure, services, student, etc.");
    }

    private static void handleUnknownCommand() {
        if (currentBound == null) {
            System.out.println("System bounds not defined.");
        } else {
            System.out.println("Unknown command. Type help to see available commands.");
        }
    }
    
    private static void handleEating(String[] tokens, ServiceCollection sCollection){
        if (tokens.length < 5) {
            System.out.println("Invalid bounds.");
            return;
        }
        
        try{
            int latService = Integer.parseInt(tokens[1]);
            int lonService = Integer.parseInt(tokens[2]);
            int priceService = Integer.parseInt(tokens[3]);
            String nameService = tokens[4];
            String type = "Restaurant";

            if (latService > BoundManager.getBound().maxLat || latService < BoundManager.getBound().minLat || lonService > BoundManager.getBound().maxLon || lonService < BoundManager.getBound().minLon) {
                System.out.println(nameService + "location invalid!");
                return;
            }

            if (priceService <= 0){
                System.out.println("Invalid menu price!");
                return;
            }

            if (sCollection.hasService(nameService)){
                System.out.println(nameService + " already exists!");
                return;
            }

            

        }
    }
}   

