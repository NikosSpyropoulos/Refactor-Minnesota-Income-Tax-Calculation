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
    private ArrayList<String[]> infoFromTemplateFile;
    private final int CHECK_IF_INCREASE = 4;

    public GeneratorLogFile(String typeOfFile) {
        this.typeOfFile = typeOfFile;
        try {
            this.infoFromTemplateFile = getInfoFromTemplateFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> getInfoFromTemplateFile() throws IOException {

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

    public void saveTaxpayerInfoToLogFile(String folderSavePath, int taxpayerIndex){

        PrintWriter outputStream = null;
        try
        {
            if(typeOfFile.equals("XML")) {
                outputStream = new PrintWriter(new FileOutputStream(folderSavePath + "//" + getTaxpayer(taxpayerIndex).getAFM() + "_LOG.xml"));
            }
            else if(typeOfFile.equals("TXT")){
                outputStream = new PrintWriter(new FileOutputStream(folderSavePath+"//"+getTaxpayer(taxpayerIndex).getAFM()+"_LOG.txt"));
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Problem opening: "+folderSavePath+"//"+getTaxpayer(taxpayerIndex).getAFM()+"_LOG.xml");
        }

        WriteToLogFile(getTaxpayer(taxpayerIndex), outputStream);

        outputStream.close();

        JOptionPane.showMessageDialog(null, "Η αποθήκευση ολοκληρώθηκε", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
    }

    private Taxpayer getTaxpayer(int taxpayerIndex) {
        return database.getTaxpayerFromArrayList(taxpayerIndex);
    }

    private void WriteToLogFile(Taxpayer taxpayer, PrintWriter outputStream) {
        for(int i = 0; i < getTaxPayerInfo(taxpayer).length; i++){

            if ( i == CHECK_IF_INCREASE){
                if (taxpayer.getTaxInxrease() == 0) {
                    i++;    //overpass "Tax Increase: " go to "Tax Decrease: "
                    outputStream.println(infoFromTemplateFile.get(i)[0] + getTaxPayerInfo(taxpayer)[i] + infoFromTemplateFile.get(i)[1]);
                } else {
                    outputStream.println(infoFromTemplateFile.get(i)[0] + getTaxPayerInfo(taxpayer)[i] + infoFromTemplateFile.get(i)[1]);
                    i++;    //overpass "Tax Decrease: "
                }
                continue;
            }
            outputStream.println(infoFromTemplateFile.get(i)[0] + getTaxPayerInfo(taxpayer)[i] + infoFromTemplateFile.get(i)[1]);

        }
    }

    public String[] getTaxPayerInfo(Taxpayer taxpayer) {
        return new String[]{taxpayer.getName(), taxpayer.getAFM(), String.valueOf(taxpayer.getIncome()),
                String.valueOf(taxpayer.getBasicTax()), String.valueOf(taxpayer.getTaxInxrease()),
                String.valueOf(taxpayer.getTaxDecrease()), String.valueOf(taxpayer.getTotalTax()),
                String.valueOf(taxpayer.getTotalReceiptsAmount()),
                String.valueOf(taxpayer.getSpecificReceiptsTotalAmount("Entertainment")),
                String.valueOf(taxpayer.getSpecificReceiptsTotalAmount("Basic")),
                String.valueOf(taxpayer.getSpecificReceiptsTotalAmount("Travel")),
                String.valueOf(taxpayer.getSpecificReceiptsTotalAmount("Health")),
                String.valueOf(taxpayer.getSpecificReceiptsTotalAmount("Other"))
        };
    }

}