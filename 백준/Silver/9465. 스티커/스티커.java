import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 스티커 최대개수 : 20만개
 * 
 * 20만개 중 떼내는 경우의 수??
 * 
 * 한 스티커를 떼내면 십자가 범위 스티커는 못 쓰는 제약이 생긴다.
 * 
 * 3개
 * dp[N][3] => 선택 안함, 위선택, 아래선택
 * 
 * n-1에서 선택 안함 => D[0][n-1]
 * 위 선택 -> D[1][n-1]
 * 아래 선택 -> D[2][n-1]
 * 
 * n에서 선택 X -> max(0, 1, 2)
 * n에서 위 선택 -> S[0][n] + max(0, 2)
 * n에서 아래 선택 -> S[1][n] + max(0, 1)
 * 
 */

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(st.nextToken());
    while (T > 0) {
      int N = Integer.parseInt(br.readLine());
      int[][] S = new int[2][N + 1];
      int[][] D = new int[3][N + 1];

      for (int i = 0; i < 2; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          S[i][j + 1] = Integer.parseInt(st.nextToken());
        }
      }

      for (int i = 1; i < N + 1; i++) {

        // n이 선택 안할 때,
        D[0][i] = Integer.max(D[0][i - 1], D[1][i - 1]);
        D[0][i] = Integer.max(D[0][i], D[2][i - 1]);

        // n이 위 선택할 때
        D[1][i] = Integer.max(D[0][i - 1], D[2][i - 1]) + S[0][i];

        // n이 아래 선택할 때
        D[2][i] = Integer.max(D[0][i - 1], D[1][i - 1]) + S[1][i];

      }

      int ans = Integer.max(D[0][N], D[1][N]);
      ans = Integer.max(ans, D[2][N]);
      sb.append(ans).append('\n');
      T--;
    }

    System.out.println(sb.toString());

  }
}
