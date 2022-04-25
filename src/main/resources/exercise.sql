use employees;


select *from titles;

select *from employees;

select *from dept_emp;

select *from departments;

select *from dept_manager;

select *from salaries;

select *from Production;


-- cau 1.1
select *
from employees e
where year(e.hire_date) >=1999
order by e.emp_no limit 10;

-- cau 1.2

select count(ee.emp_no) as So_luong
from employees ee
where ee.gender = 'F'
	and (YEAR(ee.birth_date) between 1950 and 1960)
    and ee.first_name like 'Mon%';



-- cau 1.3
select ee.first_name, ee.last_name, ee.hire_date, temp.sum_salary as salary_total
from employees ee, 
	(select sum(s.salary) as sum_salary
    from salaries s, titles t
    where s.emp_no = 10005
		and s.emp_no = t.emp_no
		and t.title = 'Staff'
	) as temp
where ee.emp_no = 10005;




/*
Tìm xem người quản lý có tên là Margareta Markovitch trong thời gian giữ chức quản 
lý thì đã quản lý bao nhiêu nhân viên. (Đếm cả những nhân viên mà người này quản lý
dù chỉ 1 ngày
*/
 
 -- cau 1.4
 select count(*) as so_nhan_vien
 from dept_emp dm, 
	 (select dm.dept_no as dept_no, dm.from_date as from_date ,dm.to_date as to_date
	 from employees ee , dept_manager dm
	 where ee.first_name = 'Margareta'
	 and ee.last_name = 'Markovitch'
	 and dm.emp_no = ee.emp_no) as temp
 where temp.dept_no = dm.dept_no
	 and datediff(dm.from_date,temp.from_date) >0
	 and datediff(dm.from_date,temp.to_date) <0;
     
-- cau 1.5
-- Tính lương trung bình phải trả cho từng nhóm title trong năm 1988
select tl.title, avg(sl.salary) as trung_binh_luong
from titles tl, salaries sl
where year(sl.from_date) = '1988'
	and year(tl.from_date) <= '1988'
	and year(tl.to_date) >= '1988'
	and sl.emp_no = tl.emp_no
group by tl.title;






-- cau 1.7
select d.dept_name, temp.name
from  departments d, 
	(select t.emp_no as name, de.dept_no
	from titles t, salaries s, dept_emp de
	where t.title = "Staff"
		and t.emp_no = s.emp_no
        and de.emp_no = t.emp_no
        and year(de.to_date) = 9999
   -- group by t.emp_no, de.dept_no
    order by s.salary desc
    limit 5) as temp
where temp.dept_no = d.dept_no;


-- Trong mỗi phòng ban, hãy tìm 5 người đang làm việc giữ chức vụ staff có lương cao 
-- nhất.
-- 1.7.2




 

-- cau 6
-- Liệt kê các nhân viên nam chỉ làm một trong 2 phòng d003 và d002 (không liệt kê nhân 
-- viên đã từng làm cả 2 phòng)

select ee.*
from employees ee, dept_emp de
where (de.dept_no = 'd003' or de.dept_no = 'd002')
	and ee.emp_no = de.emp_no
	and ee.emp_no not in 
        (select ee.emp_no
		from employees ee, dept_emp de
		where ee.emp_no = de.emp_no
			and de.dept_no = 'd003'
			and ee.emp_no in (select ee2.emp_no
		from employees ee2, dept_emp de2
		where ee2.emp_no = de2.emp_no
			and de2.dept_no = 'd002'));
     
-- 1.7.1
select d.dept_name, sum(s.salary) as tong_luong
from departments d, salaries s, dept_emp de
where de.emp_no = s.emp_no
	and de.dept_no = d.dept_no
    and datediff('1988-06-25',de.from_date) <= 0 
    and datediff('1989-06-25',de.to_date) >= 0
group by de.dept_no
having tong_luong > 3000000;
     


 -- 1.7.2
 
 select t2.dept_no ,t2.emp_no, t2. salary
 from 
	(select t.dept_no ,t.emp_no, t. salary,row_number() 
	over (partition by t.dept_no 
		  order by t.salary desc) as salary_rank
		  from 
			(select temp.dept_no , temp.emp_no, sl.salary
			from salaries sl, 
				(select de.dept_no as dept_no ,tl.emp_no as emp_no
				from dept_emp de
				left join titles tl on tl.emp_no = de.emp_no
				where tl.title = 'Staff'
				and year(de.to_date) = '9999') as temp
			where sl.emp_no = temp.emp_no
			and year(sl.to_date) = '9999') as t) as t2
