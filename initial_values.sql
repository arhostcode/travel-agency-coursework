-- Добавление в таблицу tour_place
INSERT INTO tour_place (name, description, city)
VALUES
    ('Eiffel Tower', 'One of the most iconic landmarks in the world', 'Paris'),
    ('Grand Canyon', 'A steep-sided canyon carved by the Colorado River', 'Arizona'),
    ('Great Wall of China', 'A series of fortifications made of stone, brick, tamped earth, wood, and other materials', 'Beijing'),
    ('Sagrada Familia', 'A large unfinished Roman Catholic basilica in Barcelona', 'Barcelona'),
    ('Machu Picchu', 'An ancient Inca citadel located in the Andes Mountains in Peru', 'Cusco'),
    ('Taj Mahal', 'An ivory-white marble mausoleum on the south bank of the Yamuna River in the Indian city of Agra', 'Agra'),
    ('Sydney Opera House', 'A multi-venue performing arts centre at Sydney Harbour', 'Sydney'),
    ('Pyramids of Giza', 'An archaeological site on the Giza Plateau, on the outskirts of Cairo, Egypt', 'Cairo'),
    ('Statue of Liberty', 'A symbol of freedom and democracy, located in New York Harbor', 'New York'),
    ('Petra', 'A historical and archaeological city in southern Jordan', 'Maan');

-- Добавление в таблицу hotel
INSERT INTO hotel (name, description, city, location, image, price)
VALUES
    ('Paris Luxury Suites', 'A lavish hotel offering panoramic views of Paris and the Eiffel Tower', 'Paris', 'City Center', 'https://www.lux-mag.com/wp-content/uploads/2017/04/IMG_0728.jpg', 300.00),
    ('Grand Canyon Resort', 'A comfortable resort with breathtaking views of the Grand Canyon', 'Arizona', 'Canyon Rim', 'https://i.pinimg.com/originals/64/5e/6a/645e6a1fe21cdba08994be5ed787fea0.jpg', 250.00),
    ('Great Wall Inn', 'A cozy inn located near the Great Wall of China', 'Beijing', 'Wallside Village', 'https://avatars.mds.yandex.net/i?id=bbd7dcefa229ef6baf2d0ca6286efa7132a78ce3-12605172-images-thumbs&n=13', 150.00),
    ('Sagrada Hotel', 'A boutique hotel with views of the Sagrada Familia', 'Barcelona', 'Eixample District', 'https://www.multitour.ru/files/imgs/252456976e66c73bff7b8aa59e94dc16c3cfcb0e.jpeg', 200.00),
    ('Machu Picchu Lodge', 'A rustic lodge nestled in the Andes Mountains near Machu Picchu', 'Cusco', 'Mountain Valley', 'https://static.tildacdn.com/tild3433-3635-4265-a337-343735333537/2023-08-06_11-15-16_.jpg', 180.00),
    ('Taj Mahal Palace', 'An elegant palace hotel offering views of the Taj Mahal', 'Agra', 'Riverfront Boulevard', 'http://www.hulutrip.com/uploadfileen/hotel/agoda/177/1770bfda8920597bb7274146db58e0c4.jpg', 350.00),
    ('Sydney Harbor Hotel', 'A modern hotel overlooking the Sydney Opera House', 'Sydney', 'Harborfront Avenue', 'https://i.travelapi.com/hotels/1000000/30000/22800/22718/99ae6229_z.jpg', 280.00),
    ('Pyramid Resort', 'A unique resort with views of the Pyramids of Giza', 'Cairo', 'Desert Oasis', 'https://niood.com/wp-content/uploads/2021/03/le-meridien-pyramids-niood.jpg', 220.00),
    ('Liberty Plaza Hotel', 'A contemporary hotel conveniently located near the Statue of Liberty', 'New York', 'Downtown', 'https://cf.bstatic.com/xdata/images/hotel/max1024x768/151616172.jpg?k=b758fd59170c16aba3145db80bc3890cf3089402d59a63eaf72c73f0d3d4ec73&o=&hp=1', 320.00),
    ('Petra Oasis Resort', 'A luxurious resort offering relaxation near Petra', 'Maan', 'Desert Retreat', 'https://globe.by/wp-content/uploads/2021/12/GRAND-OASIS-4_1.jpg', 270.00);

-- Добавление в таблицу flight
INSERT INTO flight (name, from_place, to_place, start_date, finish_date, price)
VALUES
    ('F001','New York', 'Paris', '2024-05-01 08:00:00+00', '2024-05-01 13:00:00+00', 500.00),
    ('F005','Phoenix', 'Arizona', '2024-05-02 09:00:00+00', '2024-05-02 11:00:00+00', 150.00),
    ('F006','Beijing', 'Cusco', '2024-05-03 10:00:00+00', '2024-05-03 20:00:00+00', 800.00),
    ('F002','Barcelona', 'Sydney', '2024-05-04 11:00:00+00', '2024-05-04 23:00:00+00', 700.00),
    ('F001','Lima', 'Machu Picchu', '2024-05-05 12:00:00+00', '2024-05-05 14:00:00+00', 100.00),
    ('F001','Delhi', 'Agra', '2024-05-06 13:00:00+00', '2024-05-06 15:00:00+00', 80.00),
    ('F003','Auckland', 'Sydney', '2024-05-07 14:00:00+00', '2024-05-07 16:00:00+00', 120.00),
    ('F005','Cairo', 'New York', '2024-05-08 15:00:00+00', '2024-05-08 19:00:00+00', 600.00),
    ('F007','Paris', 'Dubai', '2024-05-09 16:00:00+00', '2024-05-09 18:00:00+00', 400.00),
    ('F009','Petra', 'Rome', '2024-05-10 17:00:00+00', '2024-05-10 19:00:00+00', 300.00);

