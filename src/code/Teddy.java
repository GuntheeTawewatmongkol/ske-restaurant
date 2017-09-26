package code;

import java.util.Scanner;

public class Teddy {
	public static int getTeddyFloor(String TeddyRoom){
		int teddyFloor = 0;
		switch(TeddyRoom.toLowerCase()){
		case "lobby" : teddyFloor = 1;
		break;
		case "emergency room" : teddyFloor = 2;
		break;
		case "icu" : teddyFloor = 3;
		break;
		case "pharmacy" : teddyFloor = 4;
		break;
		case "ward" : teddyFloor = 5;
		break;
		case "office" : teddyFloor = 6;
		break;
		}
		return teddyFloor;
	}
	
	public static int getNextFloor(String floorList, int i){
		int currentFloor = floorList.charAt(i);
		return (int)currentFloor-48;
	}
	
	public static boolean isTeddyHere (int teddyFloor, int currentFloor){
		if(currentFloor==teddyFloor){
			return true;
		}
		return false;
	}
	
	public static boolean isFloorValid(int currentFloor){
		if((currentFloor>6)||(currentFloor<1)){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args){
		Scanner myScan = new Scanner(System.in);
		System.out.print("Please input room where Teddy is: ");
		String TeddyRoom = myScan.nextLine();
		System.out.print("Please input list of searching floors: ");
		String floorList = myScan.nextLine();
		
		int length = floorList.length();
		int teddyFloor = getTeddyFloor(TeddyRoom);
		int currentFloor = getNextFloor(floorList,(length-1));
		
		for(int x = 0;x<length;x++){
			teddyFloor = getTeddyFloor(TeddyRoom);
			currentFloor = getNextFloor(floorList,x);
			if(isTeddyHere (teddyFloor,currentFloor)){
				System.out.printf("Congrats! Teddy is found on floor %d.",currentFloor);
				break;
			}
			else if(isFloorValid(currentFloor)){
				System.out.printf("Invalid floor %d!\n",currentFloor);
			}
			else if(!isTeddyHere (teddyFloor,currentFloor)){
				System.out.printf("Currently, you are on floor %d. Teddy is not here.\n",currentFloor);
			}
		}
		if(!isTeddyHere (teddyFloor,currentFloor)){
		System.out.printf("Teddy is on floor %d. Sorry, you miss him.",teddyFloor);
		}
	}
}
