import java.util.*;

public class Main {

  public static void main(String[] args) {
    /*** 스택에 push하는 순서는 반드시 오름차순을 지키도록 한다고 하자. */
    /**
     * 목표: 4, 3, 6, 8, 7, 5, 2, 1
     * 
     * 1,2,3,4,5,6,7,8
     * 
     * 
     * 1,2,3,4 => push
     * 4=> pop
     * 3=> pop
     * 5,6 => push
     * 6 => pop
     * 7 => pop
     * 5 => pop
     * 2=>pop
     * 1=>pop
     * 
     */

    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    StringBuilder sb = new StringBuilder();
    Stack<Integer> accStack = new Stack<>();
    Stack<Integer> baseStack = new Stack<>();

    // 1부터 pop할 수 있는 Base 스택 만들기
    int i = n;
    while (i > 0) {
      baseStack.add(i);
      i--;
    }

    // 첫번째 자리부터 스택에 넣으면서 가능성 찾기
    while (n > 0) {
      int target = sc.nextInt();
      // System.out.println("target: " + target);
      // 두 스택이 모두 비어있지 않는 경우 둘 중 하나에서 가져올 수 있어야한다!
      while (!baseStack.isEmpty() || !accStack.isEmpty()) {
        int accTop = -1;
        int baseTop = -1;

        if (!accStack.isEmpty()) {
          accTop = accStack.peek();
        }

        if (!baseStack.isEmpty()) {
          baseTop = baseStack.peek();
        }

        // System.out.println("acc: " + accTop + " base : " + baseTop);

        // base에서 acc로 push!
        if (accTop != target && baseTop != target) {
          if (!baseStack.isEmpty()) {
            int temp = baseStack.pop();
            accStack.add(temp);
            sb.append("+\n");
          } else {
            System.out.println("NO");
            return;
          }

        } // target을 만나면 더 이상 while문을 돌지 않고 멈추기, 다만 pop은 수행!
        else if (accTop == target || baseTop == target) {
          if (!accStack.isEmpty() && accTop == target) {
            accStack.pop();
          }
          if (!baseStack.isEmpty() && baseTop == target) {
            baseStack.pop();
            sb.append("+\n");
          }

          sb.append("-\n");
          break;
        } else {
          // 끝까지 target을 만나지 못한 경우...
          System.out.println("NO");
          return;
        }
      }

      n--;
    }
    System.out.println(sb.toString());

  }
}
