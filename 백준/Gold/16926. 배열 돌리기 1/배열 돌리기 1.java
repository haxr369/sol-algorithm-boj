import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 반시계로 회전
 * min(N,M)/2 == 깊이 = d
 * (0,0) -> (1,1)-> (d,d)
 * 길이 (N,M) -> (N-2, M-2) -> ...
 */
public class Main {
    private static int N,M,R;
    private static int[][] mp = new int[304][304];
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
        while(R>0){
            int deep = 0;
            while(Math.min(N,M)/2 > deep){
//            System.out.println("deep : "+deep);
//            System.out.println("bound (deep, deep) : "+deep+" :, "+deep+", (N-deep-1 : "+(N-deep-1)+", M-deep-1 "+ (M-deep-1) );
                // N-2*deep, M-2*deep
                // 맨 위쪽
                int tempUp = mp[deep][deep];
                for(int j=deep; j<M-deep-1; j++){
                    mp[deep][j] = mp[deep][j+1];
                }
                int tempLeft = mp[N-deep-1][deep];
                for(int i=N-deep-2; i>deep; i--){
                    mp[i+1][deep] = mp[i][deep];
                }
                mp[deep+1][deep] = tempUp;

                int tempButtom = mp[N-deep-1][M-deep-1];
                for(int j=M-deep-2; j>deep; j--){
                    mp[N-deep-1][j+1] = mp[N-deep-1][j];
                }
                mp[N-deep-1][deep+1] = tempLeft;
//                System.out.println("tempLeft : "+tempButtom);
                for(int i=deep; i<N-deep-1; i++){
                    mp[i][M-deep-1] = mp[i+1][M-deep-1];
                }
                mp[N-deep-2][M-deep-1] = tempButtom;

//            System.out.println("temp : "+tempLeft);
                deep++;
//            break;
            }
            R--;
        }


        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                sb.append(mp[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

/*

4 5 1
1 2 3 4 5
14 1 2 3 6
13 4 5 6 7
12 11 10 9 8


 */
