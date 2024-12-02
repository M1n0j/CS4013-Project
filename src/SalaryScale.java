public class SalaryScale {
    private String position;
    private int level;
    private double salary;

    public SalaryScale(String position, int level, double salary) {
        this.position = position;
        this.level = level;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public int getLevel() {
        return level;
    }

    public double getSalary() {
        return salary;
    }

    public String toCSV() {
        return position + "," + level + "," + salary;
    }

    public static SalaryScale fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");

        if (fields.length < 3) {
            throw new IllegalArgumentException("Invalid CSV line for SalaryScale: " + csvLine);
        }

        String position = fields[0];
        int level = Integer.parseInt(fields[1]);
        double salary = Double.parseDouble(fields[2]);

        return new SalaryScale(position, level, salary);
    }
}
