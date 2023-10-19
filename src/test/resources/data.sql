insert into customers(customer_id,FirstName,LastName,Email,Phone) values 
('6fd4b523-c145-55ea-94d9-a67045029162', 'Lukas','dd','xx@gmail.com','+421459034456256'),
('5fd4c523-c178-55ea-94d9-a67045029162', 'Tomas','XX','dsa@gmail.com','+56455554544'),
('4fd4c523-c178-55ea-94d9-a67045029162', 'Peter','Hale','ds@Yahoo.com', '+585456425'),
('3fd4c523-c178-55ea-94d9-a67045029162', 'Jozef','Fuska','fuska@joyev.com', '+421456456456'),
('2fd4c523-c178-55ea-94d9-a67045029162', 'Marek','Hulka','hulka@azet.sk', '+421456456456');

insert into instructors(instructor_id,FirstName,LastName,Email,Phone, QualificationType, QualificationLevel) values 
('6fd4b523-c145-55ea-94d9-a67045029162', 'Lukas','dd','xx@gmail.com','+421459034456256', 'ski', 1),
('5fd4c523-c178-55ea-94d9-a67045029162', 'Tomas','XX','dsa@gmail.com','+56455554544', 'snowboard', 2),
('4fd4c523-c178-55ea-94d9-a67045029162', 'Peter','Hale','ds@Yahoo.com', '+585456425', 'ski', 3),
('3fd4c523-c178-55ea-94d9-a67045029162', 'Jozef','Fuska','fuska@joyev.com', '+421456456456', ' snowboard', 2),
('2fd4c523-c178-55ea-94d9-a67045029162', 'Marek','Hulka','hulka@azet.sk', '+421456456456', 'ski', 1);

INSERT INTO public.lessons (lessontype, lessonlevel, starttime, endtime, maxcapacity, price)
VALUES 
    ('Skiing', 1, '2023-10-18 09:00:00+00:00', '2023-10-18 11:00:00+00:00', 10, 49.99),
    ('Snowboarding', 2, '2023-10-18 10:30:00+00:00', '2023-10-18 12:30:00+00:00', 15, 59.99),
    ('Skiing', 3, '2023-10-18 11:00:00+00:00', '2023-10-18 13:00:00+00:00', 8, 69.99),
    ('Snowboarding', 1, '2023-10-18 12:30:00+00:00', '2023-10-18 14:30:00+00:00', 12, 55.99),
    ('Skiing', 2, '2023-10-18 14:00:00+00:00', '2023-10-18 16:00:00+00:00', 20, 64.99),
    ('Snowboarding', 3, '2023-10-18 15:30:00+00:00', '2023-10-18 17:30:00+00:00', 7, 75.99),
    ('Skiing', 1, '2023-10-18 16:00:00+00:00', '2023-10-18 18:00:00+00:00', 13, 47.99),
    ('Snowboarding', 2, '2023-10-18 17:30:00+00:00', '2023-10-18 19:30:00+00:00', 18, 58.99),
    ('Skiing', 3, '2023-10-18 18:00:00+00:00', '2023-10-18 20:00:00+00:00', 11, 68.99),
    ('Snowboarding', 1, '2023-10-18 19:30:00+00:00', '2023-10-18 21:30:00+00:00', 14, 53.99);

INSERT INTO public.lessons_instructors (instructor_id, lesson_id)
VALUES
    ('6fd4b523-c145-55ea-94d9-a67045029162', 1),
    ('5fd4c523-c178-55ea-94d9-a67045029162', 2),
    ('4fd4c523-c178-55ea-94d9-a67045029162', 3),
    ('3fd4c523-c178-55ea-94d9-a67045029162', 4),
    ('2fd4c523-c178-55ea-94d9-a67045029162', 5);