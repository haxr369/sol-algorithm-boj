-- 코드를 입력하세요
SELECT p.* FROM PLACES p
JOIN (
        SELECT ps.HOST_ID FROM PLACES ps
        GROUP BY ps.HOST_ID
        HAVING COUNT(ps.HOST_ID) >= 2
    ) AS sub
ON p.HOST_ID = sub.HOST_ID
ORDER BY p.ID ASC;