package tests;

import dataManagePackage.Database;
import dataManagePackage.FamilyStatus;
import dataManagePackage.Receipt.Receipt;
import dataManagePackage.Taxpayer;
import gui.TaxpayerReceiptsManagementJDialog;
import inputManagePackage.InputSystem;
import org.junit.Test;
import outputManagePackage.OutputSystem;

import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class DatabaseTest {

    private  Database database = Database.getInstance();

    @Test
    public void addTaxpayerToList() {

        Taxpayer taxpayer = taxpayer();
        int size = database.getTaxpayersArrayListSize();
        database.addTaxpayerToList(taxpayer);
        assertEquals(size + 1, database.getTaxpayersArrayListSize());
    }

    @Test
    public void getTaxpayerFromArrayList() {

        Taxpayer taxpayer = taxpayer();
        database.addTaxpayerToList(taxpayer);
        assertEquals(taxpayer, database.getTaxpayerFromArrayList(0));
    }

    @Test
    public void removeTaxpayerFromArrayList() {

        Taxpayer taxpayer = taxpayer();
        database.addTaxpayerToList(taxpayer);
        int size = database.getTaxpayersArrayListSize();

        database.removeTaxpayerFromArrayList(0);
        assertEquals(size - 1, database.getTaxpayersArrayListSize());

    }

    @Test
    public void getTaxpayerNameAfmValuesPairList() {
        String nameAfm  = "Nikos Zisis | 130456094";
        Taxpayer taxpayer = taxpayer();
        database.addTaxpayerToList(taxpayer);
        assertEquals(nameAfm, database.getTaxpayerNameAfmValuesPairList(0));

    }

    @Test
    public void getTaxpayersNameAfmValuesPairList() {

        Taxpayer taxpayer = taxpayer();
        database.addTaxpayerToList(taxpayer);
        database.addTaxpayerToList(taxpayer);

        String[] taxpayersNameAfmValuesPairList = {"Nikos Zisis | 130456094", "Nikos Zisis | 130456094"};

        assertArrayEquals(taxpayersNameAfmValuesPairList,
                database.getTaxpayersNameAfmValuesPairList());
    }

//    @Test
//    public void updateTaxpayerInputFile() throws IOException {
//
//        List<String> files = new ArrayList<>();
//        files.add("130456093_INFO.txt");
//        files.add("130456094_INFO.xml");
//        Path fileName = Path.of("InputFiles/130456093_INFO.txt");
//        InputSystem.addTaxpayersDataFromFilesIntoDatabase("InputFiles", files);
//
//        Taxpayer taxpayer1 = Database.getTaxpayerFromArrayList(0);
//        ArrayList<Receipt> receipt1 = taxpayer1.getReceiptsArrayList();
//
//
//
//        TaxpayerReceiptsManagementJDialog taxpayerReceiptsManagementJDialog =
//                new TaxpayerReceiptsManagementJDialog(null, 0);
//        taxpayerReceiptsManagementJDialog.
//
//
//        String fileBfrEdit = Files.readString(fileName);
//
//        OutputSystem.saveUpdatedTaxpayerTxtInputFile(
//                "D:\\SoftDevII-ProjectMaterial-2021\\project-material\\Minnesota Income Tax Calculation Project\\InputFiles"
//                , 0);
//
//       // Database.updateTaxpayerInputFile(0);
//
//        String fileAfterEdit = Files.readString(fileName);
//
//        assertThat(fileBfrEdit, not(fileAfterEdit));
//
//
//    }

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