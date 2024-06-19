import java.util.*;
class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<elements.length; i++){
            int ret = elements[i];
            set.add(ret);
            for(int len=1; len<elements.length; len++){ // 길이 추가
                int idx = (i+len)%elements.length;
                ret += elements[idx];
                set.add(ret);
            }
        }
        
        return set.size();
    }
}