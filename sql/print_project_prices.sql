
SELECT p.id_project, SUM(salary)*DATEDIFF(MONTH, start_date, finish_date) AS price
FROM project p
JOIN project_worker ON p.id_project = project_worker.project_id
JOIN worker ON project_worker.worker_id = worker.id_worker
GROUP BY p.id_project
ORDER BY price DESC;