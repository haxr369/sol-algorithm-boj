/**

붕대 1초마다 x의 체력 회복
    => T초 감기 성공하면 y의 추가 체력 회복
    => 최대 체력 이상 회복은 불가
    
t초에 동작

1. 공격 당하기 
    => 체력 깎임
    => 연속 성공 0초.
    => 0 이하면 캐릭터 죽음
2. 붕대 감기

**/

class Solution {
    static class Man{
        int h = 0; // 현재 체력
        int maxH = 0;
        int cT = 0; // 연속 시간
        int stdCT = 0; // 기준 연속 시간
        int rc = 0; // 초당 회복량
        int addRc = 0; // 추가 회복량
        public Man(int h, int cT, int stdCT, int rc, int addRc){
            this.h = h;
            this.maxH = h;
            this.cT = cT;
            this.stdCT = stdCT;
            this.rc = rc;
            this.addRc = addRc;
        }
        public void stepRecovery(){
            cT += 1;
            h = Integer.min(maxH, h+rc);
            if(cT == stdCT){
                cT = 0;
                h = Integer.min(maxH, h+addRc);
            }
        }
        /**
        1: 캐릭터 생존
        0: 캐릭터 죽음
        **/
        public int attacked(int att){
            h -= att;
            cT = 0;
            
            if(h <= 0){
                return 0;
            }
            return 1;
        }
    }
    public int solution(int[] bandage, int h, int[][] attacks) {
        int answer = 0;
        int time = 0;
        int attIdx = 0;
        Man man = new Man(h, 0, bandage[0], bandage[1], bandage[2]);
        while(true){
            
            // time에 더 받을 공격이 있는지 check
            int check = checkAttack(time, attacks, attIdx);
            // 0: 공격이 끝나진 않았지만, time에 공격이 없음
            // 1: 공격 받을 시간
            // 2: 더 이상 받을 공격이 없음!
                // 남은 체력 저장하고 break
            
            if(check == 0){
                // 1초 회복 시전
                man.stepRecovery();
            } else if(check == 1){
                int rslt = man.attacked(attacks[attIdx][1]);
                // 캐릭터 죽음
                if(rslt == 0){
                    return -1; 
                }
                // 다음 공격 준비!
                attIdx += 1;
            } else{ // 모든 공격을 버텼다!
                return man.h;
            }
            time+=1;
        }
    }
    
    private int checkAttack(int time, int[][] attacks, int addIdx){
        if(attacks.length <= addIdx){
            // 더 이상 받을 공격이 없는 상황
            return 2;
        }
        if(attacks[addIdx][0] == time){
            // 공격 받을 타이밍!
            return 1;
        }
        return 0;
    }
}