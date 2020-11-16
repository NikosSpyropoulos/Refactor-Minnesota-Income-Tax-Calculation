package outputManagePackage;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class GeneratorLogFile {

    private Database database = Database.getInstance();

    public void saveTaxpayerInfoToTxtLogFile(String folderSavePath, int taxpayerIndex){
        Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);

        PrintWriter outputStream = null;
        try
        {
            outputStream = new PrintWriter(new FileOutputStream(folderSavePath+"//"+taxpayer.getAFM()+"_LOG.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Problem opening: "+folderSavePath+"//"+taxpayer.getAFM()+"_LOG.txt");
        }

        outputStream.println("Name: "+taxpayer.getName());
        outputStream.println("AFM: "+taxpayer.getAFM());
        outputStream.println("Income: "+taxpayer.getIncome());
        outputStream.println("Basic Tax: "+taxpayer.getBasicTax());
        if (taxpayer.getTaxInxrease()!=0){
            outputStream.println("Tax Increase: "+taxpayer.getTaxInxrease());
        }else{
            outputStream.println("Tax Decrease: "+taxpayer.getTaxDecrease());
        }
        outputStream.println("Total Tax: "+taxpayer.getTotalTax());
        outputStream.println("Total Receipts Amount: "+taxpayer.getTotalReceiptsAmount());
        outputStream.println("Entertainment: "+taxpayer.getSpecificReceiptsTotalAmount("Entertainment"));
        outputStream.println("Basic: "+taxpayer.getSpecificReceiptsTotalAmount("Basic"));
        outputStream.println("Travel: "+taxpayer.getSpecificReceiptsTotalAmount("Travel"));
        outputStream.println("Health: "+taxpayer.getSpecificReceiptsTotalAmount("Health"));
        outputStream.println("Other: "+taxpayer.getSpecificReceiptsTotalAmount("Other"));

        outputStream.close();

        JOptionPane.showMessageDialog(null, "Η αποθήκευση ολοκληρώθηκε", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
    }

    public void saveTaxpayerInfoToXmlLogFile(String folderSavePath, int taxpayerIndex){
        Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);

        PrintWriter outputStream = null;
        try
        {
            outputStream = new PrintWriter(new FileOutputStream(folderSavePath+"//"+taxpayer.getAFM()+"_LOG.xml"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Problem opening: "+folderSavePath+"//"+taxpayer.getAFM()+"_LOG.xml");
        }

        outputStream.println("<Name> "+taxpayer.getName()+" </Name>");
        outputStream.println("<AFM> "+taxpayer.getAFM()+" </AFM>");
        outputStream.println("<Income> "+taxpayer.getIncome()+" </Income>");
        outputStream.println("<BasicTax> "+taxpayer.getBasicTax()+" </BasicTax>");
        if (taxpayer.getTaxInxrease()!=0){
            outputStream.println("<TaxIncrease> "+taxpayer.getTaxInxrease()+" </TaxIncrease>");
        }else{
            outputStream.println("<TaxDecrease> "+taxpayer.getTaxDecrease()+" </TaxDecrease>");
        }
        outputStream.println("<TotalTax> "+taxpayer.getTotalTax()+" </TotalTax>");
        outputStream.println("<Receipts> "+taxpayer.getTotalReceiptsAmount()+" </Receipts>");
        outputStream.println("<Entertainment> "+taxpayer.getSpecificReceiptsTotalAmount("Entertainment") +" </Entertainment>");
        outputStream.println("<Basic> "+taxpayer.getSpecificReceiptsTotalAmount("Basic")+" </Basic>");
        outputStream.println("<Travel> "+taxpayer.getSpecificReceiptsTotalAmount("Travel")+" </Travel>");
        outputStream.println("<Health> "+taxpayer.getSpecificReceiptsTotalAmount("Health")+" </Health>");
        outputStream.println("<Other> "+taxpayer.getSpecificReceiptsTotalAmount("Other") +" </Other>");

        outputStream.close();

        JOptionPane.showMessageDialog(null, "Η αποθήκευση ολοκληρώθηκε", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
    }
}
