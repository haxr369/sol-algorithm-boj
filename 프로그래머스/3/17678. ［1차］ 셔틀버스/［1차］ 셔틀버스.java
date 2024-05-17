import java.util.*;
/**
콘이 셔틀을 타고 사무실로 갈 수 있는 도착 시각 중 제일 늦은 시각
 같은 시각에 도착한 크루 중 대기열에서 제일 뒤에 선다. 
셔틀은 09:00부터 총 n회 t분 간격으로 역에 도착하
**/

// 시간을 분 단위로 재설정
class Solution {
    
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        List<Integer> times = new ArrayList<>();    
        for(String tt : timetable){
            times.add(switchString(tt));
        }
        Collections.sort(times);
        int busTime = switchString("09:00");
        int start = -1; // 지금까지 탑승한 사람의 인덱스
        // 버스 배차에 따라 앞부분 제거
        for(int i=0; i<n-1; i++){ // 마지막 버스 전까지 제거
            int cnt = 0;
            System.out.println("버스 시간 : "+busTime);
            
            for(int man=start+1; man<times.size(); man++){
                if(times.get(man) <= busTime){
                    System.out.println(man+"이 탔다.");
                    start = man;
                    cnt++;
                } 
                
                if(cnt >= m) break;
            }
            busTime+= t;
        }
        
        // 맨 마지막 버스에 대해 
        // times.get(start+1) ~ busTime 사이에서 m 안에 들도록
        System.out.println(times.get(start+1));
        System.out.println(busTime);
        int cnt = 0;
        int lastTime = 0;
        for(int i=start+1; i<times.size(); i++){
            if(times.get(i) <= busTime){
                System.out.println(times.get(i) +"이 버스에 탔다!");
                lastTime = times.get(i);
                cnt++;
                if(cnt >= m){
                    break;
                }
            }
            else{
                break;
            }
        }

        // m명 꽉찼다? 그럼 m-1번째 보다 1분 앞서자
        if(cnt==m){
            return switchInt(lastTime-1);
        }
        else{ // 자리가 남는다? 그럼 버스 시간에 도작하자.
            return switchInt(busTime);
        }

    }
    
    public int switchString(String s){
        String[] times = s.split(":");
        int ret = 0;
        ret += 60*Integer.parseInt(times[0]);
        ret += Integer.parseInt(times[1]);
        return ret;
    }
    public String switchInt(int t){
        StringBuilder sb = new StringBuilder();
        if(t/60 >= 10){
            sb.append(String.valueOf(t/60));
        }
        else{
            sb.append("0").append(String.valueOf(t/60));
        }
        sb.append(":");
        if(t%60 >= 10){
            sb.append(String.valueOf(t%60));
        }
        else{
            sb.append("0").append(String.valueOf(t%60));
        }
        return sb.toString();
    }
}