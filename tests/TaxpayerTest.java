package tests;

import dataManagePackage.FamilyStatus;
import dataManagePackage.Receipt.Receipt;
import dataManagePackage.Taxpayer;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class TaxpayerTest {


    @Test
    public void calculateTaxForMarriedFilingJointlyTaxpayerFamilyStatus() {
        Taxpayer taxpayer = taxpayer();

        double tax= 0.0;
        double totalIncome = 1000.0;

        if (totalIncome < 36080){
            tax = (5.35/100) * totalIncome;
        }
        else if (totalIncome < 90000){
            tax = 1930.28 + ((7.05/100) * (totalIncome-36080));
        }
        else if (totalIncome < 143350){
            tax = 5731.64 + ((7.05/100) * (totalIncome-90000));
        }
        else if (totalIncome < 254240){
            tax = 9492.82 + ((7.85/100) * (totalIncome-143350));
        }
        else{
            tax = 18197.69 + ((9.85/100) * (totalIncome-254240));
        }

        assertEquals(tax, taxpayer.calculateTax( totalIncome,
                taxpayer.getFamilyStatus().getRates(), taxpayer.getFamilyStatus().getValues(),
                taxpayer.getFamilyStatus().getIncomes()));
    }

    @Test
    public void getReceiptsList() {
        Taxpayer taxpayer = taxpayer();
        taxpayer.addReceiptToList(receipt("Basic"));
        taxpayer.addReceiptToList(receipt("Basic"));

        String[] receiptsList = {"id | date | 100.0", "id | date | 100.0"};

        assertArrayEquals(receiptsList,
                taxpayer.getReceiptsList());

    }

    @Test
    public void getSpecificReceiptsTotalAmount() {
        Taxpayer taxpayer = taxpayer();
        taxpayer.addReceiptToList(receipt("Basic"));
        taxpayer.addReceiptToList(receipt("Basic"));

        assertEquals(200.0, taxpayer.getSpecificReceiptsTotalAmount("Basic"));

    }

    @Test
    public void getTotalReceiptsAmount() {
        Taxpayer taxpayer = taxpayer();
        taxpayer.addReceiptToList(receipt("Basic"));
        taxpayer.addReceiptToList(receipt("Other"));
        assertEquals(200.0, taxpayer.getTotalReceiptsAmount());
    }

    @Test
    public void addReceiptToList() {
        Taxpayer taxpayer = taxpayer();
        int size = taxpayer.getReceiptsArrayList().size();
        taxpayer.addReceiptToList(receipt("Basic"));

        assertEquals(size +1, taxpayer.getReceiptsArrayList().size());

    }

    @Test
    public void removeReceiptFromList() {
        Taxpayer taxpayer = taxpayer();
        taxpayer.addReceiptToList(receipt("Basic"));
        int size = taxpayer.getReceiptsArrayList().size();
        taxpayer.removeReceiptFromList(0);

        assertEquals(size -1, taxpayer.getReceiptsArrayList().size());
    }

    @Test
    public void calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts() {
        Taxpayer taxpayer = taxpayer();
        double income = taxpayer.getIncome();
        double basicTax = taxpayer.getBasicTax();
        double totalReceiptsAmount = taxpayer.getTotalReceiptsAmount();
        double taxIncrease = taxpayer.getTaxInxrease();
        double taxDecrease = taxpayer.getTaxDecrease();

        if ((totalReceiptsAmount/ income) < 0.2){
            taxIncrease = basicTax * 0.08;
        }
        else if ((totalReceiptsAmount/ income) < 0.4){
            taxIncrease = basicTax * 0.04;
        }
        else if ((totalReceiptsAmount/ income) < 0.6){
            taxDecrease = basicTax * 0.15;
        }
        else{
            taxDecrease = basicTax * 0.30;
        }
        double tax = basicTax + taxIncrease - taxDecrease;
        taxpayer.calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts();

        assertEquals(new BigDecimal(tax).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(),
                taxpayer.getTotalTax());
    }

    private Receipt receipt(String type){

        Receipt receipt = new Receipt(type, "id", "date", "100", "", ""
                , "", "", "");

        return receipt;
    }

    private Taxpayer taxpayer(){
        ArrayList<Double> rates = new ArrayList<>();
        ArrayList<Double> incomes = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();
        initializeLists(rates, incomes, values);

        ArrayList<ArrayList<Double>> valuesOfStatusList = new ArrayList<>();
        valuesOfStatusList.add(rates);
        valuesOfStatusList.add(incomes);
        valuesOfStatusList.add(values);

        return new Taxpayer("Nikos Zisis", "130456094",
                FamilyStatus.initializeFamilyInfo("Married filing jointly", valuesOfStatusList),"10.0");
    }

    private void initializeLists (ArrayList<Double> rates, ArrayList<Double> incomes, ArrayList<Double> values ){

        rates.add(5.35);
        rates.add(7.05);
        rates.add(7.05);
        rates.add(7.85);
        rates.add(9.85);

        incomes.add(36080.0);
        incomes.add(90000.0);
        incomes.add(143350.0);
        incomes.add(254240.0);

        values.add(0.0);
        values.add(1930.28);
        values.add(5731.64);
        values.add(9492.82);
        values.add(18197.69);

    }
}