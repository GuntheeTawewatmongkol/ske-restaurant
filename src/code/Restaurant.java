package code;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Restaurant {
	public static RestaurantManager restaurant;

	public static String[] menuArray;
	public static String[] priceStr;
	public static double[] priceDb;
	public static String current;

	public static String[] receipt;
	public static ArrayList<Object> allReceipt;

	static int[] discountPercent = { 0, 5, 10, 25, 50 };

	static String outputFile = "src/data/AllReceipt.txt";
	static Scanner myScan = new Scanner(System.in);

	public static double[] getDouble(String[] price) {
		double[] newPrice = new double[price.length];
		for (int x = 0; x < price.length; x++) {
			newPrice[x] = Integer.parseInt(price[x]);
		}
		return newPrice;
	}

	public static String askRestaurantName() {
		Scanner getRestName = new Scanner(System.in);
		System.out.print("What is the name of your restaurant?? ");
		return getRestName.nextLine();
	}

	public static String askMenuFile() {
		Scanner getFileName = new Scanner(System.in);
		System.out.println("This is your NEW RESTAURANT!!");
		System.out.print("Please input name of your menufile : ");
		String fileName = getFileName.nextLine();
		return String.format("data/%s.txt", fileName);
	}

	public static void makeNewRestaurant() {
		String fileLocation = askMenuFile();
		restaurant = new RestaurantManager(fileLocation);

		menuArray = new String[restaurant.getMenuList().size()];
		menuArray = restaurant.getMenuList().toArray(menuArray);

		priceStr = new String[restaurant.getPriceList().size()];
		priceDb = getDouble(restaurant.getPriceList().toArray(priceStr));
	}

	public static String getChoice() {
		Scanner getChoice = new Scanner(System.in);
		System.out.print("Enter your Choice : ");
		return getChoice.nextLine();
	}

	public static int getMenuChoice() {
		System.out.print("Enter the menu number : ");
		int choice = myScan.nextInt();
		return choice;
	}

	public static int getQuantity() {
		System.out.print("Enter Quantity : ");
		int quantity = myScan.nextInt();
		return quantity;
	}

	public static double getTotal(int[] sumQuantity) {
		double total = 0.0;
		for (int x = 0; x < sumQuantity.length; x++) {
			total += sumQuantity[x] * priceDb[x];
		}
		return total;
	}

	public static void chooseMenu(double total,double[] sumPrice,int[] sumQuantity) {
		int quantity = 0;
		int choice = getMenuChoice();
		if ((choice >= 1) && (choice <= menuArray.length)) {
			quantity = getQuantity();
			System.out.println();
			sumQuantity[choice - 1] += quantity;
			sumPrice[choice - 1] += priceDb[choice - 1] * quantity;
			total = getTotal(sumQuantity);
		}
	}

	public static String date() {
		String dateTime = LocalDateTime.now().toString();
		String date = dateTime.substring(0, 10);
		return date;
	}

	public static String time() {
		String dateTime = LocalDateTime.now().toString();
		String time = dateTime.substring(11, 19);
		return time;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public static void makeReceiptForm(double total,double[] sumPrice,int[] sumQuantity) {
		int y = 1;
		String space = "|\t";
		receipt[0] = "\n__________MENU__________ _____Qty_______ _____PRICE_____\n";
		for (int x = 0; x < sumPrice.length; x++) {
			sumPrice[x] = round(sumPrice[x], 2);
			
			if(menuArray[x].length() <= 6) space = "\t\t\t|\t";
			else if(menuArray[x].length() <= 12) space = "\t\t|\t";
			else if(menuArray[x].length() <= 21) space = "\t|\t";
			
			receipt[x + 1] = "| " + menuArray[x] + space + sumQuantity[x] + "\t|" + "\t" + sumPrice[x]
					+ "\t|\n";
			y++;
		}
		total = round(total, 2);
		receipt[y] = "________________________________________________________\n";
		receipt[y + 1] = "| Total\t\t\t|" + "\t\t\t" + total + "\t|\n";
		receipt[y + 2] = "________________________________________________________\n";
		receipt[y + 3] = "Date >> " + date() + "\nTime >> " + time() + "\n\n";

	}

	public static void printReceipt(int[] sumQuantity) {
		for (int x = 0; x < receipt.length; x++) {
			if ((x >= 1) && (x <= menuArray.length)) {
				if (sumQuantity[x - 1] != 0) {
					System.out.print(receipt[x]);
				}
			} else {
				System.out.print(receipt[x]);
			}
		}
	}

	public static void luckyPro1(double total,double[] sumPrice,int[] sumQuantity) {
		System.out.print("Please enter number 1,2 or 3 : ");
		int num = myScan.nextInt();
		Random rand = new Random();
		int randomNumber = rand.nextInt(3) + 1;
		if (num == randomNumber) {
			System.out.println("CONGRATS!!!!YOU GET 'FREE' MEAL!!!!!..(*O*)b");
			for (int x = 0; x < menuArray.length; x++) {
				sumQuantity[x] = 0;
				sumPrice[x] = 0;
			}
			total = getTotal(sumQuantity);
			makeReceiptForm(total,sumPrice,sumQuantity);
			printReceipt(sumQuantity);
		} else {
			System.out.println("Sorry...You have to pay x2 for this meal...(ToT)");
			total = getTotal(sumQuantity) * 2;
			makeReceiptForm(total,sumPrice,sumQuantity);
			printReceipt(sumQuantity);
		}
	}

	public static void luckyPro2(double total,double[] sumPrice,int[] sumQuantity) {
		System.out.print("Please input your name : ");
		String name = myScan.next();
		int randomDiscount = new Random().nextInt(discountPercent.length);
		int discount = discountPercent[randomDiscount];
		if (discount != 0) {
			System.out.print("Congrats!..You get " + discount + "% discount.(^_^)");
			total = (getTotal(sumQuantity) * (100 - discount)) / 100;
			makeReceiptForm(total,sumPrice,sumQuantity);
			printReceipt(sumQuantity);

		} else {
			System.out.print("Sorry...You get 0% discount.(ToT)");
			makeReceiptForm(total,sumPrice,sumQuantity);
			printReceipt(sumQuantity);
		}
	}

	public static void chooseChoice(String choice,double total,double[] sumPrice,int[] sumQuantity) {
		if (choice.equalsIgnoreCase("O")) {
			chooseMenu(total,sumPrice,sumQuantity);
		} else if (choice.equalsIgnoreCase("T")) {
			makeReceiptForm(total,sumPrice,sumQuantity);
			printReceipt(sumQuantity);
		} else if (choice.equalsIgnoreCase("E")) {
			makeReceiptForm(total,sumPrice,sumQuantity);
			printReceipt(sumQuantity);
		} else if (choice.equalsIgnoreCase("L1")) {
			luckyPro1(total,sumPrice,sumQuantity);
		} else if (choice.equalsIgnoreCase("L2")) {
			luckyPro2(total,sumPrice,sumQuantity);
		}
	}

	public static void makeOrder(String restName,double total,double[] sumPrice,int[] sumQuantity) {
		Scanner getComment = new Scanner(System.in);
		String choice = "";
		String mode = "normal mode";

		sumQuantity = new int[priceDb.length];
		sumPrice = new double[priceDb.length];
		receipt = new String[5 + menuArray.length];

		do {
			choice = getChoice();
			chooseChoice(choice,total,sumPrice,sumQuantity);
			total = getTotal(sumQuantity);
			if ((choice.equalsIgnoreCase("L1")) || (choice.equalsIgnoreCase("L2"))) {
				break;
			}
		} while (!choice.equalsIgnoreCase("E"));

		if (choice.equalsIgnoreCase("L1"))
			mode = "Lucky Promotion 1";
		else if (choice.equalsIgnoreCase("L2"))
			mode = "Lucky Promotion 2";

		System.out.print("Your comments/suggestion : ");
		String comment = getComment.nextLine();
		RestaurantReceipt myReceipt = new RestaurantReceipt(restName, receipt, comment, mode);
		allReceipt.add(myReceipt);
		System.out.println("===== Thank you =====");

	}

	public static void introMenu(String restName) {
		System.out.printf("\n---------Welcome to %s Restaurant---------\n", restName);
		System.out.println("[ O ] - Order food");
		for (int x = 0, y = 1; x < menuArray.length; x++, y++) {
			System.out.printf("\t%d.) %-25s%.0f\t%3s.\n", y, menuArray[x], priceDb[x], current);
		}
		System.out.print("[ T ] - Check your total price\n[ E ] - Exit (Stop choosing any choice in this bill)\n");
		System.out.println("--------- ::Lucky Promotion:: ---------");
		System.out.println("**If you choose 'Lucky Promotion',you can't order more food or drink**");
		System.out.println("[ L1 ] - Free or x2");
		System.out.println("[ L2 ] - Get discount!! >> 0% to 50% <<\n");
	}

	public static void saveAllReceiptInText() {
		OutputStream out = null;
		try {
			out = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't open output file " + outputFile);
		}
		printAllReceipt(out);
	}

	public static void printAllReceipt(OutputStream out) {
		PrintStream pout = new PrintStream(out);
		for (int x = 0; x < allReceipt.size(); x++) {
			Object myReceiptOb = allReceipt.get(x);
			String myRestName = ((RestaurantReceipt) myReceiptOb).getRestaurantName();
			String myMode = ((RestaurantReceipt) myReceiptOb).getMode();
			String[] myReceipt = ((RestaurantReceipt) myReceiptOb).getReciept();
			String myComment = ((RestaurantReceipt) myReceiptOb).getComment();

			pout.println(myRestName + " Restaurant");
			pout.println("Mode : " + myMode);
			for (int y = 0; y < myReceipt.length; y++) {
				pout.print(myReceipt[y]);
			}
			pout.println("Comment/Suggestion : " + myComment);
			pout.println("=======================================================");
		}
	}

	public static void checkInput(String choiceManager) {
		Scanner getChoiceMan = new Scanner(System.in);
		while (menuArray.length == 0) {
			System.out.println("This file doesn't have any menu please press [N] to create your restaurant again!!");
			System.out.print("[N]ew restaurant or [E]xit : ");
			choiceManager = getChoiceMan.nextLine();
			System.out.println();
			if (choiceManager.equalsIgnoreCase("N")) {
				makeNewRestaurant();
			} else if (choiceManager.equalsIgnoreCase("E"))
				break;
		}
	}

	public static void editMenu() {
		Scanner getMenuName = new Scanner(System.in);
		ArrayList<String> newMenu = restaurant.getMenuList();
		ArrayList<String> newPrice = restaurant.getPriceList();

		System.out.print("[A]dd menu or [D]elete menu : ");
		String editChoice = myScan.next();
		if (editChoice.equalsIgnoreCase("A")) {
			System.out.print("Menu name : ");
			String newMenuName = getMenuName.nextLine();
			if (!newMenu.contains(newMenuName)) {
				newMenu.add(newMenuName);
				System.out.print("Menu Price : ");
				String newMenuPrice = myScan.next();
				newPrice.add(newMenuPrice);
			} else if (newMenu.contains(newMenuName)) {
				System.out.print("You already have this menu!!\n");
			}
		} else if (editChoice.equalsIgnoreCase("D")) {
			for (int x = 0, y = 1; x < menuArray.length; x++, y++) {
				System.out.printf("\t%d.) %-25s%.0f\t%3s.\n", y, menuArray[x], priceDb[x], current);
			}
			System.out.print("Choose the number of menu to delete menu : ");
			int menuChoice = myScan.nextInt();
			newMenu.remove(menuChoice - 1);
			newPrice.remove(menuChoice - 1);
		}
		restaurant.setMenuList(newMenu);
		menuArray = new String[restaurant.getMenuList().size()];
		menuArray = restaurant.getMenuList().toArray(menuArray);

		restaurant.setPriceList(newPrice);
		priceStr = new String[restaurant.getPriceList().size()];
		priceDb = getDouble(restaurant.getPriceList().toArray(priceStr));
	}

	public static void main(String[] args) {
		Scanner getCha = new Scanner(System.in);
		String cha = "" , choiceManager = "";
		double total = 0.0;
		int[] sumQuantity = null;
		double[] sumPrice = null;
		allReceipt = new ArrayList<Object>();
		do {
			makeNewRestaurant();
			if (menuArray.length == 0) {
				checkInput(choiceManager);
			}
			if (choiceManager.equalsIgnoreCase("E"))
				break;

			String restName = askRestaurantName();
			System.out.print("Please input current: ");
			current = myScan.next();

			do {
				introMenu(restName);
				makeOrder(restName,total,sumPrice,sumQuantity);
				do {
					System.out.print("\n[M]ake new order [N]ew restaurant [E]dit menu or [Q]uit : ");
					cha = getCha.nextLine();
					System.out.println();
					if (cha.equalsIgnoreCase("E")) {
						editMenu();
					} else if (cha.equalsIgnoreCase("Q")) {
						saveAllReceiptInText();
						break;
					}
				} while (cha.equalsIgnoreCase("E"));
			} while (cha.equalsIgnoreCase("M"));
		} while (cha.equalsIgnoreCase("N"));

	}

}
