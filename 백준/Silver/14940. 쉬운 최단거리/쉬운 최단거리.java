import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 모든 지점에 대해서 목표지점까지의 거리
 * n은 세로의 크기, m은 가로의 크기
 * 0은 갈 수 없는 땅이고 1은 갈 수 있는 땅, 2는 목표지점
 *
 * 원래 갈 수 없는 땅인 위치는 0을 출력
 * 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1
 *
 * 2에서 시작해서 bfs로 각 지점까지 거리 계산
 */
class Point1{
    int y;
    int x;
    public Point1(int y, int x){
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public int getX(){
        return x;
    }
}
public class Main {
    private static Queue<Point1> qu = new LinkedList<Point1>();
    private static int[][] mp = new int[1004][1004];
    private static int[][] visited = new int[1004][1004];
    private static int Sy, Sx;
    private static int[] dy = {0,0,-1,1};
    private static int[] dx = {1,-1,0,0};
    private static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i=0; i<N;i++){
            Arrays.fill(visited[i], -1);
        }
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                mp[i][j] = Integer.parseInt(st.nextToken());
                if(mp[i][j]==2){
                    Sy = i;
                    Sx = j;
                }
                else if(mp[i][j] == 0){
                    visited[i][j] = 0;
                }
            }
        }

        visited[Sy][Sx] = 0;
        qu.add(new Point1(Sy, Sx));

        while(!qu.isEmpty()){
            Point1 p = qu.poll();

            // 주변 점 확인
            for(int i=0; i<4; i++){
                int ny = p.getY() + dy[i];
                int nx = p.getX() + dx[i];
                if(ny <0 || nx<0 || ny >= N || nx >= M || visited[ny][nx] != -1)
                    continue;
                visited[ny][nx] = visited[p.getY()][p.getX()] + 1;
                qu.add(new Point1(ny, nx));
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                sb.append(visited[i][j]).append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

}
