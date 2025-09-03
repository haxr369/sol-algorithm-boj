#1181
import sys
from collections import deque
n=int(input())
alpalist={}
for i in range(n):
    go=sys.stdin.readline().strip()
    alpalist.setdefault(go,len(go))
alpalist= sorted(alpalist.items(),key= lambda x: x[1])
#print(alpalist)
answer=[]
wordlong=0
for alpa in alpalist:
    if alpa[1]>wordlong:
      #  print("ddd",[alpa[0]])
        answer.append([alpa[0]])
        wordlong=alpa[1]
    else:
     #   print("aaa",alpa[0])
        answer[-1].append(alpa[0])

#print(answer)
for ans in answer:
    ans.sort()
    #print("ans",ans)
    for i in ans:
        print(i)