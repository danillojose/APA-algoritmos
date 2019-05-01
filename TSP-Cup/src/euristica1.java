import java.util.*;

// calcula distancia euclidiana entre dois pontos 
private int euclidian_distance(double xi, double yi, double xj, double yj)
{
	double x = xi - xj; // calcula x e y pra elevar ao quadrado
	double y = yi - yj;
	int distance;
	distance = Math.pow(x, 2) + Math.pow(y, 2); // calcula distancia euclidiana
	distance = Math.sqrt(distance);
	Math.round(distance);
	return distance;
}
// heuristica de construcao do vizinho mais proximo 
private void nearest_neighbour(int[] adj_matrix)
{ // vetor booleano pra marcar os visitados
	ArrayList<Boolean> visited_node = new ArrayList<Boolean>(1500);
	// acumular o custo do caminho
	int cost = 0;
	// guardo o no atual 
	int curr_node = 0;
	// marca o no "0" visitado
	visited_node.set(curr_node, true);
	// vistado anteriormente
	int past_visited = 0;
	// lista para os nos do caminho
	ArrayList<Integer> path = new ArrayList<Integer>();
	path.add(curr_node);
	// enquanto o tamanho do vetor path for menor que 1500
	while (path.size() < 1500)
	{ // min = maior valor possivel
		int min = DefineConstants.__INT_MAX__;
		for (int i = 0; i < 1500; ++i)
		{ // se nó i nao tiver sido visitado, e o nó atual for menor que o minimo
			// adj_matrix[curr_node][i]
			if ((!visited_node.get(i)) && (adj_matrix[curr_node * 1500 + i] < min))
			{
				// minimo recebe o no atual
				min = adj_matrix[curr_node * 1500 + i];
				// visitado anterior recebe i
				past_visited = i;
			}
		} // acumula valores de custo
		cost += min;
		// marca coluna j como visitada
		visited_node.set(past_visited, true);
		// no atual vai ser o anterior
		curr_node = past_visited;
		// coloca o anterior na lista do caminho
		path.add(past_visited);
	} // imprime o custo total
	System.out.print("Custo minimio total: ");
	System.out.print(cost);
	System.out.print("\n");
	System.out.print("Caminho: ");
	//imprime o caminho
	for (int i = 0; i < path.size(); ++i)
	{
		System.out.print(path.get(i));
		System.out.print(", ");
	}
	System.out.print("0");
}
private static void Main(String[] args)
{ // array pra posicoes x e y
	double[] pos_x = new double[1500];
	double[] pos_y = new double[1500];
	// contador
	int count = 0;
	// alocacao dinamica matriz[1500][1500]
	int[] adj_matrix = new int[1500 * 1500];
	// abre o arquivo com os pontos x e y
	ifstream in = new ifstream("tsp1.txt");
	// pega 1500 valores
	while (count != 1500)
	{ // pega os valores de x e y
		in = pos_x[count];
		in = pos_y[count];
		  count++;
	}
	for (int i = 0; i < 1500; i++)
	{
		for (int j = 0; j < 1500; j++)
		{ //adj_matrix[i][j]
			adj_matrix[i * 1500 + j] = euclidian_distance(pos_x[i], pos_y[i], pos_x[j], pos_y[j]);
		}
	}
	// chama heuristica de construcao do vizinho mais proximo passando a matriz de adjacencia
	nearest_neighbour(adj_matrix);
	// deleta a matriz alocada
	adj_matrix = null;
}

final class DefineConstants
{
	public static final int __INT_MAX__ = 2147483647;
}