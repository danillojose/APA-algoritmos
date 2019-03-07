import java.util.Scanner;

public class Principal {

	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		System.out.println("Defina o tamanho do array:");
		Common.size = sc.nextInt();
		Common.array = new int[Common.size];
		
		System.out.println("\nInsira os numeros em cada posicao do array:");
		for(int i=0; i < Common.size; i++) {
			System.out.println("["+ (i+1) +"]: ");
			Common.array[i] = sc.nextInt();
		}
		
		int aux = 0;
		do{
			System.out.println("Selecione o Algoritmo\n\n1-CountingSort\n2-HeapSort\n\n");
			aux = sc.nextInt();
			if(aux == 1)
				new CountingSort(Common.array);
			else if(aux == 2)
				new HeapSort(Common.array);
			else
				System.out.println("Entrada invalida.");
		}while ((aux != 1) && (aux != 2));
	}

}
