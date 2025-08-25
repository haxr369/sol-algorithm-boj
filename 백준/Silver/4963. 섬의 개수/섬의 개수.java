import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  private static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
  private static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;
    StringBuilder sb = new StringBuilder();
    while (true) {
      st = new StringTokenizer(br.readLine());
      int w = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());

      if (w == 0 && h == 0) {
        break;
      }

      int[][] mp = new int[h + 4][w + 4];

      for (int i = 0; i < h; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < w; j++) {
          mp[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      int[][] visited = new int[h + 4][w + 4];
      int landCnt = 0;

      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
          // i,j 노드부터 주변을 탐색하기!
          if (visited[i][j] == 0 && mp[i][j] == 1) {
            landCnt++;
            visited[i][j] = 1;
            visite(mp, visited, i, j, h, w);
          }
        }
      }

      sb.append(landCnt).append("\n");

    }
    System.out.println(sb.toString());
  }

  private static void visite(int[][] mp, int[][] visited, int i, int j, int h, int w) {

    for (int n = 0; n < 8; n++) {
      int nI = i + dy[n];
      int nJ = j + dx[n];

      if (nI >= 0 && nI < h && nJ >= 0 && nJ < w && visited[nI][nJ] == 0 && mp[nI][nJ] == 1) {
        visited[nI][nJ] = 1;
        visite(mp, visited, nI, nJ, h, w);
      }
    }
  }
}
