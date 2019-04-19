package code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Mochila_inteira {

	private static int M = 0;
	private static int[][] p_v = null;
	private static int n = 0;

	/**		ESTOURO DE MEMORIA PARA ARQUIVOS 2500 E 5000	**/
	public Mochila_inteira(int n, int M, int[][]p_v) throws IOException {
		Integer[][] k = new Integer[n+1][M+1];
		for(int i=0; i<n+1; i++) {
			for(int m=0; m<M+1; m++) {
				if(i == 0 || m==0)
					k[i][m] = 0;
				else
					if(p_v[i-1][0]<=m)
						k[i][m] = Math.max(p_v[i-1][1] + k[i-1][m-p_v[i-1][0]], k[i-1][m]);
					else
						k[i][m] = k[i-1][m];
				//System.out.print("["+k[i][m]+"]\t");
			}
			//System.out.println("");
		}
		System.out.println("Valor: " + k[n][M]);		//imprime o ultimo elemento
		
		/*		CALCULANDO OS ITENS INSERIDOS		*/
		int aux_i = n-1, aux_j = M;
		String itens = ".";
		while(aux_i >= 0) {
			if(k[aux_i][aux_j] < k[aux_i+1][aux_j]) {				//se o termo acima e menor houve insercao do item
				itens = Integer.toString(aux_i+1) + ", " + itens;		//adiciona o item a string
				aux_j = aux_j - p_v[aux_i][0];						//diminui o indice j no valor do peso do item inserido
				aux_i--;											//sobe uma linha
			}
			else {													//senao o item nao foi inserido
				aux_i--;											//sobe uma linha
			}
		}
		System.out.print("Itens: " + itens);						//itens iniciando em 1
	}

	private static void archive_Treatment(String filename) throws NumberFormatException, IOException {
		
		int i = 0;
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String line;
		while((line = in.readLine()) != null){
			StringTokenizer st = new StringTokenizer(line, " ");
				if(i==0) {
					n = Integer.parseInt(st.nextToken());				//associando numero de itens
					M = Integer.parseInt(st.nextToken());				//associando capacidade da mochila
					p_v = new int[n][2];								//criando array peso/valor
				}
				else{
					p_v[i-1][0] = Integer.parseInt(st.nextToken());		//associando peso
					p_v[i-1][1] = Integer.parseInt(st.nextToken());		//associando valor
				}
				i++;
		}

		System.out.println("n = " + n + " e M = " + M);
		System.out.println("p_v = " + Arrays.deepToString(p_v));
		in.close();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		archive_Treatment("../instancias/mochila02.txt");
		new Mochila_inteira(n, M, p_v);
	}

}
