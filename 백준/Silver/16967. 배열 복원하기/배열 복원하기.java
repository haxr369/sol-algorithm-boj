import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Supplier;

/**
 * H x W인 행렬 A A를 아래로 X, 오른쪽으로 Y 옮긴 행렬 A'
 * A와 A'를 이용해 (H + X) x (W + Y)인 B를 만든다.
 *
 * (i, j)가 두 배열 모두에 포함되지 않으면, Bi,j = 0이다.
 * (i, j)가 두 배열 모두에 포함되면, Bi,j = Ai,j + Ai-X,j-Y이다.
 * (i, j)가 두 배열 중 하나에 포함되면, Bi,j = Ai,j 또는 Ai-X,j-Y이다.
 *
 * B의 맨 밑은 A'만 있기 때문에 한칸 위쪽 라인의 A 값을 구할 수 있다.
 * 또는 맨 위는 A만 있기 때문에 한칸 아래쪽 라인의 A 값을 명확하게 구할 수 있다.
 * 계속 내려가면서 H번 다 돌면 전부 찾음
 */

public class Main {
    private static int H,W,X,Y;
    private static int[][] B = new int[1000][1000];
    private static int[][] A = new int[304][304];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        for(int i=0; i<H+X; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W+Y; j++){
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        StringBuilder sb = new StringBuilder();
        // 위에서 부터 X줄 A로 읽기
        for(int i=0; i<X; i++){
            for(int j=0; j<W; j++){
                A[i][j] = B[i][j];
                sb.append(B[i][j]).append(" ");
            }
            sb.append("\n");
        }
        // X+1번 줄 부터 H줄 검사 단, Y+1행 부터 W줄 검사
        for(int i=X; i<H; i++){
            for(int j=0; j<W; j++){
                if(j<Y){
                    A[i][j] = B[i][j];
                    sb.append(B[i][j]).append(" ");
                }
                else{
                    A[i][j] = B[i][j] - A[i-X][j-Y];
                    sb.append(B[i][j] - A[i-X][j-Y]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}

/*

3 3 1 1
1 2 3 0
1 3 5 3
1 3 5 3
0 1 2 3

1 2 3
1 2 3
1 2 3
 */
