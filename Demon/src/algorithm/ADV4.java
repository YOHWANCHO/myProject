package algorithm;
import java.util.Scanner;

public class ADV4 {
	
//8 1
//1 2 1 3 2 4 2 5 4 8 5 8 3 6 3 7 6 8 7 8 -1 -1
	
	static int vertex;
	static int startVertex;
	static int[][] vertexMap;
	static int[] visitVertex;
	static int vt1, vt2;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		vertex = sc.nextInt();
		startVertex = sc.nextInt();
		vertexMap = new int [vertex+1][vertex+1];
		visitVertex = new int [vertex+1];
		
		while(true) {
			
			vt1 = sc.nextInt();
			vt2 = sc.nextInt();
			
			if(vt1==-1 && vt2==-1) {
				break;
			}
			vertexMap[vt1][vt2]=vertexMap[vt2][vt1]=1;		
		}
		
		dfs(startVertex);
		
	}
	
	public static void dfs(int n) {
		visitVertex[n]=1;
		
		for(int i=0; i<vertex; i++) {
			if(visitVertex[i]==0&&vertexMap[n][i]==1) {
				System.out.println(n + "->" + i + "로 이동합니다");
			dfs(i);
			}
		}
	}
	
}
