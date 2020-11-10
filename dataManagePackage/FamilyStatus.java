package dataManagePackage;

public class FamilyStatus {


    private static final FamilyStatus SINGLE = new FamilyStatus();
    private static final FamilyStatus HEAD_OF_HOUSEHOLD = new FamilyStatus();
    private static final FamilyStatus MARRIED_FILLING_JOINTLY = new FamilyStatus();
    private static final FamilyStatus MARRIED_FILLING_SEPARATELY = new FamilyStatus();

    public void setRates(double[] rates) {
        this.rates = rates;
    }

    public void setIncomes(double[] incomes) {
        this.incomes = incomes;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public double[] getRates() {
        return rates;
    }

    public double[] getIncomes() {
        return incomes;
    }

    public double[] getValues() {
        return values;
    }

    private double rates[];
    private double incomes[];
    private double values[];



    public static FamilyStatus getFamilyStatus(String familyStatus){

        if (familyStatus.equals("single")){
            SINGLE.setRates(new double[]{5.35, 7.05, 7.05, 7.85, 9.85});
            SINGLE.setIncomes(new double[]{36080.0, 90000.0, 143350.0, 254240.0});
            SINGLE.setValues(new double[]{0.0, 1930.28,5731.64,9492.82, 18197.69});
            return SINGLE;
        }
        else if (familyStatus.equals("head of household")){
            return HEAD_OF_HOUSEHOLD;
        }
        else if (familyStatus.equals("married filing separately")){
            return MARRIED_FILLING_SEPARATELY;
        }
        else if (familyStatus.equals("married filing jointly")){
            return MARRIED_FILLING_JOINTLY;
        }

        return null;

    }
}
