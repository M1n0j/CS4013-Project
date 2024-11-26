import java.util.List;

public class SalaryScale {
    private String scaleId;
    private String scaleName;
    private List<Double> salaryPoints;
    private int maxPoints;

    public SalaryScale(String scaleId, String scaleName, List<Double> salaryPoints, int maxPoints){
        this.scaleId = scaleId;
        this.scaleName = scaleName;
        this.salaryPoints = salaryPoints;
        this.maxPoints = maxPoints;
    }

    // Getter for scaleId
    public String getScaleId() {
        return scaleId;
    }

    public double getSalaryPoint(int point){
        if (point < 1 || point > maxPoints){
            throw new IllegalArgumentException("Invalid salary point");
        }
        return salaryPoints.get(point - 1);
    }

    // Method to get salary at a specific point
    public double getSalaryAtPoint(int point) {
        return getSalaryPoint(point);
    }

    public boolean isAtMaxPoint(int currentPoint){
        return currentPoint >= maxPoints;
    }

    // Additional getters for completeness
    public String getScaleName() {
        return scaleName;
    }

    public int getMaxPoints() {
        return maxPoints;
    }
}