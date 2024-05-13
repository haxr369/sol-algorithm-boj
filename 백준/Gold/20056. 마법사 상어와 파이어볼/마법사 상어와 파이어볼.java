import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  i번 파이어볼의
 *      위치는 (ri, ci),
 *      질량은 mi이고,
 *      방향은 di,
 *      속력은 si
 *  격자의 행과 열은 1번부터 N번까지 번호
 *
 *  파이어볼의 방향은 어떤 칸과 인접한 8개의 칸의 방향을 의미하며, 정수로는 다음과 같다.
 *
 *   7 0 1
 *   6 x 2
 *   5 4 3
 *
 *   1. 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
 *   2. 한 칸에 여러 파이어볼이 있다면,
 *         - 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
 *         - 파이어볼은 4개의 파이어볼로 나누어진다.
 *         - 나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
 *              - 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋
 *              - 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋
 *              - 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고,
 *                                                             그렇지 않으면 1, 3, 5, 7
 *              - 질량이 0인 파이어볼은 소멸되어 없어진다.
 *
 *  마법사 상어가 이동을 K번 명령한 후, 남아있는 파이어볼 질량의 합을 구해보자.
 */




public class Main {
    static class FireBall{
        int m;
        int s;
        int d;
        public FireBall(int m, int s, int d){
            this.m = m;
            this.s = s;
            this.d = d;
        }
        public int getM() {
            return m;
        }
        public int getD(){
            return d;
        }
        public int getS(){
            return s;
        }
    }

    private static int N,M,K;
    private static Queue<FireBall>[][] ballMap = new LinkedList[54][54];
    private static Queue<FireBall>[][] tempMap;
    private static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                ballMap[i][j] = new LinkedList<>();
            }
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            ballMap[r][c].add(new FireBall(m,s,d));
        }


        while(K>0){
            // 초기화
            tempMap = new LinkedList[54][54];
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    tempMap[i][j] = new LinkedList<>();
                }
            }

            // 이동 하기
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    while(!ballMap[i][j].isEmpty()){
                        FireBall fireBall = ballMap[i][j].poll();
                        addNextFireBall(i,j,fireBall, tempMap);
                    }
                }
            }
            ballMap = tempMap;
            tempMap = new LinkedList[54][54];
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    tempMap[i][j] = new LinkedList<>();
                }
            }

            // 병합하고 새로운 파이어볼 만들기
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(ballMap[i][j].isEmpty()) continue;
                    int sumM = 0;
                    int sumS = 0;
                    int nd = 0; // 전부 짝수라면 0, 전부 홀수라면
                    int d = ballMap[i][j].element().getD();
                    int size = ballMap[i][j].size();

                    while(!ballMap[i][j].isEmpty()){
                        FireBall fireBall = ballMap[i][j].poll();
                        sumM += fireBall.getM();
                        sumS += fireBall.getS();
                        nd += fireBall.getD()%2;
                        d = fireBall.getD();
                    }
//                    System.out.println("i : "+i+" j : "+j+" sumM : "+sumM);
                    if(size>1){
                        if(sumM/5 == 0) continue;
                        if(nd == size || nd == 0){ // nd는 0이거나 size 거나 0이라면 전부 짝수 size 라면 전부 홀수
                            for(int b=0; b<4; b++){
                                tempMap[i][j].add(new FireBall(sumM/5, sumS/size, b*2));
                            }
                        }
                        else{ //
                            for(int b=0; b<4; b++){
                                tempMap[i][j].add(new FireBall(sumM/5, sumS/size, b*2+1));
                            }
                        }
                    }
                    else {
                        tempMap[i][j].add(new FireBall(sumM, sumS, d));
                    }
                }
            }
            ballMap = tempMap;
//            printBalls();

            K--;
        }
        printMass();

    }

    private static void printMass() {
        int ans = 0;
        // 이동 하기
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                while(!ballMap[i][j].isEmpty()){
                    ans += ballMap[i][j].poll().getM();
                }
            }
        }
        System.out.println(ans);
    }

    // N+1 == 1;   N == 0;
    public static void addNextFireBall(int y, int x, FireBall fireBall, Queue<FireBall>[][] tempMap){
        int ny=y + dy[fireBall.getD()]*fireBall.getS();
        if(ny <0) // N은 7일 때 ny가 -50이라면?
            while(ny<0){
                ny += N;
            }
        else
            ny %=N;
        int nx = x + dx[fireBall.getD()]*fireBall.getS();
        if(nx < 0)
            while(nx<0) {
                nx += N;
            }
        else
            nx %=N;

//        System.out.println("-----old y : "+y+" x : "+x);
//        System.out.println("-----new y : "+ny+" x : "+nx);
//        System.out.println("--speed "+fireBall.getS());
        tempMap[ny][nx].add(fireBall);
    }

    public static void printBalls(){
        StringBuilder sb = new StringBuilder();
        // 이동 하기
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(!ballMap[i][j].isEmpty()){
                    FireBall fireBall = ballMap[i][j].peek();
                    sb.append(fireBall.getM()).append(" ");
                }
                else{
                    sb.append(0).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
