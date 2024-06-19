/**
컨테이너 벨트에 놓인 순서대로 택배상자를 내려 바로 트럭에 싣게 되면 택배 기사님이 배달하는 순서와 택배상자가 실려 있는 순서가 맞지 않아 배달에 차질이 생깁니다. 
따라서 택배 기사님이 미리 알려준 순서에 맞게 영재가 택배상자를 실어야 합니다.

보조 컨테이너 벨트를 추가로 설치 -> 스택 구조

expect : [4, 3, 1, 2, 5]
real  :  [1, 2, 3, 4, 5]

**/
import java.util.*;
class Solution {
    public int solution(int[] order) {
        Stack<Integer> st = new Stack<>();
        int orderIdx = 0;
        int realIdx = 1;
        while(orderIdx < order.length){
            // System.out.println("order : "+order[orderIdx]);
            // 보조에서 찾아보기
            if(!st.isEmpty() && order[orderIdx] ==st.peek()){
                // System.out.println("스택에서 뺌! 남은 스택 크기 : "+st.size());
                // 있다면 스택 값 빼고
                st.pop();
                orderIdx++;
            } else if(order[orderIdx] == realIdx){
                // System.out.println("컨베이어에서 뺌!");
                // 보조에서 못 찾으면 컨베이어에서 찾기
                realIdx++;
                orderIdx++;
            } else if(realIdx <= order.length && order[orderIdx] != realIdx){ 
                // 컨베이어 밸트 상자와 명령이 다른 경우 추가하기
                // System.out.println("스택에 추가 : "+realIdx);
                st.push(realIdx);
                realIdx++;
            } else{ // realIdx가 길이를 초과하거나 보조와 컨베이어 둘 다 명령과 다르면 끝
                break;
            }
        }
        
        return orderIdx;
    }
}