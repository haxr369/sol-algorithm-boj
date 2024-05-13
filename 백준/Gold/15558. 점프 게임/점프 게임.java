import java.util.*;

/**
 * 그래프 문제
 *
 * 편의상 유저가 먼저 움직이고, 칸이 사라진다고 가정한다
 *
 * 1. 한 칸 앞으로 이동한다
 * 2. 한 칸 뒤로 이동한다.
 * 3. 반대편 줄로 점프한다. 이때, 원래 있던 칸보다 k칸 앞의 칸으로 이동
 *
 * 첫째 줄에 N과 k가 주어진다.
 * i번째 문자가 0인 경우에는 위험한 칸이고, 1인 경우에는 안전한 칸
 *
 * 게임을 클리어할 수 있으면 1을, 없으면 0을 출력한다.
 * 왼쪽 줄의 1번 칸에서 시작
 * N번 칸보다 더 큰 칸으로 이동하는 경우 성공
 */

/**
 * 위치를 큐에 저장
 * 방문한 시간 저장 -> 재방문 막기
 * 매 시간 마다 time+1 이하 위치는 방문 불가
 */
class Point{
    int line;
    int x;
    public Point(int line, int x){
        this.line = line;
        this.x = x;
    }
    public int getLine(){
        return line;
    }
    public int getX(){
        return x;
    }

    public List<Point> getNextPoints(int k){
        List<Point> points = new ArrayList<>();
        points.add(new Point(line, x+1));
        points.add(new Point(line, x-1));
        points.add(new Point(line==0 ? 1:0, x+k));
        return points;
    }
}
public class Main {
    private static int N,K;
    private static int[][] mp = new int[2][100004];
    private static int[][] visited = new int[2][200004];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        for(int i=0; i<=1; i++){
            String temp = sc.next();
            for(int j=0; j<N; j++){
                mp[i][j] = temp.charAt(j) - '0';
            }
        }

        Queue<Point> qu = new LinkedList<>();
        int time = 0;
        qu.add(new Point(0,0));
        boolean isClear = false;
        while(!qu.isEmpty()){
            int size = qu.size();
//            System.out.println("------ time : "+time);
            for(int t=0; t<size; t++){
                Point np = qu.poll();
                int nline = np.getLine();
                int nx = np.getX();
//                System.out.println("현재 "+nline+" 줄에 "+nx+" 위치에요.");
                for(Point p : np.getNextPoints(K)){
                    int line = p.getLine();
                    int x = p.getX();

                    if(x > time && (x >= N || mp[line][x] == 1)){
//                        System.out.println("--- 다음 "+line+" 줄에 "+x+" 위치에요.");
                        if(visited[line][x]==0 || visited[line][x] > visited[nline][nx]+1){
                            if(x >= N){
                                isClear = true;
                                break;
                            }
                            qu.add(p);
                            visited[line][x] = visited[nline][nx]+1;
                        }
                    }
                }
                if(isClear) break;
            }
            if(isClear) break;
            time++;
        }
        if(isClear){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
    }
}
