/**
2:07

1->N->1 순환구조
앞사람이 말한 마지막 문자로 시작하는 단어를 말해야함.
이전에 등장했던 단어 X
한글자 단어 X

Set<String> usedWords

순서, words

i번째 단어 판단.
i-1번째 단어 체크
used 여부 체크 
체크 실패 시 return

모든 단어 성공 시 [0,0] 출력

**/


import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        Set<String> usedWords = new HashSet<>();
        usedWords.add(words[0]);
        
        for(int i=1; i<words.length; i++){
            int userNumb = i%n + 1; // 참가자 번호
            boolean isVaild = true;
            
            if(usedWords.contains(words[i])){
                isVaild = false;
            }
            char bfEnd = words[i-1].charAt(words[i-1].length()-1);
            char nowStrt = words[i].charAt(0);
            // System.out.println("bf->"+bfEnd+" nowStrt->"+nowStrt);
            if(bfEnd != nowStrt){
                isVaild = false;
            }
            if(words[i].length() <= 1){
                isVaild = false;
            }
            
            if(!isVaild){
                int[] answer = {userNumb, i/n + 1};
                return answer;
            }
            usedWords.add(words[i]);
        }
        
      

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        int[] answer = {0,0};
        return answer;
    }
}