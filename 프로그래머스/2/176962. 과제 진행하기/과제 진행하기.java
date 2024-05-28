
/**
멈춰둔 과제가 어려개면 가장 최근에 멈춘 과제부터 시작 -> 스택
멈춘 과제 스택에 저장
새로운 과제는 무조건 시작

정렬

과제 스택 생성

prevPlayTime : 이전 과제 목표 시간, 기본 -1
과제 배열 순회
    만약 prevPlayTime 보다 startTime이 더 작다면
        prevPlayTime-=startTime인 과제를 스택에 저장
        prevPlayTime = startTime + playTime으로 업데이트
            
    prevPlayTime과 startTime이 같다면
        prevPlayTime인 과제를 완성으로 판정
        prevPlayTime = startTime + playTime으로 업데이트
        
    prevPlayTime 보다 startTime이 크다면
        만약 name이 NULL이 아니면
            prevPlayTime인 과제를 완성으로 판정
        만약 스택에 값이 있다면
            스택에 값을 빼기
            만약 스택 목표 시간이 현재 startTime 보다 크다면 뺀 만큼 다시 넣기 break;
                스택 목표 시간이 현재 starttime이랑 같으면  완성으로 판정하고 다시 시도
                스택 목표 시간이 현재 starttime 보다 크다면 완성으로 판정하고 다시 시도
        prevPlayTime = startTime + playTime으로 업데이트
        
**/
import java.util.*;
class Assignment {
    String name;
    int startTime; // -1이라면 스택에 있는 것
    int playTime;
    public Assignment(String name, int startTime, int playTime){
        this.name = name;
        this.startTime = startTime;
        this.playTime = playTime;
    }
    public int getStartTime(){
        return startTime;
    }
    public String getName(){
        return name;
    }
    public int getPlayTime(){
        return playTime;
    }
}

class Solution {
    public String[] solution(String[][] plans) {
    
        List<String> answer = new ArrayList<>();
        
        Assignment[] assignments = new Assignment[plans.length];
        for(int i=0; i<plans.length; i++){
            assignments[i] = new Assignment(plans[i][0], getTime(plans[i][1]), Integer.parseInt(plans[i][2]));
        }
        
        Arrays.sort(assignments, new Comparator<Assignment>(){
            @Override
            public int compare(Assignment a, Assignment b){
                return a.getStartTime() - b.getStartTime();
            }
        });
        // 스택 생성
        Stack<Assignment> st = new Stack<>();
        // int lastStartTime = 0;
        int prevPlayTime = Integer.MAX_VALUE;
        String prevName = "NULL";
        for(int i=0; i<plans.length; i++){
            // System.out.println(assignments[i].getName()+" 과제 시작 시간 : "+assignments[i].getStartTime()+" 현재 시간 : "+prevPlayTime);
            if(prevPlayTime > assignments[i].getStartTime()){
                if(prevName!="NULL"){
                    // System.out.println(prevName+" 스택으로!");
                    st.push(new Assignment(prevName, -1, prevPlayTime - assignments[i].getStartTime()));   
                }
                // lastStartTime = prevPlayTime;
                prevName = assignments[i].getName();
                prevPlayTime = assignments[i].getStartTime() + assignments[i].getPlayTime();
            } else if(prevPlayTime == assignments[i].getStartTime()){
                // System.out.println(prevName+" 완료!");
                answer.add(prevName); // 새로 시작하는 과제
                // lastStartTime = prevPlayTime;
                prevPlayTime = assignments[i].getStartTime() + assignments[i].getPlayTime();
                prevName = assignments[i].getName();
            } else{ // 과제 시작 시간이 더 큰 경우
                if(prevName != "NULL"){
                    // System.out.println(prevName+" 완료!");
                    answer.add(prevName);
                }
                while(!st.isEmpty()){
                    Assignment a = st.peek();
                    // lastStartTime + a.playTime <= assignments[len].getStartTime()
                        // 스택 값을 답으로
                    //                             >
                        // assignments[len] 값을 답으로
                        // break;
                    System.out.println(assignments[i].getName()+" 시간 : "+assignments[i].getStartTime()+" "+a.getName()+" 스택 목표 시간 : "+(prevPlayTime+a.getPlayTime()));
                    if(prevPlayTime+a.getPlayTime() <= assignments[i].getStartTime()){
                        st.pop();
                        answer.add(a.getName());
                        prevPlayTime += a.getPlayTime();
                    } else{
                        st.pop();
                        st.add(
                            new Assignment(a.getName(), -1, 
                                           prevPlayTime+a.getPlayTime() - assignments[i].getStartTime())
                        );
                        // lastStartTime = prevPlayTime;
                        break;
                    }
                }
                prevPlayTime = assignments[i].getStartTime() + assignments[i].getPlayTime();
                prevName = assignments[i].getName();
            }
        }
    
        answer.add(prevName);
        
        
        while(!st.isEmpty()){
            Assignment a = st.pop();
            answer.add(a.getName());
        }
        
        return answer.toArray(new String[plans.length]);
    }
    
    private int getTime(String t){
        String[] times = t.split(":");
        return Integer.parseInt(times[0])*60 + Integer.parseInt(times[1]);
    }
}