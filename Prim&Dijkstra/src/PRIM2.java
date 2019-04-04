import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PRIM2 {

	public PRIM2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[][] pesos = {
				{null,8,4,0},
				{null,null,9,2},
				{null,null,null,7},
				{null,null,null,null},
		};
		int len = pesos[0].length, max = Integer.MAX_VALUE;
		ArrayList<Integer> Q = new ArrayList<Integer>(len);
		ArrayList<Integer> nulls = new ArrayList<Integer>(len);
		ArrayList<Integer> keys = new ArrayList<Integer>(len);
		ArrayList<Integer> dads = new ArrayList<Integer>(len);
		Integer[][] sol = new Integer[len][len];
		
		for(int i = 0; i < len; i++)				//inicializando array sol com zeros
			for(int j = 0; j < len; j++)
				if(pesos[i][j] != null)
					sol[i][j] = 0;
		
		for(int i = 0; i < len; i++) { 		//inicializando vertices em array Q
			Q.add(i, i);
			nulls.add(i,max);				//array auxiliar de parada
		}
		for(int i = 0; i < len; i++) {		//inicializando valor infinito aos vertices e 0 para raiz
			keys.add(i, max);
			dads.add(i, null);
		}
		keys.add(0, 0);
		
		/**------- ALGORITMO DE PRIM -------**/
		while(!Q.equals(nulls)){
			int u = keys.indexOf(Collections.min(keys));		//seleciona o vertice u com menor key
			int value_u = Q.get(u);
			Q.set(u, max);										//atribui max para o indice u simulando a retirada do vertice
			System.out.println("O item " + u + " saiu da lista Q.");
			
			for(int i = 0; i< len; i++) {
				if(pesos[u][i] != null && pesos[u][i] != 0 && Q.get(i) != max && pesos[u][i] < keys.get(i)) {
					dads.set(i, value_u);
					keys.set(i, pesos[u][i]);
				}
			}
		}
		//System.out.println("END OF Q ARRAY.");
		/**---------------------------------**/
		
		System.out.println(Arrays.deepToString(pesos));
		System.out.println(Arrays.deepToString(sol));
		System.out.println("Q: \t" + Q.toString());
		System.out.println("CHAVES: " + keys.toString());
		System.out.println("PAIS: \t" + dads.toString());
	}

	
}