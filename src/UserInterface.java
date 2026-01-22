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
            System.exit(0);
        }
        if (isInteger(commandInput)) {
            int command = Integer.parseInt(commandInput);

            switch (command) {
                case 1:
                    String name = readNonBlankString("Name: ");
                    int sets = readPositiveInteger("Sets: ");
                    int reps = readPositiveInteger("Reps: ");
                    double weight = readNonNegativeDouble("Weight: ");

                    Exercise exerciseName = new Exercise(name, sets, reps, weight);
                    workout.addExercise(exerciseName);

                    System.out.println();
                    System.out.println("\u001B[32m" + "Exercise added" + "\u001B[0m");
                    System.out.println();
                    break;
                case 2:
                    if (workout.size() == 0) {
                        noExercisesAddedErrorMessage();
                        return;
                    }
                    workout.printWorkout();
                    System.out.println();
                    break;

                case 3:
                    if (workout.size() == 0) {
                        noExercisesAddedErrorMessage();
                        return;
                    }
                    System.out.println(workout.calculateTotalWorkoutVolume());

                    System.out.println();
                    break;
                case 4:
                    if (workout.size() == 0) {
                        noExercisesAddedErrorMessage();
                        return;
                    }
                    Exercise highestVolume = workout.highestVolumeExercise();
                    System.out.println(
                            highestVolume.getName() + " had a volume of " + highestVolume.calculateTotalVolume());
                    System.out.println();
                    break;
                case 5:
                    if (workout.size() == 0) {
                        noExercisesAddedErrorMessage();
                        return;
                    }
                    Exercise e = workout.highestVolumeExercise();
                    System.out.println(workout.getName() + ", Total Sets: " + workout.totalSets() + ", Total Reps: "
                            + workout.totalReps() +
                            "Total Volume: " + workout.calculateTotalWorkoutVolume() + e.getName()
                            + " was your highest volume exercise with a volume of "
                            + e.calculateTotalVolume());
                            break;
                default:
                    System.out.println("Unknown command. Type 'help' to see available options");
            }
        } else
            System.out.println("Invalid command number. Type 'help' to see valid commands.");

    }

    private void commandList() {
        System.out.println("1: Add exercise");
        System.out.println("2: List workout");
        System.out.println("3: Calculate total workout volume");
        System.out.println("4: Show highest volume exercise");
        System.out.println("5: Print workout Summary");
        System.out.println("help - List commands again ");
        System.out.println("quit- Quits the program");
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String readNonBlankString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            if (input.isBlank()) {
                System.out.println("Please enter a non blank name");
                continue;
            }
            return input;
        }
    }

    private int readPositiveInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            if (!isInteger(input)) {
                System.out.println("Please enter a whole number");
                continue;
            }

            int value = Integer.parseInt(input);

            if (value < 1) {
                System.out.println("Please enter a positive number");
                continue;
            }
            return value;
        }
    }

    private double readNonNegativeDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            try {
                double weight = Double.parseDouble(input);
                if (weight < 0) {
                    System.out.println("Please enter a non negative number");
                    continue;
                }
                return weight;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
                continue;
            }
        }
    }

    private void noExercisesAddedErrorMessage() {
        System.out.println("\u001B[31m" + "No exercises are in the workout" + "\u001B[0m");

    }
}
