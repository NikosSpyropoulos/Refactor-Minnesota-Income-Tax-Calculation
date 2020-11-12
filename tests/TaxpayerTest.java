package tests;

import dataManagePackage.Database;
import dataManagePackage.Receipt.Receipt;
import dataManagePackage.Receipt.ReceiptFactory;
import dataManagePackage.Taxpayer;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class TaxpayerTest {

    private static Taxpayer taxpayer = new Taxpayer("name", "afm", "familyStatus", "100");

    @Test
    public void calculateTaxForMarriedFilingJointlyTaxpayerFamilyStatus() {
        double rates[];
        double incomes[];
        double values[];
        rates = new double[]{5.35, 7.05, 7.05, 7.85, 9.85};

        incomes= new double[]{36080.0, 90000.0, 143350.0, 254240.0};

        values= new double[]{0.0 ,1930.28,5731.64,9492.82, 18197.69};

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
                rates, values, incomes));
    }
//
//    @Test
//    void calculateTaxForMarriedFilingSeparately() {
//    }
//
//    @Test
//    void calculateTaxForSingles() {
//    }
//
//    @Test
//    void calculateTaxForHeadOfHousehold() {
//    }
//
//
//    @Test
//    void getReceipt() {
//
//
//    }
//
//    @Test
//    void getReceiptsArrayList() {
//    }
//
    @Test
    public void getReceiptsList() {

        taxpayer.addReceiptToList(receipt("Basic"));
        taxpayer.addReceiptToList(receipt("Basic"));

        String[] receiptsList = {"id | date | 100.0", "id | date | 100.0"};

        assertArrayEquals(receiptsList,
                taxpayer.getReceiptsList());

    }

    @Test
    public void getSpecificReceiptsTotalAmount() {

        taxpayer.addReceiptToList(receipt("Basic"));
        taxpayer.addReceiptToList(receipt("Basic"));

        assertEquals(200.0, taxpayer.getSpecificReceiptsTotalAmount("Basic"));

    }

//    @Test
//    void getEntertainmentReceiptsTotalAmount() {
//    }
//
//    @Test
//    void getTravelReceiptsTotalAmount() {
//    }
//
//    @Test
//    void getHealthReceiptsTotalAmount() {
//    }
//
//    @Test
//    void getOtherReceiptsTotalAmount() {
//    }
//
    @Test
    public void getTotalReceiptsAmount() {

        taxpayer.addReceiptToList(receipt("Basic"));
        taxpayer.addReceiptToList(receipt("Other"));
        assertEquals(200.0, taxpayer.getTotalReceiptsAmount());
    }

    @Test
    public void addReceiptToList() {

        int size = taxpayer.getReceiptsArrayList().size();
        taxpayer.addReceiptToList(receipt("Basic"));

        assertEquals(size +1, taxpayer.getReceiptsArrayList().size());

    }

    @Test
    public void removeReceiptFromList() {

        taxpayer.addReceiptToList(receipt("Basic"));
        int size = taxpayer.getReceiptsArrayList().size();
        taxpayer.removeReceiptFromList(0);

        assertEquals(size -1, taxpayer.getReceiptsArrayList().size());
    }

    @Test
    public void calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts() {

        double income = taxpayer.getIncome();
        double basicTax = taxpayer.getBasicTax();
        double totalReceiptsAmount = taxpayer.getTotalReceiptsAmount();
        double taxIncrease = taxpayer.getTaxInxrease();
        double taxDecrease = taxpayer.getTaxDecrease();

        if ((totalReceiptsAmount/(double)income) < 0.2){
            taxIncrease = basicTax * 0.08;
        }
        else if ((totalReceiptsAmount/(double)income) < 0.4){
            taxIncrease = basicTax * 0.04;
        }
        else if ((totalReceiptsAmount/(double)income) < 0.6){
            taxDecrease = basicTax * 0.15;
        }
        else{
            taxDecrease = basicTax * 0.30;
        }

        taxpayer.calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts();
        assertEquals((basicTax + taxIncrease - taxDecrease),
                taxpayer.getTotalTax());
    }

    private Receipt receipt(String type){

        Receipt receipt = ReceiptFactory.createNewReceipt(type, "id", "date", "100", "", ""
                , "", "", "");

        return receipt;
    }
}