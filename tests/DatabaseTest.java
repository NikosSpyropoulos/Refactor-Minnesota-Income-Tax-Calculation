package tests;

import dataManagePackage.Database;
import dataManagePackage.FamilyStatus;
import dataManagePackage.Receipt.Receipt;
import dataManagePackage.Receipt.ReceiptFactory;
import dataManagePackage.Taxpayer;
import gui.InsertNewReceiptJDialog;
import inputManagePackage.InputSystem;
import org.junit.Test;
import outputManagePackage.OutputSystem;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class DatabaseTest {

    private Database myDatabase = new Database();


    @Test
    public void proccessTaxpayersDataFromFilesIntoDatabase() {

    }

    @Test
    public void addTaxpayerToList() {

        Taxpayer taxpayer = taxpayer();
        int size = Database.getTaxpayersArrayListSize();
        Database.addTaxpayerToList(taxpayer);
        assertEquals(size + 1, myDatabase.getTaxpayersArrayListSize());
    }

    @Test
    public void getTaxpayerFromArrayList() {

        Taxpayer taxpayer = taxpayer();
        Database.addTaxpayerToList(taxpayer);
        assertEquals(taxpayer, Database.getTaxpayerFromArrayList(0));
    }



    @Test
    public void removeTaxpayerFromArrayList() {

        Taxpayer taxpayer = taxpayer();
        Database.addTaxpayerToList(taxpayer);
        int size = Database.getTaxpayersArrayListSize();

        Database.removeTaxpayerFromArrayList(0);
        assertEquals(size - 1, Database.getTaxpayersArrayListSize());

    }

    @Test
    public void getTaxpayerNameAfmValuesPairList() {
        String nameAfm  = "Nikos Zisis | 130456094";
        Taxpayer taxpayer = taxpayer();
        Database.addTaxpayerToList(taxpayer);
        assertEquals(nameAfm, Database.getTaxpayerNameAfmValuesPairList(0));

    }

    @Test
    public void getTaxpayersNameAfmValuesPairList() {

        Taxpayer taxpayer = taxpayer();
        Database.addTaxpayerToList(taxpayer);
        Database.addTaxpayerToList(taxpayer);

        String[] taxpayersNameAfmValuesPairList = {"Nikos Zisis | 130456094", "Nikos Zisis | 130456094"};

        assertArrayEquals(taxpayersNameAfmValuesPairList,
                Database.getTaxpayersNameAfmValuesPairList());
    }

//    @Test
//    public void updateTaxpayerInputFile() throws IOException {
//
//
//
//        ArrayList<String > taxpayersAfmInfoFiles = new ArrayList<String>();
//        taxpayersAfmInfoFiles.add("130456094_INFO.xml");
//        InputSystem.addTaxpayersDataFromFilesIntoDatabase("InputFiles/130456094_INFO.xml", taxpayersAfmInfoFiles);
//
//        Path fileNameBefore = Path.of("InputFiles/130456094_INFO.xml");
//        String stringBefore = Files.readString(fileNameBefore);
//
////        OutputSystem.saveUpdatedTaxpayerTxtInputFile("InputFiles/130456093_INFO.txt", 0);
//
//        Database.updateTaxpayerInputFile(0);
//
//        Path fileNameAfter = Path.of("InputFiles/130456094_INFO.xml");
//        String stringAfter = Files.readString(fileNameBefore);
//
//
//        assertEquals(stringBefore, stringAfter);
//
//    }

    private Receipt receipt(String type){

        Receipt receipt = ReceiptFactory.createNewReceipt(type, "id", "date", "100", "", ""
                , "", "", "");

        return receipt;
    }

    private Taxpayer taxpayer(){

        return new Taxpayer("Nikos Zisis", "130456094",
                null,"10.0");
    }
}