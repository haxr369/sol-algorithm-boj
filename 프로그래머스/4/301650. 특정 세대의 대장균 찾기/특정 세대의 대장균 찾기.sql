-- 코드를 작성해주세요
-- 좌,우 테이블 둘 다 있을 경우 나오는 INNER JOIN 사용
-- ed3는 3세대 균들

WITH RECURSIVE HierarchicalData AS (
    SELECT ID, PARENT_ID, 1 AS HIGH
    FROM ECOLI_DATA
    WHERE PARENT_ID IS NULL
    
    UNION ALL
    
    SELECT ed.ID, ed.PARENT_ID, (hd.HIGH+1) AS HIGH
    FROM ECOLI_DATA ed
    INNER JOIN HierarchicalData hd ON hd.ID = ed.PARENT_ID
)
SELECT h.ID
FROM HierarchicalData h
WHERE h.HIGH = 3
ORDER BY h.ID ASC;