-- 코드를 작성해주세요

SELECT d.ID, d.EMAIL, d.FIRST_NAME, d.LAST_NAME FROM DEVELOPERS d
WHERE EXISTS (
        SELECT * FROM SKILLCODES sc
        WHERE sc.CATEGORY = "Front End"
            AND sc.CODE & d.SKILL_CODE > 0
    )
ORDER BY d.ID ASC;