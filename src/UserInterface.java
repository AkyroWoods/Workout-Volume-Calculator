import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        System.out.print("Name of Workout: ");
        String workoutName = scanner.nextLine();

        Workout workout = new Workout(workoutName);

    

        while (true) {
            System.out.println("1: Add exercise");
            System.out.println("2: List workout");
            System.out.println("3: Calculate total workout volume");
            System.out.println("4: show highest volume exercise");

            System.out.print("Command: ");
            int commandInput = Integer.valueOf(scanner.nextLine());

            if (commandInput == -1) {
                break;
            }
            commandProcessor(workout, commandInput);
        }

    }

    public void commandProcessor(Workout workout, int commandInput) {
        if (commandInput == 1) {
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
        } else if (commandInput == 2) {
            workout.printWorkout();
            System.out.println();
        } else if (commandInput == 3) {
            System.out.println(workout.calculateTotalWorkoutVolume());
            System.out.println();
        } else if (commandInput == 4) {
            workout.highestVolumeExercise();
            System.out.println();
        }
    }
}
