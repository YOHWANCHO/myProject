package algorithm;
import java.util.Arrays;

public class ADV3 {

	static int N=4;
	static int[] num = new int[N];
	static int[] perm = new int[N];
		
	public static void main(String[] args) {
		backTrack(0);
	}
	
	public static void backTrack(int n) {
		System.out.println("perm[i] : "+Arrays.toString(perm));
		if(isSolution(n)) {
			processSolution();
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(perm[i]==0) {
				num[n] = i+1;
				perm[i] = 1;
				backTrack(n+1);
				perm[i]=0;
				
			}
		}
	}
	
	public static boolean isSolution(int n) {
		return (n==N);
	}
	
	public static void processSolution() {
		
		for(int i=0; i<N; i++) {
			System.out.print(num[i]);
		}
		
		System.out.println("");
	}
}
