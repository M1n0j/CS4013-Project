
class Deductions {
    private double prsi;
    private double usc;
    private double incomeTax;
    private double unionFee;

    public Deductions() {
        this.prsi = 0;
        this.usc = 0;
        this.incomeTax = 0;
        this.unionFee = 20.0;
    }

    public double getPrsi() {
        return prsi;
    }

    public void setPrsi(double prsi) {
        this.prsi = prsi;
    }

    public double getUsc() {
        return usc;
    }

    public void setUsc(double usc) {
        this.usc = usc;
    }

    public double getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(double incomeTax) {
        this.incomeTax = incomeTax;
    }

    public double getUnionFee() {
        return unionFee;
    }

    public double calcDeductions(double grossSalary) {
        prsi = calcPrsi(grossSalary);

        usc = calcUsc(grossSalary);

        incomeTax = calcIncomeTax(grossSalary);

        return getTotalDeductions();
    }

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

    public double calcPrsi(double grossSalary) {
        double prsi = grossSalary * 0.04;
        return prsi;
    }

     public double getTotalDeductions() {
         return prsi + usc + incomeTax + unionFee;
     }

}