import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnalyticsEngine {
    private double pushVolume;
    private double pullVolume;
    private double legsVolume;
    private double totalVolume;
    private Exercise highestVolumeExercise;
    private Exercise lowestVolumeExercise;
    

    public void calculateVolumeBreakdown(Workout workout) {
        pushVolume = 0;
        pullVolume = 0;
        legsVolume = 0;
        totalVolume = totalVolume();
        if (workout.getExercises().isEmpty()) {
            highestVolumeExercise = null;
            lowestVolumeExercise = null;
            return;
        }
        highestVolumeExercise = workout.getExercises().get(0);
        lowestVolumeExercise = workout.getExercises().get(0);
        for (Exercise e : workout.getExercises()) {
            double currentExerciseVolume = e.calculateTotalVolume();

            if (e.classifyExercise().equals("Push")) {
                pushVolume += currentExerciseVolume;
            } else if (e.classifyExercise().equals("Pull")) {
                pullVolume += currentExerciseVolume;
            } else if (e.classifyExercise().equals("Legs")) {
                legsVolume += currentExerciseVolume;
            }

            if (currentExerciseVolume > highestVolumeExercise.calculateTotalVolume()) {
                highestVolumeExercise = e;
            }
            if (currentExerciseVolume < lowestVolumeExercise.calculateTotalVolume()) {
                lowestVolumeExercise = e;
            }

        }
    }

    public Map<Exercise, Double> getExerciseVolumePercentages(Workout workout) {
        Map<Exercise, Double> percentages = new HashMap<>();
        double totalVolume = workout.calculateTotalWorkoutVolume();

        if (totalVolume == 0) {
            return percentages;
        }

        for (Exercise e : workout.getExercises()) {
            double volume = e.calculateTotalVolume();
            double percentage = volume / totalVolume;
            percentages.put(e, percentage);
        }
        return percentages;
    }

   public Map<Exercise, Double> getSortedExerciseVolumePercentages(Workout workout) {
        Map<Exercise, Double> percentages = new HashMap<>();
        List<Map.Entry<Exercise, Double>> sortedPercentages = new ArrayList<>();
        double totalVolume = workout.calculateTotalWorkoutVolume();

        for (Exercise e : workout.getExercises()) {
            double volume = e.calculateTotalVolume();
            double percentage = volume / totalVolume;
            percentages.put(e, percentage);
        }

        for (Map.Entry<Exercise, Double> pair : percentages.entrySet()) {
            sortedPercentages.add(pair);
        }
        Collections.sort(sortedPercentages,
                (a, b) -> Double.compare(b.getValue(), a.getValue()));

        LinkedHashMap<Exercise, Double> rankedExercises = new LinkedHashMap<>();

        for (Map.Entry<Exercise, Double> entry : sortedPercentages) {
            rankedExercises.put(entry.getKey(), entry.getValue());
        }
        return rankedExercises;

    }

    public List<Map.Entry<Exercise, Double>> topNExercises(Workout workout, int n) {
        Map<Exercise, Double> sorted = getSortedExerciseVolumePercentages(workout);
        List<Map.Entry<Exercise, Double>> topN = new ArrayList<>();

        int count = 0;
        for(Map.Entry<Exercise, Double> pair: sorted.entrySet()) {
            topN.add(pair);
            if (count >= n) {
                break;
            }
            count++;
        }
        return topN;
    }
    public List<Map.Entry<Exercise, Double>> bottomNExercises(Workout workout, int n) {
        Map<Exercise, Double> sorted = getSortedExerciseVolumePercentages(workout);

        List<Map.Entry<Exercise, Double>> entries = new ArrayList<>(sorted.entrySet());
        int size = entries.size();
        
        int start = Math.max(0, size - n);

        return entries.subList(start, size);
    }

    public Map<String, Double> volumePercentageSplit() {
        Map<String, Double> ppl = new LinkedHashMap<>();
        ppl.put("Push", getPushPercentage());
        ppl.put("Pull", getPullPercentage());
        ppl.put("Legs", getLegsPercentage());
        return ppl;
    }

    public double getPushPercentage() {
        return pushVolume / totalVolume;
    }

    public double getPullPercentage() {
        return pullVolume / totalVolume;
    }

    public double getLegsPercentage() {
        return legsVolume / totalVolume;
    }

    public Exercise getHighestVolumeExercise() {
        return highestVolumeExercise;
    }

    public Exercise getLowestVolumeExercise() {
        return lowestVolumeExercise;
    }

    private double totalVolume() {
        double totalVolume = pushVolume + pullVolume + legsVolume;
        // Prevent division by 0 before analysis runs
        if (totalVolume == 0) {
            return 1;
        }
        return totalVolume;
    }
}
