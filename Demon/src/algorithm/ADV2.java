package algorithm;
import java.util.Scanner;

public class ADV2 {
	
	static int[][] map;//map을 나타내는 배열
	
	static int map_size;	//map 사이즈
	static int min;			//최소값

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		map_size = sc.nextInt();
		map = new int[map_size+1][map_size+1];
		min = map_size*map_size;
		for(int i=0; i<map_size; i++) {
			for(int j=0; j<map_size; j++) {
				map[i][j] = sc.nextInt();				
			}
		}
		depthFirstSearch(0,0,1);
		System.out.println("최단거리 : "+min);
	}
	
	public static void depthFirstSearch(int x, int y, int length) {
		if(x==map_size-1 && y==map_size-1) {
			if(min > length)min=length;
			return;
		}
		System.out.println("map : "+map[x][y]);
		map[y][x] = 0;
		
		if(x > 0 && map[y][x-1] == 1) {
			depthFirstSearch(x-1, y, length+1);
			
		}
		
		if(x < map_size && map[y][x+1] == 1) {
			depthFirstSearch(x+1, y, length+1);
		}
		
		if(y < map_size && map[y+1][x] == 1) {
			depthFirstSearch(x, y+1, length+1);
		}
		
		if(y > 0 && map[y-1][x] == 1) {
			depthFirstSearch(x, y-1, length+1);
		}
		
		map[y][x] = 1;
	}

}
