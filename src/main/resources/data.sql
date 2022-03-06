insert into reader(reader_id, reader_email, reader_password,reader_name, reader_address)
values
(1, 'admin@gmail.com', 'admin', 'Admin', 'Library'),
(2, 'hieutran@gmail.com', 'password', 'Trần Quốc Hiếu', 'HCM'),
(3, 'namtrinh@gmail.com', 'password', 'Trịnh Đình Nam', 'HCM'),
(4, 'sonnguyen@gmail.com', 'password', 'Nguyễn Võ Thanh Sơn', 'HCM');

insert into book(book_id, book_name, book_year_publishing, book_type, book_publishing, book_author)
values
(1, 'Tuyên Ngôn Độc Lập', 1945, 'Văn chính luận', 'NXB Thế Giới', 'Hồ Chí Minh'),
(2, 'Hai đứa trẻ', 1938, 'Văn học', 'NXB Văn Học', 'Thạch Lam'),
(3, 'Việt Bắc', 1954, 'Thơ', 'NXB Văn học', 'Tố Hữu');

insert into borrow_return_book (reader_id, book_id, br_date_borrow, br_date_return)
values
(2, 1, '2022-02-01','2022-02-07'),
(4, 1, '2022-02-01',null),
(2, 3, '2022-01-05','2022-01-12'),
(3, 3, '2022-02-28',null);