where t2.salary_rank <= 5;


-- Tìm các nhân viên đã từng làm ở 2 phòng ban khác nhau, và phải giữ chức vụ khác 
-- nhau khi làm ở mỗi phòng ban

-- 1.8
select distinct ee.*
from employees ee, dept_emp de1, dept_emp de2, titles t1, titles t2
where t1.emp_no in(
		select de.emp_no
		from dept_emp de
		group by de.emp_no
		having count(de.dept_no) = 2
	)
    and t1.emp_no = t2.emp_no
    and t1.emp_no = de1.emp_no
    and t1.from_date = de1.from_date
    and t1.to_date = de1.to_date
    and t2.emp_no = de2.emp_no
    and t2.from_date = de2.from_date
    and t2.to_date = de2.to_date
    and de1.dept_no != de2.dept_no
    and t1.title != t2.title
    and t1.emp_no = ee.emp_no;


-- 2.1 Thăng chức cho nhân viên 10002 từ “Staff” lên “Senior Staff”. Lưu ý, phải dừng chức 
-- vụ hiện tại mới được chuyển chức vụ mới.

update titles tl
set titles.to_date = if(tl.to_date = '9999-01-01', curdate(), tl.to_date)
where titles.emp_no = 10002;

insert into titles(emp_no , title, from_date , to_date)
value (10002, 'Senior Staff' , curdate() , '9999-01-01');

select *from titles;

truncate production;

-- 2.3 Thêm phòng ban mới “Bigdata & ML” và bổ nhiệm nhân viên có ID = 10173 lên làm 
-- quản lý.
select *from departments; 
select *from dept_manager;

insert into departments(dept_no, dept_name)
value ('d010', 'BigData & ML');

insert into dept_manager(dept_no, emp_no, from_date , to_date)
value ('d010', 10173, curdate() , '9999-01-01');

-- 3. Viết một Stored Procedure với input là tên nhân viên. Cần trả lại 2 result - kết quả trong 
-- cùng một lần gọi store:
-- a. Kết quả 1: Lấy ra id, full name, giới tính, title (hay chức vụ), tên phòng ban
-- b. Kết quả 2: Tính tổng lương của từng người có tên đó trong khoảng thời gian từ lúc nhận 
-- lương đến thời điểm hiện tại

DELIMITER $$
DROP PROCEDURE IF EXISTS getEmpoylee $$
CREATE PROCEDURE getEmpoylee(IN name varchar(16))
BEGIN
    SELECT ee.emp_no , concat (ee.first_name,' ', ee.last_name) as full_name, ee.gender, tt.title, de.dept_no
    FROM employees ee
    left join titles tt on tt.emp_no = ee.emp_no
    left join dept_emp de on de.emp_no = ee.emp_no 
    WHERE ee.first_name = name;
    
    select ee.emp_no , concat (ee.first_name,' ', ee.last_name) as full_name,sum(sl.salary) as tong_luong
    from employees ee
    left join salaries sl on sl.emp_no = ee.emp_no
    where ee.first_name = name
    group by ee.emp_no, full_name;
END $$
-- DELIMITER; 

call getEmpoylee ('Georgi');


-- 4. Viết một Store Procedure để thuyên chuyển phòng ban cho một nhân viên nào đó, với chức 
-- vụ mới (không chuyển lên làm quản lý). Đồng thời trả lại một kết quả bao gồm:
-- ra id, full name, giới tính, title (hay chức vụ), tên phòng ban
-- update nhan vien co emp_id = 10005 tu Senior Staff phong d004 - Human Resources
-- thanh Staff o phong Big Data & ML 

DELIMITER $$
DROP PROCEDURE IF EXISTS updateEmpoylee $$
CREATE PROCEDURE updateEmpoylee(IN id int,
								IN new_dept_no CHAR(4),
                                IN new_title VARCHAR(50))
BEGIN
	update dept_emp de
    set de.to_date = if(de.to_date = '9999-01-01', curdate(), de.to_date)
    where de.emp_no = id;
    
    insert into dept_emp (emp_no , dept_no , from_date , to_date)
    value (id, new_dept_no, curdate(), '9999-01-01');
    
    update titles tl 
    set tl.to_date = if(tl.to_date = '9999-01-01', curdate(), tl.to_date)
    where tl.emp_no = id;
    
    insert into titles (emp_no , title , from_date, to_date)
    value (id, new_title, curdate(), '9999-01-01');
    
    select tl.emp_no as ma_nhan_vien, concat(ee.first_name, ee.last_name) as full_name, ee.gender as gioi_tinh, tl.title as chuc_vu, dp.dept_name as ten_phong_ban
    from titles tl, employees ee, dept_emp de, departments dp
    where tl.emp_no = ee.emp_no
    and dp.dept_no = de.dept_no
    and de.emp_no = tl.emp_no
    and tl.emp_no = id
    and tl.title = new_title
    and de.dept_no = new_dept_no;

