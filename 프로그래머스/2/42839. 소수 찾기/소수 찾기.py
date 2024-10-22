answer  = []
decimals = [1 for i in range(10000001)]
def findDecimal(size):
    for i in range(2, 10000):
        # i는 소수
        j = 2
        if decimals[i] == 1:
            while i*j <10000000:
                # i*j는 소수가 아님!
                decimals[i*j] = 0;
                j+=1
    decimals[0] = 0
    decimals[1] = 0

def isDecimal(numb):
    numb = int(numb)
    if decimals[numb] == 1:
        return True
    return False

# col은 전체 숫자 모음 리스트
# used는 사용한 인덱스를 1로 만든 bit마스크
def arrange(col, used, size, numb):
    #print(1 << size)
    if(used == ((1<<size)-1)): # 모든 수를 다 쓰면 return 
        return 0
    #print("numb : "+numb)
    for i in range(size):
        # 이미 사용한 숫자는 스킵
        if used & (1<<i):
            continue
        numb += col[i]
        used |= (1<<i) # 사용처리
        if isDecimal(numb):
            answer.append(int(numb))
        arrange(col, used, size, numb)
        used ^= (1<<i) # 비사용처리
        numb = numb[:-1]
    
def solution(numbers):
    size = len(numbers)
    
    #print(size)
    findDecimal(size)
    
    splitStr = [s for s in list(numbers)]
    #print(splitStr)
    arrange(splitStr, 0, size, '')
    #print(set(answer))
    return len(set(answer))