"""

45656이란 수
N인 계단 수가 총 몇 개 있는지 구해보자. 0으로 시작하는 수는 계단수가 아니다.

첫째 줄에 정답을 1,000,000,000으로 나눈 나머지

// numb가 length 길이에서 가질 수 있는 총 경우의 수
numberCnts[numb][length]

numb: 0~9, 단 0일 때 length는 0~N-2
length: 0~N-1

numberCnts[0][N-1] = 0 고정

numberCnts[1][0] = 1
numberCnts[2][0] = 1
...
numberCnts[8][0] = 1 
numberCnts[9][0] = 1

numberCnts[3][1] = numberCnts[2][0] + numberCnts[4][0]


numberCnts[l][n] = numb는 0 보다 작거나 9보다 크지 않아야함 numberCnts[l-1][n-1] + numberCnts[l-1][n+1]

"""

N = int(input())

numberCnts = [[0 for _ in range(10)] for _ in range(N)]

numberCnts[N-1][0] = 0 # 고정

for i in range(10):
  # 한자리만 있으면 0은 넣을 수 없음
  if N==1 and i==0:
    continue
  numberCnts[0][i] = 1

for l in range(1, N):
  # print("l->",l)
  for n in range(0, 10):
    # print("   n->",n)
    if(l==N-1 and n==0):
      continue
    temp = 0
    if(n-1 >= 0):
      temp += numberCnts[l-1][n-1]
    if(n+1 <= 9):
      temp += numberCnts[l-1][n+1]
    numberCnts[l][n] = temp
    # print("   n->",n,"  numbCnts->",numberCnts[l][n])

print(sum(numberCnts[N-1])%1000000000)

    