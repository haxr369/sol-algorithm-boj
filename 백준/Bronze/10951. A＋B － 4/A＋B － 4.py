import sys
pp=[]
while True:
    p=list(map(int,sys.stdin.readline().split()))
    if p==[]:
        break
    else:
        pp.append(p[0]+p[1])    
for aaa in pp:
    print(aaa)