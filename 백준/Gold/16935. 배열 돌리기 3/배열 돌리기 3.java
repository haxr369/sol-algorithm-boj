import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 상하 반전 연산 -> 오른쪽으로 180도 회전
 * 2. 배열을 좌우 반전시키는 연산 -> i를 역순으로
 * 3. 오른쪽으로 90도 회전시
 * 4. 왼쪽으로 90도 회전시키는 연산 -> 오른쪽으로 270도 회전
 * 5. 4부분으로 나눠서 시계방향 -> batch 오른쪽으로 90도 회전
 * 6. 4부분으로 나눠서 반시계 방향 -> batch 오른쪽으로 270도 회전
 */
public class Main {
    private static int N, M, R;
    private static int[][] mp = new int[104][104];
    private static int[] ops = new int[1004];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                mp[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<R; i++){
            ops[i] = Integer.parseInt(st.nextToken());
        }

//        smallRotate();
//        largeRotate();
        for(int i=0; i<R; i++){
            switch (ops[i]){
                case 1 :
                    reflectUD();
                    break;
                case 2 :
                    reflectLR();
                    break;
                case 3 :
                    smallRotate(1);
                    break;
                case 4 :
                    smallRotate(3);
                    break;
                case 5 :
                    largeRotate(1);
                    break;
                case 6 :
                    largeRotate(3);
                    break;
                default:
                    break;
            }
        }
        printMp();
    }

    private static void reflectLR() {
        int[][] newMp = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                newMp[i][M-j-1] = mp[i][j];
            }
        }
        mp = newMp;
    }

    private static void reflectUD() {
        int[][] newMp = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                newMp[N-1-i][j] = mp[i][j];
            }
        }
        mp = newMp;
    }


    private static void largeRotate(int cnt) {
        while(cnt > 0){
            int[][] newMp = new int[N][M];
            // 좌상단
            for(int i=0; i<N/2; i++){
                for(int j=0; j<M/2; j++){
                    newMp[i][j+M/2] = mp[i][j];
                }
            }
            // 우상단
            for(int i=0; i<N/2; i++){
                for(int j=M/2; j<M; j++){
                    newMp[i+N/2][j] = mp[i][j];
                }
            }
            // 우하단
            for(int i=N/2; i<N; i++){
                for(int j=M/2; j<M; j++){
                    newMp[i][j-M/2] = mp[i][j];
                }
            }
            // 좌하단
            for(int i=N/2; i<N; i++){
                for(int j=0; j<M/2; j++){
                    newMp[i-N/2][j] = mp[i][j];
                }
            }
            mp = newMp;
            cnt--;
        }
    }

    private static void printMp() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                sb.append(mp[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    /**
     * mp를 오른쪽으로 90도 회전
     */
    private static void smallRotate(int cnt){
        while(cnt > 0){
            int[][] newMp = new int[M][N];
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    newMp[j][N-i-1] = mp[i][j];
                }
            }
            mp = newMp;
            int temp = N;
            N = M;
            M = temp;
            cnt--;
        }
    }
}
