import java.util.Arrays;

public class MergeSort {

	public MergeSort(int[] array) {
		int []aux = new int[array.length];
		
		separar(array, aux, 0, array.length-1);
		System.out.println("Array Ordenado: " + Arrays.toString(array));
	}

	private static void separar(int[] array, int[] aux, int inicio, int fim) {
		if(inicio < fim) {
			int meio = (inicio + fim)/2;
			separar(array, aux, inicio, meio);
			separar(array, aux, meio+1, fim);
			intercalar(array, aux, inicio, meio, fim);
		}
	}

	private static void intercalar(int[] array, int[] aux, int inicio, int meio, int fim) {
		for(int k = inicio; k <= fim; k++)
			aux[k] = array[k];
		
		int i = inicio;
		int j = meio + 1;
		
		for(int k = inicio; k <= fim; k++) {
			if(i > meio) 
				array[k] = aux[j++];
			else if(j > fim) 
				array[k] = aux[i++];
			else if(aux[i] < aux[j]) 
				array[k] = aux[i++];
			else 
				array[k] = aux[j++];
		}
	}

}
