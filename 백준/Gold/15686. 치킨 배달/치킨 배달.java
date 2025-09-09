import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Main {

  /**
   * 크기가 N×N인 도시가 있다.
   * 
   * 빈 칸, 치킨집, 집
   * 0은 빈 칸, 1은 집, 2는 치킨집
   * 두 칸 (r1, c1)과 (r2, c2) 사이의 거리는 |r1-r2| + |c1-c2|
   * 
   * 
   * 치킨 거리는 집과 가장 가까운 치킨집 사이의 거리
   * 
   * 집은 치킨 거리를 가지고 있다.
   * 도시의 치킨 거리는 모든 집의 치킨 거리의 합
   * 
   * 
   * 선택해야하는 것은 영업중인 치킨집 중 폐업시킬 치킨집
   * 영업 치킨집 최대 개수 = 13
   * 폐업시킬 치킨집 최대 개수 = 12
   * => 초과된 만큼만 폐업시키면 된다..!
   * 
   * 
   * @param args
   * @return 도시의 치킨 거리의 최솟값
   * @throws IOException
   */

  private static int N;
  private static int M;
  private static int ans = Integer.MAX_VALUE;
  private static List<int[]> chickenPosiList;
  private static List<int[]> homePosiList;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    int[][] mp = new int[N + 4][N + 4];

    int[] outOfBiz = new int[20]; // 폐업된 치킨집 인덱스
    chickenPosiList = new ArrayList<>(); // 1~13개
    homePosiList = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        mp[i][j] = Integer.parseInt(st.nextToken());
        if (mp[i][j] == 2) {
          int[] c = { i, j };
          chickenPosiList.add(c);
        } else if (mp[i][j] == 1) {
          int[] h = { i, j };
          homePosiList.add(h);
        }
      }
    }

    // dfs를 돌며 폐업예정만큼 치킨집을 찾기 => 해당 상태에서 도시 치킨 거리 계산하기
    dfs(-1, 0, outOfBiz);
    System.out.println(ans);
  }

  // 폐점 목표 수 size - M
  // size
  // size - 1
  // ...
  // size - (size - M) + 1
  // 폐업 수가 목표 수만큼 집입시 도시 치킨 거리 계산하기
  private static void dfs(int beforOutOfBizIdx, int outOfBizCnt, int[] outOfBiz) {
    // 폐업 수가 목표만큼 도달
    if (outOfBizCnt == (chickenPosiList.size() - M)) {
      int cityDistSum = getCityDistSum(outOfBiz);
      // System.out.println(" cityDistSum->" + cityDistSum);
      ans = Integer.min(ans, cityDistSum);
      return;
    }
    // System.out.println(
    // "beforOutOfBizIdx->" + beforOutOfBizIdx + " outOfBizCnt->" + outOfBizCnt + ".
    // sum->" + (M + outOfBizCnt + 1));
    for (int i = beforOutOfBizIdx + 1; i < M + outOfBizCnt + 1; i++) {
      outOfBiz[i] = 1;
      // System.out.println(" i->" + i);
      dfs(i, outOfBizCnt + 1, outOfBiz);
      outOfBiz[i] = 0;
    }

  }

  private static int getCityDistSum(int[] outOfBiz) {
    int dists = 0;

    for (int[] home : homePosiList) {
      int homeMinDist = Integer.MAX_VALUE;
      for (int i = 0; i < chickenPosiList.size(); i++) {
        if (outOfBiz[i] == 1) {
          continue;
        }

        int dist = getDist(home[0], home[1], chickenPosiList.get(i)[0], chickenPosiList.get(i)[1]);
        homeMinDist = Integer.min(homeMinDist, dist);
      }
      dists += homeMinDist;
    }
    return dists;
  }

  private static int getDist(int y1, int x1, int y2, int x2) {
    int dy = 0;
    int dx = 0;
    if (y1 > y2) {
      dy = y1 - y2;
    } else {
      dy = y2 - y1;
    }

    if (x1 > x2) {
      dx = x1 - x2;
    } else {
      dx = x2 - x1;
    }
    return dy + dx;
  }
}
