import java.util.ArrayList;

public class Workout {
    private ArrayList<Exercise> exercises;
    private String name;

    public Workout(String name) {
        this.name = name;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }
    public double calculateTotalWorkoutVolume() {
        double totalVolume = 0;
        for (Exercise e: exercises) {
            totalVolume += e.calculateTotalVolume();
        }
        return totalVolume;
    }
    public void highestVolumeExercise() {
        double exerciseVolume = exercises.get(0).calculateTotalVolume();
        String exercise = exercises.get(0).getName();

        for (int ii = 0; ii < exercises.size(); ii++) {
            double currentExerciseVolume = exercises.get(ii).calculateTotalVolume();
            if (currentExerciseVolume > exerciseVolume) {
                exerciseVolume = currentExerciseVolume;
                exercise = exercises.get(ii).getName();
            }
        }
        System.out.println(exercise + ": was your highest volume exercise " +
   " with a volume of: " + exerciseVolume);
    }

    public void printWorkout() {
        int exerciseCounter = 1;
        for (Exercise e: exercises) {
            System.out.println(exerciseCounter + ": " + e);
        }
    }
    public int totalSets() {
        int sets = 0;
        for (Exercise e: exercises) {
            sets += e.getSets();
        }
        return sets;
    }
    
    public int totalReps() {
        int reps = 0;
        for (Exercise e: exercises) {
            reps += e.getReps();
        }
        return reps;
    }

    public int size() {
        return exercises.size();
    }
}
