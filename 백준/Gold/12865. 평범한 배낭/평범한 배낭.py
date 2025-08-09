"""
 N개의 물건이 있다. 각 물건은 무게 W와 가치 V를 가지는데
 최대 K만큼의 무게만을 넣을 수 있는 배낭

 첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 
 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다. 
 
 두 번째 줄부터 N개의 줄에 거쳐 
 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.

 i번째 물건을 넣을까 말까

 2^100 => 시간초과...
 가방 무게가 w일 때 최대 가치를 maxValues[w]라고 할 때

 maxValues[K] = max((max(무게가 1인 물건들의 가치) + maxValues[K-1]), 
                    (max(무게가 2인 물건들의 가치) + maxValues[K-2]),
                    ...
                    (max(무게가 K-2인 물건들의 가치) + maxValues[2]),
                    (max(무게가 K-1인 물건들의 가치) + maxValues[1]))

                    
0번째 물건을 넣고 시작
maxValues[weights[0]] = values[0]

i번째는 maxValues의 1부터 maxValues[maxWeight]까지 순회하면서 
  값이 존재하면 복사 or 물건[i]를 적용한 값을 저장
  => i번째 물건을 배낭에 넣거나 말거나
maxValues에서 최대값이 정답

"""


totalCnt, maxWeight = map(int, input().split())

weights = []
values = []

for i in range(totalCnt):
  w,v = map(int, input().split())
  weights.append(w)
  values.append(v)

# w 무게의 최대 가치를 저장
maxValues = [0]*100004

ans = 0

for i in range(0, totalCnt):
  temp = [0]*100004
  # i번째 물건만 넣는 경우, 그런데 기존 값 보다 커야 넣을만하지
  if(weights[i] <= maxWeight):
    temp[weights[i]] = max(values[i], maxValues[weights[i]])
    ans = max(ans, temp[weights[i]])
  # 값이 존재하면 복사 and i번째 물건 적용
  for w in range(1, maxWeight+1):
    # 존재하면,
    if(maxValues[w] != 0):
      # 만약 이전에 신규 생성한 값이 기존 값 보다 더 클 수 있기 때문!
      temp[w] = max(maxValues[w], temp[w])
      ans = max(ans, temp[w])
      # 신규 위치가 최대 값을 넘어가지 않아야함
      if(w + weights[i] <= maxWeight):
        # 기존 값과 신규 생성 값을 비교
        temp[w + weights[i]] = max(maxValues[w + weights[i]], maxValues[w] + values[i])
        ans = max(ans, temp[w + weights[i]])
  # print("i->",i," ans->",ans)
  # print("     ",temp[:maxWeight+1])
  for w in range(maxWeight+1):
    maxValues[w] = temp[w]

print(ans)
