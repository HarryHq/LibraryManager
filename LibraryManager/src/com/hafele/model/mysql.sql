create table sys_admin(
	sys_number varchar(20) primary key,
	sys_name varchar(20),
	sys_password varchar(20),
	sys_sex varchar(5),
	sys_age varchar(5),
	sys_regdate date,
	sys_telNumber varchar(20),
	sys_idCard varchar(50)
);

create table system_books(
	bookid varchar(10),
	bookname varchar(20),
	bookstylenumber varchar(10),
	bookauthor varchar(20),
	bookpub varchar(50),
	bookpubdate date,
	bookindate date,
	bookprice double,
	isborrowed varchar(10)
);

create table book_style(
	bookStyleNumber varchar(20),	
	bookStyle varchar(20),	
	borrowDays varchar(10),	
	amerce varchar(10)	
);

create table system_readers(
	readerid varchar(10),
	reader_name varchar(20),
	reader_sex varchar(5),
	reader_age varchar(5),
	reader_occupation varchar(10),
	id_type smallint,
	id_number varchar(20),
	tel_number varchar(20),
	guarantee_deposit double,
	regdate date
);

create table borrow_record(
	bookId varchar(10),
	readerId varchar(10),
	borrowDate varchar(20),
	backDate varchar(20)
);
















