package dataManagePackage;

public class FamilyStatus {

    private static final double[][] allRates = new double[][]{{5.35, 7.05, 7.05, 7.85, 9.85},
            {5.35, 7.05, 7.85, 7.85, 9.85},
            {5.35, 7.05, 7.85, 7.85, 9.85},
            {5.35, 7.05, 7.05, 7.85, 9.85}};
    private static final double[][] allIncomes = new double[][]{{36080.0, 90000.0, 143350.0, 254240.0},
            {18040.0, 71680.0, 90000.0, 127120.0},
            {24680.0, 81080.0, 90000.0, 152540.0},
            {30390.0, 90000.0, 122110.0, 203390.0}};
    private static final double allValues[][]= new double[][]{{0.0, 1930.28,5731.64,9492.82, 18197.69},
            {0.0,965.14,4746.76, 6184.88, 9098.80},
            {0.0,1320.38, 5296.58, 5996.80, 10906.19},
            {0.0,1625.87, 5828.38, 8092.13, 14472.61}};


    private static final FamilyStatus SINGLE = new FamilyStatus(allRates[0], allIncomes[0], allValues[0]);
    private static final FamilyStatus HEAD_OF_HOUSEHOLD = new FamilyStatus(allRates[1], allIncomes[1], allValues[1]);
    private static final FamilyStatus MARRIED_FILLING_JOINTLY = new FamilyStatus(allRates[2], allIncomes[2], allValues[2]);
    private static final FamilyStatus MARRIED_FILLING_SEPARATELY = new FamilyStatus(allRates[3], allIncomes[3], allValues[3]);

    private double[] rates;
    private double[] incomes;
    private double[] values;

    public double[] getRates() {return rates;}
    public double[] getIncomes() {return incomes;}
    public double[] getValues() {return values;}

    private FamilyStatus(double[] rates, double[] incomes, double[] values) {

        this.rates = rates;
        this.incomes = incomes;
        this.values = values;
    }

    public static FamilyStatus getFamilyStatus(String familyStatus){

        switch (familyStatus) {
            case "single":
                return SINGLE;
            case "head of household":
                return HEAD_OF_HOUSEHOLD;
            case "married filing separately":
                return MARRIED_FILLING_SEPARATELY;
            case "married filing jointly":
                return MARRIED_FILLING_JOINTLY;
        }

        return null;

    }
}
