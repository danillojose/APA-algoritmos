/**
 * Representacao da solucao;
 * Implementacao heuristica de construcao;
 * Implementacao dos movimentos de vizinhanca (Minimo 2);
 * Implementacao do VND;
**/

package code;

import java.util.Arrays;

public class TSP {

	public TSP() {
		
	}

	private static int calc_value(int[] s) {
		int weight = 0;
		int[][] value = {{0,4,6,1},
						{4,0,2,5},
						{6,2,0,3},
						{1,5,3,0}};
		
		for(int i = 0; i < s.length-1; i++)
			weight += value[s[i]-1][s[i+1]-1];
		return weight;
	}
	
	private static int[] reinsertion(int[] s, int[][] pesos) {
		//ALTERA A POSICAO DE UM ELEMENTO. RETORNA A MELHOR SOLUCAO.
		int value = calc_value(s), new_value = value, aux = value;
		int aux_i = 0, aux_j = 0;
		int size = s.length;
		for(int i = 1; i < size-1; i++) {
			for(int j = i+1; j < size-1; j++) {
				System.out.println("Reinsercao do elemento " + s[i] + " pelo elemento " + s[j] + ".");
				new_value = value + (- pesos[s[i-1]-1][s[i]-1] - pesos[s[i]-1][s[i+1]-1] - pesos[s[j]-1][s[j+1]-1]) + (pesos[s[i-1]-1][s[i+1]-1] + pesos[s[j]-1][s[i]-1] + pesos[s[i]-1][s[j+1]-1]); //arestas que mudaram
				if(new_value <= aux) {
					aux = new_value;
					aux_i = i; aux_j = j;
				}
			}
		}
		System.out.println("MELHOR ARRAY RE-INSERTION: " + Arrays.toString(s) + " COM VALOR: " + new_value);
		//System.out.println("O menor peso para o RE-INSERTION foi " + aux + " com a reinsercao do elemento " + s[aux_i] + " em " + s[aux_j]);

		//OBTEM O ARRAY s COM A REINSERCAO FEITA
		int aux2 = s[aux_i];
		for(int i = aux_i+1; i <= aux_j; i++) {
			s[i-1] = s[i];
		}
		s[aux_j] = aux2;
		
		return s;
	}
	
	private static int[] OPT_2(int[] s, int cut_size) {
		/*REMOVE DOIS ARCOS COM DISTANCIAS DE CORTE CUT_SIZE, INVERTE OS NOS E LIGA-OS NOVAMENTE. RETORNA A MELHOR SOLUCAO*/
		/*CUT_SIZE IGUAL A 1 SIGNIFICA SWAP ENTRE DOIS TERMOS VIZINHOS*/
		int size = s.length, min = calc_value(s);
		int[] copy_s = s.clone();
		int[] min_s = s.clone();
		
		for(int k=1; k<=size-1; k++) {
			int i = k, j = i+cut_size, aux;
			if(j < size-1) {
				for(i = k; i <= j ; i++) {
				    aux = s[j];
				    s[j] = s[i];
				    s[i] = aux;
				    j--;
				}
				//System.out.println("" + Arrays.toString(s));
				if(calc_value(s) <= min) {			//CALCULA SE O MIN PESO DA COMBINACAO CORRENTE DOS ELEMENTOS
					min = calc_value(s);
					min_s = s.clone();
				}
				s = copy_s.clone();
			}
		}
		System.out.println("MELHOR ARRAY 2-OPT: " + Arrays.toString(min_s) + " COM VALOR: " + calc_value(min_s));
		return min_s;
	}
	
