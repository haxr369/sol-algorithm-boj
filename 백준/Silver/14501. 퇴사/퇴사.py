"""
각각의 상담은 상담을 완료하는데 걸리는 기간 Ti와 
상담을 했을 때 받을 수 있는 금액 Pi로 이루어져 있다.


첫째 줄에 N (1 ≤ N ≤ 15)
둘째 줄부터 N개의 줄에 Ti와 Pi가 공백으로 구분되어서 주어지며, 
1일부터 N일까지 순서대로 주어진다. (1 ≤ Ti ≤ 5, 1 ≤ Pi ≤ 1,000)

i번째 상담을 하는 경우
  j번째 상담 O
          X

i번째 상담을 안 하는 경우
  j번째 상담 O
          X

모든 날짜 상담에 대해 O,X => 2^15 ~= 32000  전체 탐색해도 OK

"""

totalDays = int(input())

timeCosts = []
amounts = []

for i in range(totalDays):
  cost, amount = map(int, input().split())
  timeCosts.append(cost)
  amounts.append(amount)

maxAmount = -1

# day일자 상담 여부 결정해서 다음 상담 결정으로 넘기기
# acc는 day일자 전까지 얻은 이익
# day가 totalDays를 초과하면 skip, 같으면 update&return, 작으면 다음 예약 보러가기
def doCounsel(day, acc):
  global maxAmount
  # print("day->",day," acc->",acc)
  if(totalDays < day):
    return
  else:
    maxAmount = max(maxAmount, acc)
    if(totalDays == day): # 다음 상담은 무조건 못하기 때문에 stop
      return
    
    # d일차 상담을 스킵하는 경우
    doCounsel(day+1, acc)

    # d일차 상담을 들어가는 경우
    doCounsel(day+timeCosts[day], acc+amounts[day])

  return 

doCounsel(0,0)
print(maxAmount)