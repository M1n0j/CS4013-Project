/**
 * Deductions class calculates and stores multiple deductions applicable to an employees salary
 * These include prsi, usc, income tax, and union fees
 */
class Deductions {
    private double prsi;
    private double usc;
    private double incomeTax;
    private double unionFee;


    /**
     * Constructor, initializes the Deductions object with default values
     * Union fee set €20.0, the rest of the data fields are set to 0.0
     */
    public Deductions() {
        this.prsi = 0;
        this.usc = 0;
        this.incomeTax = 0;
        this.unionFee = 20.0;
    }

    /**
     * Gets the calculated prsi deduction
     *
     * @return The prsi deduction
     */

    public double getPrsi() {
        return prsi;
    }

    /**
     * Sets the prsi deduction
     * @param prsi The value to set as the prsi deduction
     */

    public void setPrsi(double prsi) {
        this.prsi = prsi;
    }

    /**
     * Gets the calculated usc deduction
     * @return The usc deduction
     */

    public double getUsc() {
        return usc;
    }

    /**
     * Gets the calculated usc deduction
     * Setting the usc deduction
     */

    public void setUsc(double usc) {
        this.usc = usc;
    }

    /**
     * Gets the calculated income tax deduction
     * @return The income tax deduction
     */

    public double getIncomeTax() {
        return incomeTax;
    }

    /**
     * Sets the income tax deduction
     * @param incomeTax The value to set as the income tax deduction
     */


    public void setIncomeTax(double incomeTax) {
        this.incomeTax = incomeTax;
    }

    /**
     * Gets the flat union fee deduction
     * @return The union fee deduction
     */

    public double getUnionFee() {
        return unionFee;
    }

    /**
     * Calculates total deductions based on the gross salary
     * It calculates prsi, usc and income tax using their methods and includes the union fee
     * @param grossSalary The gross salary from which deductions are calculated
     * @return The total amount of deductions
     */

    public double calcDeductions(double grossSalary) {
        prsi = calcPrsi(grossSalary);

        usc = calcUsc(grossSalary);

        incomeTax = calcIncomeTax(grossSalary);

        return getTotalDeductions();
    }

    /**
     * Calculates the usc based on the different brackets
     * @param grossSalary The gross salary from which USC is calculated
     * @return The calculated USC
     */

    public double calcUsc(double grossSalary) {
        double usc = 0.0;

        if (grossSalary <= 12012) {
            usc = grossSalary * 0.005;
        } else {
            usc = 12012 * 0.005;
            grossSalary -= 12012;
        }


        if (grossSalary <= (21295 - 12012)) {
            usc += grossSalary * 0.02;
        } else {
            usc += (21295 - 12012) * 0.02;
            grossSalary -= (21295 - 12012);
        }


        if (grossSalary <= (70044 - 21295)) {
            usc += grossSalary * 0.045;
        } else {
            usc += (70044 - 21295) * 0.045;
            grossSalary -= (70044 - 21295);


            usc += grossSalary * 0.08;
        }

        return usc;
    }

    /**
     * Calculates the income tax based on the different wage brackets
     * A 20% tax is applied to the first 35,300, and 40% to the remaining income
     * @param grossSalary The gross salary from which income tax is calculated
     * @return The calculated income tax
     */

    public double calcIncomeTax(double grossSalary) {
        double incomeTax = 0.0;

        if(grossSalary <= 35300) {
            incomeTax = grossSalary * 0.2;
        }else {
            incomeTax = 35300 * 0.2;
            grossSalary -= 35300;

            incomeTax += grossSalary * 0.4;
        }

        return incomeTax;
    }

    /**
     * Calculates the prsi
     * Prsi is calculated as 4% of the gross salary
     * @param grossSalary The gross salary from which prsi is calculated
     * @return The calculated prsi
     */

    public double calcPrsi(double grossSalary) {
        double prsi = grossSalary * 0.04;
        return prsi;
    }

    /**
     * Gets the total deductions including prsi, usc, income tax, and union fee
     * @return The total amount of deductions.
     */

     public double getTotalDeductions() {
         return prsi + usc + incomeTax + unionFee;
     }

}