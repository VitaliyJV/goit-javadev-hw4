
SELECT id_project, DATEDIFF(MONTH, start_date, finish_date) AS month_count
FROM project
GROUP BY id_project
HAVING month_count IN (
	SELECT MAX(month_count)
	FROM (
		SELECT DATEDIFF(MONTH, start_date, finish_date) AS month_count
		FROM project
		GROUP BY id_project
	)
)