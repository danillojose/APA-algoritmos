package src;

import java.util.Scanner;

public class Principal {
	
	private static Scanner sc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sc = new Scanner(System.in);
		
		System.out.println("Defina o tamanho do array:");
		Common.size = sc.nextInt();
		Common.array = new int[Common.size];
		
		System.out.println("\nInsira os numeros em cada posicao do array:");
		for(int i=0; i < Common.size; i++) {
			System.out.println("["+ (i+1) +"]: ");
			Common.array[i] = sc.nextInt();
		}
		
		/**IMPLEMENTACOES**/
		new SelectionSort(Common.array);
		new InsertionSort(Common.array);
		
	}

}
