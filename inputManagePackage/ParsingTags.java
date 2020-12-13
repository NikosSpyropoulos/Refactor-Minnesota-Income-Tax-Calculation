package inputManagePackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParsingTags {

    public ArrayList<ArrayList<String[]>> getTags(String fileName) {

        ArrayList<ArrayList<String[]>> infoOfFile = new ArrayList<>();
        ArrayList<String[]> taxpayerInfo = new ArrayList<>();
        ArrayList<String[]> receiptsInfo = new ArrayList<>();

        FileReader input = null;
        try {
            input = new FileReader("tests/"+fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;
        boolean flagReceipts = false; //first line of receipts info

        if(fileName.equals("InputInfoForTestTXT")){
            while(true){
                try {
                    if (!((myLine = bufRead.readLine())!=null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(myLine.equals("Receipts:")){flagReceipts=true; continue;}

                if(flagReceipts) receiptsInfo.add(new String[]{myLine + " ", ""});

                else taxpayerInfo.add(new String[]{myLine + " ", ""});
            }
        }
        else{
            while(true){
                try {
                    if (!((myLine = bufRead.readLine())!=null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(myLine.equals("<Receipts>")){flagReceipts=true; continue;}

                if(flagReceipts) {
                    String[] receiptsArray = new String[2];
                    receiptsArray[0] = myLine.split("\\s+")[0] + " ";
                    receiptsArray[1] = " " + myLine.split("\\s+")[1];
                    receiptsInfo.add(receiptsArray);
                }

                else {

                    String[] taxpayerArray = new String[2];
                    taxpayerArray[0] = myLine.split("\\s+")[0] + " ";
                    taxpayerArray[1] = " " + myLine.split("\\s+")[1];

                    taxpayerInfo.add(taxpayerArray);

                }
            }
        }

        infoOfFile.add(taxpayerInfo);
        infoOfFile.add(receiptsInfo);

        return infoOfFile;
    }
}
