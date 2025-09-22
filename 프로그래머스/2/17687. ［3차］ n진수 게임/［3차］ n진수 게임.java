/***

n진수일 때
0 ~ n^0-1, n^0 ~ n^1-1, n^1 +1 ~ n^2, n^3, ... => 범위
즉 n^i ~ n^(i+1) - 1 범위는 i+1개 문자로 한 수를 구한다.

n^(i-1)-1 <= N < n^i, => i+1개 문자로 한 수를 구한다.

8진수
0 <= N < 8
8 <= N < 16

십진수
0 <= N < 10
10 <= N < 100

16진수
0 <= N < 16 -> 1자리
16 <= N < 256 -> 2자리
... 


1. 튜브가 구해야하는 숫자 Numbs 리스트를 구하기
2. Numbs를 n진법으로 변환한 문자열 생성하기
3. 각 문자열을 병합해서 출력하기

156 => 16^1 <= 156 < 16^2
9C

16진수
***/
import java.util.*;
class Solution {
    
    // p가 어떤 정답을 말해야지 구하기
    public String solution(int n, int T, int m, int p) {
        String answer = "";
        List<Integer> arrT = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        // 1. 튜브가 구해야하는 숫자 Numbs 리스트를 구하기
        /**
        m명 중 p번째 사람.
        t개를 구하기
        => 숫자 변환 문자열 중 t번째 문자만 읽으면 됨.
        p-1, m+p-1, 2*m+p-1, => t*m+p-1
        **/
        for(int t=0; t<T; t++){
            arrT.add(t*m+p-1);
        }
        // System.out.println(arrT.toString());
        // String convtS = convt(n, t);
        // System.out.println(convtT(16, 2567, 256));
        String allS = convt(n, T, arrT.get(arrT.size()-1));
        
        for(int t : arrT){
            sb.append(allS.charAt(t)+"");
        }
        // System.out.println(allS);
        return sb.toString();
    }
    
    // 0~t까지 n진법으로 나열한 문자열 출력
    // n^(i-1) <= N < n^i, => i개 문자로 한 수를 구한다.
    private String convt(int n,  int t, int mxIdx){
        // System.out.println("n->"+n+" t->"+t);
        int std = 1;       // i를 나누는 값
        int multiple = 1; // 나눠떨어질 때마다 더해지기
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(true){
            // i가 커져서 다음 자리가 필요하게 되는 경우
            if(i/(std*n) != 0){
                multiple += 1;
                std = std*n;
            }
            String si = convtT(n, i, std);
            sb.append(si);
            i++;
            if(sb.length() > mxIdx){
                break;
            }
        }
        return sb.toString();
    }
    
    // 숫자 i를 n진법으로 변환
    // std <= i < std*n 인 상태
    private String convtT(int n, int i, int std){
        // i를 std으로 나눈 몫과 나머지
        // 나머지를 std//n으로 나눈 몫과 나머지
        // ...
        StringBuilder sb = new StringBuilder();
        
        int tmpStd = std;
        int rst = i;
        while(tmpStd >= 1){
            String share = getNum(rst/tmpStd);
            // System.out.println("share->"+share+" rst->"+rst+" tmpStd->"+tmpStd);
            sb.append(share);
            rst = rst%tmpStd;
            tmpStd/=n;
        }
        return sb.toString();
    }
    
    private String getNum(int i){
        if(i < 10){
            return String.valueOf(i);
        } else {
            return String.valueOf((char)(65 - 10 + i));
        }
                
    }
}