END $$

-- Call updateEmpoylee(10005, 'd010', 'Staff');

delete from titles
where titles.emp_no = 10005
and titles.title = 'Staff';

delete from dept_emp de
where de.emp_no = 10005
and de.dept_no = 'd010';

select *from dept_emp
select *from titles

2.1
delete from titles tl
where tl.emp_no = 10002
and tl.title = 'Senior Staff'

update titles tl
set 
tl.to_date = '9999-01-01'
where tl.emp_no = 10002
and tl.title = 'Staff'

2.3
delete from dept_manager dm
where dm.dept_no = 'd010'
and dm.emp_no = 10173

delete from departments dp
where dp.dept_no = 'd010'

-- exercise 7 
/*
7. Tạo một webservice trả ra json là danh sách các nhân viên với tất cả thông tin của nhân viên 
trong bản employees. Các nhân viên có thể được lọc bằng một trong những param sau:
a. hire_date_from: thời gian bắt đầu làm việc lớn hơn mức này
b. salary: mức lương hàng năm hiện tại lớn hơn mức này
c. dept_no: làm ở phòng ban này
d. title: chức vụ hiện tại
*/

-- a.
DELIMITER $$
DROP PROCEDURE IF EXISTS filter_hiredate $$
CREATE PROCEDURE filter_hiredate (IN hire_date_from date)
BEGIN
	select *from employees ee    
    where datediff(hire_date_from, ee.hire_date) < 0;
END $$

call filter_hiredate('1987-01-01');

-- b
DELIMITER $$
DROP PROCEDURE IF EXISTS filter_salary $$
CREATE PROCEDURE filter_salary (IN salary int)
BEGIN
	select *from employees ee    
    left join salaries sl on sl.emp_no = ee.emp_no
    where sl.salary > salary;
END $$

call filter_salary(123243)

-- c
DELIMITER $$
DROP PROCEDURE IF EXISTS filter_deptNo $$
CREATE PROCEDURE filter_deptNo(IN dept_no varchar(4))
BEGIN
	select *
    from employees ee
    left join dept_emp de on de.emp_no = ee.emp_no
    where de.dept_no = dept_no;
END $$
 
 call filter_deptNo ('d007');
 
 
 -- d
 DELIMITER $$
DROP PROCEDURE IF EXISTS filter_title $$
CREATE PROCEDURE filter_title(IN title varchar(50))
BEGIN
	select * 
    from employees ee
    left join titles tl on tl.emp_no = ee.emp_no
    where tl.title = title
    and year(tl.to_date) = 9999;
END $$
 
 call filter_title ('Staff');
 
 
 -- 9.
  DELIMITER $$
DROP PROCEDURE IF EXISTS insert_employees $$
CREATE PROCEDURE insert_employees(IN dateOfBir date,
								  IN fullName varchar(100),
                                  IN gender char(4),
                                  IN hire_date date,
                                  IN title varchar(30),
                                  IN from_date date,
                                  IN to_date date,
                                  IN salary int)
BEGIN
	declare first_name varchar(50);
	declare	last_name varchar(50);
    declare emp_no int;
    
    SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(fullName, ' ', 1), ' ', -1) into first_name;
    SELECT SUBSTRING_INDEX(fullName,' ', -1) into last_name;
    SELECT (max(employees.emp_no)+1) from employees into emp_no;
    
    insert into employees(emp_no, birth_date, first_name, last_name, gender, hire_date)
    value(emp_no,dateOfBir, first_name, last_name, gender, hire_date);
    
    insert into titles(emp_no, title, from_date, to_date)
    value (emp_no, title, from_date, to_date );
    
    insert into salaries (emp_no, salary,from_date, to_date)
    value (emp_no, salary, from_date, to_date);
    
END $$

call insert_employees('2001-04-17', 'Huan Dinh', 'M', '2018-01-01', 'Staff', '2018-01-01', '9999-01-01',56000);

select *from employees
where emp_no = 499999

delete from employees
where employees.emp_no = '500000'


 
 
 
 










		
		
