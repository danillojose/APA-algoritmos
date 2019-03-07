import java.util.Arrays;

public class CountingSort {

	public CountingSort(int[] array) {
		int[] ad = array;							//array desordenado
		int[] ao = new int[ad.length];				//array ordenado
		int max = 0, min = 0;
		
		System.out.println("Array desordenado: ");
		for(int i = 0; i < ad.length; i++) {		//descobrindo qual o maior valor do array
			if(ad[i] > max)
				max = ad[i];
			System.out.print(ad[i]+" ");
		}
		min = max;
		for(int i = 0; i < ad.length; i++) {		//descobrindo qual o menor valor do array (criacao do outro for para nao haver necessidade de atribuir ao min a variavel MAX_VALUE)
			if(ad[i] < min) {
				min = ad[i];
			}
		}
		System.out.println();
		if(min < 0) {								//so executa se houver algum numero negativo (evita-se o desperdicio de memoria)
			for(int i = 0; i < ad.length; i++) {	//somando o menor valor a todos as posicoes do array
				ad[i] = ad[i] - min;
				System.out.print(ad[i]+" ");
			}
			System.out.println();
			for(int i = 0; i < ad.length; i++) {	//redescobrindo o maior valor do array
				if(ad[i] > max)
					max = ad[i];
			}
		}
		
		System.out.println("\nNumero max do array: " + max);
		System.out.println("Numero min do array: " + min + "\n");
		int[] ac = new int[max+1];					//array de contagem
		for(int i = 0; i < ad.length; i++) {		//atribuindo valores de contagem
			ac[ad[i]] = ac[ad[i]]+1;
		}
		for(int i = 1; i < ac.length; i++) {		//soma de cada elemento com o anterior
			ac[i] += ac[i-1];
		}
		System.out.println("Array de contagem: ");
		for(int i = 0; i < ac.length; i++)			//imprimindo ac
			System.out.print(ac[i]+" ");
		System.out.println("\n");
		
		for(int i = 0; i < ad.length; i++) {		//atribuindo valores de ao apartir de ad e ac
			if(ac[ad[i]] > 0) {
				ao[ac[ad[i]]-1] = ad[i];
				ac[ad[i]] = ac[ad[i]] - 1;
			}
		}
		
		System.out.println("Array ordenado: ");
		if(min<0)
			for(int i = 0; i<ao.length; i++) {			//subtraindo o menor valor a todos as posicoes do array (retornando aos valores originais)
				ao[i] = ao[i] + min;
				System.out.print(ao[i]+" ");
			}
		else
			for(int i = 0; i<ao.length; i++) {
				System.out.print(ao[i]+" ");
			}
		System.out.println();
			
	}
}