-- Добавление в таблицу tour
INSERT INTO tour (name, description, start_date, finish_date, tour_place, hotel, flight)
VALUES
    ('Romantic Paris Getaway', 'Experience the romance of Paris with a view of the Eiffel Tower', '2024-05-01 08:00:00+00', '2024-05-05 08:00:00+00', (SELECT id FROM tour_place WHERE name = 'Eiffel Tower'), (SELECT id FROM hotel WHERE name = 'Paris Luxury Suites'), (SELECT id FROM flight WHERE from_place = 'New York' AND to_place = 'Paris')),
    ('Grand Canyon Adventure', 'Embark on an adventurous journey to explore the wonders of the Grand Canyon', '2024-05-02 09:00:00+00', '2024-05-06 09:00:00+00', (SELECT id FROM tour_place WHERE name = 'Grand Canyon'), (SELECT id FROM hotel WHERE name = 'Grand Canyon Resort'), (SELECT id FROM flight WHERE from_place = 'Phoenix' AND to_place = 'Arizona')),
    ('Great Wall Expedition', 'Discover the majestic Great Wall of China on this unforgettable expedition', '2024-05-03 10:00:00+00', '2024-05-07 10:00:00+00', (SELECT id FROM tour_place WHERE name = 'Great Wall of China'), (SELECT id FROM hotel WHERE name = 'Great Wall Inn'), (SELECT id FROM flight WHERE from_place = 'Beijing' AND to_place = 'Cusco')),
    ('Barcelona Cultural Retreat', 'Immerse yourself in the vibrant culture of Barcelona with a view of the Sagrada Familia', '2024-05-04 11:00:00+00', '2024-05-08 11:00:00+00', (SELECT id FROM tour_place WHERE name = 'Sagrada Familia'), (SELECT id FROM hotel WHERE name = 'Sagrada Hotel'), (SELECT id FROM flight WHERE from_place = 'Barcelona' AND to_place = 'Sydney')),
    ('Mystical Machu Picchu', 'Uncover the mysteries of Machu Picchu while staying in a cozy lodge in the Andes', '2024-05-05 12:00:00+00', '2024-05-09 12:00:00+00', (SELECT id FROM tour_place WHERE name = 'Machu Picchu'), (SELECT id FROM hotel WHERE name = 'Machu Picchu Lodge'), (SELECT id FROM flight WHERE from_place = 'Lima' AND to_place = 'Machu Picchu')),
    ('Taj Mahal Extravaganza', 'Indulge in luxury with a stay at the Taj Mahal Palace while admiring the majestic Taj Mahal', '2024-05-06 13:00:00+00', '2024-05-10 13:00:00+00', (SELECT id FROM tour_place WHERE name = 'Taj Mahal'), (SELECT id FROM hotel WHERE name = 'Taj Mahal Palace'), (SELECT id FROM flight WHERE from_place = 'Delhi' AND to_place = 'Agra')),
    ('Sydney Harbour Escape', 'Relax and unwind with stunning views of the Sydney Opera House', '2024-05-07 14:00:00+00', '2024-05-11 14:00:00+00', (SELECT id FROM tour_place WHERE name = 'Sydney Opera House'), (SELECT id FROM hotel WHERE name = 'Sydney Harbor Hotel'), (SELECT id FROM flight WHERE from_place = 'Auckland' AND to_place = 'Sydney')),
    ('Egyptian Odyssey', 'Journey through ancient history with a stay near the Pyramids of Giza', '2024-05-08 15:00:00+00', '2024-05-12 15:00:00+00', (SELECT id FROM tour_place WHERE name = 'Pyramids of Giza'), (SELECT id FROM hotel WHERE name = 'Pyramid Resort'), (SELECT id FROM flight WHERE from_place = 'Cairo' AND to_place = 'New York')),
    ('Paris-Dubai Adventure', 'Experience the excitement of two vibrant cities with a stopover in Dubai', '2024-05-09 16:00:00+00', '2024-05-13 16:00:00+00', (SELECT id FROM tour_place WHERE name = 'Statue of Liberty'), (SELECT id FROM hotel WHERE name = 'Liberty Plaza Hotel'), (SELECT id FROM flight WHERE from_place = 'Paris' AND to_place = 'Dubai')),
    ('Petra Exploration', 'Uncover the ancient mysteries of Petra on this fascinating exploration', '2024-05-10 17:00:00+00', '2024-05-14 17:00:00+00', (SELECT id FROM tour_place WHERE name = 'Petra'), (SELECT id FROM hotel WHERE name = 'Petra Oasis Resort'), (SELECT id FROM flight WHERE from_place = 'Petra' AND to_place = 'Rome'));
