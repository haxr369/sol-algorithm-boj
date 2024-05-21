import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있
 * 연속된 세 개의 계단을 모두 밟아서는 안 된다.
 * 마지막 도착 계단은 반드시 밟아야 한다.
 * 총 점수의 최댓값
 *
 * 한 계단씩 갈때 이전 두 계단을 모두 밟았는지 체크해야함.
 * 마지막 계단 보다 큰 값은 skip
 * 각 계단을 도착했을 때 가장 높은 점수를 DP에 저장하자.
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] stairs = new int[304];
        int [] D = new int[304];
        for(int i=1; i<=N; i++){
            stairs[i] = Integer.parseInt(br.readLine());
        }

        D[1] = stairs[1];
        D[2] = stairs[1] + stairs[2];
        for(int i=3; i<=N; i++){
            D[i] = Math.max(D[i-2]+stairs[i], D[i-3]+stairs[i-1]+stairs[i]);
        }
        System.out.println(D[N]);
    }
}
