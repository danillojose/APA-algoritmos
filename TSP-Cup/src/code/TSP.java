/**
 * Representacao da solucao;
 * Implementacao heuristica de construcao;
 * Implementacao dos movimentos de vizinhanca (Minimo 2);
 * Implementacao do VND;
**/

package code;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import code.euristica.DefineConstants;

public class TSP {
	
	private static int[][] adj_matrix;
	
	public TSP() {
		
	}

	private static int calc_value(int[] s) {
		int weight = 0;
		int[][] value = adj_matrix;
		
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
				//System.out.println("Reinsercao do elemento " + s[i] + " pelo elemento " + s[j] + ".");
				new_value = value + (- pesos[s[i-1]-1][s[i]-1] - pesos[s[i]-1][s[i+1]-1] - pesos[s[j]-1][s[j+1]-1]) + (pesos[s[i-1]-1][s[i+1]-1] + pesos[s[j]-1][s[i]-1] + pesos[s[i]-1][s[j+1]-1]); //arestas que mudaram
				if(new_value <= aux) {
					aux = new_value;
					aux_i = i; aux_j = j;
				}
			}
		}
		//System.out.println("MELHOR ARRAY RE-INSERTION: " + Arrays.toString(s) + " COM VALOR: " + new_value);
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
		//System.out.println("MELHOR ARRAY 2-OPT: " + Arrays.toString(min_s) + " COM VALOR: " + calc_value(min_s));
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
				//System.out.println("SWAP do elemento " + s[i] + " pelo elemento " + s[j] + ".");
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
		
		//System.out.println("MELHOR ARRAY SWAP: " + Arrays.toString(s) + " COM VALOR: " + aux);
		
		return s;
	}
	
	public static int[] VND(int[] s, int[][] value) {
		/*Opcões: SWAP, 2-OPT, RE-INSERTION*/
		int[] new_s = null;
		int[] aux_s = s.clone();
		int r = 3;					//numero de estuturas de vizinhanca
		int k = 1;					//estrutura de vizinhanca corrente
		while(k<=r) {
			if(k==1) {				//SWAP
				//System.out.println("\n--- SWAP METHOD ---");
				new_s = SWAP(s, value); //Tentativa 1 e 6
				//new_s = reinsertion(s, value); //Tentativa 2, 3 e 4
				//new_s = OPT_2(s, 2); //Tentativa 5
			}	
			else					//2-OPT
				if(k==2) {
					//System.out.println("\n--- 2-OPT METHOD ---");
					//new_s = OPT_2(s, 1); //Tentativa 1 e 3
					//new_s = SWAP(s, value); //Tentativa 2, 4 e 5
					new_s = reinsertion(s, value); //Tentativa 6
				}
				else {
					//System.out.println("\n--- RE-INSERTION METHOD ---");
					//new_s = reinsertion(s, value); //Tentativa 1 e 5
					//new_s = OPT_2(s, 1); //Tentativa 2
					//new_s = SWAP(s, value); //Tentativa 3
					//new_s = OPT_2(s, 4); //Tentativa 4
					new_s = OPT_2(s, 2); //Tentativa 6
				}
			if(calc_value(new_s) < calc_value(aux_s)) {			//COMPARA O VALOR DA ESTRUTURA SELECIONADA COM O VALOR CORRENTE
				//System.out.println(calc_value(new_s));
				aux_s = new_s.clone();							//SE FOR MENOR, ATUALIZA
				k = 1;				//K RETORNA AO VALOR INICIAL
			}
			else {					//SE FOR MAIOR, ADICIONA K PARA TESTAR COM NOVA EST. DE VIZINHANCA
				k++;
			}
		}
		//System.out.println("\n\n\n");
		return aux_s;
	}
	
	private static int[] GRASP(int[][] value, int GRASPmax, int dimension) {
		//SOLUCAO s, MATRIZ DE ADJACENCIA value E MAX DE ITERACOES DO METODO
		int f = 100000000, f_linha;
		int[] s = null, vmp = null; int[] vnd;
		for(int i = 0; i < GRASPmax; i++) {
			vmp = nearest_neighbour(adj_matrix, dimension, 0.5);
			vnd = VND(vmp, value);
			f_linha = calc_value(vnd);
			if(f_linha < f) {
				s = vnd.clone(); 
				f = f_linha;
			}
			//System.out.println(f_linha);
		}
		
		return s;
	}
	
		// calcula distancia euclidiana entre dois pontos 
	private static int euclidian_distance(double xi, double yi, double xj, double yj)
		{
			double x = xi - xj; // calcula x e y pra elevar ao quadrado
			double y = yi - yj;
			int distance;
			distance = (int) (Math.pow(x, 2) + Math.pow(y, 2)); // calcula distancia euclidiana
			distance = (int) Math.sqrt(distance);
			Math.round(distance);
			return distance;
		}
		
		
		// heuristica de construcao do vizinho mais proximo 
	private static int[] nearest_neighbour(int[][] adj_matrix, int x, double alfa){ 
		
		int dimension = x;
		// vetor booleano pra marcar os visitados
		Boolean[] visited_node = new Boolean[dimension];
		for (int i = 0; i < dimension; i++) {
			visited_node[i] = false;
		}
		
		// acumular o custo do caminho
		int cost = 0;
		
		// guardo o no atual 
		int curr_node = 0;
		
		// marca o no "0" visitado
		visited_node[curr_node] = true;
		// vistado anteriormente
		int past_visited = 0;
		
		// lista para os nos do caminho
		ArrayList<Integer> path = new ArrayList<Integer>();
		path.add(curr_node);
		
		// enquanto o tamanho do vetor path for menor que o tamanho da dimensao que o arquivo nos da
		while (path.size() < dimension)
		{ 
			// min = maior valor possivel
			int min = DefineConstants.__INT_MAX__;
			for (int i = 0; i < dimension; ++i)
			{ 
				// se nÃ³ i nao tiver sido visitado, e o nÃ³ atual for menor que o minimo
				// adj_matrix[curr_node][i]
				if ((!visited_node[i]) && (adj_matrix[curr_node][i] < min))	
				{
					// minimo recebe o no atual
					min = adj_matrix[curr_node][i];
					
					// visitado anterior recebe i
					past_visited = i;
				}
			} 
			// acumula valores de custo
			cost += min;
			
			// marca coluna j como visitada
			visited_node[past_visited] = true;
			
			// no atual vai ser o anterior
			curr_node = past_visited;
			
			// coloca o anterior na lista do caminho
			path.add(past_visited);
		} 
		
		//shuffle do caminho mais proximo
		ArrayList <Integer> path_aux_alfa = new ArrayList<Integer>();
		for(int i = 1; i < alfa*dimension+1; i++) { // pega os valores e salva em outro array, a partir da posicao 2 (inidice 1)
			path_aux_alfa.add(i-1, path.get(i));
		}
		
		Collections.shuffle(path_aux_alfa); //embaralha os valores
		
		for(int i = 1; i < alfa*dimension+1; i++) { //Volta para o path a partir da posicao 2(indice 1)
			path.set(i, path_aux_alfa.get(i-1));
		}
		
		// imprime o custo total
		/*System.out.print("Custo minimio total: ");
		System.out.print(cost);
		System.out.print("\n");
		System.out.print("Caminho: ");*/
		
		//imprime o caminho
		/*for (int i = 0; i < path.size(); ++i)
		{
			System.out.print(path.get(i));
			System.out.print(", ");
		}*/
		path.add(0);
		//System.out.print("0");
		
		int[] path2 = path.stream().filter(Objects::nonNull).mapToInt(Integer::intValue).toArray(); // converte lista para array
		for (int i = 0; i < path2.length; ++i){
			path2[i] += 1;
		}
		//System.out.println("\nArray do caminho: " + Arrays.toString(path2));
		//System.out.println("CUSTO DA SOLUÇÃO: " + calc_value(path2));
		
		return path2;
		
	}	
	
	
	//COLOCAR A SAIDA DO EURISTICA N SOLUTION

	//public static void main(String[] args) {