	public static int[] SWAP(int[] s, int[][] pesos) {
		/*REALIZA AS POSSIVEIS TROCAS DOS ELEMENTOS. RETORNA A MENOR SOLUCAO*/
		int value = calc_value(s), new_value = value, aux = value;
		int k, aux_i = 0, aux_j = 0;
		int size = s.length;
		for(int i = 1; i < size-1; i++) {		//SWAP NÃO PODE EXISTIR NO PRIMEIRO E ULTIMO TERMO
			k = i;
			for(int j = k+1; j < size-1; j++) {
				System.out.println("SWAP do elemento " + s[i] + " pelo elemento " + s[j] + ".");
				if(i+1 == j) {
					//System.out.println("peso: " + (value + (- pesos[s[i-1]-1][s[i]-1] - pesos[s[i]-1][s[i+1]-1] - pesos[s[j]-1][s[j+1]-1]) + (pesos[s[i-1]-1][s[j]-1] + pesos[s[j]-1][s[i]-1] + pesos[s[i]-1][s[j+1]-1])));
					new_value = value + (- pesos[s[i-1]-1][s[i]-1] - pesos[s[i]-1][s[i+1]-1] - pesos[s[j]-1][s[j+1]-1]) + (pesos[s[i-1]-1][s[j]-1] + pesos[s[j]-1][s[i]-1] + pesos[s[i]-1][s[j+1]-1]);
				}
				else {
					//System.out.println("peso: " + (value + (- pesos[s[i-1]-1][s[i]-1] - pesos[s[i]-1][s[i+1]-1] - pesos[s[j-1]-1][s[j]-1] - pesos[s[j]-1][s[j+1]-1]) + (pesos[s[i-1]-1][s[j]-1] + pesos[s[j]-1][s[i+1]-1] + pesos[s[j-1]-1][s[i]-1] + pesos[s[i]-1][s[j+1]-1])));
					new_value = value + (- pesos[s[i-1]-1][s[i]-1] - pesos[s[i]-1][s[i+1]-1] - pesos[s[j-1]-1][s[j]-1] - pesos[s[j]-1][s[j+1]-1]) + (pesos[s[i-1]-1][s[j]-1] + pesos[s[j]-1][s[i+1]-1] + pesos[s[j-1]-1][s[i]-1] + pesos[s[i]-1][s[j+1]-1]);
				}
				if(new_value <= aux) {
					aux = new_value;
					aux_i = i; aux_j = j;
				}
			}
		}
		//System.out.println("O menor peso para o SWAP foi " + aux + " com a troca do elemento " + s[aux_i] + " por " + s[aux_j]);

		//OBTEM O ARRAY s COM O SWAP
		int aux2 = s[aux_i];
		s[aux_i] = s[aux_j];
		s[aux_j] = aux2;
		
		System.out.println("MELHOR ARRAY SWAP: " + Arrays.toString(s) + " COM VALOR: " + aux);
		
		return s;
	}
	
	public static int[] VND(int[] s, int[][] value) {
		/**Opcões: SWAP, 2-OPT, RE-INSERTION**/
		int[] new_s = null;
		int[] aux_s = s.clone();
		int r = 3;					//numero de estuturas de vizinhanca
		int k = 1;					//estrutura de vizinhanca corrente
		while(k<=r) {
			if(k==1) {				//SWAP
				System.out.println("\n--- SWAP METHOD ---");
				new_s = SWAP(s, value);
			}	
			else					//2-OPT
				if(k==2) {
					System.out.println("\n--- 2-OPT METHOD ---");
					new_s = OPT_2(s, 1);
				}
				else {
					System.out.println("\n--- RE-INSERTION METHOD ---");
					new_s = reinsertion(s, value);
				}
			
			if(calc_value(new_s) < calc_value(aux_s)) {			//COMPARA O VALOR DA ESTRUTURA SELECIONADA COM O VALOR CORRENTE
				aux_s = new_s.clone();							//SE FOR MENOR, ATUALIZA
				k = 1;				//K RETORNA AO VALOR INICIAL
			}
			else {					//SE FOR MAIOR, ADICIONA K PARA TESTAR COM NOVA EST. DE VIZINHANCA
				k++;
			}
		}
		return aux_s;
	}

	public static void main(String[] args) {
		int[] solution = {1,3,4,2,1};
		int[][] value = {{0,4,6,1},
						{4,0,2,5},
						{6,2,0,3},
						{1,5,3,0}};
		
		int[] vnd_solution = VND(solution, value);
		System.out.println("\nMELHOR SOLUCAO VND (SWAP + 2-OPT + RE-INSERTION): " + Arrays.toString(vnd_solution) + " COM VALOR DE: " + calc_value(vnd_solution));
	}

}
