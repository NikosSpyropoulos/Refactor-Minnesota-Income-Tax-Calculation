package tests;

import dataManagePackage.Database;
import dataManagePackage.Receipt.Receipt;
import dataManagePackage.Taxpayer;
import inputManagePackage.InputSystem;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class InputSystemTest {

    private Database database = Database.getInstance();
    private InputSystem inputSystem =null;

    @Test
    public void addTaxpayersDataFromFilesIntoDatabase() throws IOException {


        List<String> files = new ArrayList<>();
        files.add("130456093_INFO.txt");
        files.add("130456094_INFO.xml");
        database.proccessTaxpayersDataFromFilesIntoDatabase("InputFiles", files);

        Taxpayer taxpayerTxt = database.getTaxpayerFromArrayList(0);
        Taxpayer taxpayerXml = database.getTaxpayerFromArrayList(1);

        ArrayList<Receipt> receiptTxt = taxpayerTxt.getReceiptsArrayList();
        ArrayList<Receipt> receiptXml = taxpayerXml.getReceiptsArrayList();

        String[] taxpayerInfo1 = {taxpayerTxt.getName(), taxpayerTxt.getAFM(), taxpayerTxt.getFamilyStatus(),
                 String.valueOf(taxpayerTxt.getIncome())};
        String[] taxpayerInfo2 = {taxpayerXml.getName(), taxpayerXml.getAFM(), taxpayerXml.getFamilyStatus(),
                 String.valueOf(taxpayerXml.getIncome())};

        ArrayList<ArrayList<String>> allReceiptsTxt = getReceipts(receiptTxt);
        ArrayList<ArrayList<String>> allReceiptsXml = getReceipts(receiptXml);

        testFiles("InputFiles/130456094_INFO.xml", allReceiptsXml, setInfoTxtFile("XML",
                "InputInfoForTestXML"), taxpayerInfo2);
        testFiles("InputFiles/130456093_INFO.txt", allReceiptsTxt, setInfoTxtFile("TXT",
                "InputInfoForTestTXT"), taxpayerInfo1);

    }

    private ArrayList<ArrayList<String[]>> setInfoTxtFile(String typeOfFile, String fileName) throws IOException {

        ArrayList<ArrayList<String[]>> infoOfFile = new ArrayList<>();
        ArrayList<String[]> taxpayerInfo = new ArrayList<>();
        ArrayList<String[]> receiptsInfo = new ArrayList<>();

        FileReader input = null;
        input = new FileReader("tests/"+fileName);

        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;
        boolean flagReceipts = false;

        if(typeOfFile.equals("TXT")){
            while((myLine = bufRead.readLine())!=null){
                if(myLine.equals("Receipts:")){flagReceipts=true; continue;}

                if(flagReceipts) receiptsInfo.add(new String[]{myLine + " ", ""});

                else taxpayerInfo.add(new String[]{myLine + " ", ""});
            }
        }
        else{
            while((myLine = bufRead.readLine())!=null){
                if(myLine.equals("<Receipts>")){flagReceipts=true; continue;}

                if(flagReceipts) receiptsInfo.add(myLine.split("\\s+"));

                else taxpayerInfo.add(myLine.split("\\s+"));;
            }
        }

        infoOfFile.add(taxpayerInfo);
        infoOfFile.add(receiptsInfo);

        return infoOfFile;
    }

    private ArrayList<ArrayList<String>> getReceipts(ArrayList<Receipt>  receipt){

        ArrayList<ArrayList<String>> allReceipts = new ArrayList<>();

        for(int i =0; i<receipt.size(); i++){

            ArrayList<String> currentReceipt = new ArrayList<>();
            currentReceipt.add(receipt.get(i).getId());
            currentReceipt.add(receipt.get(i).getDate());
            currentReceipt.add(receipt.get(i).getKind());
            currentReceipt.add(String.valueOf(receipt.get(i).getAmount()));
            currentReceipt.add(receipt.get(i).getCompany().getName());
            currentReceipt.add(receipt.get(i).getCompany().getCountry());
            currentReceipt.add(receipt.get(i).getCompany().getCity());
            currentReceipt.add(receipt.get(i).getCompany().getStreet());
            currentReceipt.add(receipt.get(i).getCompany().getNumber());

            allReceipts.add(currentReceipt);
        }

        return allReceipts;
    }

    private void testFiles(String path, ArrayList<ArrayList<String>> allReceipts, ArrayList<ArrayList<String[]>> infoOfFile
    ,String[] taxpayerInfo) throws IOException {

        FileReader input = null;
        input = new FileReader(path);

        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;
        int lineCounterTaxPayer = 0;
        boolean flagReceipts = false;

        while((myLine = bufRead.readLine()) != null){

            if(myLine.isBlank() || myLine.equals("Receipts:") ||myLine.equals("<Receipts>")){
                flagReceipts = true;
                continue;
            }
            if(myLine.equals("</Receipts>")) break;

            if(flagReceipts){

                for (int i =0; i< allReceipts.size(); i++){

                    if(myLine.isBlank())  myLine = bufRead.readLine();

                    for (int j =0; j< 9; j++) {
                        if(path.endsWith(".txt")){
                            assertEquals(myLine.substring(infoOfFile.get(1).get(j)[0].length(),
                                    myLine.length() - infoOfFile.get(1).get(j)[1].length()),
                                    allReceipts.get(i).get(j));
                        }
                        else{
                            assertEquals(myLine.substring(infoOfFile.get(1).get(j)[0].length(),
                                    myLine.length() - infoOfFile.get(1).get(j)[1].length()),
                                    " " + allReceipts.get(i).get(j) + " ");
                        }

                        myLine = bufRead.readLine();
                    }

                }

            }

            else {
                if(path.endsWith(".txt")) {
                    assertEquals(myLine.substring(infoOfFile.get(0).get(lineCounterTaxPayer)[0].length(),
                            myLine.length() - infoOfFile.get(0).get(lineCounterTaxPayer)[1].length()),
                            taxpayerInfo[lineCounterTaxPayer]);
                }else{
                    assertEquals(myLine.substring(infoOfFile.get(0).get(lineCounterTaxPayer)[0].length(),
                            myLine.length() - infoOfFile.get(0).get(lineCounterTaxPayer)[1].length()),
                            " " + taxpayerInfo[lineCounterTaxPayer] + " ");
                }
                lineCounterTaxPayer++;
            }

        }
    }
}