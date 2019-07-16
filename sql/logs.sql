create table logs (
	log_id INT AUTO_INCREMENT PRIMARY KEY,
	ticket_no INT(3),
	Comment VARCHAR(200),
	date VARCHAR(30),
	submit_by VARCHAR(15)
);