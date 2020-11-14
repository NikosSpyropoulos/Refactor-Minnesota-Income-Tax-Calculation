package tests;

import dataManagePackage.Database;
import dataManagePackage.Receipt.Receipt;
import dataManagePackage.Taxpayer;
import inputManagePackage.InputSystem;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class InputSystemTest {

    @Test
    public void addTaxpayersDataFromFilesIntoDatabase() throws IOException {


        List<String> files = new ArrayList<>();
        files.add("130456093_INFO.txt");
        files.add("130456094_INFO.xml");
        InputSystem.addTaxpayersDataFromFilesIntoDatabase("InputFiles", files);
        Taxpayer taxpayer1 = Database.getTaxpayerFromArrayList(0);
        ArrayList<Receipt> receipt1 = taxpayer1.getReceiptsArrayList();

        ArrayList<ArrayList<String>> infoOfFile = setInfoTxtFile();

        String[] taxpayerInfo = {taxpayer1.getName(), taxpayer1.getAFM(), taxpayer1.getFamilyStatus(),
                 String.valueOf(taxpayer1.getIncome())};

        ArrayList<ArrayList<String>> allReceipts = new ArrayList<>();


        for(int i =0; i<receipt1.size(); i++){

            ArrayList<String> currentReceipt = new ArrayList<>();
            currentReceipt.add(receipt1.get(i).getId());
            currentReceipt.add(receipt1.get(i).getDate());
            currentReceipt.add(receipt1.get(i).getKind());
            currentReceipt.add(String.valueOf(receipt1.get(i).getAmount()));
            currentReceipt.add(receipt1.get(i).getCompany().getName());
            currentReceipt.add(receipt1.get(i).getCompany().getCountry());
            currentReceipt.add(receipt1.get(i).getCompany().getCity());
            currentReceipt.add(receipt1.get(i).getCompany().getStreet());
            currentReceipt.add(receipt1.get(i).getCompany().getNumber());

            allReceipts.add(currentReceipt);
        }


        FileReader input = null;
        input = new FileReader("InputFiles/130456093_INFO.txt");

        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;
        int lineCounterTaxPayer = 0;
        boolean flagReceipts = false;

        while((myLine = bufRead.readLine()) != null){

            if(myLine.isBlank() || myLine.equals("Receipts:")){
                flagReceipts = true;
              continue;
            }

            if(flagReceipts){

              for (int i =0; i< allReceipts.size(); i++){

                  if(myLine.isBlank())  myLine = bufRead.readLine();

                  for (int j =0; j< 9; j++) {
                      assertEquals(myLine.substring(infoOfFile.get(1).get(j).length(), myLine.length()),
                              allReceipts.get(i).get(j));
                      myLine = bufRead.readLine();
                  }

              }

            }

            else {
                assertEquals(myLine.substring(infoOfFile.get(0).get(lineCounterTaxPayer).length(), myLine.length()),
                        taxpayerInfo[lineCounterTaxPayer]);

                lineCounterTaxPayer++;
            }

        }

    }

    private ArrayList<ArrayList<String>> setInfoTxtFile(){

        ArrayList<ArrayList<String>> infoOfFile = new ArrayList<>();
        ArrayList<String> taxpayerInfo = new ArrayList<>();
        ArrayList<String> receiptsInfo = new ArrayList<>();

        taxpayerInfo.add("Name: ");
        taxpayerInfo.add("AFM: ");
        taxpayerInfo.add("Status: ");
        taxpayerInfo.add("Income: ");

        receiptsInfo.add("Receipt ID: ");
        receiptsInfo.add("Date: ");
        receiptsInfo.add("Kind: ");
        receiptsInfo.add("Amount: ");
        receiptsInfo.add("Company: ");
        receiptsInfo.add("Country: ");
        receiptsInfo.add("City: ");
        receiptsInfo.add("Street: ");
        receiptsInfo.add("Number: ");

        infoOfFile.add(taxpayerInfo);
        infoOfFile.add(receiptsInfo);

        return infoOfFile;
    }

    private ArrayList<ArrayList<String>> setInfoXmlFile(){

        ArrayList<ArrayList<String>> infoOfFile = new ArrayList<>();
        ArrayList<String> taxpayerInfo = new ArrayList<>();
        ArrayList<String> receiptsInfo = new ArrayList<>();

        taxpayerInfo.add("Name: ");
        taxpayerInfo.add("AFM: ");
        taxpayerInfo.add("Status: ");
        taxpayerInfo.add("Income: ");

        receiptsInfo.add("Receipt ID: ");
        receiptsInfo.add("Date: ");
        receiptsInfo.add("Kind: ");
        receiptsInfo.add("Amount: ");
        receiptsInfo.add("Company: ");
        receiptsInfo.add("Country: ");
        receiptsInfo.add("City: ");
        receiptsInfo.add("Street: ");
        receiptsInfo.add("Number: ");

        infoOfFile.add(taxpayerInfo);
        infoOfFile.add(receiptsInfo);

        return infoOfFile;
    }
}