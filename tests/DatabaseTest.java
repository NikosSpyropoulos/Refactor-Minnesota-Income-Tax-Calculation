package tests;

import dataManagePackage.Database;
import dataManagePackage.Receipt.Receipt;
import dataManagePackage.Receipt.ReceiptFactory;
import dataManagePackage.Taxpayer;
import gui.InsertNewReceiptJDialog;
import inputManagePackage.InputSystem;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    @Test
    public void updateTaxpayerInputFile() throws IOException {


        InputSystem inputSystem = new InputSystem();

        Path fileName = Path.of("InputFiles/130456093_INFO.txt");

        String actual = Files.readString(fileName);

        System.out.println(actual);



//        Taxpayer taxpayer = new Taxpayer("Nikos Zisis", "130456094",
//                "","10.0");
//        Database.addTaxpayerToList(taxpayer);
//
//        Receipt receipt1 = receipt("Basic");
//        Receipt receipt2 = receipt("Other");
//
//        taxpayer.addReceiptToList(receipt1);
//        taxpayer.addReceiptToList(receipt2);
//
//        int sizeBfrDelete = taxpayer.getReceiptsArrayList().size();

//        taxpayer.removeReceiptFromList(0);


    }

    private Receipt receipt(String type){

        Receipt receipt = ReceiptFactory.createNewReceipt(type, "id", "date", "100", "", ""
                , "", "", "");

        return receipt;
    }
}