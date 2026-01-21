public class Exercise {
    private String name;
    private int sets;
    private int reps;
    private double weight;

    public Exercise(String name, int sets, int reps, double weight)  throws IllegalArgumentException{
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Exercise name cannot be blank");
        } 
        if (sets < 1) {
            throw new IllegalArgumentException("Sets cannot be less than 1");
        }
        if (reps < 1) {
            throw new IllegalArgumentException("Reps cannot be less than 1");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public double getWeight() {
        return weight;
    }
    
    public double calculateTotalVolume() {
        return weight * (sets * reps);
    }

    @Override
    public String toString() {
        return name + " -  Sets: " + sets + ", Reps: " + reps + ", Weight: " + weight;
    }
    
}
