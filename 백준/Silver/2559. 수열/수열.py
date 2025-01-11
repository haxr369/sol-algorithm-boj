import queue

a,b = map(int, input().split())
numbs = list(map(int, input().split()))
qu = queue.Queue()
data = 0
ans = -100000000

""" 
0  1 2  3  4 5 6
0 12 1 -1 -2 4 5

0  0 0

7 -3 +1 = 5
"""

""" 큐 초기화 """
for i in range(b):
  data += numbs[i]
  qu.put(numbs[i])

ans = data

for i in range(b, a):
  front = qu.get()
  back = numbs[i]
  data = data - front + back
  qu.put(back)
  if data > ans:
    ans = data

print(ans)

