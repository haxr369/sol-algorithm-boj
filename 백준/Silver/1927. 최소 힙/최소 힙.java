import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 최소 힙
 * 배열에 자연수 x를 넣는다.
 * 배열에서 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다.
 *
 * 연산의 개수 N(1 ≤ N ≤ 100,000)
 *
 * x가 자연수라면 배열에 x라는 값을 넣는(추가하는) 연산
 * x가 자연수라면 배열에 x라는 값을 넣는(추가하는) 연산
 * 0이 주어진 횟수만큼 답을 출력
 */
public class Main {

    private static StringBuilder sb = new StringBuilder();
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Queue<Integer> qu = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            int ops = Integer.parseInt(br.readLine());
            if(ops == 0){
                if(qu.isEmpty()){
                    sb.append(0).append('\n');
                }
                else{
                    sb.append(qu.poll()).append('\n');
                }
            }
            else{
                qu.add(ops);
            }
        }
        System.out.println(sb);
    }

}
