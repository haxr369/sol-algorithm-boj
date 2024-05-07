import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 도형의 겉넓이 구하기
 *
 * 윗면은 무조건 1, 아래면은 무조건 1
 * 옆면은 자기 보다 높거나 같은 기둥이 있다면 0
 *              작은 기둥이 있다면 (Hme - H옆)
 *              장외라면 Hme
 */
public class Main {
    private static int N,M;
    private static int[][] mp = new int[104][104];
    private static int[] dy = {0,0,-1,1};
    private static int[] dx = {-1,1,0,0};
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                mp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                ans += 2; // 위 아래 면
                for(int d=0; d<4; d++){
                    int ny = i + dy[d];
                    int nx = j + dx[d];
                    if(ny < 0 || nx <0 || ny >= N || nx >= M){ // 장외일 경우 자기 자신 높이 추가
                        ans += mp[i][j];
                    }
                    else if(mp[i][j] > mp[ny][nx]){ // 자기 높이가 옆 기둥 보다 높을 경우 차를 추가
                        ans += mp[i][j] - mp[ny][nx];
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
