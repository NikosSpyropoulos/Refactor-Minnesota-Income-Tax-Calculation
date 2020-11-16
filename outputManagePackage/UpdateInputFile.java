package outputManagePackage;

import dataManagePackage.Database;
import dataManagePackage.Receipt.Receipt;
import dataManagePackage.Taxpayer;

import java.io.*;
import java.util.ArrayList;

public class UpdateInputFile {

    private final Database database = Database.getInstance();
    private final String typeOfFile;
    private final String fileName;
    private ArrayList<ArrayList<String[]>> allInfo;

    public UpdateInputFile(String typeOfFile, String filename){
        this.typeOfFile = typeOfFile;
        this.fileName = filename;
        try {
            this.allInfo = getInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<ArrayList<String[]>> getInfo() throws IOException {

        ArrayList<ArrayList<String[]>> infoOfFile = new ArrayList<>();
        ArrayList<String[]> taxpayerInfo = new ArrayList<>();
        ArrayList<String[]> receiptsInfo = new ArrayList<>();

        boolean flagReceipts = false;

        if(typeOfFile.equals("TXT")){

            FileReader input = null;
            input = new FileReader("tests/InputInfoForTestTXT");
            BufferedReader bufRead = new BufferedReader(input);
            String myLine = null;

            while((myLine = bufRead.readLine())!=null){
                if(myLine.equals("Receipts:")){ receiptsInfo.add(new String[]{myLine, ""}); flagReceipts=true; continue;}

                if(flagReceipts) receiptsInfo.add(new String[]{myLine + " ", ""});

                else taxpayerInfo.add(new String[]{myLine + " ", ""});
            }
        }
        else if(typeOfFile.equals("XML")){

            FileReader input = null;
            input = new FileReader("tests/InputInfoForTestXML");
            BufferedReader bufRead = new BufferedReader(input);
            String myLine = null;

            while((myLine = bufRead.readLine())!=null){
                if(myLine.equals("<Receipts>")){receiptsInfo.add(new String[]{myLine, ""}); flagReceipts=true; continue;}

                if(flagReceipts){
                    String[] receiptsArray = new String[2];
                    receiptsArray[0] = myLine.split("\\s+")[0] + " ";
                    receiptsArray[1] = " " + myLine.split("\\s+")[1];

                    receiptsInfo.add(receiptsArray);
                }

                else{
                    String[] taxPayerArray = new String[2];
                    taxPayerArray[0] = myLine.split("\\s+")[0]+ " ";
                    taxPayerArray[1] = " " + myLine.split("\\s+")[1];


                    taxpayerInfo.add(taxPayerArray);
                }
            }
            receiptsInfo.add(new String[] {"</Receipts>",""});
        }

        infoOfFile.add(taxpayerInfo);
        infoOfFile.add(receiptsInfo);

        return infoOfFile;

    }

    public void saveUpdatedTaxpayerInputFile(String filePath, int taxpayerIndex){
        PrintWriter outputStream = null;
        try
        {
            outputStream = new PrintWriter(new FileOutputStream(filePath));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Problem opening: "+filePath);
        }

        Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);
        ArrayList<Receipt> receipt = taxpayer.getReceiptsArrayList();
        String[] taxpayerInfo = {taxpayer.getName(), taxpayer.getAFM(), taxpayer.getFamilyStatus(),
                String.valueOf(taxpayer.getIncome())};
        ArrayList<ArrayList<String>> allReceipts = getReceipts(receipt);

        for(int i = 0; i < taxpayerInfo.length; i++){
            outputStream.println(allInfo.get(0).get(i)[0] + taxpayerInfo[i] + allInfo.get(0).get(i)[1]);
        }

        if (taxpayer.getReceiptsArrayList().size() > 0){
            outputStream.println();
            outputStream.println(allInfo.get(1).get(0)[0]);
            outputStream.println();


            for (int i = 0; i < allReceipts.size(); i++){
                if(i>0)
                    outputStream.println();
                for (int j =1; j< 10; j++) {
                    outputStream.println(allInfo.get(1).get(j)[0] + allReceipts.get(i).get(j-1) + allInfo.get(1).get(j)[1]);
                }

            }
            if(allInfo.get(1).size() == 11){
                outputStream.println();
                outputStream.println("</Receipts>");
            }
        }

        outputStream.close();
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
}
