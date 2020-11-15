package inputManagePackage;

import dataManagePackage.FamilyStatus;
import dataManagePackage.Receipt.Receipt;
import dataManagePackage.Taxpayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputSystemTxt extends InputSystem{

//    public Taxpayer loadTaxpayerDataFromFileIntoDatabase(ArrayList<String> taxpayerInfo) {
//        String taxpayerName = getParameterValueFromTxtFileLine(taxpayerInfo.get(0), "Name: ");
//        String taxpayerAFM = getParameterValueFromTxtFileLine(taxpayerInfo.get(1), "AFM: ");
//        String taxpayerStatus = getParameterValueFromTxtFileLine(taxpayerInfo.get(2), "Status: ");
//        String taxpayerIncome = getParameterValueFromTxtFileLine(taxpayerInfo.get(3), "Income: ");
//
//        ArrayList<ArrayList<Double>> valuesOfStatusList = new ArrayList<>();
//        try {
//            valuesOfStatusList = familyStatusInfo(taxpayerStatus);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Taxpayer newTaxpayer = new Taxpayer(taxpayerName, taxpayerAFM,FamilyStatus.initializeFamilyInfo(taxpayerStatus, valuesOfStatusList) , taxpayerIncome);
//
//        return newTaxpayer;
//    }

    private static InputSystemTxt firstInstance = null;

	private InputSystemTxt(){ }

    public static InputSystemTxt getInstance() {
        if(firstInstance == null) {
            firstInstance = new InputSystemTxt();
        }

        return firstInstance;
    }
    public ArrayList<String> loadDataFromFileIntoDatabase(ArrayList<String> typeInfo, String type) {

        ArrayList<String> allInfo = new ArrayList<>();

        if (type.equals("taxpayer")) {
            allInfo.add(getParameterValueFromFileLine(typeInfo.get(0), "Name: ", ""));
            allInfo.add(getParameterValueFromFileLine(typeInfo.get(1), "AFM: ", ""));
            allInfo.add(getParameterValueFromFileLine(typeInfo.get(2), "Status: ", ""));
            allInfo.add(getParameterValueFromFileLine(typeInfo.get(3), "Income: ", ""));
        } else if (type.equals("receipt")) {
            allInfo.add(getParameterValueFromFileLine(typeInfo.get(0), "Receipt ID: ", ""));
            allInfo.add(getParameterValueFromFileLine(typeInfo.get(1), "Date: ", ""));
            allInfo.add(getParameterValueFromFileLine(typeInfo.get(2), "Kind: ", ""));
            allInfo.add(getParameterValueFromFileLine(typeInfo.get(3), "Amount: ", ""));
            allInfo.add(getParameterValueFromFileLine(typeInfo.get(4), "Company: ", ""));
            allInfo.add(getParameterValueFromFileLine(typeInfo.get(5), "Country: ", ""));
            allInfo.add(getParameterValueFromFileLine(typeInfo.get(6), "City: ", ""));
            allInfo.add(getParameterValueFromFileLine(typeInfo.get(7), "Street: ", ""));
            allInfo.add(getParameterValueFromFileLine(typeInfo.get(8), "Number: ", ""));
        }
        return allInfo;
    }

//    public Receipt loadReceiptsDataFromFileIntoDatabase(String fileLine, ArrayList<String> receiptInfo) {
//        if (fileLine.equals("")) return null;
//        if (fileLine.indexOf("Receipts:")!=-1) return null;
//
//        String receiptID = getParameterValueFromTxtFileLine(fileLine, "Receipt ID: ");
//        String receiptDate = getParameterValueFromTxtFileLine(receiptInfo.get(0), "Date: ");
//        String receiptKind = getParameterValueFromTxtFileLine(receiptInfo.get(1), "Kind: ");
//        String receiptAmount = getParameterValueFromTxtFileLine(receiptInfo.get(2), "Amount: ");
//        String receiptCompany = getParameterValueFromTxtFileLine(receiptInfo.get(3), "Company: ");
//        String receiptCountry = getParameterValueFromTxtFileLine(receiptInfo.get(4), "Country: ");
//        String receiptCity = getParameterValueFromTxtFileLine(receiptInfo.get(5), "City: ");
//        String receiptStreet = getParameterValueFromTxtFileLine(receiptInfo.get(6), "Street: ");
//        String receiptNumber = getParameterValueFromTxtFileLine(receiptInfo.get(7), "Number: ");
//
//        Receipt newReceipt = new Receipt(receiptKind, receiptID, receiptDate, receiptAmount, receiptCompany, receiptCountry, receiptCity, receiptStreet, receiptNumber);
//        return newReceipt;
//    }
//
//
//    private String getParameterValueFromTxtFileLine(String fileLine, String parameterName){
//        return fileLine.substring(parameterName.length(), fileLine.length());
//    }


}
