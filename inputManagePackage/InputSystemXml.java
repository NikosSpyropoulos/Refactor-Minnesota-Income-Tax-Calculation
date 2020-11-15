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

        return allInfo;
    }


}
