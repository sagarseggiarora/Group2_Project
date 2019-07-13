create table login (
	Emp_id INT AUTO_INCREMENT PRIMARY KEY,
	Emp_Fname VARCHAR(50),
	Emp_Lname VARCHAR(50),
	Username VARCHAR(50),
	Password VARCHAR(200)
);
insert into login (Emp_id, Emp_Fname, Emp_Lname, Username, Password) SELECT '1', 'TestFName', 'TestLName', 'Test', SHA2('test', 512);
insert into login (Emp_id, Emp_Fname, Emp_Lname, Username, Password) SELECT '2', 'Sagar', 'Arora', 'Sagar', SHA2('sagar123', 512);
insert into login (Emp_id, Emp_Fname, Emp_Lname, Username, Password) SELECT '3', 'Parth', 'Khaneja', 'Parth', SHA2('parth123', 512);
insert into login (Emp_id, Emp_Fname, Emp_Lname, Username, Password) SELECT '4', 'Dashmeet', 'Singh', 'Dashmeet', SHA2('Dash123', 512);
