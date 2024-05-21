import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 도착한 칸이 사다리면, 사다리를 타고 위로 올라간다.
 * 뱀이 있는 칸에 도착하면, 뱀을 따라서 내려가게 된다.
 * 게임의 목표는 1번 칸에서 시작해서 100번 칸에 도착하는 것이다.
 * 주사위를 굴려야 하는 횟수의 최솟값
 *
 * 사다리의 수 N(1 ≤ N ≤ 15)과 뱀의 수 M(1 ≤ M ≤ 15)
 * N개의 줄에는 사다리의 정보를 의미하는 x, y (x < y)가 주어진다. x번 칸에 도착하면, y번 칸으로 이동
 * 다음 M개의 줄에는 뱀의 정보를 의미하는 u, v (u > v)가 주어진다. u번 칸에 도착하면, v번 칸으로 이동
 *
 * 전형적인 bfs + 이동맵
 * 1,2,3,4,5,6 전진
 * 전진한 칸에 사다리나 뱀이 있다면 이동
 * 이동한 칸 숫자를 큐에 넣기
 * 각 칸에 도착하면 visited 추가
 */
public class Main {

    private static int N,M;
    private static int[] ladder = new int[104], snakes = new int[104];

    public static void main(String[] args) throws IOException {
        input();

        int[] visited = new int[104];
        Arrays.fill(visited, -1);
        Queue<Integer> qu = new LinkedList<>();
        visited[1] = 0;
        qu.add(1);
        int time = 0;
        boolean isFind = false;
        while(!qu.isEmpty()){
            int size = qu.size();
            for(int s=0; s<size; s++) {
                int q = qu.poll();
                for (int i = 1; i < 7; i++) {
                    int nq = q + i;
                    if (nq == 100) {
                        isFind = true;
                        break;
                    } else if (nq < 100) {
                        if (visited[nq] == -1) { // nq는 처음 방문하는 위치
                            visited[nq] = visited[q] + 1; // 사다리나 뱀을 타기 전 위치까지 도달한 시간 저장
                            if (ladder[nq] > 1) {
                                nq = ladder[nq];
                                if(visited[nq] != -1) continue;
                            } else if (snakes[nq] > 1) {
                                nq = snakes[nq];
                                if(visited[nq] != -1) continue;
                            }
                            visited[nq] = visited[q] + 1; // 사다리나 뱀을 탄 후 위치까지 도달한 시간 저장
                            qu.add(nq);
                        }
                    }
                }
            }
            time++;
            if(isFind)
                break;
        }
        System.out.println(time);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a] = b;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            snakes[a] = b;
        }
    }

}
