--  사원별 성과금 정보

SELECT 
        g.EMP_NO,
        e.EMP_NAME,
        CASE
            WHEN AVG(g.SCORE) < 80
                THEN "C"
            WHEN AVG(g.SCORE) < 90
                THEN "B"
            WHEN AVG(g.SCORE) < 96
                THEN "A"
            ELSE "S"
        END AS "GRADE",
        CASE 
            WHEN AVG(g.SCORE) < 80
                THEN 0
            WHEN AVG(g.SCORE) < 90
                THEN e.SAL * 0.1
            WHEN AVG(g.SCORE) < 96
                THEN e.SAL * 0.15
            ELSE e.SAL * 0.2
        END AS "BONUS"
FROM HR_GRADE g
JOIN HR_EMPLOYEES e
    ON e.EMP_NO = g.EMP_NO
GROUP BY g.EMP_NO
ORDER BY e.EMP_NO ASC;