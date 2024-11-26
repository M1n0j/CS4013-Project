
class Deductions {
    private static double prsi;
    private static double usc;
    private static double incomeTax;

    public Deductions() {
        this.prsi = 0;
        this.usc = 0;
        this.incomeTax = 0;
    }

    public static double getPrsi() {
        return prsi;
    }

    public void setPrsi(double prsi) {
        this.prsi = prsi;
    }

    public static double getUsc() {
        return usc;
    }

    public void setUsc(double usc) {
        this.usc = usc;
    }

    public static double getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(double incomeTax) {
        this.incomeTax = incomeTax;
    }

    public double getTotalDeductions() {
        return prsi + usc + incomeTax;
    }
}