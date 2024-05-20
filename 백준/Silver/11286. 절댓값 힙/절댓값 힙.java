import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Queue<Integer> qu = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(Math.abs(o1)== Math.abs(o2)){
                    return o1-o2;
                }
                else return Math.abs(o1) - Math.abs(o2); // 절대 값이 더 작은게 앞으로
            }
        });
//        qu.add(1);
//        qu.add(-5);
//        qu.add(-6);
//        qu.add(7);
//        qu.add(-8);
//        qu.add(9);qu.add(-10);
//        for(int i=0; i<4; i++){
//            System.out.println(qu.poll());
//        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            int ops = Integer.parseInt(br.readLine());
            if(ops==0){
                if(qu.isEmpty()){
                    sb.append(0).append('\n');
                }
                else {
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
