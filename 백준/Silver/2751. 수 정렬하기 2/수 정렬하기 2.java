import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * https://www.acmicpc.net/problem/10989
 *
 * 병합 정렬 O(Nlong(N))
 *
 */
public class Main {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] mp = new int[N];
        for(int i=0; i<N; i++){
            mp[i] = Integer.parseInt(br.readLine());
        }
        mergeSort(mp);
        printMap(mp);
    }

    /**
     * e-s == 2 일 경우 두 부분을 정렬
     * e-s가 2 초과라면
     *  1. 두 배열로 나누고 각 배열을 정렬
     *  2. 두 배열을 합친다. 다만 작은게 앞으로 가도록 합친다.
     */
    private static int[] mergeSort(int [] arr) {
        if(arr.length == 1){ // 1개 뿐이다. 그냥 return
            return arr;
        }
        else if(arr.length == 2){
            if(arr[0] > arr[1]){
                int temp = arr[0];
                arr[0] = arr[1];
                arr[1] = temp;
                return arr;
            }
            return arr;
        }
        else{
            // 두 부분으로 자른다.
            int[] frontArr = Arrays.copyOfRange(arr, 0,(arr.length)/2);
            int[] behindArr = Arrays.copyOfRange(arr, arr.length/2, arr.length);
            // 각각을 정렬한다.
            mergeSort(frontArr); // 참조 변수를 인자로 넣기 때문에 따로 return 받지 않아도 된다.
            mergeSort(behindArr);

            // 각각을 합친다.
            int fIdx = 0;
            int bIdx = 0;
            while(fIdx < frontArr.length && bIdx < behindArr.length){
                if(frontArr[fIdx] < behindArr[bIdx]){ // f꺼 하나 추가
                    arr[fIdx+bIdx] = frontArr[fIdx];
                    fIdx++;
                }
                else{ // b꺼 하나 추가
                    arr[fIdx+bIdx] = behindArr[bIdx];
                    bIdx++;
                }
            }
            // f가 먼저 끝났다면 b를 마저 넣자
            if(fIdx == frontArr.length){
                while(bIdx < behindArr.length){
                    arr[fIdx+bIdx] = behindArr[bIdx];
                    bIdx++;
                }
            }

            if(bIdx == behindArr.length){
                while(fIdx < frontArr.length){
                    arr[fIdx+bIdx] = frontArr[fIdx];
                    fIdx++;
                }
            }
            return arr;
        }
    }

    private static void printMap(int[] mp) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            sb.append(mp[i]).append('\n');
        }
        System.out.println(sb);
    }
}
