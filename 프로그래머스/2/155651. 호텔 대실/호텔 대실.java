/**
한번 사용한 객실은 퇴실 시간 기준으로 10분간 청소
동시에 겹치는 예약 수의 최대 값 구하기

예약들의 범위에 맞게 사용량 누적하기

24*60 = 1440

**/
class Solution {
    int[] used = new int[1480];
    public int solution(String[][] book_time) {
        int answer = 0;
        
        for(int i=0; i<book_time.length; i++){
            String[] start = book_time[i][0].split(":");
            String[] end = book_time[i][1].split(":");
            int s = Integer.parseInt(start[0])*60 + Integer.parseInt(start[1]);
            int e = Integer.parseInt(end[0])*60 + Integer.parseInt(end[1]) + 10;
            for(int j=s; j<e; j++){
                used[j]++;
                answer = Math.max(answer, used[j]);
            }
        }
        return answer;
    }
}