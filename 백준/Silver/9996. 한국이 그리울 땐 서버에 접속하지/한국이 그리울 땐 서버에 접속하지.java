import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 패턴 시작부터 * 전까지 (1)
 * *
 * * 후부터 패턴 마지막까지 (2)
 * 3가지로 나누고
 * 
 * (1)이 맨 앞에 있는지
 * (2)가 맨 뒤에 있는지 확인
 * 
 */
import java.util.*;

public class Main {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int count = 0;
    String pattern = "";
    String[] patterns;
    StringBuilder sb = new StringBuilder();

    count = Integer.parseInt(br.readLine());
    pattern = br.readLine();
    patterns = pattern.split("\\*", 2);
    // System.out.println(patterns[0] + " " + patterns[1]);
    // *이 있냐 없냐로 분기
    while (count > 0) {
      String word = br.readLine();

      // *가 없는 경우
      if (word.startsWith(patterns[0]) && word.endsWith(patterns[1])
          && (word.length() >= (patterns[0].length() + patterns[1].length()))) {
        sb.append("DA");
      } else {
        sb.append("NE");
      }

      if (count != 1) {
        sb.append('\n');
      }

      count--;
    }
    System.out.println(sb.toString());
  }

}
