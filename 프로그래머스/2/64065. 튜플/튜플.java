/**
집합들은 길이가 1~N까지 길이이고,
길이 1에서 등장하는 요소가 1번째
길이 2에서             2번째
길이 N에서             N번째 요소...

배열의 개수는 500*501/2 = 125,250개

1. 문자열을 길이 1~N개 배열로 만들기
2. 각 배열을 길이로 정렬?    => 약 100만
3. 각 배열을 방문해서 unusedNum인지 체크, 튜플에 append
4. unusedNum인지 체크는 Set을 이용해서 확인 => O(1)
5. 최종 튜플을 출력

**/

import java.util.*;

class Solution {
    private int N = 0;
    
    public int[] solution(String s) {
        
        List<List<Integer>> lis = convS2L(s);
        // System.out.println("====before sort=====");
        // for(List<Integer> li : lis){
        //     System.out.println(li.toString());
        // }
        
        Collections.sort(lis, new Comparator<List<Integer>>(){
            @Override
            public int compare(List<Integer> a, List<Integer> b){
                return Integer.compare(a.size(), b.size());
            }
        });
        int[] answer = new int[lis.size()];
        Set<Integer> st = new HashSet<>();
        // System.out.println("====after sort=====");
        int curIdx = 0;
        for(List<Integer> li : lis){
            // System.out.println(li.toString()); // ← li 직접 출력해야 정상 동작
            for(int n : li){
                if(!st.contains(n)){
                    // System.out.println(n);
                    answer[curIdx] = n;
                    st.add(n);
                    curIdx+=1;
                } 
            }
        }
        return answer;
    }
    
    private List<List<Integer>> convS2L(String s){
        List<List<Integer>> lis  = new ArrayList<>();
        
        boolean isInLrgSet = false;
        boolean isInSmlSet = false;
        int curIdx = 0;
        int curNum = 0;
        List<Integer> tmp = new ArrayList<>();
        
        for(char c : s.toCharArray()){
            if(c == '{' && !isInLrgSet){
                isInLrgSet = true;
            } else if (c == '{' && isInLrgSet && !isInSmlSet){
                isInSmlSet = true;
            } else if (c == ',' && isInLrgSet && isInSmlSet){ // 숫자를 tmp에 넣기
                tmp.add(curNum);
                curNum = 0;
            } else if (c == ',' && isInLrgSet && !isInSmlSet){ // 다음 set 준비하기
                tmp = new ArrayList<>();
                curIdx +=1;
            } else if (c == '}' && isInLrgSet && isInSmlSet){ // 작은 set 읽기 종료 및 출력에 저장
                tmp.add(curNum);
                curNum = 0;
                List<Integer> deepCopied = new ArrayList<>(tmp);
                lis.add(deepCopied);
                isInSmlSet = false;
            } else if(c == '}' && isInLrgSet && !isInSmlSet){
                // 종료!
                continue;
            } else { // 숫자가 들어오는 경우!
                int n = Integer.parseInt(String.valueOf(c));
                curNum = curNum*10 + n;
            }

        }
        return lis;
    }
    
    
}