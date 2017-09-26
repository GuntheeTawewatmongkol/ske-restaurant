package code;

import java.util.Random;
import java.util.Scanner;

public class Restaurant {
	static int choice = 0,qtt = 0,price = 0,sumQ = 0,sum = 0;
	static int sum1,sum2,sum3,sumQ1,sumQ2,sumQ3,price1,price2,price3;
	
	public static int choiceChoose(int choicex,int qttx,int p){
			price = qttx*p;
			return price;
	}
	public static int priceEachMenu(int choicex,int pricex,int sumx){
		sumx = sumx + pricex;
		return sumx;
	}
	public static int qttPlus(int choicex,int qttx,int sumQx){
			sumQx = sumQx + qttx;
			return sumQx;
	}
	public static void menuChoose(int choicex,int sumx1,int sumx2,int sumx3,int sumxQ1,int sumxQ2,int sumxQ3){
		System.out.println("\n______MENU______ _____Qty_______ _____PRICE_____");
		if(sumx1>=0){
			System.out.println("| Pizza\t\t|       "+sumxQ1+"\t|"+"       "+sumx1+"  \t|");
		}
		if(sumx2>=0){
			System.out.println("| Chickens\t|       "+sumxQ2+"\t|"+"       "+sumx2+"  \t|");
		}
		if(sumx3>=0){
			System.out.println("| Coke\t\t|       "+sumxQ3+"\t|"+"       "+sumx3+"  \t|");
		}
		System.out.println    ("________________________________________________");
		System.out.println  ("| Total\t\t\t|"+"\t\t"+(sumx1+sumx2+sumx3)+"\t|");
		System.out.println    ("________________________________________________\n");
	}
	public static void luckyPro6(int num){
		Random rand = new Random();
		int ran1 = rand.nextInt(3)+1;
		if(num==ran1){
			System.out.println("CONGRATS!!!!YOU GET 'FREE' MEAL!!!!!..(*O*)b");
			menuChoose(0,0,0,0,0,0,0);
		}
		else{
			System.out.println("Sorry...You have to pay x2 for this meal...(ToT)");
			menuChoose(choice,sum1*2,sum2*2,sum3*2,sumQ1,sumQ2,sumQ3);
		}
	}
	public static void luckyPro7(){
		Random rand = new Random();
		int ran1 = rand.nextInt(5)+0;
		int ran2 = rand.nextInt(2)+1;
		int r = ran2*5;
		int disc = r*ran1;
		if(disc!=0){
			System.out.print("Congrats!..You get "+disc+"% discount.(^_^)");
			menuChoose(choice,(sum1*(100-disc))/100,(sum2*(100-disc))/100,(sum3*(100-disc))/100,sumQ1,sumQ2,sumQ3);
		}
		else{
			System.out.print("Sorry...You get 0% discount.(ToT)");
			menuChoose(choice,sum1,sum2,sum3,sumQ1,sumQ2,sumQ3);
		}
	}
	public static void main(String[] args){
		Scanner myScan = new Scanner(System.in);
		System.out.print("---------Welcome to SKE Restaurant---------\n");
		System.out.println("1.) Pizza\t250  Baht.");
		System.out.println("2.) Chickens\t120  Baht.");
		System.out.println("3.) Coke\t45   Baht.");
		System.out.println("4.) Total");
		System.out.println("5.) Exit\n");
		System.out.println("--------- ::Lucky Promotion:: ---------");
		System.out.println("**If you choose 'Lucky Promotion',you can't order more food or drink**");
		System.out.println("6.) Free or x2");
		System.out.println("7.) Get discount!! >> 0% to 50% <<\n");
		
		while(choice!=5){
			System.out.print("Enter your Choice : ");
			choice = myScan.nextInt();
		
			if(choice==4){
				menuChoose(choice,sum1,sum2,sum3,sumQ1,sumQ2,sumQ3);
				}
			else if(choice==6){
				System.out.print("Please enter number 1,2 or 3 : ");
				int luckNum1 = myScan.nextInt();
				luckyPro6(luckNum1);
				break;
			}
			else if(choice==7){
				System.out.println("...Your Total before discount...");
				menuChoose(choice,sum1,sum2,sum3,sumQ1,sumQ2,sumQ3);
				System.out.print("Please input your name : ");
				String name = myScan.next();
				luckyPro7();
				break;
			}
			else if(choice==5){
				break;
			}
			else{
			System.out.print("Enter Quantity : ");
			qtt = myScan.nextInt();
			System.out.println();
			
			if(choice==1){
				price1 = choiceChoose(choice,qtt,250);
				sum1 = priceEachMenu(choice,price1,sum1);
				sumQ1 = qttPlus(choice,qtt,sumQ1);
			}
			if(choice==2){
				price2 = choiceChoose(choice,qtt,120);
				sum2 = priceEachMenu(choice,price2,sum2);
				sumQ2 = qttPlus(choice,qtt,sumQ2);
			}
			if(choice==3){
				price3 = choiceChoose(choice,qtt,45);
				sum3 = priceEachMenu(choice,price3,sum3);
				sumQ3 = qttPlus(choice,qtt,sumQ3);
			}
			}
		}
		System.out.print("Your comments/suggestion : ");
		String comment = myScan.next();
		System.out.println("===== Thank you =====");
	}
}
