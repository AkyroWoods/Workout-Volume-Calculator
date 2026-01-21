import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        System.out.print("Name of Workout: ");
        String workoutName = scanner.nextLine();

        while (workoutName.isBlank()) {
            System.out.println("\u001B[31m" + "Invalid workout name" + "\u001B[0m");
            System.out.print("Name of Workout: ");
            workoutName = scanner.nextLine();
        }

        Workout workout = new Workout(workoutName);

        System.out.println();
        commandList();

        while (true) {
            System.out.println();
            System.out.print("Command: ");
           String commandInput = scanner.nextLine();
            commandProcessor(workout, commandInput);
        }

    }

    public void commandProcessor(Workout workout, String commandInput) {
        
        if (commandInput.equalsIgnoreCase("help")) {
            System.out.println();
            commandList();
            return;
        } else if (commandInput.equalsIgnoreCase("quit")) {
            System.exit(0);;
        } else if (isInteger(commandInput)) {
            int command = Integer.parseInt(commandInput);

            if (command == 1) {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Sets: ");
            int sets = Integer.valueOf(scanner.nextLine());
            System.out.print("Reps: ");
            int reps = Integer.valueOf(scanner.nextLine());
            System.out.print("Weight: ");
            double weight = Double.valueOf(scanner.nextLine());

            Exercise exerciseName = new Exercise(name, sets, reps, weight);
            workout.addExercise(exerciseName);

            System.out.println();
            System.out.println("\u001B[32m" + "Exercise added" + "\u001B[0m");
            System.out.println();
        } else if (command == 2) {
            if (workout.size() == 0) {
                noExercisesAddedErrorMessage();
                return;
            }
            workout.printWorkout();
            System.out.println();

        } else if (command == 3) {
            if (workout.size() == 0) {
                noExercisesAddedErrorMessage();
                return;
            }
            System.out.println(workout.calculateTotalWorkoutVolume());

            System.out.println();
        } else if (command == 4) {
            if (workout.size() == 0) {
                noExercisesAddedErrorMessage();
                return;
            }

            workout.highestVolumeExercise();
            System.out.println();
        } 
        else 
            System.out.println("Unknown Command");
        } else 
            System.out.println("Unknown Command");
        
        
    }

    private void commandList() {
        System.out.println("1: Add exercise");
        System.out.println("2: List workout");
        System.out.println("3: Calculate total workout volume");
        System.out.println("4: show highest volume exercise");
        System.out.println("help - list commands again ");
    }
    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private void noExercisesAddedErrorMessage() {
        System.out.println("\u001B[31m" + "No exercises are in the workout" + "\u001B[0m");

    }
}
