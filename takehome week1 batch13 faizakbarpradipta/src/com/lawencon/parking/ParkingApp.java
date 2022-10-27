package com.lawencon.parking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class ParkingApp {
	
	public final  Scanner scanStr = new Scanner(System.in);
	public final Scanner scanInt = new Scanner(System.in);
	public final Scanner scanByt = new Scanner(System.in);
	public final Scanner scanChar = new Scanner(System.in);
	public final List<String> checkIn  = new ArrayList<String>();
	public final List<String> checkOut  = new ArrayList<String>();
	public final List<String> dateTimeCheckin  = new ArrayList<String>();
	public final List<String> dateTimeCheckout  = new ArrayList<String>();


	public static void main(String[]args) {
		final ParkingApp app = new ParkingApp();
		
		app.showMainenu();
		
	}
	
	public void showMainenu() {
		System.out.println("----------------");
		System.out.println("----MAIN MENU----");
		System.out.println("----------------");
		
		System.out.println("1. Check In");
		System.out.println("2. Check Out");
		System.out.println("3. Laporan");
		System.out.println("4. Keluar");
		System.out.println("");
		

		final byte mainMenu = byteCheck("Masukkan pilihan:");
		System.out.println("Pilihan:"+mainMenu);
		
		switch(mainMenu) {
			case 1:
				showCheckin();
				break;
			case 2:
				showCheckout();
				break;
			case 3:
				showInformation();
				break;
			default:
				showExit();
				break;
				
		}	
	}

	public void showCheckin() {
		
		System.out.println("1. Mobil");
		System.out.println("2. Motor");

		final byte chooseType = byteCheck("Pilih jenis kendaraan:");
		
		if (chooseType == 1) {
			showcheckinCar();
			showDateCheckin();

		} else if (chooseType == 2) {
			showcheckinBike();
			showDateCheckin();
			
		}else {
			System.out.println("Pilihan Tidak ada dalam menu");
		}
		
		backChoice();
	}
	
	public void showCheckout() {
		System.out.println("--------------------");
		System.out.println("----Menu Check Out----");
		
		for (int i = 0; i < checkIn.size(); i++) {
			System.out.println((i + 1) + "." + checkIn.get(i));
		}
		
		final byte chooseType = byteCheck("Masukkan kendaraan yang ingin check out :");
		System.out.println("pilihan kendaraan yang mau checkout:" + chooseType);
		checkOut.add(checkIn.get(chooseType-1));
		
		checkIn.remove(chooseType-1);
		showDateCheckout();
		backChoice();
	}
	
	public void showInformation() {
		System.out.println("------LAPORAN------");
		for (int i = 0; i < checkIn.size(); i++) {
			System.out.print((i + 1) + "." + checkIn.get(i)+" "+dateTimeCheckin.get(i));
			System.out.println(" Check In");
		}
		for (int i = 0; i < checkOut.size(); i++) {
			System.out.print((i + 1) + "." + checkOut.get(i)+" "+dateTimeCheckout.get(i));
			System.out.println(" Check Out");
		}
		
		System.out.println("");

		backChoice();
	}
	
	public void showExit() {
		System.out.println("---APLIKASI BERHENTI---");
	}
	
	public int numberCheck(String msg) {
		try {			
			System.out.println(msg);
			int input = scanInt.nextInt();
			
			return input;
		} catch(InputMismatchException e){
			scanInt.nextLine();
			System.err.println("Format harus angka");
			return numberCheck("silahkan masukkan lagi:");
		} 
	}
	
	public byte byteCheck(String msg) {
		try {			
			System.out.println(msg);
			byte input = scanByt.nextByte();
			
			return input;
		} catch(InputMismatchException e){
			scanByt.nextLine();
			System.err.println("Format harus angka");
			return byteCheck("silahkan masukkan lagi:");
		} 
	}
	public void backChoice() {
		System.out.println("Kembali? (jika YA, ketik 1)");
		System.out.println("Jika TIDAK, ketik 2)");
		
		final byte pilih = byteCheck("Masukkan Pilihan:");
		if (pilih==1) {
			showMainenu();
		}else {
			backChoice();
		}
	}
	
	
	public void  arrayDate(){
		
	}
	
	public String showDateCheckin() {
		 Date dateNow = new Date();
		SimpleDateFormat formater = 
				new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		
		String dateNowStr = formater.format(dateNow);
		dateTimeCheckin.add(dateNowStr);
		return dateNowStr;
	}
	public String showDateCheckout() {
		 Date dateNow = new Date();
		 
		SimpleDateFormat formater = 
				new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		
		String dateNowStr = formater.format(dateNow);
		dateTimeCheckout.add(dateNowStr);
		return dateNowStr;
	}
	
	public void showcheckinCar() {
		System.out.println("Masukkan digit pertama:");
		final Character inputFirstdigit = scanChar.next().charAt(0);


		final Integer inputSeconddigit = numberCheck("Masukkan digit kedua:");

		System.out.println("Masukkan digit ketiga:");
		final String inputThirddigit = scanStr.nextLine();
		
	
		if(inputFirstdigit != 'B') {
			System.out.println("Jenis Kendaraan tidak sesuai");
			showcheckinCar();
		}else if (String.valueOf(inputSeconddigit).length() == 4){
			String plateNumber = inputFirstdigit + " " + inputSeconddigit + " " + inputThirddigit;

			checkIn.add(plateNumber + " "+ PriceList.CAR.type + " "+PriceList.CAR.price);
		}else {
			System.out.println("Kendaraan Tidak Bisa Parkir (Masukkan angka minimal 4 digit)");
			showcheckinCar();
		}
	}
	
	public void showcheckinBike() {
		System.out.println("Masukkan digit pertama:");
		final char inputFirstdigit = scanChar.next().charAt(0);
		final int inputSeconddigit = numberCheck("Masukkan digit kedua:");
		
		System.out.println("Masukkan digit ketiga:");
		final String inputThirddigit = scanStr.nextLine();
		
		if(inputFirstdigit != 'B') {
			System.out.println("Jenis Kendaraan tidak sesuai");
			showcheckinBike();
		}else if (String.valueOf(inputSeconddigit).length() == 4){
			String plateNumber = inputFirstdigit + " " + inputSeconddigit + " " + inputThirddigit;

			checkIn.add(plateNumber + " "+ PriceList.BIKE.type + " "+PriceList.BIKE.price);
		}else {
			System.out.println("Kendaraan Tidak Bisa Parkir (Masukkan angka minimal 4 digit)");
			showcheckinBike();
		}
	}

}
