-- 코드를 입력하세요
# 고양이와 개가 각각 몇 마리인지 조회
# 고양이를 개보다 먼저 조
SELECT a.ANIMAL_TYPE, COUNT(a.ANIMAL_TYPE) as count
FROM ANIMAL_INS a
GROUP BY a.ANIMAL_TYPE
ORDER BY a.ANIMAL_TYPE ASC;