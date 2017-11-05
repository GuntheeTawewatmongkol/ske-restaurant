package code;

import java.util.Random;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Main class for SKE Restaurarnt order taking application. It displays a menu
 * and accepts items to order. When done, it prints a receipt including the
 * order total. Or playing the lucky promotions to give some allowance to
 * customer.
 *
 * @author Gunthee Tawewatmingkol
 */
public class Restaurant {
	static RestaurantManager manager = new RestaurantManager();
	static Scanner myScan = new Scanner(System.in);
	static String[] menu = new String[RestaurantManager.menuList.size()];
	static String[] priceStr = new String[RestaurantManager.priceList.size()];
	static int[] allPrice = new int[priceStr.length];
	static int[] discountPercent = { 0, 5, 10, 25, 50 };
	static String outputFile = "Last-Receipt.txt";

	public static int getChoice() {
		System.out.print("Enter your Choice : ");
		int choice = myScan.nextInt();
		return choice;
	}

	public static int getQuantity() {
		System.out.print("Enter Quantity : ");
		int quantity = myScan.nextInt();
		return quantity;
	}

	public static double getTotal(int[] unitQuantity) {
		double total = 0.0;
		for (int x = 0; x < unitQuantity.length; x++) {
			total += unitQuantity[x] * allPrice[x];
		}
		return total;
	}

	public static int[] sumOfUnitPrice(int[] unitPrice, int[] unitQuantity) {
		for (int x = 0; x < menu.length; x++) {
			unitPrice[x] = allPrice[x] * unitQuantity[x];
		}
		return unitPrice;
	}
	
	public static void dateTime() {
		String dateTime = LocalDateTime.now().toString();
		String date = dateTime.substring(0, 10);
		String time = dateTime.substring(11,19);
		System.out.println("Date >> "+ date);
		System.out.println("Time >> "+ time);
		System.out.println();
	}
	
	public static void printReceipt(int[] sumQuantity, int[] sumPrice, double total) {
		
		System.out.println("\n______MENU______ _____Qty_______ _____PRICE_____");
		if (sumQuantity[0] >= 0) {
			System.out.printf("| %s     \t|       %d\t|" + "       %d  \t|\n", menu[0], sumQuantity[0], sumPrice[0]);
		}
		if (sumQuantity[1] >= 0) {
			System.out.printf("| %s     \t|       %d\t|" + "       %d  \t|\n", menu[1], sumQuantity[1], sumPrice[1]);
		}
		if (sumQuantity[2] >= 0) {
			System.out.printf("| %s     \t|       %d\t|" + "       %d  \t|\n", menu[2], sumQuantity[2], sumPrice[2]);
		}
		System.out.println("________________________________________________");
		System.out.printf("| Total\t\t\t|" + "\t\t%.2f\t|\n", total);
		System.out.println("________________________________________________");
		dateTime();
	}
	
	public static void printReceiptToOutput(int[] sumQuantity, int[] sumPrice, double total, OutputStream out,int choice, String comment) {
		String dateTime = LocalDateTime.now().toString();
		String date = dateTime.substring(0, 10);
		PrintStream pout = new PrintStream(out);
		pout.println(date);
		pout.println("\n______MENU______ _____Qty_______ _____PRICE_____");
		if (sumQuantity[0] >= 0) {
			pout.printf("| %s     \t|       %d\t|" + "       %d  \t|\n", menu[0], sumQuantity[0], sumPrice[0]);
		}
		if (sumQuantity[1] >= 0) {
			pout.printf("| %s     \t|       %d\t|" + "       %d  \t|\n", menu[1], sumQuantity[1], sumPrice[1]);
		}
		if (sumQuantity[2] >= 0) {
			pout.printf("| %s     \t|       %d\t|" + "       %d  \t|\n", menu[2], sumQuantity[2], sumPrice[2]);
		}
		pout.println("________________________________________________");
		pout.printf("| Total\t\t\t|" + "\t\t%.2f\t|\n", total);
		pout.println("________________________________________________");
		if(choice == 6) {
			pout.println("Using-Lucky-Promotion 6");
		}
		if(choice == 7) {
			pout.println("Using-Lucky-Promotion 7");
		}
		pout.println("Comments/Suggestion : "+ comment);
		pout.print("\n===============================================================");
	}
	
	public static void saveReceiptInText(int[] sumQuantity, int[] sumPrice, double total,int choice,String comment) {
		OutputStream out = null;
		try {
			out = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e) {
			 System.out.println("Couldn't open output file "+outputFile);
		}
		printReceiptToOutput(sumQuantity, sumPrice, total, out, choice, comment);
	}

