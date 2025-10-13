/***
닉네임 변경 방법

in/out 메세지는 관리자창에서 항상 뜬다.

1. 채팅방을 나간 후, 새로운 닉네임으로 다시 들어간다.
2. 채팅방에서 닉네임을 변경한다.

닉네임이 변경된다면 기존 메세지도 다 변경되어야한다.

즉 유저는
1. 유저ID - 중복X
2. 닉네임 - 중복 허용

친구와 메세지는 친구PK로 연결되고, 닉네임부분은 표시 시점의 친구 닉네임으로 표시된다.

Map 자료구조로 유저ID - 닉네임 데이터를 저장하기


1. record를 따라가면서 [유저ID, MSG]를 리스트에 저장
2. record를 따라가면서 유저ID - 닉네임을 저장

***/

import java.util.*;

class Solution {
    private String ENTER_MSG = "님이 들어왔습니다.";
    private String LEAVE_MSG = "님이 나갔습니다.";
    private Map<String, String> idNckNm = new HashMap<>();
    
    
    public String[] solution(String[] record) {
        
        
        // [유저ID, 메세지]
        List<String[]> msges = new ArrayList<>();
        
        for(int i=0; i<record.length; i++){
            String[] wds = record[i].split(" ");
            
            if(wds[0].equals("Enter")){
                // 이미 존재하는 경우 변경
                // 없을 경우 신규 등록
                // System.out.println(wds[0]+" "+wds[1]+" "+wds[2]);
                idNckNm.put(wds[1], wds[2]);
                String[] putWord = {wds[1], ENTER_MSG};
                msges.add(putWord);
            } else if(wds[0].equals("Leave")){
                // System.out.println(wds[0]+" "+wds[1]);
                String[] putWord = {wds[1], LEAVE_MSG};
                msges.add(putWord);
            } else{
                // System.out.println(wds[0]+" "+wds[1]+" "+wds[2]);
                idNckNm.put(wds[1], wds[2]);
            }
        }
        String[] answer = new String[msges.size()];
        for(int i=0; i<msges.size(); i++){
            String[] w = msges.get(i);
            String nickName = idNckNm.get(w[0]);
            answer[i] = nickName+w[1];
        }
        
        return answer;
    }
}