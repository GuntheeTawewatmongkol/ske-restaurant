package code;

import java.util.Scanner;

public class Robot {
	public static void turnPrint(String direc){
		int length = direc.length();
		char cha;
		char cha2;
		int n=0,s=0,e=0,w=0;
		
		for(int y = 0;y<=length-1;){
			int sumN = 0,sumS = 0,sumE = 0,sumW = 0;
			do{
				cha = direc.charAt(y);
				cha2 = 0;
				
				if(y!=length-1){
					cha2 = direc.charAt(y+1);
				}
					if(cha == 'n'){
					sumN = sumN+1;
					}
					if(cha == 's'){
					sumS = sumS+1;
					}
					if(cha == 'e'){
					sumE = sumE+1;
					}
					if(cha == 'w'){
					sumW = sumW+1;
					}
				
				if(y==length){
					break;
				}
			}while(cha == cha2);
			
			if(sumN!=0){
				System.out.printf("Move to North %d unit(s).\n",sumN);
				n = n+sumN;
			}
			if(sumS!=0){
				System.out.printf("Move to South %d unit(s).\n",sumS);
				s = s+sumS;
			}
			if(sumE!=0){
				System.out.printf("Move to East %d unit(s).\n",sumE);
				e = e+sumE;
			}
			if(sumW!=0){
				System.out.printf("Move to West %d unit(s).\n",sumW);
				w = w+sumW;
			}
		}
		System.out.printf("Final robot location: x = %d, y = %d.",(e-w),(n-s));
		}
	public static void main(String[]args){
		Scanner myScan = new Scanner(System.in);
		System.out.print("Input String command for robot: ");
		String direcOld = myScan.nextLine();
		String direc = direcOld.toLowerCase();
		System.out.println("Robot will ");
		
		turnPrint(direc);
	}
}