	public static void chooseChoices(int choice, int[] sumQuantity, int[] sumPrice, double total) {
		if ((choice >= 1) && (choice <= 3)) {
			int quantity = getQuantity();
			System.out.println();
			if (choice == 1) {
				sumQuantity[0] += quantity;
			}
			if (choice == 2) {
				sumQuantity[1] += quantity;
			}
			if (choice == 3) {
				sumQuantity[2] += quantity;
			}
		}
		total = getTotal(sumQuantity);
		int[] sumPrice1 = sumOfUnitPrice(sumPrice, sumQuantity);
		
		if (choice == 4) {
			printReceipt(sumQuantity, sumPrice1, total);
			
		} else if (choice == 6) {
			System.out.println("...Your Total before play the game...");
			printReceipt(sumQuantity, sumPrice1, total);
			System.out.print("Please enter number 1,2 or 3 : ");
			int luckNum1 = myScan.nextInt();
			luckyPro6(luckNum1, choice, sumQuantity, sumPrice1);
			
		} else if (choice == 7) {
			System.out.println("...Your Total before discount...");
			printReceipt(sumQuantity, sumPrice1, total);
			System.out.print("Please input your name : ");
			String name = myScan.next();
			luckyPro7(discountPercent, choice, sumQuantity, sumPrice1);
		}
	}

	public static void luckyPro6(int num, int choice, int[] sumQuantity, int[] sumPrice) {
		Random rand = new Random();
		int randomNumber = rand.nextInt(3) + 1;
		if (num == randomNumber) {
			System.out.println("CONGRATS!!!!YOU GET 'FREE' MEAL!!!!!..(*O*)b");
			for (int x = 0; x < menu.length; x++) {
				sumQuantity[x] = 0;
				sumPrice[x] = 0;
			}
			double total = getTotal(sumQuantity);
			printReceipt(sumQuantity, sumPrice, total);
		} else {
			System.out.println("Sorry...You have to pay x2 for this meal...(ToT)");
			double total = getTotal(sumQuantity) * 2;
			printReceipt(sumQuantity, sumPrice, total);
		}
	}

	public static void luckyPro7(int[] discountPercent, int choice, int[] sumQuantity, int[] sumPrice) {
		int randomDiscount = new Random().nextInt(discountPercent.length);
		int discount = discountPercent[randomDiscount];
		if (discount != 0) {
			System.out.print("Congrats!..You get " + discount + "% discount.(^_^)");
			double total = (getTotal(sumQuantity) * (100 - discount)) / 100;
			printReceipt(sumQuantity, sumPrice, total);

		} else {
			System.out.print("Sorry...You get 0% discount.(ToT)");
			for (int x = 0; x < menu.length; x++) {
				sumQuantity[x] = 0;
				sumPrice[x] = 0;
			}
			double total = getTotal(sumQuantity);
			printReceipt(sumQuantity, sumPrice, total);
		}
	}
	
	public static void makeOrder() {
		Scanner getComment = new Scanner(System.in);
		int choice = 0;
		double total = 0;
		int[] unitQuantity = new int[menu.length];
		int[] unitPrice = new int[menu.length];
		do {
			choice = getChoice();
			chooseChoices(choice, unitQuantity, unitPrice, total);
			if ((choice == 6) || (choice == 7)) {
				break;
			}
		} while (choice != 5);
		System.out.print("Your comments/suggestion : ");
		String comment = getComment.nextLine();
		System.out.println("===== Thank you =====");
		saveReceiptInText(unitQuantity, unitPrice, total,choice, comment);
	}

	public static void intro(String[] menu, int[] price) {
		System.out.print("---------Welcome to SKE Restaurant---------\n");
		System.out.printf("1.) %s\t%d\t%s.\n", menu[0], price[0], "Baht");
		System.out.printf("2.) %s\t%d\t%s.\n", menu[1], price[1], "Baht");
		System.out.printf("3.) %s\t%d\t%s.\n", menu[2], price[2], "Baht");
		System.out.println("4.) Total");
		System.out.println("5.) Exit\n");
		System.out.println("--------- ::Lucky Promotion:: ---------");
		System.out.println("**If you choose 'Lucky Promotion',you can't order more food or drink**");
		System.out.println("6.) Free or x2");
		System.out.println("7.) Get discount!! >> 0% to 50% <<\n");
	}

	public static int[] getInt(String[] price){
		int[] newPrice = new int[price.length];
		for(int x = 0;x < price.length;x++){
			newPrice[x] = Integer.parseInt(price[x]);
		}
		return newPrice;
	}
	
	public static void main(String[] args) {
		Scanner getCha = new Scanner(System.in);
		String cha = "";
		RestaurantManager.main();
		menu = manager.menuList.toArray(menu);
		priceStr = manager.priceList.toArray(priceStr);
		allPrice = getInt(priceStr);
		
		do {
		intro(menu, allPrice);
		makeOrder();
		System.out.print("\n[N]ew order or [Q]uit : ");
		cha = getCha.nextLine();
		System.out.println();
		}while(cha.equalsIgnoreCase("N"));
	}
}
