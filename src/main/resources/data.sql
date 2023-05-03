INSERT INTO Category (title) VALUES ('MEDICINE');
INSERT INTO Category (title) VALUES ('SCIENCE');
INSERT INTO Category (title) VALUES ('COMEDY');
INSERT INTO Category (title) VALUES ('NOVEL');
INSERT INTO Category (title) VALUES ('FICTION');
INSERT INTO Category (title) VALUES ('MAGAZINE');

INSERT INTO Book (category_id, description, price, title, is_available) VALUES (1,'Human Anatomy', 13.5, 'Human Anatomy', true);
INSERT INTO Book (category_id, description, price, title, is_available) VALUES (1,'Cells', 3.5, 'Cells', true);
INSERT INTO Book (category_id, description, price, title, is_available) VALUES (1,'Introduction to Health', 8.5, 'Introduction to Health', true);
INSERT INTO Book (category_id, description, price, title, is_available) VALUES (1,'Dental', 35, 'Dental', true);
INSERT INTO Book (category_id, description, price, title, is_available) VALUES (1,'Bones', 44.5, 'Bones', false );
INSERT INTO Book (category_id, description, price, title, is_available) VALUES (2,'Astronomy', 66, 'Astronomy', true);
INSERT INTO Book (category_id, description, price, title, is_available) VALUES (4,'Karikoga Gumi remiseve', 12, ' Karikoga Gumi remiseve', true);
INSERT INTO Book (category_id, description, price, title, is_available) VALUES (5,'Star Wars', 35, 'Star Wars', true);
INSERT INTO Book (category_id, description, price, title, is_available) VALUES (5,'Marvel', 3.5, 'Marvel', true);
INSERT INTO Book (category_id, description, price, title, is_available) VALUES (6,'People Magazine', 2, 'People', true);