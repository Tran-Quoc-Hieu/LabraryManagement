create table if not exists reader (
	reader_id INT IDENTITY(1, 1) primary key,
	reader_email varchar(255),
	reader_password varchar(25),
	reader_name varchar(255),
	reader_address varchar(255)
);

create table if not exists book (
	book_id INT IDENTITY(1, 1) primary key,
	book_name varchar(255),
	book_year_publishing INT,
	book_type varchar(255),
	book_publishing varchar(255),
	book_author varchar(255)
);

create table if not exists borrow_return_book (
	reader_id INT,
	book_id INT,
	br_date_borrow DATE,
	br_date_return DATE,
	primary key (reader_id, book_id)
);


