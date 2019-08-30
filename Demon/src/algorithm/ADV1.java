package algorithm;
import java.util.Scanner;

public class ADV1 {
	
	
//	8 1
//	1 2 1 3 2 4 2 5 4 8 5 8 3 6 3 7 6 8 7 8 -1 -1
	
	
	static int vertex;
	static int startVertex;
	static int[][] vertexMap;
	static int[] vertexVisit;
	static int vt1,vt2;	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		vertex = sc.nextInt();
		startVertex = sc.nextInt();
		vertexMap = new int[vertex+1][vertex+1];
		vertexVisit = new int[vertex+1];
		
		
		while(true) {
			vt1 = sc.nextInt();
			vt2 = sc.nextInt();
			if(vt1==-1 && vt2==-1)break;
			vertexMap[vt1][vt2]=vertexMap[vt2][vt1]=1;
		}
		DFS(startVertex);
	}
	
	public static void DFS(int startVertex) {
		vertexVisit[startVertex]=1;
		for(int i=1; i<=vertex; i++) {
			if(vertexMap[startVertex][i]==1 && vertexVisit[i]==0) {
				System.out.println(startVertex + "->" + i + "로 이동합니다");
				DFS(i);
			}
		}
	}

}
