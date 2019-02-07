package src;

public class SelectionSort {

	public SelectionSort(int[] array){
		System.out.println("\n\nAlgoritmo Selection Sort");
		System.out.println("------------------------------");
		int[] arr = array.clone();
		
		System.out.println("Array anterior: ");
		for(int i = 0; i < arr.length; i++) 
			System.out.print(arr[i] + "\t");
		
		/**----------------CODE-----------------**/
		for(int i = 0; i < arr.length-1; i++) {		//Na ultima iteracao todo o array ja se encontra ordenado
			int index = i;
			for(int j = index+1; j < arr.length; j++) {
				if(arr[j] < arr[index])
					index = j;
			}
			int aux = arr[i];
			arr[i] = arr[index];
			arr[index] = aux;
		}
		/**-------------------------------------**/
		
		System.out.println("\nArray ordenado: ");
		for(int i = 0; i < arr.length; i++) 
			System.out.print(arr[i] + "\t");
	}
}
