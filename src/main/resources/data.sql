insert into publishing_company(pc_id, pc_name)
values
(1, 'NXB Trẻ'),
(2, 'NXB Thế Giới'),
(3, 'NXB Văn Học');

insert into author(author_id, author_name, author_year_birthday, author_nationality)
values
(1, 'Hồ Chí Minh', 1890, 'Việt Nam'),
(2, 'Tô Hoài', 1920, 'Việt Nam'),
(3, 'Tố Hữu', 1920, 'Việt Nam');

insert into reader(reader_id, reader_name, reader_address)
values
(1, 'Trần Quốc An', 'HCM'),
(2, 'Trịnh Đình Nam', 'HCM'),
(3, 'Nguyễn Võ Thanh Sơn', 'HCM');

insert into book(book_id, book_name, book_year_publishing, book_type, book_pc_id, book_author_id)
values
(1, 'Tuyên Ngôn Đọc Lập', 1945, 'Văn chính luận', 2, 1),
(2, 'Hai đứa trẻ', 1938, 'Văn học', 3, 3),
(3, 'Việt Bắc', 1954, 'Thơ', 3, 3);

insert into borrow_return_book (br_reader_id, br_book_id, br_date_borrow, br_date_return)
values 
(1, 1, '2022-02-01','2022-02-07'),
(2, 3, '2022-01-05','2022-01-12'),
(3, 3, '2022-02-28',null);

