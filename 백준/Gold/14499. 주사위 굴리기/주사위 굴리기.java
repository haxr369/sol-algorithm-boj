import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 지도의 오른쪽은 동쪽, 위쪽은 북쪽
 * 지도의 좌표는 (r, c) -> NxM
 *
 *   북
 * 서 4  동
 *   남
 *
 *   2
 * 4 1 3 -> 이동할 때마다 6번에 있는 값을 출력하기
 *   5
 *   6
 *
 * 주사위를 굴렸을 때,
 *      이동한 칸에 쓰여 있는 수가 0이면,
 *          주사위의 바닥면에 쓰여 있는 수가 칸에 복사
 *      0이 아닌 경우에는
 *          주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0
 *
 *  주사위가 이동했을 때 마다 상단에 쓰여 있는 값
 */
public class Main {
    private static int Sy,Sx, N,M, K;
    private static int[][] mp = new int[24][24];
    private static int[] dirs = new int[1004];
    private static StringBuilder sb = new StringBuilder();
    private static int[] dice = new int[6];
    private static int[] dy = {0,0,-1,1};
    private static int[] dx = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Sy = Integer.parseInt(st.nextToken());
        Sx = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                mp[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){
            dirs[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        for(int i=0; i<K; i++){

            if(!moveDicePosition(dirs[i]))
                continue; // 주사위 위치 움직이기
            moveDice(dirs[i]); // 주사위 숫자 움직이기
            if(mp[Sy][Sx] == 0){ // 판이 0인 경우 주사위 값을 판에 넣기
                 mp[Sy][Sx] = dice[0];
            }
            else{ // 판이 0이 아닌 경우
                dice[0] = mp[Sy][Sx];
                mp[Sy][Sx] = 0;
            }
            sb.append(dice[5]).append("\n");
//            printDice();
//            System.out.println("\n");
//            System.out.println(Sy+" "+Sx);
        }
        System.out.println(sb);
    }

    private static boolean moveDicePosition(int dir) {
        int ny = Sy + dy[dir];
        int nx = Sx + dx[dir];
        if(ny < 0 || nx < 0 || ny>=N || nx>=M)
            return false;
        Sy = ny;
        Sx = nx;
        return true;
    }

    private static void printDice() {
        System.out.println("  "+dice[1]);
        System.out.println(dice[3]+" "+dice[0]+" "+dice[2]);
        System.out.println("  "+dice[4]);
        System.out.println("  "+dice[5]);
    }

    private static void moveDice(int d) { // 0,1,2,3 -> 동서북남
        if(d==0){ // 동
            int temp = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[3];
            dice[3] = temp;
        }
        else if(d==1){ // 서
            int temp = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[2];
            dice[2] = temp;
        }
        else if(d==2){ // 북
            int temp = dice[0];
            dice[0] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[4];
            dice[4] = temp;
        }
        else{ // 남
            int temp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[5];
            dice[5] = dice[1];
            dice[1] = temp;
        }
    }
}
