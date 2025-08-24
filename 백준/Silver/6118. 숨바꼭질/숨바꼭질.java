
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 헛간의 개수는 N(2 <= N <= 20,000)개이며, 1 부터 샌다고 하자
 * 모든 헛간은 M(1<= M <= 50,000)개의 양방향 길로 이어져 있고,
 * 그 양 끝을 A_i 와 B_i(1<= A_i <= N; 1<= B_i <= N; A_i != B_i)로 나타낸다.
 * 
 * 첫 번째는 숨어야 하는 헛간 번호를(만약 거리가 같은 헛간이 여러개면 가장 작은 헛간 번호를 출력한다),
 * 두 번째는 그 헛간까지의 거리를,
 * 세 번째는 그 헛간과 같은 거리를 갖는 헛간의 개수
 * 
 * 1. 1번부터 가장 먼 헛간의 번호를 찾기
 * 2. 그 거리 찾기
 * 3. 그 거리만큼 떨어진 헛간이 몇개 있는지 계산하기
 * 
 * 
 * 1. 양방향 연결리스트 그래프 셋팅.
 * 2. bfs로 1번부터 최대한 멀리떨어진 위치 찾기
 * 3. 번호가 작은 헛간부터 찾아야하기 때문에 연결리스트 정렬하기
 * 4. scndAns에 최장 거리 값 저장, frstAns에 해당 헛간 번호 저장
 * 5. thrdAns에 그 거리만큼 떨어진 노드 개수 찾기
 * 
 */

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    Integer N = 0, M = 0;

    Integer maxDistNode = Integer.MAX_VALUE;
    Integer maxDist = -1;
    Set<Integer> nodeSet = new HashSet<>();

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    int[] distances = new int[N + 4];
    List<Integer>[] linkedList = new ArrayList[N + 4];

    // 0부터 N-1까지 자리 만들어주기, 0부터 N-1 거리 초기화하기
    for (int i = 1; i < N + 1; i++) {
      linkedList[i] = new ArrayList<>();
      distances[i] = -1;
    }

    // 연결리스트 작성
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      Integer ai = Integer.parseInt(st.nextToken());
      Integer bi = Integer.parseInt(st.nextToken());

      linkedList[ai].add(bi);
      linkedList[bi].add(ai);
    }

    // bfs로 1로부터 모든 정점의 최단거리 찾기
    Queue<Integer> qu = new LinkedList<>();
    qu.add(1);
    distances[1] = 0;

    while (!qu.isEmpty()) {
      int node = qu.poll();

      // node 연결된 다른 노드로 이동
      for (int nxtNode : linkedList[node]) {
        // nxtNode가 방문을 안했을 경우만 탐색
        if (distances[nxtNode] == -1) {
          distances[nxtNode] = distances[node] + 1;
          qu.add(nxtNode);
        }
      }
    }

    for (int i = 1; i < N + 1; i++) {
      // update!
      if (maxDist < distances[i]) {
        maxDist = distances[i];
        nodeSet.clear();
        nodeSet.add(i);
        maxDistNode = i;
      } else if (maxDist == distances[i]) { // 추가
        nodeSet.add(i);
        maxDistNode = Integer.min(maxDistNode, i);
      }
    }

    System.out.print(maxDistNode + " ");
    System.out.print(maxDist + " ");
    System.out.print(nodeSet.size());

    // // 각 연결리스트 정렬
    // for (int i = 0; i < N; i++) {
    // Collections.sort(linkedMap.get(i));
    // // System.out.println(linkedMap.get(i).toString());
    // }

    // Queue<Integer> qu = new LinkedList<>();
    // qu.add(0);
    // distances[0] = 0;
    // maxDist = 0;
    // maxDistNode = 0;
    // nodeSet.add(0);

    // while (!qu.isEmpty()) {
    // Integer node = qu.poll();
    // // System.out.println("node->" + node);

    // Integer nxtDistance = distances[node] + 1;

    // for (int nxtNode : linkedMap.get(node)) {
    // // System.out.println(" nxtNode->" + nxtNode);

    // // 이미 방문을 했었던 헛간?? || 더 짧은 경로를 찾아버린 상황!
    // if (distances[nxtNode] == Integer.MIN_VALUE) {
    // distances[nxtNode] = nxtDistance;
    // // System.out.println(" nxtDistance->" + nxtDistance + "
    // distances[nxtNode]->" +
    // // distances[nxtNode]);
    // // update
    // if (nxtDistance > maxDist) {
    // maxDist = nxtDistance;
    // maxDistNode = nxtNode;
    // nodeSet.clear();
    // nodeSet.add(nxtNode);
    // } else if (nxtDistance == maxDist) {
    // maxDistNode = Integer.min(maxDistNode, nxtNode);
    // nodeSet.add(nxtNode);
    // }
    // qu.add(nxtNode);

    // }
    // }
    // }

    // }
  }
}
