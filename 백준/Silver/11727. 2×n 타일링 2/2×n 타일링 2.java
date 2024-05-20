import java.util.Scanner;

/**
 *  1×2, 2×1과 2×2 타일로 채우는 방법의 수
 * D[n] = nx2 크기 직사각형을 체우는 경우의 수
 * D[1] = 1
 * D[2] = 3;
 * D[3] =
 */
public class Main {
    private static int N;
    private static int[] D = new int[1004];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        D[1] = 1;
        D[2] = 3;
        for(int i=3; i<N+1; i++){
            D[i] = (D[i-1] + 2*D[i-2])%10007;
        }
        System.out.println(D[N]);
    }
}
