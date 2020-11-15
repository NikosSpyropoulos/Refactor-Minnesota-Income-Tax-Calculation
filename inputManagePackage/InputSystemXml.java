package inputManagePackage;

import dataManagePackage.FamilyStatus;
import dataManagePackage.Receipt.Receipt;
import dataManagePackage.Taxpayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputSystemXml extends InputSystem {

    private static InputSystemXml firstInstance = null;

    private InputSystemXml(){ }

    public static InputSystemXml getInstance() {
        if(firstInstance == null) {
            firstInstance = new InputSystemXml();
        }

        return firstInstance;
    }
    public ArrayList<String> loadDataFromFileIntoDatabase(ArrayList<String> typeInfo, String type) {

       ArrayList<String> allInfo = new ArrayList<>();

       if(type.equals("taxpayer")){
           allInfo.add(getParameterValueFromFileLine(typeInfo.get(0), "<Name> ", " </Name>"));
           allInfo.add(getParameterValueFromFileLine(typeInfo.get(1), "<AFM> ", " </AFM>"));
           allInfo.add(getParameterValueFromFileLine(typeInfo.get(2), "<Status> ", " </Status>"));
           allInfo.add(getParameterValueFromFileLine(typeInfo.get(3), "<Income> ", " </Income>"));
       }
       else if(type.equals("receipt")){
           allInfo.add( getParameterValueFromFileLine(typeInfo.get(0), "<ReceiptID> ", " </ReceiptID>"));
           allInfo.add(getParameterValueFromFileLine(typeInfo.get(1), "<Date> ", " </Date>"));
           allInfo.add(getParameterValueFromFileLine(typeInfo.get(2), "<Kind> ", " </Kind>"));
           allInfo.add( getParameterValueFromFileLine(typeInfo.get(3), "<Amount> ", " </Amount>"));
           allInfo.add(getParameterValueFromFileLine(typeInfo.get(4), "<Company> ", " </Company>"));
           allInfo.add(getParameterValueFromFileLine(typeInfo.get(5), "<Country> ", " </Country>"));
           allInfo.add(getParameterValueFromFileLine(typeInfo.get(6), "<City> ", " </City>"));
           allInfo.add(getParameterValueFromFileLine(typeInfo.get(7), "<Street> ", " </Street>"));
           allInfo.add(getParameterValueFromFileLine(typeInfo.get(8), "<Number> ", " </Number>"));
       }
//        String taxpayerName = getParameterValueFromXmlFileLine(taxpayerInfo.get(0), "<Name> ", " </Name>");
//        String taxpayerAFM = getParameterValueFromXmlFileLine(taxpayerInfo.get(1), "<AFM> ", " </AFM>");
//        String taxpayerStatus = getParameterValueFromXmlFileLine(taxpayerInfo.get(2), "<Status> ", " </Status>");
//        String taxpayerIncome = getParameterValueFromXmlFileLine(taxpayerInfo.get(3), "<Income> ", " </Income>");

  //      ArrayList<ArrayList<Double>> valuesOfStatusList = new ArrayList<>();

//        try {
//            valuesOfStatusList = familyStatusInfo(taxpayerStatus);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Taxpayer newTaxpayer = new Taxpayer(taxpayerName, taxpayerAFM,FamilyStatus.initializeFamilyInfo(taxpayerStatus, valuesOfStatusList) , taxpayerIncome);
        return allInfo;
    }
//    public Receipt loadReceiptsDataFromFileIntoDatabase(String fileLine, ArrayList<String> receiptInfo) {
//        if (fileLine.equals("")) return null;
//        if (fileLine.indexOf("<Receipts>")!=-1) return null;
//
////        String receiptID = getParameterValueFromXmlFileLine(fileLine, "<ReceiptID> ", " </ReceiptID>");
////        String receiptDate = getParameterValueFromXmlFileLine(receiptInfo.get(0), "<Date> ", " </Date>");
////        String receiptKind = getParameterValueFromXmlFileLine(receiptInfo.get(1), "<Kind> ", " </Kind>");
////        String receiptAmount = getParameterValueFromXmlFileLine(receiptInfo.get(2), "<Amount> ", " </Amount>");
////        String receiptCompany = getParameterValueFromXmlFileLine(receiptInfo.get(3), "<Company> ", " </Company>");
////        String receiptCountry = getParameterValueFromXmlFileLine(receiptInfo.get(4), "<Country> ", " </Country>");
////        String receiptCity = getParameterValueFromXmlFileLine(receiptInfo.get(5), "<City> ", " </City>");
////        String receiptStreet = getParameterValueFromXmlFileLine(receiptInfo.get(6), "<Street> ", " </Street>");
////        String receiptNumber = getParameterValueFromXmlFileLine(receiptInfo.get(7), "<Number> ", " </Number>");
//
//        Receipt newReceipt = new Receipt(receiptKind, receiptID, receiptDate, receiptAmount, receiptCompany, receiptCountry, receiptCity, receiptStreet, receiptNumber);
//        return newReceipt;
//    }

//    private static String getParameterValueFromXmlFileLine(String fileLine, String parameterStartField, String parameterEndField){
//        return fileLine.substring(parameterStartField.length(), fileLine.length()-parameterEndField.length());
//    }

}
