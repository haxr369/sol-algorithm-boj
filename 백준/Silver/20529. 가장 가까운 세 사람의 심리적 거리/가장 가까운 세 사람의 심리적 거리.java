import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * (
 * $A$와
 * $B$ 사이의 심리적인 거리) + (
 * $B$와
 * $C$ 사이의 심리적인 거리) + (
 * $A$와
 * $C$ 사이의 심리적인 거리)
 *
 * 가장 가까운 세 학생 사이의 심리적인 거리
 *
 */


public class Main {
    /**
     * e/i
     * s/n
     * t/f
     * j/p
     */
    private static int[] mbti = new int[16];
    private static char[] estj = {'E','S','T','J'};
    private static int N,T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        while(T>0){
            Arrays.fill(mbti, 0);
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                String s = st.nextToken();
                int idx=0;
                for(int j=0; j<4; j++){
                    if(s.charAt(j) == estj[j]){
                        idx |= (1<<j);
                    }
                }
                mbti[idx]++;
            }
//            for(int i=0; i<16; i++){
//                System.out.println(i+" "+mbti[i]);
//            }

            // 3명을 뽑아서 최소 거리를 측정하자.
            // 16*16*16
            // 사람 뽑기
            int ans = Integer.MAX_VALUE;
            for(int i=0; i<16; i++){
                for(int j=i; j<16; j++){
                    for(int h=j; h<16; h++){
                        int ret = countDistance(i,j,h);
                        ans = Math.min(ret, ans);
                    }
                }
            }
            sb.append(ans).append('\n');
            T--;
        }
        System.out.println(sb);
    }

    private static int countDistance(int i, int j, int h) {
        // 중복 체크 및 재고 체크
        if(i==j || j==h){
            if(i==j && j==h && (mbti[i] <3)){ // 전부 같은데 그 만큼 없다면 X
                return Integer.MAX_VALUE;
            }
            else if(i==j && mbti[i]<2){
                return Integer.MAX_VALUE;
            }
            else if(j==h && mbti[j]<2){
                return Integer.MAX_VALUE;
            }
        }

        if(mbti[i]==0 || mbti[j]==0 || mbti[h]==0)
            return Integer.MAX_VALUE;

        int ret = 0;
        // i와 j의 거리 측정
        for(int c=0; c<4; c++){
            if((i&(1<<c)) != (j&(1<<c)))
                ret++;
        }
        // i와 h의 거리 측정
        for(int c=0; c<4; c++){

            if((i&(1<<c)) != (h&(1<<c)))
                ret++;
        }
        // j와 h의 거리 측정
        for(int c=0; c<4; c++){
            if((j&(1<<c)) != (h&(1<<c)))
                ret++;
        }
//        System.out.println("---- "+i+" : "+j+" : "+h+" = "+ret);
//        System.out.println("------"+mbti[i]+" "+mbti[j]+" "+mbti[h]);
        return ret;
    }
}
