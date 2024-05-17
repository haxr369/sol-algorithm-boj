import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 소형 기관차가 최대로 끌 수 있는 객차의 수를 미리 정해 놓고,
 *      3대의 소형 기관차가 최대로 끌 수 있는 객차의 수는 서로 같다.
 * 최대한 많은 손님을 목적지까지 운송하도록 한다.
 *      각 객차 마다 타고 있는 손님의 수는 미리 알고 있고, 다른 객차로 손님들이 이동하는 것은 허용하지 않는다.
 *
 * 각 소형 기관차는 번호가 연속적으로 이어진 객차를 끌게 한다.
 *
 *
 *  최대로 운송할 수 있는 손님 수
 *
 *   객차의 수가 입력된다. 그 수는 50,000 이하
 *   객차 당 손님의 수는 100명 이하
 *
 *   1번째 기차 놓을 수 있는 위치 0 <= c <= N-3K;
 *   2번째                    K <= c <= N-2K;
 *   3번째                    2K <= c <= N-K;
 */
public class Main {
    private static int N, K;
    private static int[] clients = new int[50004];
    private static int[][] D = new int[3][50004];// 기관차 번호 , 기관차가 끌고가는 객차 번호
    private static int[] integrates = new int[50004];// 기관차 번호 , 기관차가 끌고가는 객차 번호
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int c = 0; c<N; c++){
            clients[c] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        // 누적합 더해놓기 O(N+K)
        for(int i=0; i<=N-K; i++){
            // 초기화
            if(i==0){
                for(int j=0; j<K; j++){
                    integrates[0] += clients[j];
                }
            }
            else{
                integrates[i] = integrates[i-1] - clients[i-1] + clients[i+K-1];
            }
        }

        // 5~4
        for(int i=N-K; i>=2*K; i--){
            // 앞 D[2]와 integrates 비교
            // i+1에 기차를 놓을 건지 i에 기차를 놓을 건지 판단
            D[2][i] = Math.max(integrates[i], D[2][i+1]);
        }

//        for(int i=N-K; i>=2*K; i--){
//            System.out.println("D[2]["+i+"] = "+D[2][i]);
//        }

        // D[1] : 3~2, D[2] : 5~4
        for(int i=N-2*K; i>=K; i--){
            // i에 2번째 기차를 놨을 때 가능한 D[2]와 승객 수 더하기
            D[1][i] = integrates[i] + D[2][i+K];
            // i에 2번째 기차를 놓는 것이 나닌 i+1번째에 놓는게 좋을 수 있음
            D[1][i] = Math.max(D[1][i], D[1][i+1]);
        }

//        for(int i=N-2*K; i>=K; i--){
//            System.out.println("--- integrates : "+integrates[i]);
//            System.out.println("D[1]["+i+"] = "+D[1][i]);
//        }

        // D[0] : 1~0, D[1] : 3~2
        int ans = 0;
        for(int i=N-3*K; i>=0; i--){
            // i에 1번째 기차를 놨을 때 가능한 D[1]과 승객 수 더하기
            D[0][i] = integrates[i] + D[1][i+K];
            // i에 1번째 기차를 놓는 것이 아닌 i+1번째 놓는게 좋을 수 있음
            ans = Math.max(D[0][i], ans);
        }
        System.out.println(ans);
    }
}
/*

7
35 40 50 10 30 45 60
2

9
26 6 25 18 18 18 4 44 68
1

답 : 138


 */
