
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dy = {0,0,-1,1};
    static int[] dx = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        int T = 0;
        int w = 0;
        int h = 0;
        char[][] mp = null;
        String IMPOSSIBLE = "IMPOSSIBLE";
        int resultTime = 0;
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        while(T>0){
            st = new StringTokenizer(br.readLine());
            w   = Integer.parseInt(st.nextToken());
            h   = Integer.parseInt(st.nextToken());
            mp  = new char[h][w];
            Queue<int[]> qu     = new LinkedList<>();
            List<int[]> fires   = new ArrayList<>();
            int[] sang          = new int[2];

            for(int i=0; i<h; i++){
                String tmp = br.readLine();
                for(int j=0; j<w; j++){
                    mp[i][j] = tmp.charAt(j);
                    if(mp[i][j] == '@'){ // 상근이 위치
                        sang[0] = i;
                        sang[1] = j;
                    } else if(mp[i][j] == '*'){ // 불 위치
                        int[] fire = new int[2];
                        fire[0] = i;
                        fire[1] = j;
                        fires.add(fire);
                    }
                }
            }

            qu.addAll(fires);
            qu.add(sang);

            resultTime = exitBuilding(qu, mp, w, h);
            if(resultTime == 0){
                sb.append(IMPOSSIBLE).append('\n');
            } else{
                sb.append(resultTime).append('\n');
            }
            T--;
        }
        System.out.println(sb.toString());
    }

    private static int exitBuilding(Queue<int[]> qu, char[][] mp, int w, int h) {
        int time = 0;
        int size = 0;
        while(!qu.isEmpty()){
            time++;
            size = qu.size();
            for(int s=0; s<size; s++){

                int[] posi = qu.poll();

                for(int i=0; i<4; i++){
                    int ny = posi[0] + dy[i];
                    int nx = posi[1] + dx[i];

                    if(ny<0 || nx<0 || ny>=h || nx>=w){ // 밖으로 나간 경우
                        if(mp[posi[0]][posi[1]] == '@'){ // 상근이 경우, bfs기 때문에 가장 빠른 시간
                           return time;
                        } else{
                            continue;
                        }
                    } else if(mp[ny][nx] == '#'){ // 벽인 경우
                        continue;
                    } else if(mp[ny][nx] == '.'){
                        mp[ny][nx] = mp[posi[0]][posi[1]];
                        qu.add(new int[]{ny, nx});
                    }
                }
            }
        }

        return 0;
    }

    private static void printMp(char[][] mp) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<mp.length; i++){
            for(int j=0; j<mp[0].length; j++){
                sb.append(mp[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}
