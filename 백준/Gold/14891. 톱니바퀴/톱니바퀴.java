import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회전은 시계 방향과 반시계 방향
 * 인근 톱니 값이 다르면 B는 A가 회전한 방향과 반대방향으로 회전;
 * 서로 같기 때문에, 회전하지 않게 되고
 * 12시방향부터 시계방향 순서대로 주어진다. N극은 0, S극은 1로 나타나있다.
 *
 */
public class Main {

    private static int[][] saws = new int[4][8];
    private static int K;
    private static int[][] move = new int[104][2]; // k번째 움직임 0번째 톱니로 1회 회전한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i=0; i<4; i++){
            String temp = br.readLine();
            for(int j=0; j<8; j++){
                saws[i][j] = temp.charAt(j) - '0';
            }
        }
        K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            move[i][0] = Integer.parseInt(st.nextToken())-1;
            move[i][1] = Integer.parseInt(st.nextToken()); // 1인 경우 시계방향 회전
        }
        int cnt = 0;
        while(K > cnt){
            int[] direct = getRotateMap(cnt);
            for(int i=0; i<4; i++){
                if(direct[i] != 0) rotateSaw(i, direct[i]);
            }
//            printSaw();
            cnt++;
        }
        int ans = getScore();
        System.out.println(ans);
//        printSaw();
    }

    private static int getScore() {
        int ans = 0;
        int ret = 1;
        for(int i=0; i<4; i++){
            ans += saws[i][0]*ret;
            ret*=2;
        }
        return ans;
    }

    private static int[] getRotateMap(int cnt) {
        int[] direct = {0,0,0,0};
        direct[move[cnt][0]] = move[cnt][1]; // 시계방향이면 1, 반시계면 -1
//        System.out.println("기준 톱니 : "+move[cnt][0]+" 방향 : "+rotateMap.get(move[cnt][0]));
        Queue<Integer> qu = new LinkedList<>();
        qu.add(move[cnt][0]);
        while(!qu.isEmpty()){
            int nq = qu.poll();
//            System.out.println("기준 톱니 : "+nq+" 방향 : "+direct[nq]);
            if(nq-1 >=0 && direct[nq-1] == 0){ // 기준 톱니 왼쪽이 장외가 아닌 경우 그리고 아직 방향이 결정되지 않는 경우
                if(saws[nq][6] != saws[nq-1][2]){ // 맞닿은 톱늬의 값이 다르아면
                    direct[nq-1] = -direct[nq];
//                    System.out.println("다음 톱니 번호 : "+(nq-1));
                    qu.add(nq-1);
                }
            }
            if(nq+1 <4 && direct[nq+1] == 0){ // 기준 톱니 오른쪽이 장외가 아닌 경우
                if(saws[nq][2] != saws[nq+1][6]){
                    direct[nq+1] = -direct[nq];
//                    System.out.println("다음 톱니 번호 : "+(nq+1));
                    qu.add(nq+1);
                }
            }
        }
        return direct;
    }

    private static void printSaw() {
        for(int i=0; i<4; i++){
            for(int j=0; j<8; j++){
                System.out.print(saws[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private static void rotateSaw(int cnt, int dir) {
        int[] newSaw = new int[8];
        if(dir==1){ // 시계방향
            for(int i=0; i<7; i++){
                newSaw[i+1] = saws[cnt][i];
            }
            newSaw[0] = saws[cnt][7];
        }
        else{
            for(int i=0; i<7; i++){
                newSaw[i] = saws[cnt][i+1];
            }
            newSaw[7] = saws[cnt][0];
        }
        saws[cnt] = newSaw;
    }
}
