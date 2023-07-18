
INSERT INTO worker
(id_worker, name, birthday, level, salary)
VALUES
	(1, 'Dima Taran', '1978-04-04', 'Senior', 3500),
	(2, 'Oleg Bobkov', '1999-03-08', 'Junior', 9000),
	(3, 'Aleks Karacun', '1984-11-08', 'Middle', 2500),
	(4, 'Misha Frol', '1993-12-02', 'Senior', 5500),
	(5, 'Horhe Valverde', '1996-03-10', 'Junior', 1000),
	(6, 'Cynthia Mager', '1994-06-07', 'Trainee', 600),
	(7, 'Hanz Zimmer', '1961-09-01', 'Middle', 1800),
	(8, 'Eddie Merphy', '1972-04-03', 'Senior', 4500),
	(9, 'Dan Balan', '1988-08-11', 'Trainee', 700),
	(10, 'Alik Karapetiyan', '1989-08-10', 'Junior', 1100);


INSERT INTO client (id_client, name)
VALUES
	(1, 'BSH'),
	(2, 'Delongi Group'),
	(3, 'Phillip Morris'),
	(4, 'Kaercher'),
	(5, 'BAT');

INSERT INTO project (id_project, client_id, start_date, finish_date)
VALUES
	(110, 1, '2018-01-01', '2023-12-31'),
	(120, 2, '2018-01-01', '2023-12-31'),
	(210, 3, '2019-01-01', '2023-12-31'),
	(220, 4, '2019-01-01', '2023-12-31'),
	(310, 5, '2020-01-01', '2023-12-31'),
	(320, 1, '2020-01-01', '2023-12-31'),
	(410, 2, '2021-01-01', '2023-12-31'),
	(420, 3, '2021-01-01', '2023-12-31'),
	(510, 4, '2022-01-01', '2023-12-31'),
	(520, 5, '2022-01-01', '2023-12-31');

INSERT INTO project_worker (project_id , worker_id)
VALUES
	(110, 1),
	(110, 2),
	(110, 3),
	(120, 4),
	(120, 5),
	(210, 6),
	(210, 7),
	(220, 8),
	(220, 9),
	(220, 10),
	(220, 1),
	(310, 1),
	(310, 2),
	(310, 3),
	(320, 4),
	(410, 5),
	(410, 6),
	(420, 7),
	(420, 8),
	(510, 9),
	(510, 10),
	(510, 1),
	(510, 2),
	(510, 3),
	(520, 4),
	(520, 5);
	(520, 6);

