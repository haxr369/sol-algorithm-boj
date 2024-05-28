/**
두 원 사이에서 x,y가 정수인 점의 개수
원 위의 점도 포함

**/
class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        // x축 마다 가능한 y의 범위를 구하기
        for(int x=1; x<=r1; x++){
            long y1 = (long) Math.sqrt((long)r1*r1-(long)x*x);
            long y2 = (long) Math.sqrt((long)r2*r2-(long)x*x);
            
            if((long)y1*y1+(long)x*x == (long)r1*r1){ // y1이 r1 위에 걸쳐있는 경우
                if(y1==0){
                    answer += 2*(2*y2 + 1);
                } else{
                    answer += 4*(y2-y1+1);
                }
            } else{ // y1이 r1 내부에 있는 경우
                answer += 4*(y2-y1);
            }
        }
        
        for(int x=(r1+1); x<=r2; x++){
            long y2 = getY(x, r2);
            // System.out.println("x : "+x+" y2 : "+y2);
            if(y2==0){
                answer += 2;
            } else{
                answer += 2*(2*y2 + 1);
            }
        }
        answer += 2*(r2-r1+1);
        
        return answer;
    }
    
    // 원 포함 y의 범위
    private long getY(int x, int r){

        long y = (long) Math.sqrt((long)r*r-(long)x*x);
        return y;
    }
}