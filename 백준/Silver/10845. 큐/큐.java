import java.util.*;
public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] arg){

        Deque<Integer> queue    = new ArrayDeque<>();
        Scanner sc              = new Scanner(System.in);
        Integer cnt             = null;
        String cmd              = null;
        Integer cmdNumb         = null;

        cnt = sc.nextInt();

        while(cnt > 0){
            cmd = sc.next();
            if(cmd.equals("push")){
                cmdNumb = sc.nextInt();
            }

            switch(cmd){
                case "push" : pushNumb(queue, cmdNumb);
                    break;
                case "pop" : popNumb(queue);
                    break;
                case "size" : sizeQu(queue);
                    break;
                case "empty" : isEmptyQu(queue);
                    break;
                case "front" : frontQu(queue);
                    break;
                case "back" : backQu(queue);
                    break;
            }
            cnt--;
        }
        System.out.println(sb.toString());

    }

    private static void backQu(Deque<Integer> queue) {
        if(queue.isEmpty()){
            sb.append(-1);
        } else {
            Integer front = queue.getLast();
            sb.append(front);
        }
        sb.append("\n");
    }

    private static void frontQu(Deque<Integer> queue) {
        if(queue.isEmpty()){
            sb.append(-1);
        } else {
            Integer front = queue.peek();
            sb.append(front);
        }
        sb.append("\n");
    }

    private static void isEmptyQu(Deque<Integer> queue) {
        if(queue.isEmpty()){
            sb.append(1);
        } else{
            sb.append(0);
        }
        sb.append('\n');
    }

    private static void sizeQu(Deque<Integer> queue) {
        sb.append(queue.size()).append('\n');
    }

    private static void popNumb(Deque<Integer> queue) {
        if(queue.isEmpty()){
            sb.append(-1);
        } else {
            Integer front = queue.poll();
            sb.append(front);
        }
        sb.append("\n");
    }

    private static void pushNumb(Deque<Integer> queue, Integer cmdNumb) {
        queue.add(cmdNumb);
    }
}
