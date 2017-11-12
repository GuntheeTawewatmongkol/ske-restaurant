package code;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantManager {
	
	private ArrayList<String> menuList = new ArrayList<String>();
	private ArrayList<String> priceList = new ArrayList<String>();
	private ArrayList<Object> allReceipt = new ArrayList<Object>();

	static ClassLoader loader = RestaurantManager.class.getClassLoader();
	static Scanner sc = new Scanner(System.in);
	
	public RestaurantManager (String filename,ArrayList<Object> allReceipt) {
		loadFile(filename);
		this.allReceipt = allReceipt;
	}

	public ArrayList<Object> getAllReceipt() {
		return allReceipt;
	}

	public void setAllReceipt(ArrayList<Object> allReceipt) {
		this.allReceipt = allReceipt;
	}

	public ArrayList<String> getMenuList() {
		return menuList;
	}

	public ArrayList<String> getPriceList() {
		return priceList;
	}
	
	public String menuName(String menu) {
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

	public String menuPrice(String menu) {
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
	
	public void addMenuAndPrice(String line) {
		String menu = line.trim();
		String newMenu = menuName(menu);
		if (newMenu != null) {
			this.menuList.add(newMenu);
			String price = menuPrice(menu);
			if (price != null) {
				this.priceList.add(price);
			}
		}
	}
	
	public void loadFile(String filename) {
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
				addMenuAndPrice(line);
			}
		}
		reader.close();
	}
}
