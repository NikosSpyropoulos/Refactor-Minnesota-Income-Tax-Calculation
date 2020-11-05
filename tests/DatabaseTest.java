package tests;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import gui.InsertNewReceiptJDialog;
import inputManagePackage.InputSystem;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class DatabaseTest {

    private static InputSystem inputSystem;
    private Database myDatabase = new Database();
    private static String filepath;


    @Test
    public void proccessTaxpayersDataFromFilesIntoDatabase() {

    }

    @Test
    public void addTaxpayerToList() {

        Taxpayer taxpayer = new Taxpayer("Nikos Zisis", "130456094",
                "","10.0");
        int size = Database.getTaxpayersArrayListSize();
        Database.addTaxpayerToList(taxpayer);
        assertEquals(size + 1, myDatabase.getTaxpayersArrayListSize());
    }

    @Test
    public void getTaxpayerFromArrayList() {

        Taxpayer taxpayer = new Taxpayer("Nikos Zisis", "130456094",
                "","10.0");
        Database.addTaxpayerToList(taxpayer);
        assertEquals(taxpayer, Database.getTaxpayerFromArrayList(0));
    }



    @Test
    public void removeTaxpayerFromArrayList() {

        Taxpayer taxpayer = new Taxpayer("Nikos Zisis", "130456094",
                "","10.0");
        Database.addTaxpayerToList(taxpayer);
        int size = Database.getTaxpayersArrayListSize();

        Database.removeTaxpayerFromArrayList(0);
        assertEquals(size - 1, Database.getTaxpayersArrayListSize());

    }

    @Test
    public void getTaxpayerNameAfmValuesPairList() {
        String nameAfm  = "Nikos Zisis | 130456094";
        Taxpayer taxpayer = new Taxpayer("Nikos Zisis", "130456094",
                 "","10.0");
       // ArrayList<Taxpayer> taxpayerArrayList = new ArrayList<>();
        Database.addTaxpayerToList(taxpayer);
        //taxpayerArrayList.add(taxpayer);
        assertEquals(nameAfm, Database.getTaxpayerNameAfmValuesPairList(0));


    }

    @Test
    public void getTaxpayersNameAfmValuesPairList() {

        Taxpayer taxpayer = new Taxpayer("Nikos Zisis", "130456094",
                "","10.0");
        Database.addTaxpayerToList(taxpayer);
        Database.addTaxpayerToList(taxpayer);

        String[] taxpayersNameAfmValuesPairList = {"Nikos Zisis | 130456094", "Nikos Zisis | 130456094"};

        assertArrayEquals(taxpayersNameAfmValuesPairList,
                Database.getTaxpayersNameAfmValuesPairList());
    }

//    @Test
//    public void updateTaxpayerInputFile() {
//
//
//        Taxpayer taxpayer = new Taxpayer("Nikos Zisis", "130456094",
//                "","10.0");
//        Database.addTaxpayerToList(taxpayer);
//        File file = new File(Database.getTaxpayersInfoFilesPath());
//
//       // InsertNewReceiptJDialog;
//
//        File file1 = new File(Database.getTaxpayersInfoFilesPath());
//
//
//
//        Database.updateTaxpayerInputFile(0);
//
//        Database.getTaxpayersInfoFilesPath();
//        assertFalse(file.equals(Database.getTaxpayersInfoFilesPath()));
//
//    }
}