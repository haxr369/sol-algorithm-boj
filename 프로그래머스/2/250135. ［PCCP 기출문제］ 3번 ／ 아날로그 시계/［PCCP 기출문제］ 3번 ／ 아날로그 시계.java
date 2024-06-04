/**
특정 시간 동안 알람이 울린 횟수 구하기


분당 시침과 분침을 한번씩 만남 => 1분 당 2회 => 최대 2880회 -> base
59분에서는 분침과 초침이 만날 수 없음 -> 시간 당 -1 => -24회 -> sub1

00시 00분 00초 -> 시침과 분침이 겹치므로 -1 -> sub2
12시 00분 00초 -> 시침과 분침이 겹치므로 -1 -> sub3

11시 59분에서는 시침과 초침이 만날 수 없음 -> h가 12 이상 일 경우 -1 -> sub4

분침,시침과 초침 사이 위치에 따라서도 횟수가 달라림

00시 30분 50초 와
00시 30분 10초는 횟수가 달라진다.
같은 분,시라도 위치에 따라 1개가 더할 수 있다.
각에 따라 +1을 해줘야함

**/

class Solution {
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int base1 = calculateBase(h1, m1, s1);
        int base2 = calculateBase(h2, m2, s2);
        
        // Calculate total overlaps from both times and find the difference
        if(base2==base1){
            if((h1==0 || h1==12) && m1==0 && s1==0)
                return 1;
            else
                return 0;
        } else{
            if((h1==0|| h1==12) && m1==0 && s1==0) base1-=1;
            return base2-base1;
        }
    }
    
    private int calculateBase(int h, int m, int s) {
        int base = 2 * (h * 60 + m); // 시침과 분침을 초침이 지남 -> 1분 당 2회 알람
        base -= h; // 59분 -> 00분으로 갈 때 분침과 초침은 만나지 않음 -> 1시간 당 알림 -1
        
        // 00시 00분 00초, 12시 00분 00초 시-분-초 모두 겹침. 11시 59분에서는 시침과 초침이 만날 수 없음 시침-초침
        if (h >= 12) {
            base -= 3;
        } else { // 00시 00분 00초
            base -= 1;
        }
        System.out.println("분과 시간을 이용한 베이스 : "+base);
        base += getAddSec(h, m, s);
        System.out.println("초의 위치를 적용 : "+getAddSec(h, m, s));
        return base;
    }
    
    private int getAddSec(int h, int m, int s) {
        double hDegree = (h % 12) * 30 + m * 0.5 + s * (0.5 / 60);
        double mDegree = m * 6 + s * 0.1;
        double sDegree = s * 6;
        
        int ret = 0;
        if (sDegree >= mDegree) ret += 1;
        if (sDegree >= hDegree) ret += 1;
        
        return ret;
    }
}