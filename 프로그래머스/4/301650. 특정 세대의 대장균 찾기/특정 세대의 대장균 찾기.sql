-- 코드를 작성해주세요
-- 좌,우 테이블 둘 다 있을 경우 나오는 INNER JOIN 사용
-- ed3는 3세대 균들

SELECT ed3.ID FROM ECOLI_DATA ed3
INNER JOIN ECOLI_DATA ed2
    ON ed3.PARENT_ID = ed2.ID
INNER JOIN ECOLI_DATA ed1
    ON ed2.PARENT_ID = ed1.ID AND ed1.PARENT_ID IS NULL
ORDER BY ed3.ID ASC;
    