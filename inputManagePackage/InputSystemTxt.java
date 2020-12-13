package inputManagePackage;

import java.util.ArrayList;

public class InputSystemTxt extends InputSystem{

    private static InputSystemTxt firstInstance = null;

	private InputSystemTxt(){ }

    public static InputSystemTxt getInstance() {
        if(firstInstance == null) {
            firstInstance = new InputSystemTxt();
        }

        return firstInstance;
    }

    public ArrayList<String> getDataFromFile(ArrayList<String> typeInfo, String type) {

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

}
