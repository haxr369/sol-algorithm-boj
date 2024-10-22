
# 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
# 연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
# 마지막 도착 계단은 반드시 밟아야 한다.
# 1번을 가는 경우   -> 2번을 갈 수 있음
#     안가는경우    -> 2번을 갈 수 있음

stepCnt = int(input())
S = [0]
for i in range(stepCnt):
  s = int(input())
  S.append(s)

D = [0 for i in range(stepCnt+1)]
D[1] = S[1]
if stepCnt > 1 :
  D[2] = S[1] + S[2]

for i in range(3, stepCnt+1):
  tmp1 = D[i-2] + S[i]
  tmp2 = D[i-3] + S[i-1] + S[i]
  D[i] = tmp1 if tmp1 > tmp2 else tmp2

print(D[stepCnt])
