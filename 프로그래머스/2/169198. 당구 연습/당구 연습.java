/**
두 공의 좌표가 정확히 일치하는 경우만 맞음
맞기 전에 멈추지 않음

꼭짓점은 반대로
벽과 평행한 속력은 그대로, 벽과 수직인 속력은 반대로

원쿠션 후 공에 맞아야함
1. 동,서,남,북 방향으로 목표와 공의 중간 지점을 선택
2. 각 뱡향으로 흰공을 움직였을 때, 벽에 맞고 목표에 도달하는지 판단.
3. 거리 제곱의 최소값 구하기

**/

import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        for(int b=0; b<balls.length; b++){
            int subY = Math.abs(balls[b][1] - startY);
            int subX = Math.abs(balls[b][0] - startX);
            int minLen = Integer.MAX_VALUE;
            
            // 위쪽
            if(!(startY >= balls[b][1] && subX==0))
                minLen = Math.min(minLen, (startY+balls[b][1])*(startY+balls[b][1]) + subX*subX );
            // System.out.println("세로 길이 : "+(startY+balls[b][1])+" 가로 길이 : "+subX);
            // 아래쪽
            if(!(startY <= balls[b][1] && subX==0))
                minLen = Math.min(minLen, (n-startY+n-balls[b][1])*(n-startY+n-balls[b][1]) + subX*subX );
            // System.out.println("세로 길이 : "+(n-startY+n-balls[b][1])+" 가로 길이 : "+subX);
            
            // 왼쪽
            if(!(startX >= balls[b][0] && subY==0)) // 목표가 시작의 왼쪽에 있으면서 같은 Y라면 스킵 
                minLen = Math.min(minLen, (startX+balls[b][0])*(startX+balls[b][0]) + subY*subY);
     
            
            // System.out.println("세로 길이 : "+subY+" 가로 길이 : "+ (startX+balls[b][0]));
            // 오른쪽
            if(!(startX <= balls[b][0] && subY==0))
                minLen = Math.min(minLen, (m-startX+m-balls[b][0])*(m-startX+m-balls[b][0]) + subY*subY);
             // System.out.println("세로 길이 : "+subY+" 가로 길이 : "+ (m-startX+m-balls[b][0]));
            answer[b] = minLen;
        }
        
        
        return answer;
    }
}