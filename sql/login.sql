create table login (
	Emp_id INT AUTO_INCREMENT PRIMARY KEY,
	Emp_Fname VARCHAR(50),
	Emp_Lname VARCHAR(50),
	Username VARCHAR(50),
	Password VARCHAR(50)
);
insert into login (Emp_id, Emp_Fname, Emp_Lname, Username, Password) values (1, 'TestFName', 'TestLName', 'Test', 'Test');
insert into login (Emp_id, Emp_Fname, Emp_Lname, Username, Password) values (2, 'Sagar', 'Arora', 'Sagar', 'Sagar123');
insert into login (Emp_id, Emp_Fname, Emp_Lname, Username, Password) values (3, 'Parth', 'Khaneja', 'Parth', 'Parth123');
insert into login (Emp_id, Emp_Fname, Emp_Lname, Username, Password) values (4, 'Dashmeet', 'Singh', 'Dashmeet', 'Dash123');
