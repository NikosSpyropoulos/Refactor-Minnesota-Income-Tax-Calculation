package outputManagePackage;

import dataManagePackage.Database;
import dataManagePackage.Receipt.Receipt;
import dataManagePackage.Taxpayer;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class GeneratorLogFile {

    private Database database = Database.getInstance();
    private final String typeOfFile;
    private final String fileName;
    private ArrayList<String[]> allInfo;

    public GeneratorLogFile(String typeOfFile, String filename) {
        this.typeOfFile = typeOfFile;
        this.fileName = filename;
        try {
            this.allInfo = getInfo();
        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    private ArrayList<String[]> getInfo() throws IOException {

        ArrayList<String[]> taxpayerInfo = new ArrayList<>();

        if(typeOfFile.equals("TXT")){

            FileReader input = null;
            input = new FileReader("outputManagePackage/InfoToLogFileTxt");

            BufferedReader bufRead = new BufferedReader(input);
            String myLine = null;

            while((myLine = bufRead.readLine())!=null){

                taxpayerInfo.add(new String[]{myLine + " ", ""});
            }
        }
        else if(typeOfFile.equals("XML")){

            FileReader input = null;

            input = new FileReader("outputManagePackage/InfoToLogFileXml");
            BufferedReader bufRead = new BufferedReader(input);
            String myLine = null;

            while((myLine = bufRead.readLine())!=null){
                String[] taxPayerArray = new String[2];
                taxPayerArray[0] = myLine.split("\\s+")[0]+ " ";
                taxPayerArray[1] = " " + myLine.split("\\s+")[1];


                    taxpayerInfo.add(taxPayerArray);
            }
        }

        return taxpayerInfo;

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

    public void saveTaxpayerInfoToTxtLogFile(String folderSavePath, int taxpayerIndex){
        Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);

        PrintWriter outputStream = null;
        try
        {
            outputStream = new PrintWriter(new FileOutputStream(folderSavePath+"//"+taxpayer.getAFM()+"_LOG.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Problem opening: "+folderSavePath+"//"+taxpayer.getAFM()+"_LOG.txt");
        }

        outputStream.println("Name: "+taxpayer.getName());
        outputStream.println("AFM: "+taxpayer.getAFM());
        outputStream.println("Income: "+taxpayer.getIncome());
        outputStream.println("Basic Tax: "+taxpayer.getBasicTax());
        if (taxpayer.getTaxInxrease()!=0){
            outputStream.println("Tax Increase: "+taxpayer.getTaxInxrease());
        }else{
            outputStream.println("Tax Decrease: "+taxpayer.getTaxDecrease());
        }
        outputStream.println("Total Tax: "+taxpayer.getTotalTax());
        outputStream.println("Total Receipts Amount: "+taxpayer.getTotalReceiptsAmount());
        outputStream.println("Entertainment: "+taxpayer.getSpecificReceiptsTotalAmount("Entertainment"));
        outputStream.println("Basic: "+taxpayer.getSpecificReceiptsTotalAmount("Basic"));
        outputStream.println("Travel: "+taxpayer.getSpecificReceiptsTotalAmount("Travel"));
        outputStream.println("Health: "+taxpayer.getSpecificReceiptsTotalAmount("Health"));
        outputStream.println("Other: "+taxpayer.getSpecificReceiptsTotalAmount("Other"));

        outputStream.close();

        JOptionPane.showMessageDialog(null, "Η αποθήκευση ολοκληρώθηκε", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
    }

    public void saveTaxpayerInfoToLogFile(String folderSavePath, int taxpayerIndex){
        Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);

        PrintWriter outputStream = null;
        try
        {
            if(typeOfFile.equals("XML")) {
                outputStream = new PrintWriter(new FileOutputStream(folderSavePath + "//" + taxpayer.getAFM() + "_LOG.xml"));
            }
            else if(typeOfFile.equals("TXT")){
                outputStream = new PrintWriter(new FileOutputStream(folderSavePath+"//"+taxpayer.getAFM()+"_LOG.txt"));
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Problem opening: "+folderSavePath+"//"+taxpayer.getAFM()+"_LOG.xml");
        }


        ArrayList<Receipt> receipt = taxpayer.getReceiptsArrayList();

        String[] taxpayerInfo = {taxpayer.getName(), taxpayer.getAFM(), String.valueOf(taxpayer.getIncome()),
                String.valueOf(taxpayer.getBasicTax()), String.valueOf(taxpayer.getTaxInxrease()),
                String.valueOf(taxpayer.getTaxDecrease()), String.valueOf(taxpayer.getTotalTax()),
                String.valueOf(taxpayer.getTotalReceiptsAmount()),
                String.valueOf(taxpayer.getSpecificReceiptsTotalAmount("Entertainment")),
                String.valueOf(taxpayer.getSpecificReceiptsTotalAmount("Basic")),
                String.valueOf(taxpayer.getSpecificReceiptsTotalAmount("Travel")),
                String.valueOf(taxpayer.getSpecificReceiptsTotalAmount("Health")),
                String.valueOf(taxpayer.getSpecificReceiptsTotalAmount("Other"))
        };

        for(int i = 0; i < taxpayerInfo.length; i++){

            if ( i == 4){
                if (taxpayer.getTaxInxrease()!=0){
                    outputStream.println(allInfo.get(i)[0] + taxpayerInfo[i] + allInfo.get(i)[1]);
                }else{
                    i++;
                    outputStream.println(allInfo.get(i)[0] + taxpayerInfo[i] + allInfo.get(i)[1]);
                }
            }
            outputStream.println(allInfo.get(i)[0] + taxpayerInfo[i] + allInfo.get(i)[1]);

        }

        outputStream.close();

        JOptionPane.showMessageDialog(null, "Η αποθήκευση ολοκληρώθηκε", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
    }
}
