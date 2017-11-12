package code;

public class RestaurantReceipt {
	private String[] receipt;
	private String comment;
	private String restaurantName;
	private String mode;
	
	public RestaurantReceipt(String restaurantName,String[] receipt,String comment,String mode) {
		this.restaurantName = restaurantName;
		this.receipt = receipt;
		this.comment = comment;
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public String[] getReciept() {
		return receipt;
	}

	public String getComment() {
		return comment;
	}
}
