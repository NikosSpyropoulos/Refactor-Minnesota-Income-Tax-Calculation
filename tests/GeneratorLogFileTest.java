package tests;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import org.junit.Test;
import outputManagePackage.GeneratorLogFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class GeneratorLogFileTest {

    private Database database = Database.getInstance();
    private final int CHECK_IF_INCREASE = 4;
    private final String INPUT_FILE_TXT = "TXT";
    private final String INPUT_FILE_XML = "XML";
    private final String OUTPUT_FILE_TXT = "TXT";
    private final String OUTPUT_FILE_XML = "XML";
    private final int TAXPAYER_TXT = 0;
    private final int TAXPAYER_XML = 1;

    @Test
    public void saveTaxpayerInfoToLogFile() throws IOException {

        initializeTaxPayers();

        CheckInfoEquality(database.getTaxpayerFromArrayList(TAXPAYER_TXT), INPUT_FILE_TXT, OUTPUT_FILE_TXT);
        CheckInfoEquality(database.getTaxpayerFromArrayList(TAXPAYER_TXT), INPUT_FILE_TXT, OUTPUT_FILE_XML);
        CheckInfoEquality(database.getTaxpayerFromArrayList(TAXPAYER_XML), INPUT_FILE_XML, OUTPUT_FILE_TXT);
        CheckInfoEquality(database.getTaxpayerFromArrayList(TAXPAYER_XML), INPUT_FILE_XML, OUTPUT_FILE_XML);

    }

    private void initializeTaxPayers() {
        List<String> files = new ArrayList<>();
        files.add("130456093_INFO.txt");
        files.add("130456094_INFO.xml");
        database.proccessTaxpayersDataFromFilesIntoDatabase("InputFiles", files);
    }

    private void CheckInfoEquality(Taxpayer taxpayer, String typeOfInputFile, String typeOfLOGFile) throws IOException {

        GeneratorLogFile generatorLogFile = new GeneratorLogFile(typeOfLOGFile);

        BufferedReader bufRead = new BufferedReader(getFileReader(typeOfInputFile, typeOfLOGFile, generatorLogFile));
        String myLine = null;

         int lineOfFileItem=0;

         while((myLine = bufRead.readLine())!=null){
             assertEquals(myLine,getLinesOfFile(taxpayer, generatorLogFile.getTaxPayerInfo(taxpayer),
                     generatorLogFile.getInfoFromTemplateFile()).get(lineOfFileItem));
             lineOfFileItem++;
         }
    }

    private ArrayList<String> getLinesOfFile(Taxpayer taxpayer, String[] taxpayerInfo, ArrayList<String[]> infoFromTemplateFile) {
        ArrayList<String> linesOfFile = new ArrayList<>();

        for(int i = 0; i < taxpayerInfo.length; i++) {

            if ( i == CHECK_IF_INCREASE){
                if (taxpayer.getTaxInxrease() == 0) {
                    i++;    //overpass "Tax Increase: " go to "Tax Decrease: "
                    linesOfFile.add(infoFromTemplateFile.get(i)[0].concat(taxpayerInfo[i].concat(infoFromTemplateFile.get(i)[1])));
                } else {
                    linesOfFile.add(infoFromTemplateFile.get(i)[0].concat(taxpayerInfo[i].concat(infoFromTemplateFile.get(i)[1])));
                    i++;    //overpass "Tax Decrease: "
                }
                continue;
            }
            linesOfFile.add(infoFromTemplateFile.get(i)[0].concat(taxpayerInfo[i].concat(infoFromTemplateFile.get(i)[1])));
        }
        return linesOfFile;
    }

    private FileReader getFileReader(String typeOfFile, String typeOfLOGFile, GeneratorLogFile generatorLogFile) throws FileNotFoundException {
        FileReader input = null;
        if(typeOfFile.equals("TXT")) {

            input = readFromFile(typeOfLOGFile, generatorLogFile, TAXPAYER_TXT);

        }else{
            input = readFromFile(typeOfLOGFile, generatorLogFile, TAXPAYER_XML);
        }
        return input;
    }

    private FileReader readFromFile(String typeOfLOGFile, GeneratorLogFile generatorLogFile, int index) throws FileNotFoundException {

        FileReader input;
        generatorLogFile.saveTaxpayerInfoToLogFile("tests", index);

        if (typeOfLOGFile.equals("TXT"))
            input = new FileReader("tests/" + database.getTaxpayerFromArrayList(index).getAFM() + "_LOG.txt");
        else
            input = new FileReader("tests/" + database.getTaxpayerFromArrayList(index).getAFM() + "_LOG.xml");
        return input;
    }
}