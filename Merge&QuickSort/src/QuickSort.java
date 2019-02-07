import java.util.Arrays;

public class QuickSort {

	public QuickSort(int[] array) {
		quickSort(array, 0, array.length-1);
		System.out.println("Array Ordenado: " + Arrays.toString(array));
	}

	private static void quickSort(int[] array, int l, int r) {
		if(l < r) {		//ate que o indice da esquerda nao cruze o da direita
			int j = separar(array, l, r);
			quickSort(array, l, j-1);
			quickSort(array, j+1, r);
		}
	}

	private static int separar(int[] array, int l, int r) {
		int i = l+1;
		int j = r;
		int pivo = array[l];
		//System.out.println(Arrays.toString(array));
		while(i <= j) {
			if(array[i] <= pivo) i++;		//avanca o i ate achar um elemento maior que o pivo
			else if(array[j] > pivo) j--;	//recua o j ate achar um elemento menor que o pivo
			else{
				trocar(array, i, j);
				i++;
				j--;
			}
		}
		trocar(array, l, j); //colocando o pivo na posicao certa
		return j;
	}

	private static void trocar(int[] array, int i, int j) {
		//System.out.println(Arrays.toString(array));
		int aux = array[i];
		array[i] = array[j];
		array[j] = aux;
		//System.out.println(Arrays.toString(array)+"\n");
	}

}
