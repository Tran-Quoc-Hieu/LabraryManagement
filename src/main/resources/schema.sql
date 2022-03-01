create table if not exists publishing_company (
	pc_id INT IDENTITY(1, 1) primary key,
	pc_name varchar(255)
);

create table if not exists author (
	author_id INT IDENTITY(1, 1) primary key,
	author_name varchar(255),
	author_year_birthday INT,
	author_nationality varchar(255)
);

create table if not exists reader (
	reader_id INT IDENTITY(1, 1) primary key,
	reader_name varchar(255),
	reader_address varchar(255)
);

create table if not exists book (
	book_id INT IDENTITY(1, 1) primary key,
	book_name varchar(255),
	book_year_publishing INT,
	book_type varchar(255),
	book_pc_id INT,
	book_author_id INT,
	foreign key (book_pc_id) references publishing_company(pc_id),
	foreign key (book_author_id) references author(author_id)
);

create table if not exists borrow_return_book (
	br_reader_id INT,
	br_book_id INT,
	br_date_borrow DATE,
	br_date_return DATE,
	primary key(br_reader_id, br_book_id)
);


