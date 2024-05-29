
/**
길이가 가장 짧은, 합이 k인 가장 왼쪽에 있는 수열의 앞 뒤 인덱스 찾기
수열의 최대 길이는 100만개
K는 최대 10억개

int sumNumb = sequence[0]
int sIdx = 0;
int eIdx = 0;

int minLength = Integer.MAX_VALUE;
int[] ans = {0,0};

while(eIdx < sequence.length && sIdx < sequence.length)
    eIdx가 sIdx 보다 작을 경우
        eIdx++
        sumNumb += sequence[eIdx];
    sumNumb가 K보다 작을 경우
        eIdx++
        sumNumb += sequence[i]
    sumNumb가 K와 같을 때
        if(eIdx-sIdx < minLength){
            ans[0] = sIdx;
            ans[1] = eIdx;
            minLength = eIdx-sIdx;
        }
        sumNumb -= sequence[sIdx];
        sIdx++;
    sumNumb가 K 보다 클 경우
        sumNumb -= sequence[sIdx];
        sIdx++;


**/
class Solution {
    
    // 누적합 필요
    public int[] solution(int[] sequence, int k) {
        
        int sumNumb = sequence[0];
        int sIdx = 0;
        int eIdx = 0;

        int minLength = Integer.MAX_VALUE;
        int[] ans = {0,0};
        
        while(eIdx < sequence.length && sIdx < sequence.length){
            // System.out.println("현재 누적 값 : "+sumNumb+" 시작 : "+sIdx+" 끝 : "+eIdx);
            if(eIdx < sIdx){
                eIdx++;
                sumNumb += sequence[eIdx];
            } else if(sumNumb < k){
                if(eIdx+1 == sequence.length){ // 더 이상 추가로 넣을 수 있는 수가 없는 경우
                    // System.out.println(eIdx+"로 더 이상 숫자를 추가할 수 없습니다.");
                    break;
                }
                eIdx++;
                sumNumb += sequence[eIdx];
            } else if(sumNumb == k){
                // System.out.println("------ k를 찾았어요. "+(eIdx-sIdx)+" : "+minLength);
                if(eIdx-sIdx < minLength){
                    ans[0] = sIdx;
                    ans[1] = eIdx;
                    minLength = eIdx - sIdx;
                }
                sumNumb -= sequence[sIdx];
                if(sIdx+1 < sequence.length) sIdx++;
            } else{
                sumNumb -= sequence[sIdx];
                if(sIdx+1 < sequence.length) sIdx++;
            }
        }
        
        return ans;
    }
}