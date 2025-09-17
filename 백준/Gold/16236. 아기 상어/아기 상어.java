import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
 * 
 * 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
 * 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다
 * 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
 * 
 * 
 * 
 * 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
 * 
 * 
 * 변수
 * 1. 물고기 개수
 * 2. 6마리 물고기 사이의 거리
 * 
 * 아기상어 -> Fish
 * 1. y위치
 * 2. x위치
 * 3. 크기
 * 
 * 물고기 -> Fish
 * 1. y위치
 * 2. x위치
 * 3. 크기
 * 
 * 
 * <물고기 탐색>
 * 거리별로 물고기를 bfs로 탐색한다.
 * 동일 거리에서 발견된 물고기를 리스트로 저장하고 가장 위, 가장 왼쪽 물고기를 찾는다.
 * 해당 물고기의 위치를 리턴, 없으면 크기 0인 Fish 리턴
 * 
 * <엄마 부르기>
 * 탐색 결과의 크기가 0이면 엄마부르기
 * 
 * <상어 이동>
 * 탐색한 위치로 상어가 이동
 * 해당 위치 물고기의 크기만큼 상어 크기를 늘린다.
 * 상어가 이동한 거리를 리턴
 * 
 * <시간 더하기>
 * 방문배열 초기화
 * 시간 더하기
 * 
 */
import java.util.*;

public class Main {
  static class Fish {
    int y;
    int x;
    int size;
    int eatCnt;
    int dint;

    public Fish(int y, int x, int size) {
      this.y = y;
      this.x = x;
      this.size = size;
      this.eatCnt = 0;
      this.dint = 0;
    }

    public Fish(int y, int x, int size, int eatCnt, int dint) {
      this.y = y;
      this.x = x;
      this.size = size;
      this.eatCnt = eatCnt;
      this.dint = dint;
    }

    @Override
    public String toString() {
      return "y->" + y + " x->" + x + " size->" + size + " eatCnt->" + eatCnt + " dist->" + dint;
    }
  }

  private static int N;
  private static int[][] mp;
  private static int[][] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    mp = new int[N + 4][N + 4];

    Fish shark = null;

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int temp = Integer.parseInt(st.nextToken());
        if (temp == 9) {
          shark = new Fish(i, j, 2);
          mp[i][j] = 0;
        } else {
          mp[i][j] = temp;
        }

      }
    }

    int time = 0;
    while (true) {
      // System.out.println("======time=== " + time);
      Fish fish = findFish(shark);

      // 엄마 부르기
      if (fish == null) {
        System.out.println(time);
        return;
      }

      // System.out.println("먹을 물고기! " + fish.toString());

      eatFish(shark, fish);

      // System.out.println("먹은 후 상어=== " + shark.toString());
      time += fish.dint;
    }
  }

  private static Fish findFish(Fish shark) {

    // {y,x} 배열을 큐에 넣는다.
    Queue<int[]> qu = new LinkedList<>();
    int[] sharkPosi = { shark.y, shark.x };
    visited = new int[N + 4][N + 4];
    qu.add(sharkPosi);

    int[] dy = { -1, 0, 0, 1 };
    int[] dx = { 0, -1, 1, 0 };
    visited[shark.y][shark.x] = 1;
    int dist = 1;
    while (!qu.isEmpty()) {
      List<Fish> li = new ArrayList<>();
      int cnt = qu.size();
      // System.out.println("--거리->" + dist);
      while (cnt > 0) {
        int[] p = qu.poll();

        for (int n = 0; n < 4; n++) {
          int ny = p[0] + dy[n];
          int nx = p[1] + dx[n];
          // System.out.println("ny->" + ny + " nx->" + nx);
          if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx] == 1) {
            continue;
          }

          if (mp[ny][nx] > shark.size) {
            continue;
          }

          // 먹을만한데?
          if (mp[ny][nx] < shark.size && mp[ny][nx] != 0) {
            // 먹으러 리턴가자!
            li.add(new Fish(ny, nx, mp[ny][nx], 0, dist));
          }

          visited[ny][nx] = 1;
          int[] nxtP = { ny, nx };
          qu.add(nxtP);
        }
        cnt -= 1;
      }

      if (!li.isEmpty()) {
        li.sort((a, b) -> {
          if (a.y == b.y) {
            return Integer.compare(a.x, b.x);
          }
          return Integer.compare(a.y, b.y);
        });
        return li.get(0);
      }
      dist += 1;
    }

    return null;
  }

  private static void eatFish(Fish s, Fish f) {
    // 1. 상어의 위치 이동하기
    mp[f.y][f.x] = 0;
    s.y = f.y;
    s.x = f.x;

    // 잡아먹기
    s.eatCnt += 1;

    // 성장하기!
    if (s.eatCnt == s.size) {
      s.size += 1;
      s.eatCnt = 0;
    }
    return;
  }
}
