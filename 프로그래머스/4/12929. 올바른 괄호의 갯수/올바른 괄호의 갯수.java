/**

1->1개
2-> 감싼것1, 1개씩 => 2개
3-> 감싼것2개, 1개씩 3개 => 5개
4-> 감싼것5개, 2+2+2+2 => 13개

**/

import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        
//         List<Set<String>> li = new ArrayList<>();
//         Set<String> tmp = new HashSet<>();
//         tmp.add("()");
//         li.add(tmp);
//         for(int i=1; i<6; i++){
//             tmp = new HashSet<>();
//             for(String s : li.get(i-1)){
//                 tmp.add("("+s+")");
//                 tmp.add(s+"()");
//                 tmp.add("()"+s);
//             }
//             li.add(tmp);
//             if(i>=4){
//                 System.out.println(li.get(i).toString());
//             }
            
//         }
        
        int ans = dfs(0, 0, n);
        
        return ans;
    }
    
    // 길이 n까지 사용한 lf와 ri의 개수
    // lf와 ri 각각은 n개를 넘을 수 없다.
    // ri의 개수는 무조건 lf보다 작거나 같아야한다.
    private int dfs(int lf, int ri, int n){
        // System.out.println("lf->"+lf+" ri->"+ri+" n->"+n);
        int rslt = 0;
        // 완전한 괄호를 만든 경우
        if(lf == n && ri == n){
            return 1;
        }
        // 우괄호를 넣을 수 있다면?
        if(lf > ri && n > ri){
            rslt += dfs(lf, ri+1, n);
        }
        // 좌괄호를 넣을 수 있다면?
        if(n > lf){
            rslt += dfs(lf+1, ri, n);
        }
        return rslt;
    }
}