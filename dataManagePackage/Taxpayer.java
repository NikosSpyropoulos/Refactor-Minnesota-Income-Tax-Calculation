package dataManagePackage;
import dataManagePackage.Receipt.Receipt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Taxpayer {
	private String name;
	private String afm;
	private String familyStatus;
	private double income;
	private double basicTax;
	private double taxIncrease;
	private double taxDecrease;
	private double totalTax;
	private ArrayList<Receipt> receipts;
	private double rates[][]= new double[][]{{5.35, 7.05, 7.05, 7.85, 9.85},
			{5.35, 7.05, 7.85, 7.85, 9.85},
			{5.35, 7.05, 7.85, 7.85, 9.85},
			{5.35, 7.05, 7.05, 7.85, 9.85}};
	private double incomes[][]= new double[][]{{36080.0, 90000.0, 143350.0, 254240.0},
			{18040.0, 71680.0, 90000.0, 127120.0},
			{24680.0, 81080.0, 90000.0, 152540.0},
			{30390.0, 90000.0, 122110.0, 203390.0}};
	private double values[][]= new double[][]{{0.0, 1930.28,5731.64,9492.82, 18197.69},
			{0.0,965.14,4746.76, 6184.88, 9098.80},
			{0.0,1320.38, 5296.58, 5996.80, 10906.19},
			{0.0,1625.87, 5828.38, 8092.13, 14472.61}};

	
	public Taxpayer(String name, String afm, String familyStatus, String income){
		this.name = name;
		this.afm = afm;
		this.familyStatus = familyStatus;
		this.income = Double.parseDouble(income);
		setBasicTaxBasedOnFamilyStatus();
		taxIncrease = 0;
		taxDecrease = 0;
		
		receipts = new ArrayList<Receipt>();

	}

	public Taxpayer() {

	}

	private void setBasicTaxBasedOnFamilyStatus(){
		switch(familyStatus.toLowerCase()){
			case("married filing jointly"):
				basicTax = calculateTax(0, income, rates, values, incomes);
				break;
			case("married filing separately"):
				basicTax = calculateTax(1, income, rates, values, incomes);
				break;
			case("single"):
				basicTax =calculateTax(2, income, rates, values, incomes);
				break;
			case("head of household"):
				basicTax = calculateTax(3, income, rates, values, incomes);
				break;
		}

		totalTax = basicTax;
	}
	public double calculateTax(int pos, double totalIncome, double rates[][], double values[][], double incomes[][]){

		double tax = 0;
//		boolean flag = false;
//
//		for(int i = 0; i < 4; i++){
//
//			if(totalIncome < incomes[pos][i]){
//				System.out.println("Ela");
//				if (i == 0){
//					System.out.println("Ela IF");
//					tax = values[pos][i] + (rates[pos][i]/100 * totalIncome);
//				}
//				else {
//					System.out.println("Ela ELSE");
//					tax = values[pos][i] + (rates[pos][i] / 100 * totalIncome - incomes[pos][i - 1]);
//				}
//				flag = true;
//				break;
//
//			}
//
//		}
//		if(flag==false) {
//		 	tax = values[pos][4] + (rates[pos][4] / 100 * totalIncome - incomes[pos][3]);
//			return tax;
//		}else{
//			return tax;
//		}

		if (totalIncome < incomes[pos][0]){
			tax = values[pos][0] + (rates[pos][0]/100) * totalIncome;
		}
		else if (totalIncome < incomes[pos][1]){
			tax = values[pos][1] + ((rates[pos][1]/100) * (totalIncome-incomes[pos][0]));
		}
		else if (totalIncome < incomes[pos][2]){
			tax = values[pos][2] + ((rates[pos][2]/100) * (totalIncome-incomes[pos][1]));
		}
		else if (totalIncome < incomes[pos][3]){
			tax = values[pos][3] + ((rates[pos][3]/100) * (totalIncome-incomes[pos][2]));
		}
		else{
			tax = values[pos][4] + ((rates[pos][4]/100) * (totalIncome-incomes[pos][3]));
		}

		return tax;
	}

	public String toString(){
		return "Name: "+name
				+"\nAFM: "+afm
				+"\nStatus: "+familyStatus
				+"\nIncome: "+String.format("%.2f", income)
				+"\nBasicTax: "+String.format("%.2f", basicTax)
				+"\nTaxIncrease: "+String.format("%.2f", taxIncrease)
				+"\nTaxDecrease: "+String.format("%.2f", taxDecrease);
	}
	
	public Receipt getReceipt(int receiptID){
		return receipts.get(receiptID);
	}
	
	public ArrayList<Receipt> getReceiptsArrayList(){
		return receipts;
	}
	
	public String[] getReceiptsList(){
		String[] receiptsList = new String[receipts.size()];
		
		int c = 0;
		for (Receipt receipt : receipts){
			receiptsList[c++] = receipt.getId() + " | " + receipt.getDate() + " | " + receipt.getAmount();
		}
		
		return receiptsList;
	}

	public double getSpecificReceiptsTotalAmount(String typeOfReceipt){

		double totalAmount = 0;

		for (Receipt receipt : receipts){
			if (receipt.getKind().equals(typeOfReceipt)){
				totalAmount += receipt.getAmount();
			}
		}

		return (new BigDecimal(totalAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

	}
	public double getBasicReceiptsTotalAmount(){
		double basicReceiptsTotalAmount = 0;

		for (Receipt receipt : receipts){
			if (receipt.getKind().equals("Basic")){
				basicReceiptsTotalAmount += receipt.getAmount();
			}
		}

		return (new BigDecimal(basicReceiptsTotalAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	public double getEntertainmentReceiptsTotalAmount(){
		double entertainmentReceiptsTotalAmount = 0;

		for (Receipt receipt : receipts){
			if (receipt.getKind().equals("Entertainment")){
				entertainmentReceiptsTotalAmount += receipt.getAmount();
			}
		}

		return (new BigDecimal(entertainmentReceiptsTotalAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	public double getTravelReceiptsTotalAmount(){
		double travelReceiptsTotalAmount = 0;

		for (Receipt receipt : receipts){
			if (receipt.getKind().equals("Travel")){
				travelReceiptsTotalAmount += receipt.getAmount();
			}
		}

		return (new BigDecimal(travelReceiptsTotalAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	public double getHealthReceiptsTotalAmount(){
		double healthReceiptsTotalAmount = 0;

		for (Receipt receipt : receipts){
			if (receipt.getKind().equals("Health")){
				healthReceiptsTotalAmount += receipt.getAmount();
			}
		}

		return (new BigDecimal(healthReceiptsTotalAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	public double getOtherReceiptsTotalAmount(){
		double otherReceiptsTotalAmount = 0;

		for (Receipt receipt : receipts){
			if (receipt.getKind().equals("Other")){
				otherReceiptsTotalAmount += receipt.getAmount();
			}
		}

		return (new BigDecimal(otherReceiptsTotalAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}



	public double getTotalReceiptsAmount(){
		double totalReceiptsAmount = 0;
		
		for (Receipt receipt : receipts){
			totalReceiptsAmount += receipt.getAmount();
		}
		
		return (new BigDecimal(totalReceiptsAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public String getName(){
		return name;
	}
	
	public String getAFM(){
		return afm;
	}
	
	public String getFamilyStatus(){
		return familyStatus;
	}
	
	public double getIncome(){
		return (new BigDecimal(income).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getBasicTax(){
		return (new BigDecimal(basicTax).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTaxInxrease(){
		return (new BigDecimal(taxIncrease).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTaxDecrease(){
		return (new BigDecimal(taxDecrease).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTotalTax(){
		return (new BigDecimal(totalTax).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public void addReceiptToList(Receipt receipt){
		receipts.add(receipt);
		
		calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts();
	}
	
	public void removeReceiptFromList(int index){
		receipts.remove(index);
		
		calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts();
	}
	
	public void calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts(){
		double totalReceiptsAmount = 0;
		for (Receipt receipt : receipts){
			totalReceiptsAmount += receipt.getAmount();
		}
		
		taxIncrease = 0;
		taxDecrease = 0;
		if ((totalReceiptsAmount/(double)income) < 0.2){
			taxIncrease = basicTax * 0.08;
		}
		else if ((totalReceiptsAmount/(double)income) < 0.4){
			taxIncrease = basicTax * 0.04;
		}
		else if ((totalReceiptsAmount/(double)income) < 0.6){
			taxDecrease = basicTax * 0.15;
		}
		else{
			taxDecrease = basicTax * 0.30;
		}
		
		totalTax = basicTax + taxIncrease - taxDecrease;
	}


}