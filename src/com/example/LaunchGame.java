package com.example;

import java.util.Random;
import java.util.Scanner;

class TicTacToe{
	static char[][] board;

	public TicTacToe() {
		board=new char[3][3];
		initBoard();
	}

	void initBoard() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				board[i][j]=' ';
			}
		}
	}

	static void displayBoard() {
		System.out.println("-------------");
		for(int i=0;i<3;i++) {
			System.out.print("| ");
			for(int j=0;j<3;j++) {
				System.out.print(board[i][j]+" | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	static void placeMark(int row,int col,char mark) {
		if(row>=0 && row<=2 && col>=0 && col<=2) {
			board[row][col] = mark;
		}
		else {
			System.out.println("Invalid Position");
		}
	}

	static boolean checkColWin() {
		for(int i=0;i<3;i++) {
			if(board[0][i]!=' ' && board[0][i]==board[1][i] && board[1][i]==board[2][i]) {
				return true;
			}
		}
		return false;
	}

	static boolean checkRowWin() {
		for(int i=0;i<3;i++) {
			if(board[i][0]!=' '&& board[i][0]==board[i][1] && board[i][1]==board[i][2]) {
				return true;
			}
		}
		return false;
	}

	static boolean checkDiaWin() {
		if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2] || board[0][2]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0] ) {
			return true;
		}
		return false;
	}
	
	static boolean checkDraw() {
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(board[i][j]==' ')
				return false;
			}
		}
		return true;
	}
}



abstract class Player{
	String name;
	char mark;
	
	abstract void makeMove();
	
	boolean isValidMove(int row,int col) {
		if(row>=0 && row<3 && col>=0 && col<3) {
			if(TicTacToe.board[row][col]==' ') {
				return true;
			}

		}
		return false;
	}
}

class HumanPlayer extends Player{
	

	HumanPlayer(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	@Override
	void makeMove() {
		Scanner sc=new Scanner(System.in);
		int row,col;
		do {
			System.out.println("Enter the Row and Column");
			row=sc.nextInt();
			col=sc.nextInt();
		}while(!isValidMove(row,col));

		TicTacToe.placeMark(row,col,mark);				
	}

	
}

class AIPlayer extends Player{
	

	AIPlayer(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	@Override
	void makeMove() {
		Scanner sc=new Scanner(System.in);
		int row,col;
		do {
			Random r=new Random();
			row=r.nextInt(3);
			col=r.nextInt(3);
		}while(!isValidMove(row,col));

		TicTacToe.placeMark(row,col,mark);				
	}

	
}


public class LaunchGame {
	public static void main(String[]args) {
		Scanner sc=new Scanner(System.in);
		TicTacToe t=new TicTacToe();
		System.out.println("Choose the Gaming mode");
		System.out.println("1 for Two Player mode or 2 for AI bot mode");
		System.out.println("Enter the number: ");
		int n=sc.nextInt();
		sc.nextLine();
		
		TicTacToe.displayBoard();
		
		if(n==2) {
		
		System.out.println("Enter the first player name: ");
		String player1_Name=sc.nextLine();
		
		
		System.out.println("Enter the first player Symbol: ");
		char player1_Symbol=sc.next().charAt(0);
		
		char ai_symbol;
		if(player1_Symbol=='X') {
			ai_symbol='O';
		}
		else {
			ai_symbol='X';
		}
		
		HumanPlayer p1 =new HumanPlayer(player1_Name,player1_Symbol);
		AIPlayer p2 =new AIPlayer("Gpt",ai_symbol);

		Player cp;
		cp=p1;

		while(true) {
			System.out.println(cp.name + " turn");
			cp.makeMove();
			TicTacToe.displayBoard();

			if(TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiaWin()) {
				System.out.println(cp.name +" has Won");
				break;
			}
			else if(TicTacToe.checkDraw()) {
				System.out.println("Game is Draw");
			}
			else {
				if(cp==p1) {
					cp=p2 ;
				}
				else {
					cp=p1;
				}
			}
		}
		}
		else if(n==1) {
			
			System.out.println("Enter the first player name: ");
			String player1_Name=sc.nextLine();
			
			System.out.println("Enter the first player Symbol: ");
			char player1_Symbol=sc.next().charAt(0);
			
			sc.nextLine();
			System.out.println("Enter the second player name: ");
			String player2_Name=sc.nextLine();
			
			System.out.println("Enter the second player Symbol: ");
			char player2_Symbol=sc.next().charAt(0);
			
			
			HumanPlayer p1 =new HumanPlayer(player1_Name,player1_Symbol);
			HumanPlayer p2 =new HumanPlayer(player2_Name,player2_Symbol);

			Player cp;
			cp=p1;

			while(true) {
				System.out.println(cp.name + " turn");
				cp.makeMove();
				TicTacToe.displayBoard();

				if(TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiaWin()) {
					System.out.println(cp.name +" has Won");
					break;
				}
				else if(TicTacToe.checkDraw()) {
					System.out.println("Game is Draw");
				}
				else {
					if(cp==p1) {
						cp=p2 ;
					}
					else {
						cp=p1;
					}
				}
			}
		}

	}
}



























