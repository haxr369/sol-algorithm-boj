/**
(2 ~ 9진법 중 하나입니다.)


    int tmp = -1;
    진법idx 0~10까지 값이 1인 진법만 실행
    if(tmp != -1 && tmp != rslt){ // 이전 실행 값과 다르다면
        return "?";
    } else if(tmp == -1){
        tmp = rslt;
    }
    return String.valueOf(tmp);
4. 각 수식 모아서 출력하기
        

**/

import java.util.*;
class Solution {
    private int[] forms = {0,0,1,1,1,1,1,1,1,1,0};
    private int bigN = 0;
    public String[] solution(String[] exprs) {
        
        
        // X가 포함된 수식 인덱스
        List<Integer> prtls = new ArrayList<>();
        // X가 없는 수식 인덱스
        List<Integer> complete = new ArrayList<>();
        
        /**
        1. 가장 큰 숫자 찾기
        forms = {0,0,1,1,1,1,1,1,1,1,0};  => bigN+1 ~ 9 진법이 범위
        **/
        
        for(int i=0; i<exprs.length; i++){
            String s = exprs[i];
            String[] splitS = s.split(" ");
            int A = getS2Int(splitS[0]);
            int B = getS2Int(splitS[2]);
            int C = 0;
            if(!splitS[4].equals("X")){
                C = getS2Int(splitS[4]);
            } else{
                C = -1;
            }
            if(C == -1){
                prtls.add(i);
            } else{
                complete.add(i);
            }
            int tmp = getLargest(A,B,C);
            bigN = Integer.max(tmp, bigN);
        }
        for(int i=0; i<bigN+1; i++){
            forms[i] = 0;
        }
        
        /**
        2. 완성 수식을 통해 진법을 지워가기
        수식 1개 당
            bigN+1 ~ 9 진법으로 수식 테스트
            테스트 통과 시 해당 진법은 생존
            테스트 미통과 시 해당 진법값 0으로 셋팅
        **/
        for(int idx : complete){
            // 수식별로 진법 체크하기
            for(int i=0; i<10; i++){
                if(forms[i] == 0) continue;
                System.out.println(exprs[idx]+" i->"+i);
                boolean chk = chkExprs(exprs[idx], i);
                if(!chk){
                    forms[i] = 0;
                }
            }
        }
        
        for(int i=0; i<10; i++){
            System.out.print(" "+forms[i]);
        }
        /***
        3. 미완성 수식 실행하기
        ***/
        List<String> rslts = new ArrayList<>();
        for(int idx : prtls){
            // 수식별 진법별 결과 체크하기
            int rslt = -1;
            for(int i=0; i<10; i++){
                if(forms[i] == 0) continue;
                System.out.println(exprs[idx]+" i->"+i);
                int tmp = excute(exprs[idx], i);
                if(rslt == -1){
                    rslt = tmp;
                } else if(rslt != -1 && rslt != tmp){
                    rslt = -1;
                    break;
                }
            }
            if(rslt == -1){
                rslts.add("?");    
            } else{
                rslts.add(String.valueOf(rslt));
            }
        }
        System.out.println(rslts.toString());
        
        /**
        4. 수식 만들기
        **/
        List<String> answer = new ArrayList<>();
        for(int i=0; i<prtls.size(); i++){
            int idx = prtls.get(i);
            String tmp = exprs[idx].replace("X", rslts.get(i));
            System.out.println(tmp);
            
            answer.add(tmp);
        }
        
        return answer.toArray(new String[0]);
    }
    
    // i진법이 idx번째 exprs에 적용되는지 체크하기
    public boolean chkExprs(String expr, int i){
        
        String[] splitS = expr.split(" ");
        int C = getS2Int(splitS[4]);
        // i진법으로 expr 수행 결과
        int rslt = excute(expr, i);
        return rslt == C;
    }
    
    // i진법으로 expr 수행하기
    public int excute(String expr, int i){
        String[] splitS = expr.split(" ");
        int A = getS2Int(splitS[0]);
        String ex = splitS[1];
        int B = getS2Int(splitS[2]);
        int C = getS2Int(splitS[4]);
        int rslt = 0;
        
        int a1 = A/10;
        int a2 = A%10;

        int b1 = B/10;
        int b2 = B%10;
        if(ex.equals("+")){ // 뺄셈
            // 올라가야지~
            if((a2+b2) >= i && (a1 + b1 + 1 >= i)){ // 1째 올라가, 2째 올라가
                rslt = 100 + (a1 + b1 + 1 - i)*10 + (a2+b2-i);
            } else if((a2+b2) >= i && (a1 + b1 + 1 < i)){ // 1째만 올라가
                rslt = (a1+b1+1)*10 + (a2+b2-i);
            } else if((a2+b2) < i && (a1 + b1  >= i)){ // 1째 안올라가고 2째 올라가
                rslt = 100 + (a1 + b1 - i)*10 + (a2+b2);
            } else{ // 둘 다 안올라가
                rslt = (a1+b1)*10 + (a2+b2);
            }
        } else{ // 덧셈
            if(a2 < b2){ // 첫째자리 부족!
                rslt = (a1-1-b1)*10 + (a2+i-b2);
            } else{
                rslt = (a1-b1)*10 + (a2-b2);
            }
        }
        return rslt;
    }

    
    public int getLargest(int A, int B, int C){
        int rslt = 0;
        
        int a1 = A/10;
        int a2 = A%10;
        int rslta = Integer.max(a1, a2);
        int b1 = B/10;
        int b2 = B%10;
        int rsltb = Integer.max(b1, b2);
        rslt = Integer.max(rslta, rsltb);
        if(C != -1){
            int c1 = C/100;
            int c2 = (C - c1*100)/10;
            int c3 = C%10;
            int tmp = Integer.max(c1, c2);
            tmp = Integer.max(tmp, c3);
            rslt = Integer.max(rslt, tmp);
        }
        return rslt;
    }
    
    public int getS2Int(String s){
        if(s==null || s.isEmpty()) return -1;
        try{
            return Integer.parseInt(s);
        } catch(Exception e){
            return -1;
        }
    }
}