public static void main(String[] args) throws NumberFormatException, IOException {
	/**--------LEITURA DE ARQUIVOS DE TESTE-------**/
	BufferedReader br  = null;
	int i = 0, dimension = 0;
	try {
	    br = new BufferedReader (new FileReader ("../instancias_teste/bays29.txt"));
	    for (String linha = br.readLine(); linha != null; linha = br.readLine()) {
	        if(i != 0 && i != 2 && i != dimension+3) {
	        	String[] separados = linha.split("\\s+");
	        	if(i == 1) {
	        		dimension = Integer.parseInt(separados[1]);
	        		adj_matrix = new int[dimension][dimension];
	        	}
	        	else{
	        		for(int j = 0; j < dimension; j++) {
	        			adj_matrix[i-3][j] = Integer.parseInt(separados[j+1]); 
	        		}
	        	}
	    	}
	    	i++;
	    }
	} finally {
	    if (br != null) try { br.close(); } catch (IOException ex) { }    
	}	
	/**------------------------------------------------------**/
	
	int[] grasp = GRASP(adj_matrix, 50, dimension);
	int cost = calc_value(grasp);
	for (int k = 0; k < grasp.length; k++){ grasp[k] -= 1; }
	
	System.out.println("\n\nSOLUCAO GRASP " + Arrays.toString(grasp) + "\nCusto igual a " + cost);
	
	// deleta a matriz alocada
	adj_matrix = null;
}

final class DefineConstants
{
	public static final int __INT_MAX__ = 2147483647;
}

}

