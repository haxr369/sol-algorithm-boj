/**

당신의 숙련도에 따라 퍼즐을 풀 때 틀리는 횟수가 바뀌게 됩니다. 

diff ≤ level이면
    time_cur만큼의 시간을 사용 => 풀기 성공
    
diff > level이면
     (diff - level)번 (time_prev + time_cur) 소요
        time_cur 사용 => 풀기 성공
    =>  (diff - level)번 (time_prev + time_cur)  + time_cur 소요
    
제한 시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값

퍼즐 객체
(diff, time_prev, time_cur)

1. 퍼즐을 난이도 오름차순으로 정렬하기
2. 첫 퍼즐(F)과 맨 나중 퍼즐(L)의 난이도 중간으로 level 셋팅
    => 통과
        => 만약 F와 L의 인덱스 차이가 1초과로 나면
            => levelNxt = (F+levelCur)/2
        => 안나면
            => 정답
    => 미통과
        => 만약 F와 L의 인덱스 차이가 1초과로 나면
            => levelNxt = (levelCur + L)/2

3. 퍼즐 리스트와 level이 주어질 때 통과 여부 계산

**/

import java.util.*;

class Solution {
    static class Puzzle{
        int diff = 0;
        int timeCur = 0;
        int timePrev = 0;
        public Puzzle (int diff, int timeCur, int timePrev){
            this.diff = diff;
            this.timeCur = timeCur;
            this.timePrev = timePrev;
        }
        public String toString(){
            return "diff->"+diff+" cur->"+timeCur+" prev->"+timePrev;
        }
    }
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        List<Puzzle> puzzles = new ArrayList<>();
        puzzles.add(new Puzzle(diffs[0], times[0], 0));
        
        for(int i=1; i<diffs.length; i++){
            puzzles.add(new Puzzle(diffs[i], times[i], times[i-1]));
        }
        
        puzzles.sort((a,b) -> Integer.compare(a.diff, b.diff));
        
        int f = 1;
        int l = puzzles.get(puzzles.size()-1).diff;
        int ans = 100004;
        while(f<=l){
            int mid = (f+l)/2;
            
            boolean midOk = isOk(puzzles, mid, limit);
            // System.out.println("f->"+f+" mid->"+mid+" l->"+l+" ok->"+midOk);
            if(midOk){ // 줄여도 OK
                // 가장 최소값 저장하기
                ans = Integer.min(ans, mid);
                l = mid-1; // f와 l이 같아지면? break된다~!
            } else{
                f = mid+1;
            }
        }
    
        
        return ans;
    }
    
    private boolean isOk(List<Puzzle> puzzles, int level, long limit){
        long used = 0;
        for(Puzzle p : puzzles){
            if(level >= p.diff){
                used += p.timeCur;
            } else {
                used += p.timeCur + (p.diff - level)*(p.timeCur + p.timePrev);
            }
            if(used > limit){
                return false;
            }
        }
        return true;
    }
}