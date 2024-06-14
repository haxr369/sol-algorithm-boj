-- 코드를 입력하세요
-- 우유(Milk)와 요거트(Yogurt)를 동시에 구입한 장바구니
SELECT c.CART_ID FROM CART_PRODUCTS c
WHERE c.NAME = "Milk" OR 
    c.NAME = "Yogurt"
GROUP BY c.CART_ID
HAVING COUNT(DISTINCT c.NAME) = 2
ORDER BY c.CART_ID