import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 지도에서 지나갈 수 있는 길이 몇 개 있는지
 * 길이란 한 행 또는 한 열 전부를 나타내며, 한쪽 끝에서 다른쪽 끝까지
 *
 * 길 조건
 * 길에 속한 모든 칸의 높이가 모두 같아야 한다.
 * 경사로를 놓아서 지나갈 수 있는 길
 *      경사로는 높이가 항상 1이며, 길이는 L
 *       경사로는 낮은 칸과 높은 칸을 연결
 *
 * 경사로 조건
 * 경사로는 낮은 칸에 놓으며, L개의 연속된 칸에 경사로의 바닥이 모두 접해야 한다
 * 낮은 칸과 높은 칸의 높이 차이는 1
 *
 *
 */

/**
 * isCheckNeed = false
 * isValid = true
 * cnt = 0;
 * 배열을 돌면서 이전과 같은 숫자가 나오면
 *                 isCheckNeed가 true라면
 *                      cnt++;
 *                      cnt가 L 이상이면
 *                          cnt=0
 *                          isCheckNeed = false;
 *                 isCheckNeed가 false라면
 *                      cnt++;
 *            작은 숫자가 나오면
 *              isCheckNeed가 false일 때,
 *                  isCheckNedd = true;
 *                  cnt = 1;
 *              isCheckNeed가 true일 때,
 *                      isValid = false;
 *                      break;
 *           1만큼 큰 숫자가 나오면
 *                 cnt가 L 이상이면
 *                      cnt = 1;
 *                 cnt가 L 미만이면
 *                      isValid = false;
 *                      break;
 *           2 이상 크거나 작은 숫자가 나오면
 *                      isValid = false;
 *                      break;
 *
 */
public class Main {
    private static int L,N;
    private static int[][] mp = new int[104][104];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                mp[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = 0;
        for(int i=0; i<N; i++){
            boolean isCheckNeed = false;
            boolean isValid = true;
            int cnt = 1;
            int lastValue = mp[i][0];
            for(int j=1; j<N; j++){
//                System.out.println("현재 높이 : "+mp[i][j]);
                if(lastValue == mp[i][j]){
//                    System.out.println("----같은 높이에요!");
                    if(isCheckNeed){ // 같은게 오도록해야함
                        cnt++;
                        if(cnt >= L ){
                            isCheckNeed = false;
                            cnt=0;
                        }
                    }
                    else{
                        cnt++;
                    }
                }
                else if(lastValue - mp[i][j] == 1){ // 1개 작을 때
                    if(!isCheckNeed){
//                        System.out.println("----내려갈거에요! ");
                        isCheckNeed = true;
                        cnt = 1;
                        if(cnt >= L){
                            isCheckNeed = false;
                            cnt=0;
                        }
                    }
                    else{
//                        System.out.println("----경사로는 평탄한 자리에 놓을 수 있어요!");
                        isValid = false;
                        break;
                    }
                }
                else if(mp[i][j] - lastValue == 1){
                    if(!isCheckNeed && cnt>= L){
//                        System.out.println("----올라갔어요! ");
                        cnt = 1;
                    }
                    else{
//                        System.out.println("----자리가 부족해요");
                        isValid = false;
                        break;
                    }
                }
                else{
//                    System.out.println("----높이가 부족해요");
                    isValid = false;
                    break;
                }

                lastValue = mp[i][j];
            }
            if(isCheckNeed && cnt <L){
                isValid = false;
            }
            if(isValid) ans++;
        }
//        System.out.println("중간 체크 : "+ans);
        for(int j=0; j<N; j++){
            boolean isCheckNeed = false;
            boolean isValid = true;
            int cnt = 1;
            int lastValue = mp[0][j];
            for(int i=1; i<N; i++){
//                System.out.println("현재 높이 : "+mp[i][j]);
                if(lastValue == mp[i][j]){
//                    System.out.println("----같은 높이에요!");
                    if(isCheckNeed){ // 같은게 오도록해야함
                        cnt++;
                        if(cnt >= L ){
                            isCheckNeed = false;
                            cnt=0;
                        }
                    }
                    else{
                        cnt++;
                    }
                }
                else if(lastValue - mp[i][j] == 1){ // 1개 작을 때
                    if(!isCheckNeed){
//                        System.out.println("----내려갈거에요! ");
                        isCheckNeed = true;
                        cnt = 1;
                        if(cnt >= L){
                            isCheckNeed = false;
                            cnt=0;
                        }
                    }
                    else{
//                        System.out.println("----경사로는 평탄한 자리에 놓을 수 있어요!");
                        isValid = false;
                        break;
                    }
                }
                else if(mp[i][j] - lastValue == 1){
                    if(!isCheckNeed && cnt>= L){
//                        System.out.println("----올라갔어요! ");
                        cnt = 1;
                    }
                    else{
//                        System.out.println("----자리가 부족해요");
                        isValid = false;
                        break;
                    }
                }
                else{
//                    System.out.println("----높이가 부족해요");
                    isValid = false;
                    break;
                }

                lastValue = mp[i][j];
            }
            if(isCheckNeed && cnt <L){
                isValid = false;
            }
            if(isValid) ans++;
        }
        System.out.println(ans);

    }


}
