def cutting(size,terget,now):
  if size==1:
    return now
  #print(size)
  if terget[1]<size//2:
    if terget[0]<size//2:
      now.append(0)
    else:
      terget[0]-=size//2
      now.append(2)
  else:
    terget[1]-=size//2
    if terget[0]<size//2:
      now.append(1)
    else:
      terget[0]-=size//2
      now.append(3)
  #print("now:",now)
  size= size//2 
  return cutting(size,terget,now)
n,r,c = map(int,input().split())    #배열은 2^n x 2^n
terget_form= cutting(2**n,[r,c],[])
terget_form.reverse()
#print(terget_form)

answer=0
for i in range(n-1,-1,-1):
  #print(i, terget_form[i])
  answer+= (4**i)*terget_form[i]
print(answer)