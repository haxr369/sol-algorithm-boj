/**
col번째 컬럼의 값을 기준으로 오름차순 정렬
    그 값이 동일하면 기본키인 첫 번째 컬럼의 값을 기준으로 내림차순 정렬
S_i => i번째 행의 값들을 i로 나눈 나머지들의 합
=> 누적해서 XOR 연산

**/
import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, new Comparator<int[]>(){
            
            @Override
            public int compare(int[] a, int[] b){
                if(a[col-1] == b[col-1]){
                    return b[0]-a[0];
                } else{
                    return a[col-1] - b[col-1];
                }
            }
            
        });
        int[] S = new int[data.length];
        for(int i=0; i<data.length; i++){
            for(int j=0; j<data[0].length; j++){
                S[i] += data[i][j]%(i+1);
            }
        }
        int answer = S[row_begin-1];
        for(int i=row_begin; i<row_end; i++){
            answer ^= S[i];
        }
//         for(int i=0; i<data.length; i++){
//             for(int j=0; j<data[0].length; j++){
//                 System.out.print(data[i][j]);
                
//             }
//             System.out.print('\n');
//         }
        
        
        return answer;
    }
}