package code;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantManager {

	static ArrayList<String> menuList = new ArrayList<String>();
	static ArrayList<Integer> priceList = new ArrayList<Integer>();

	public static String menuName(String menu) {
		for (int x = 0; x < menu.length(); x++) {
			char cha = menu.charAt(x);
			if (menu.charAt(0) == ';') {
				break;
			} else if (cha == ';') {
				String newMenu = menu.substring(0, x);
				return newMenu;
			}
		}
		return null;
	}

	public static int menuPrice(String menu) {
		for (int x = 0; x < menu.length(); x++) {
			char cha = menu.charAt(x);
			if (cha == ';') {
				if (cha == menu.charAt(menu.length() - 1)) {
					menuList.remove(menuList.size() - 1);
					break;
				} else {
					String price = menu.substring(x + 1, menu.length());
					int newPrice = Integer.parseInt(price);
					return newPrice;
				}
			}
		}
		return 0;
	}

	public static void check() {
		for (int y = 0; y < menuList.size(); y++) {
			System.out.print(menuList.get(y) + ", ");
		}
		System.out.println();
		for (int y = 0; y < priceList.size(); y++) {
			System.out.print(priceList.get(y) + ", ");
		}
	}

	public static void main(String[] args) {
		String filename = "data/menu.txt";
		ClassLoader loader = RestaurantManager.class.getClassLoader();
		InputStream in = loader.getResourceAsStream(filename);

		if (in == null) {
			System.out.println("Could not access file " + filename);
			return;
		}
		Scanner reader = new Scanner(in);

		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			if (line.startsWith("//")) {
				continue;
			} else {
				String menu = line.trim();
				String newMenu = menuName(menu);
				if (newMenu != null) {
					menuList.add(newMenu);
					int price = menuPrice(menu);
					if (price != 0) {
						priceList.add(price);
					}
				}
			}
		}
		reader.close();
//		check();
	}
}
