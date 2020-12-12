package tests;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import org.junit.Test;
import outputManagePackage.GeneratorLogFile;
import outputManagePackage.UpdateInputFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;



public class UpdateInputFileTest {

    private Database database = Database.getInstance();
    private final String INPUT_FILE_TXT = "TXT";
    private final String INPUT_FILE_XML = "XML";

    private final int TAXPAYER_TXT = 0;
    private final int TAXPAYER_XML = 1;

    @Test
    public void saveUpdatedTaxpayerInputFile() throws IOException {

        initializeTaxPayers();

        CheckInfoEquality(database.getTaxpayerFromArrayList(TAXPAYER_TXT), INPUT_FILE_TXT, TAXPAYER_TXT);
        CheckInfoEquality(database.getTaxpayerFromArrayList(TAXPAYER_XML), INPUT_FILE_XML, TAXPAYER_XML);
    }

    private void initializeTaxPayers() {
        List<String> files = new ArrayList<>();
        files.add("130456093_INFO.txt");
        files.add("130456094_INFO.xml");
        database.proccessTaxpayersDataFromFilesIntoDatabase("InputFiles", files);
    }

    private void CheckInfoEquality(Taxpayer taxpayer, String typeOfInputFile, int taxpayerIndex) throws IOException {

        UpdateInputFile updateInputFile = new UpdateInputFile(typeOfInputFile);
        taxpayer.removeReceiptFromList(0);
        updateInputFile.saveUpdatedTaxpayerInputFile("InputFiles/"+ taxpayer.getAFM() + "_INFO."+ typeOfInputFile.toLowerCase() , taxpayerIndex);
        String[] taxpayerInfo = updateInputFile.getTaxPayerInfo(taxpayerIndex);
        ArrayList<ArrayList<String>> taxpayerReceipts = updateInputFile.getReceipts(taxpayerIndex);
        ArrayList<ArrayList<String[]>> infoFromTemplateFile = updateInputFile.getInfoFromTemplateFile();
        BufferedReader bufRead = getBufferedReader("InputFiles/"+ taxpayer.getAFM() + "_INFO." + typeOfInputFile.toLowerCase());

        String myLine;
        int templateIndex = 0;
        int taxpayerValue = 0;

        while(!(myLine = bufRead.readLine()).isBlank()){
            assertEquals(myLine,
                    infoFromTemplateFile.get(0).get(templateIndex)[0].concat(taxpayerInfo[taxpayerValue].
                            concat(infoFromTemplateFile.get(0).get(templateIndex)[1])));
            templateIndex++;
            taxpayerValue++;
        }

        bufRead.readLine(); //read "Receipts: " or "<Receipts>"
        bufRead.readLine(); // read blank line

        int receiptIndex = 0;

        for(int i =0 ; i < taxpayer.getReceiptsArrayList().size() - 1;i++){

            templateIndex = 1;
            int currentReceiptValue = 0;
            while(!(myLine = bufRead.readLine()).isBlank()){

                assertEquals(myLine,
                        infoFromTemplateFile.get(1).get(templateIndex)[0].
                                concat(taxpayerReceipts.get(receiptIndex).get(currentReceiptValue).
                                        concat(infoFromTemplateFile.get(1).get(templateIndex)[1])));
                templateIndex++;
                currentReceiptValue++;
            }
            receiptIndex++;
        }

    }

    private BufferedReader getBufferedReader(String path) throws FileNotFoundException {
        FileReader input = null;
        input = new FileReader(path);
        BufferedReader bufRead = new BufferedReader(input);
        return bufRead;
    }
}