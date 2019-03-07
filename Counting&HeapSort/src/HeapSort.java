public class HeapSort {

	public HeapSort(int[] array) {
		// TODO Auto-generated constructor stub
		int comprimento = array.length;
		int tamanho_heap = array.length;
		
		BuildMaxHeap(array, tamanho_heap, comprimento);				//permite construir o max heap
		for(int i = comprimento-1; i >= 0; i--) {
			swap(array, i, 0);
			tamanho_heap = tamanho_heap - 1;
			MaxHeapify(array, tamanho_heap, comprimento, 0);		//ja nao precisamos executar o build pois os elementos ja estao obedecendo a prop  
		}
		
		System.out.print("Array ordenado: ");
		for(int i = 0; i < comprimento; i++)
			System.out.print(array[i] + " ");
		System.out.println();
	}

	private void BuildMaxHeap(int[] array, int tamanho_heap, int comprimento) {
		// TODO Auto-generated method stub
		for(int i = ((comprimento/2)-1); i >= 0; i--)				//(comp/2-1) nos leva ao ultimo indice com filhos
			MaxHeapify(array, tamanho_heap, comprimento, i);		//faz o swap com os termos necessarios
	}

	private void MaxHeapify(int[] array, int tamanho_heap, int comprimento, int i) {
		// TODO Auto-generated method stub
		Integer l = filhoesq(array, i);
		Integer r = filhodir(array, i);
		Integer maior = i;
			
		if(l!=null && l<tamanho_heap && array[l]>array[maior])		//i deve possuir um vizinho esquerdo, ser menor que o tamanho do heap e maior que o maior indice armazenado
			maior = l;
		if(r!=null && r<tamanho_heap && array[r]>array[maior])		//i deve possuir um vizinho direito, ser menor que o tamanho do heap e maior que o maior indice armazenado
			maior = r;
		if(maior != i) {
			swap(array, maior, i);									//swap do maior com o indice atual
			MaxHeapify(array, tamanho_heap, comprimento, maior);	//recursivamente faz isso com o maior ate chegar ao no folha
		}
	}

	private void swap(int[] array, int i, int j) {					//swap feito a partir dos INDICES i e j do array
		// TODO Auto-generated method stub
		int aux = array[i];
		array[i] = array[j];
		array[j] = aux;
	}

	private static Integer filhoesq(int[] array, int pai) {			//retorna o indice do filho esquerdo
		// TODO Auto-generated method stub
		if(((2*pai)+1) <= array.length-1)
			return 2*pai+1;
		else
			return null;
	}
	
	private static Integer filhodir(int[] array, int pai) {			//retorna o indice do filho direito
		// TODO Auto-generated method stub
		if(((2*pai)+2) <= array.length-1)
			return 2*pai+2;
		else
			return null;
	}

}
