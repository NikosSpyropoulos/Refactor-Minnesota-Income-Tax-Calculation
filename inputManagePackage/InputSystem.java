package inputManagePackage;
import dataManagePackage.*;
import dataManagePackage.Receipt.*;

import javax.lang.model.type.ArrayType;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class InputSystem {

	protected Database database = Database.getInstance();

	private static final String TAXPAYER = "taxpayer";
	private static final String RECEIPT = "receipt";

	public void loadTaxpayerDataFromFileIntoDatabase(String afmInfoFileFolderPath, String afmInfoFile) {

		Scanner inputStream = openFile(afmInfoFileFolderPath, afmInfoFile);

		Taxpayer newTaxpayer = initializeTaxpayer(inputStream);

		String fileLine;

		while (inputStream.hasNextLine()) {

			fileLine = inputStream.nextLine();

			if(fileLine.isBlank() || fileLine.equals("Receipts:") || fileLine.equals("<Receipts>"))	continue;

			if(fileLine.equals("</Receipts>")) break;

			Receipt newReceipt = initializeReceipt( inputStream, fileLine);
			newTaxpayer.addReceiptToList(newReceipt);

		}
		database.addTaxpayerToList(newTaxpayer);
	}

	private Scanner openFile(String afmInfoFileFolderPath, String afmInfoFile) {
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new FileInputStream(afmInfoFileFolderPath+"/"+afmInfoFile));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening " + afmInfoFile + " file.");
			System.exit(0);
		}
		return inputStream;
	}

	private ArrayList<String> parseInfo(Scanner inputStream, int numOfInfo, String type, String fileLine) {
		ArrayList<String> infoArrayList = new ArrayList<String>();
		int i = 0;
		while (i < numOfInfo){
			if(type.equals(TAXPAYER)){
				infoArrayList.add(inputStream.nextLine());
			}
			else if(type.equals(RECEIPT)){
				infoArrayList.add(fileLine);
				fileLine = inputStream.nextLine();
			}
			i++;
		}
		return infoArrayList;
	}

	private Taxpayer initializeTaxpayer(Scanner inputStream) {
		ArrayList<String> fileLines = parseInfo(inputStream, 4, TAXPAYER, null);
		ArrayList<String> newTaxpayerInfo = loadDataFromFileIntoDatabase(fileLines, TAXPAYER);

		ArrayList<ArrayList<Double>> valuesOfStatusList = new ArrayList<>();

		try {
			valuesOfStatusList = familyStatusInfo(newTaxpayerInfo.get(2));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Taxpayer newTaxpayer = new Taxpayer(newTaxpayerInfo.get(0), newTaxpayerInfo.get(1),
				FamilyStatus.initializeFamilyInfo(newTaxpayerInfo.get(2), valuesOfStatusList) , newTaxpayerInfo.get(3));
		return newTaxpayer;
	}

	private Receipt initializeReceipt(Scanner inputStream, String fileLine) {

		ArrayList<String> fileLines = parseInfo(inputStream, 9, RECEIPT, fileLine);
		ArrayList<String> newReceiptInfo = loadDataFromFileIntoDatabase(fileLines,RECEIPT);

		Receipt newReceipt = new Receipt(newReceiptInfo.get(2), newReceiptInfo.get(0), newReceiptInfo.get(1) ,
				newReceiptInfo.get(3), newReceiptInfo.get(4),newReceiptInfo.get(5) ,
				newReceiptInfo.get(6), newReceiptInfo.get(7), newReceiptInfo.get(8));

		return newReceipt;
	}

	private ArrayList<ArrayList<Double>> familyStatusInfo(String familyStatus) throws IOException {

		FileReader input = null;
		input = new FileReader("inputManagePackage/valuesForCalcTax");

		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;
		int lineCounter = 0;
		boolean flagStatus = false; // found right status

		ArrayList<ArrayList<Double>> valuesOfStatusList = new ArrayList<ArrayList<Double>>();

		while (lineCounter < 3){
			myLine = bufRead.readLine();

			if(myLine.equals(familyStatus.toLowerCase())){
				flagStatus = true;
				myLine = bufRead.readLine();
			}

			if(flagStatus){
				String[] values = myLine.split(" ");
				ArrayList<Double> doubleValues = new ArrayList<Double>();

				for(int i =0; i<values.length; i++){

					doubleValues.add(Double.parseDouble(values[i]));}

				valuesOfStatusList.add(doubleValues);
				lineCounter++;
			}

		}

		return valuesOfStatusList;
	}

	protected abstract ArrayList<String> loadDataFromFileIntoDatabase(ArrayList<String> typeInfo, String type);

	protected String getParameterValueFromFileLine(String fileLine, String parameterStartField, String parameterEndField){

		return fileLine.substring(parameterStartField.length(), fileLine.length()-parameterEndField.length());

	}
}