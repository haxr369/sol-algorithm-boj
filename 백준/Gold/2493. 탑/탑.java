import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/***
 * 
 * 탑 번호는 1~N까지
 * 
 * 6 등장! => 비교할 것 없음 0 출력
 * 6 저장
 * 9 등장! => 6과 비교 작음 더 비교할 것 없음 0 출력
 * 5 등장! => 9와 비교 더 큼! 2출력
 * 7 등장! => 5와 비교 작음 9와 비교 더 큼! 2출력
 * 
 * i번째 숫자 보다 크면서 i-1 이하 번호인 탑의 번호를 출력하기
 * 없으면 0을 출력!
 * 
 * 스택에 탑 번호를 넣기
 * 
 * hights[topNumb-1] = 탑 높이
 * 
 * 스택이 비어있으면 일단 스택에 넣고 0출력
 * 스택이 차이있으면 스택 최상단 탑 높이와 i번째 탑 높이 비교
 * 최상단 탑 높이가 더 작거나 같다? =>
 * 최상단 탑 높이가 더 크거나 빌 때까지 빼다가 i 스택에 입력
 * 비어버리면 0 출력
 * 더 큰게 있음 해당 탑 번호 출력
 * 최상단 탑 높이가 더 크다?
 * 최상단 탑 번호 출력하고 i 스택에 입력
 * 
 */

class Top {
  private int index;
  private int high;

  public Top(int index, int high) {
    this.index = index;
    this.high = high;
  }

  public int getIndex() {
    return this.index;
  }

  public int getHigh() {
    return this.high;
  }
}

public class Main {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int topCount = 0;
    // int[] hights = new int[100000004];
    Stack<Top> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();

    topCount = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    // String[] tops = br.readLine().split(" ");
    // for (int i = 1; i <= topCount; i++) {
    // hights[i] = Integer.parseInt(tops[i - 1]);
    // }

    int i = 1;
    while (i <= topCount) {
      int targetHight = Integer.parseInt(st.nextToken());
      // System.out.println("targetHight: " + targetHight);

      if (stack.isEmpty()) {
        // System.out.println("empty so add " + i + " " + targetHight);
        sb.append("0 ");
        stack.add(new Top(i, targetHight));
      } else {
        // 스택이 비거나 큰걸 만날때까지 돌아간다.
        while (true) {
          if (stack.isEmpty()) {
            // System.out.println("empty so add " + i + " " + targetHight);
            sb.append("0 ");
            stack.add(new Top(i, targetHight));
            break;
          }
          Top top = stack.peek();
          int topHight = top.getHigh();
          int topHightNumb = top.getIndex();
          // System.out.println("topIndex : " + topHightNumb + "topH: " + topHight);
          if (topHight > targetHight) {
            sb.append(topHightNumb).append(' ');
            stack.add(new Top(i, targetHight));
            break;
          } else if (topHight <= targetHight) {
            stack.pop();
          }
        }
      }
      i++;
    }

    System.out.println(sb.toString());
  }
}
