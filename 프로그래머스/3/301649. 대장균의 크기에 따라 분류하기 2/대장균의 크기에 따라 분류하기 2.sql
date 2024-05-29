-- 코드를 작성해주세요

SELECT e.ID, case
                WHEN e.per_rank <= 0.25
                    THEN "CRITICAL"
                WHEN e.per_rank <= 0.5
                    THEN "HIGH"
                WHEN e.per_rank <= 0.75
                    THEN "MEDIUM"
                ELSE "LOW"
            END AS COLONY_NAME
FROM (SELECT ed.ID, PERCENT_RANK() OVER (ORDER BY ed.SIZE_OF_COLONY DESC) as per_rank
        FROM ECOLI_DATA ed) e
ORDER BY e.ID ASC;