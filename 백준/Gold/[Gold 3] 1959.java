/**
 * 표의 바깥 또는 이미 그려진 칸에 닿아서 더 이상 이동할 수 없게 되면, 시계방향으로 선을 꺾어서 그려나간다.
 *
 * ㅇ	→	↘
 * ↗	↘	↓
 * ↑	↓	↓
 * ↑	끝	↓
 * ↖	←	↙
 *
 * 모든 칸이 채워질 때까지 선을 몇 번 꺾게 될까?
 *                      어디에서 끝나게 될까?
 */

import java.util.Scanner;

/**
 * 1. 계속 다음 위치를 찾음
 * 2. 만약 다음 위치가 유효한 위치가 아니라면
 *      2-1. 시계방향으로 90도 꺾어서 다음 위치 판별
 *      2-2. 두번째 다음 위치도 유효한 위치가 아니라면 끝
 *
 * visited 같이 저장할 수 있는 공간이 필요하다.
 * 달팽이가 이전에 방문했던 위치를 알 수 있으려면 어떻게 해야할까?
 * 그냥 저장하면 boolean 이중 배열이라고 해도 몇 백 GB가 필요하다.
 *
 *
 */
public class Main {
    private static int M,N,Sy,Sx;
    private static long rotateCnt;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();

        if(M/2 > N/2){ // 기준은 N
            if(N%2==1){ // 행이 1개 남음
                rotateCnt = (N/2)* 4L + 1;
                Sy = M - N/2;
                Sx = N - N/2;
            }
            else{ // 행이 2개 남음
                rotateCnt = (N/2 - 1)* 4L + 3L;
                Sy = N/2 + 1;
                Sx = N/2;
            }
        }
        else{ // 기준은 M
            if(M%2==1){ // 열이 1개 남음
                rotateCnt = (M/2)*4L;
                Sy = M - M/2;
                Sx = N - M/2;
            }
            else{
                rotateCnt = (M/2 - 1)* 4L + 2L;
                Sy = M - (M/2 - 1);
                Sx = M/2;
            }
        }
        System.out.println(rotateCnt);
        System.out.println(Sy+" "+Sx);

    }

}
