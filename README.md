# ske-restaurant 
This is repository for the ske-restaurant project.
@author Gunthee Tawewatmongkol


##-SKE RESTAURANT PROGRAM MANUAL-


###For Restaurant Manager

1.) You have to add your menu in data folder by create .txt file.
    In data folder has the example menu.txt and way to add your menu.
    You can change the menu in the example file or create your new menu text file. 

2.) When you open the program, program’ll create the new restaurant and ask you to input    
    the name of your menu file.

    You have to input only the name of menu file.
    For example, your menu file is “menu.txt”, you just input “menu”.

    If program doesn’t find your menu file or can’t read the menu file, it’ll print
    “Could not access file data/menu.txt” and ask you to create your restaurant again.

    If program find your menu file, it’ll ask the name of your restaurant and ask you to
    Input the current.(Baht, Dollar etc.) After that, It’ll print all of the function
    That you can choose.

Example
    	---------Welcome to Jenny Restaurant---------
	[ O ] - Order food
		1.) Pizza          250	Baht.
		2.) Somtam         50	Baht.
		3.) Chicken        150	Baht.
		4.) Banana Cake    200	Baht.
	[ T ] - Check your total price
	[ E ] - Exit (Stop choosing any choice in this bill)
	--------- ::Lucky Promotion:: ---------
	**If you choose 'Lucky Promotion',you can't order more food or drink**
	[ L1 ] - Free or x2
	[ L2 ] - Get discount!! >> 0% to 50% <<  

3.) When customer already choose E to stop their order, Program’ll ask you to make new 
    order, create the new restaurant, edit the menu ,or quit.
    
    If you choose “edit the menu”,the program’ll ask “add menu or delete menu”.
    Add menu - You have to input the name and price of menu.if it already has that menu
    it’ll not add the menu again.
    Delete menu - You just choose the menu number and it’ll delete that menu.
    After that, the program’ll ask you to make new order, create new restaurant, edit
    the menu, or quit again.

    **The capital Letter is different to the small Letter.

    If you quit the program, it’ll create the output file "AllReceipt.txt" in 
    ske - restaurant/src/data folder. This is the file that save all of the receipts and comments
    of your restaurant.
    It shows 1.the name of restaurant 2.mode in that receipt(normal, lucky promotion1/2)
    3.receipt 4.date and time 5.comment from customer.

Example
	Jenny Restaurant
	Mode : normal mode

	______MENU______ _____Qty_______ _____PRICE_____
	| Pizza     	|       1	|       250.0  	|
	| Somtam     	|       1	|       50.0  	|
	| Chicken     	|       1	|       150.0  	|
	| Cake     	|       1	|       200.0  	|
	________________________________________________
	| Total			|		650.0	|
	________________________________________________
	Date >> 2017-11-12
	Time >> 12:06:17

	Comment : -
	=======================================================
	Jenny Restaurant
	Mode : Lucky Promotion 1

	______MENU______ _____Qty_______ _____PRICE_____
	| Pizza     	|       0	|       0.0  	|
	| Somtam     	|       2	|       100.0  	|
	| Chicken     	|       0	|       0.0  	|
	| Cake     	|       3	|       600.0  	|
	________________________________________________
	| Total			|		1400.0	|
	________________________________________________
	Date >> 2017-11-12
	Time >> 12:06:35

	Comment : so sad
	=======================================================
	Jenny Restaurant
	Mode : Lucky Promotion 2

	______MENU______ _____Qty_______ _____PRICE_____
	| Pizza     	|       0	|       0.0  	|
	| Somtam     	|       0	|       0.0  	|
	| Chicken     	|       0	|       0.0  	|
	| Cake     	|       3	|       600.0  	|
	________________________________________________
	| Total			|		450.0	|
	________________________________________________
	Date >> 2017-11-12
	Time >> 12:06:50

	Comment : good meal
	=======================================================



###For Customer

1.) You have to choose the function that you want to choose.
    The program’ll ask you “Enter your Choice : ”

Choice

    O - to order the food
    The program’ll ask you to enter the menu number and ask the quantity of that food.

    T - to check your total price
    The program’ll print the last receipt.You can order more food, use the promotion, or     
    exit.

    E - to stop choosing any choice

    L1 - It is the lucky promotion 1.You have to guess one of the 3 numbers to get the 
    “free meal”.But if you have bad luck, you’ll have to pay double for this meal.

    L2 - It is the lucky promotion 2.You just input your name and wait for the result.
    You’ll get 0 to 50% discount.


