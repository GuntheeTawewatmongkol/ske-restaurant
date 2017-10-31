package code;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantManager {

	public static ArrayList<String> menuList = new ArrayList<String>();
	public static ArrayList<String> priceList = new ArrayList<String>();
	static String filename = "data/menu.txt";
	static ClassLoader loader = RestaurantManager.class.getClassLoader();

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

	public static String menuPrice(String menu) {
		for (int x = 0; x < menu.length(); x++) {
			char cha = menu.charAt(x);
			if (cha == ';') {
				if (cha == menu.charAt(menu.length() - 1)) {
					menuList.remove(menuList.size() - 1);
					break;
				} else {
					String price = menu.substring(x + 1, menu.length());
					return price;
				}
			}
		}
		return null;
	}

	public static void checkList() {
		for (int y = 0; y < menuList.size(); y++) {
			System.out.print(menuList.get(y) + ", ");
		}
		System.out.println();
		for (int y = 0; y < priceList.size(); y++) {
			System.out.print(priceList.get(y) + ", ");
		}
	}

	public static void main() {
		
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
					String price = menuPrice(menu);
					if (price != null) {
						priceList.add(price);
					}
				}
			}
		}
		reader.close();
//		checkList();
	}
}
