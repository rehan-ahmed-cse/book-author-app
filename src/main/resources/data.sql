-- =============================================
-- SAMPLE DATA: 10 Authors and 10 Books
-- =============================================

-- Insert 10 Authors
INSERT INTO authors (name, nationality, biography) VALUES
('J.K. Rowling', 'British', 'Author of the Harry Potter series, one of the best-selling book series in history.'),
('Stephen King', 'American', 'Master of horror fiction with over 60 novels published.'),
('Agatha Christie', 'British', 'Best-selling mystery novelist known for detective Hercule Poirot.'),
('George Orwell', 'British', 'Known for dystopian fiction and social criticism.'),
('Jane Austen', 'British', 'Romantic fiction novelist from the Regency era.'),
('Ernest Hemingway', 'American', 'Nobel Prize-winning author known for concise prose.'),
('F. Scott Fitzgerald', 'American', 'Jazz Age novelist famous for The Great Gatsby.'),
('Gabriel Garcia Marquez', 'Colombian', 'Nobel Prize-winning magical realist author.'),
('Haruki Murakami', 'Japanese', 'Contemporary fiction writer with surreal themes.'),
('Toni Morrison', 'American', 'Nobel Prize-winning novelist exploring African-American experience.');

-- Insert 10 Books (each linked to an author via author_id)
INSERT INTO books (title, genre, publication_year, isbn, description, author_id) VALUES
('Harry Potter and the Sorcerer''s Stone', 'Fantasy', 1997, '978-0747532699', 'First book in the Harry Potter series about a young wizard.', 1),
('The Shining', 'Horror', 1977, '978-0307743657', 'A family stays in a haunted hotel during the winter.', 2),
('Murder on the Orient Express', 'Mystery', 1934, '978-0062693662', 'Hercule Poirot solves a murder on a luxury train.', 3),
('1984', 'Dystopian', 1949, '978-0451524935', 'A totalitarian regime controls every aspect of life.', 4),
('Pride and Prejudice', 'Romance', 1813, '978-0141439518', 'A classic tale of love and social standing.', 5),
('The Old Man and the Sea', 'Fiction', 1952, '978-0684801223', 'Story of an aging fisherman and his epic battle.', 6),
('The Great Gatsby', 'Fiction', 1925, '978-0743273565', 'Critique of the American Dream in the Jazz Age.', 7),
('One Hundred Years of Solitude', 'Magical Realism', 1967, '978-0060883287', 'Multi-generational family saga in mythical Macondo.', 8),
('Norwegian Wood', 'Fiction', 1987, '978-0099448822', 'Coming of age story set in 1960s Tokyo.', 9),
('Beloved', 'Historical Fiction', 1987, '978-1400033416', 'Post-Civil War story of a haunted former slave.', 10);
