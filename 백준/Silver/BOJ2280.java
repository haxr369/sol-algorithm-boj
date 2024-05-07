package implement;

import java.util.Scanner;

/**
 *  각 숫자는 모두 s+2의 가로와 2s+3의 세로로 이루어 진다.
 *  각 숫자의 사이에는 공백이 한 칸 있어야 한다.
 *  숫자는 10개 글자
 *  s가 10이라면 최대 가로 = 12*10 = 120
 *                 세로 = 23*10 = 230
 *
 *  230 x 120 문자 이중 배열에 값을 넣기
 */
public class BOJ2290 {

    private static int s=2;
    private static String n="012";
    private static char[][] mp = new char[500][500];
    private static int[][] frames = {
            {1,1,1,0,1,1,1},
            {0,0,1,0,0,1,0},
            {1,0,1,1,1,0,1},
            {1,0,1,1,0,1,1},
            {0,1,1,1,0,1,0},
            {1,1,0,1,0,1,1},
            {1,1,0,1,1,1,1},
            {1,0,1,0,0,1,0},
            {1,1,1,1,1,1,1},
            {1,1,1,1,0,1,1}};

    public static  void main(String[] args){
        Scanner sc = new Scanner(System.in);
        s = sc.nextInt();
        n = sc.next();
        for(int i=0; i<2*s+3; i++){
            for(int j=0; j<(s+3)*n.length(); j++){
                mp[i][j] = ' ';
            }
        }
        for(int i=0; i<n.length(); i++){
            getNumber(i, n.charAt(i)-'0');
        }
        printLCD(n.length());
    }

    private static void printLCD(int size) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<2*s+3; i++){
            for(int j=0; j<(s+3)*size; j++){
                sb.append(mp[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    /**
     * (s+3)*idx ~ (s+3)*(idx+1)
     * (2+2)-> 0~3까지 + 1 = 5
     *  5부터 4개 +1
     *  0,1,2,3
     * @param idx : 표현되는 숫자가 몇 번째 숫자인지
     * @param number : 표현되는 숫자
     */
    private static void getNumber(int idx, int number) {
        for(int i=0; i<(2*s+3); i++){
            for(int j=(s+3)*idx; j<(s+3)*idx+(s+2); j++) {
                if (i == 0) { // 맨 위
                    if (j == (s + 3) * idx || j == (s + 3) * (idx) + s+1) continue;
                    if (frames[number][0] == 1) mp[i][j] = '-';

                }
                else if (i < (2 * s + 3) / 2) { // 위 두개
                    if(j==(s+3)*idx){
                        if (frames[number][1] == 1) mp[i][j] = '|';
                    }
                    else if(j==(s + 3) * (idx) + s+1){
                        if (frames[number][2] == 1) mp[i][j] = '|';
                    }
                }
                else if (i == (2 * s + 3) / 2 ) { // 중간
                    if (j == (s + 3) * idx || j == (s + 3) * (idx) + s+1) continue;
                    if (frames[number][3] == 1) mp[i][j] = '-';
                }
                else if (i < (2 * s + 2)){ // 위 두개
                    if(j==(s+3)*idx){
                        if (frames[number][4] == 1) mp[i][j] = '|';
                    }
                    else if(j==(s + 3) * (idx) + s+1){
                        if (frames[number][5] == 1) mp[i][j] = '|';
                    }
                }
                else if(i == (2 * s + 2)){ // 맨 아래
                    if (j == (s + 3) * idx || j == (s + 3) * (idx) + s+1) continue;
                    if (frames[number][6] == 1) mp[i][j] = '-';
                }
            }
        }
    }
}
