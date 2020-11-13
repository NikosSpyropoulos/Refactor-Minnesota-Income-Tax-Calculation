package dataManagePackage;

import java.util.ArrayList;

public class FamilyStatus {

//    private static final double[][] allRates = new double[][]{{5.35, 7.05, 7.05, 7.85, 9.85},
//            {5.35, 7.05, 7.85, 7.85, 9.85},
//            {5.35, 7.05, 7.85, 7.85, 9.85},
//            {5.35, 7.05, 7.05, 7.85, 9.85}};
//    private static final double[][] allIncomes = new double[][]{{36080.0, 90000.0, 143350.0, 254240.0},
//            {18040.0, 71680.0, 90000.0, 127120.0},
//            {24680.0, 81080.0, 90000.0, 152540.0},
//            {30390.0, 90000.0, 122110.0, 203390.0}};
//    private static final double allValues[][]= new double[][]{{0.0, 1930.28,5731.64,9492.82, 18197.69},
//            {0.0,965.14,4746.76, 6184.88, 9098.80},
//            {0.0,1320.38, 5296.58, 5996.80, 10906.19},
//            {0.0,1625.87, 5828.38, 8092.13, 14472.61}};

    private static ArrayList<Double> rates;
    private static ArrayList<Double> incomes;
    private static ArrayList<Double> values;

    public static void setValuesOfStatusList(ArrayList<ArrayList<Double>> valuesOfStatusList) {
        FamilyStatus.valuesOfStatusList = valuesOfStatusList;
    }

    public static ArrayList<ArrayList<Double>> valuesOfStatusList;
    public static final FamilyStatus SINGLE = new FamilyStatus(valuesOfStatusList);
    public static final FamilyStatus HEAD_OF_HOUSEHOLD = new FamilyStatus(valuesOfStatusList);
    public static final FamilyStatus MARRIED_FILLING_JOINTLY = new FamilyStatus(valuesOfStatusList);
    public static final FamilyStatus MARRIED_FILLING_SEPARATELY = new FamilyStatus(valuesOfStatusList);

    public ArrayList<Double> getRates() {return rates;}
    public ArrayList<Double> getIncomes() {return incomes;}
    public ArrayList<Double> getValues() {return values;}

    public FamilyStatus(ArrayList<ArrayList<Double>> valuesOfStatusList) {

        this.rates = valuesOfStatusList.get(0);
        this.incomes = valuesOfStatusList.get(1);
        this.values = valuesOfStatusList.get(2);
    }

    public static FamilyStatus initializeFamilyInfo(String familyStatus, ArrayList<ArrayList<Double>> valuesOfStatusList ){

        switch (familyStatus.toLowerCase()) {
            case "single":
                setValuesOfStatusList(valuesOfStatusList);
                return SINGLE;
            case "head of household":
                setValuesOfStatusList(valuesOfStatusList);
                return HEAD_OF_HOUSEHOLD;
            case "married filing separately":
                setValuesOfStatusList(valuesOfStatusList);
                return MARRIED_FILLING_SEPARATELY;
            case "married filing jointly":
                setValuesOfStatusList(valuesOfStatusList);
                return MARRIED_FILLING_JOINTLY;
        }

        return null;

    }
}
