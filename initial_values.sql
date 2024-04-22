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
    ('Paris Luxury Suites', 'A lavish hotel offering panoramic views of Paris and the Eiffel Tower', 'Paris', 'City Center', 'paris_luxury_suites.jpg', 300.00),
    ('Grand Canyon Resort', 'A comfortable resort with breathtaking views of the Grand Canyon', 'Arizona', 'Canyon Rim', 'grand_canyon_resort.jpg', 250.00),
    ('Great Wall Inn', 'A cozy inn located near the Great Wall of China', 'Beijing', 'Wallside Village', 'great_wall_inn.jpg', 150.00),
    ('Sagrada Hotel', 'A boutique hotel with views of the Sagrada Familia', 'Barcelona', 'Eixample District', 'sagrada_hotel.jpg', 200.00),
    ('Machu Picchu Lodge', 'A rustic lodge nestled in the Andes Mountains near Machu Picchu', 'Cusco', 'Mountain Valley', 'machu_picchu_lodge.jpg', 180.00),
    ('Taj Mahal Palace', 'An elegant palace hotel offering views of the Taj Mahal', 'Agra', 'Riverfront Boulevard', 'taj_mahal_palace.jpg', 350.00),
    ('Sydney Harbor Hotel', 'A modern hotel overlooking the Sydney Opera House', 'Sydney', 'Harborfront Avenue', 'sydney_harbor_hotel.jpg', 280.00),
    ('Pyramid Resort', 'A unique resort with views of the Pyramids of Giza', 'Cairo', 'Desert Oasis', 'pyramid_resort.jpg', 220.00),
    ('Liberty Plaza Hotel', 'A contemporary hotel conveniently located near the Statue of Liberty', 'New York', 'Downtown', 'liberty_plaza_hotel.jpg', 320.00),
    ('Petra Oasis Resort', 'A luxurious resort offering relaxation near Petra', 'Maan', 'Desert Retreat', 'petra_oasis_resort.jpg', 270.00);

-- Добавление в таблицу flight
INSERT INTO flight (from_place, to_place, start_date, finish_date, price)
VALUES
    ('New York', 'Paris', '2024-05-01 08:00:00+00', '2024-05-01 13:00:00+00', 500.00),
    ('Phoenix', 'Arizona', '2024-05-02 09:00:00+00', '2024-05-02 11:00:00+00', 150.00),
    ('Beijing', 'Cusco', '2024-05-03 10:00:00+00', '2024-05-03 20:00:00+00', 800.00),
    ('Barcelona', 'Sydney', '2024-05-04 11:00:00+00', '2024-05-04 23:00:00+00', 700.00),
    ('Lima', 'Machu Picchu', '2024-05-05 12:00:00+00', '2024-05-05 14:00:00+00', 100.00),
    ('Delhi', 'Agra', '2024-05-06 13:00:00+00', '2024-05-06 15:00:00+00', 80.00),
    ('Auckland', 'Sydney', '2024-05-07 14:00:00+00', '2024-05-07 16:00:00+00', 120.00),
    ('Cairo', 'New York', '2024-05-08 15:00:00+00', '2024-05-08 19:00:00+00', 600.00),
    ('Paris', 'Dubai', '2024-05-09 16:00:00+00', '2024-05-09 18:00:00+00', 400.00),
    ('Petra', 'Rome', '2024-05-10 17:00:00+00', '2024-05-10 19:00:00+00', 300.00);

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
