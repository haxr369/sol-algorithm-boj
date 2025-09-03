"""
 N은 50보다 작은 자연수이다. 


둘째 줄부터 N개의 줄에 수열의 각 수가 주어진다. 
수열의 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.

모든 수는 1번 이하로 묶일 수 있다...

음수는 음수끼리 묶이는게 좋다.
큰수는 큰수끼리 묶이는게 좋다.

1. 정렬한다.
2. 왼쪽 포인터 l
3. 오른쪽 포인터 r

N개 수가 있을 때 for문 2번 돌리기
l은 0포함 음수범위 묶기     => 0 ~ cvt_idx-1
r은 양수 범위 이동해서 묶기  => cvt_idx ~ N-1

cvt_idx는 l범위에서 r범위로 변경되는 지점 => 0에서 양수로 변경되는 지점




"""

N = int(input())
arr = []

for _ in range(N):
  arr.append(int(input()))

arr.sort()

# print(arr)

cvt_idx = 0
for n in range(N):
  if(arr[n] > 0):
    cvt_idx = n
    break

# 처음 값이 l범위인데 cvt_idx가 업데이트 안하면 끝으로 이동시킴
if(arr[0] <= 0 and cvt_idx == 0):
  cvt_idx = N

# print("cvt_idx->",cvt_idx)
ans = 0

# l범위 묶기 0 ~ cvt_idx-1까지 순회
for l in range(0,cvt_idx,2):
  # l+1도 l범위 포함된 경우
  if(l+1 <= cvt_idx-1):
    tmp = arr[l]*arr[l+1]
    # print("1l: ",l," : +",tmp)
    ans += tmp
  else: # 마지막 하나 남은 경우
    # print("2l: ",l,": +",arr[l])
    ans += arr[l]


for r in range(N-1, cvt_idx-1, -2):
  # r+1도 r범위 포함된 경우
  if(r-1 >= cvt_idx):
    tmp = arr[r]*arr[r-1]
    if(arr[r-1] == 1): # 더 작은 값이 1인 경우 
      tmp = arr[r] + arr[r-1]
    # print("1r: ",r," r +",tmp)
    ans += tmp
  else: # 마지막 하나 남은 경우
    # print("2r: ",r," r +",tmp)
    ans += arr[r]
print(ans)