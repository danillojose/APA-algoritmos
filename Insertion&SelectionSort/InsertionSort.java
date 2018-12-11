package src;

public class InsertionSort {
	
	public InsertionSort(int[] array){
		System.out.println("\n\nAlgoritmo Insertion Sort");
		System.out.println("------------------------------");
		int[] arr = array.clone();
		
		System.out.println("Array anterior: ");
		for(int i = 0; i < arr.length; i++) 
			System.out.print(arr[i] + "\t");
		
		/**----------------CODE-----------------**/
		for(int i = 1; i < arr.length; i++) {
			int aux = arr[i], j=i;
			while((j>0) && (aux < arr[j-1])) {
				arr[j] = arr[j-1];
				arr[j-1] = aux;
				j--;
			}
		}
		/**-------------------------------------**/
		
		System.out.println("\nArray ordenado: ");
		for(int i = 0; i < arr.length; i++) 
			System.out.print(arr[i] + "\t");
	}
}
