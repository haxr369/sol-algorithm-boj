import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] numbs = new int[100004];
        st = new StringTokenizer(br.readLine());
        numbs[1] = Integer.parseInt(st.nextToken());
        for(int i=2; i<=N; i++){
            int temp = Integer.parseInt(st.nextToken());
            numbs[i] = temp + numbs[i-1]; // 누적합
        }
        StringBuilder sb = new StringBuilder();
        while(M>0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(numbs[e]-numbs[s-1]).append('\n');
            M--;
        }
        System.out.println(sb);
    }
}
