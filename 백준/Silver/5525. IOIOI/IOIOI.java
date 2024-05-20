import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * N+1개의 I와 N개의 O로 이루어져 있으면, I와 O이 교대로 나오는 문자열을 PN
 * P3 IOIOIOI
 * S안에 PN이 몇 군데 포함되어 있는지 구하는 프로그램
 *
 * Pn 길이 = 2*n+1
 *
 * 스택을 이용하자
 * 스택 상단 = I
 *      다음 문자 = I
 *          스택 비우기, 다음 문자 확인
 *      다음 문자 = O
 *          스택에 추가
 * 스택 상단 = O
 *      다음 문자 = O
 *          스택 비우기, 다음 문자 확인
 *      다음 문자 = I
 *          스택에 추가, (stack size - 1)/2 >= N 일 경우 cnt 추가
 */

public class Main {
    private static int N,M;
    private static String S;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        S = sc.next();

        int cnt=0;
        Stack<Character> st = new Stack<>();
        for(int i=0; i<S.length(); i++){
            char c = S.charAt(i);
            if(c == 'I'){
                if(st.isEmpty()){
                    st.push(c);
                }
                else if(st.peek() == 'I'){
                    st.clear();
                    st.push(c);
                }
                else{
                    st.push('I');
                    if((st.size()-1)/2 >= N){
                        cnt++;
                    }
                }
            }
            else if(!st.isEmpty()){ // 다음이 O
                if(st.peek() == 'O'){
                    st.clear();
                }
                else{
                    st.push(c);
                }
            }
        }
        System.out.println(cnt);
    }
}
