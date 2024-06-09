/**

n=4, cap=5;

n  = 1 2 3 4
ds = 3 2 5 1
ps = 2 4 1 2

=> 결론 최대한 멀리부터 최대한 꽉꽉 체워서 가져오거나 가져가기

---- 가까운 것

cyc 1 : dest=4
ds = 0 0 5 1
ps = 0 1 1 2

cyc 2 : dest=6
ds = 0 0 0 1
ps = 0 0 0 2

cyc 3 : dest=8
ds = 0 0 0 0
ps = 0 0 0 0

---- 개수 맞추기

cyc 1 : dest=6
ds = 3 2 0 1
ps = 2 0 0 2

cyc 2 : dest=8
ds = 1 0 0 0
ps = 0 0 0 0

cyc 3 : dest =2
ds = 0 0 0 0
ps = 0 0 0 0


---- 최대한 멀리부터 끝내기

cyc 1 : dest=8
ds = 3 2 1 0
ps = 2 2 0 0

cyc 2 : dest=6
ds = 1 0 0 0
ps = 0 0 0 0

cyc 3 : dest=2
ds = 0 0 0 0
ps = 0 0 0 0

1~n이 있는데
idx=n;
idx부터 cap씩 빼고 => answer+=idx*2;
    deliveries 의 최대 인덱스 d
        or
    pickups 의 최대 인덱스 p
    
    next idx = Max(d,p);
    idx가 -1이 될때까지
**/
import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int idx = n-1;
        int d=idx;
        int p=idx;
        
        while(idx >= 0){
            // System.out.println("----idx : "+idx);
            int maxValue=-1;
            int accD=0;
            int id=idx;
            while(id>=0){
                if(deliveries[id]>0) // 가장 첫번째 수화물
                    maxValue = Math.max(maxValue, id);
                
                if(accD+deliveries[id] <= cap){
                    accD += deliveries[id];
                    deliveries[id]=0;
                } else{
                    deliveries[id] -= (cap-accD);
                    d=id;
                    break;
                }
                id--;
            }
            // System.out.print("deliveries ");
            // printArr(deliveries);
            
            int accP=0;
            int ip=idx;
            while(ip>=0){
                if(pickups[ip]>0) // 가장 첫번째 수화물
                    maxValue = Math.max(maxValue, ip);
                if(accP+pickups[ip] <= cap){
                    accP += pickups[ip];
                    pickups[ip]=0;
                } else{
                    pickups[ip] -= (cap-accP);
                    p=ip;
                    break;
                }
                ip--;
            }
            // System.out.print("pickups ");
            // printArr(pickups);
            idx = Math.max(ip,id);
            answer += 2*(maxValue+1);
        }
        
        
        return answer;
    }
    
    private void printArr(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]);
        }
        System.out.print('\n');
        
    }
}