BEGIN TRANSACTION;

DROP TABLE IF EXISTS  pet_user, users, pet, location, review, play_date CASCADE;

CREATE TABLE users (
    user_id SERIAL,
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    role varchar(50) NOT NULL,
    CONSTRAINT PK_user PRIMARY KEY (user_id),
    rating int,
    email_address varchar(50) NOT NULL,
    registered_date TIMESTAMP NOT NULL,
    location_id int NOT NULL,
    user_img varchar(150),
    CONSTRAINT chk_rating CHECK (rating >= 1 AND rating <= 5)
);


CREATE TABLE pet (
    pet_id serial,
    user_id int NOT NULL,
    pet_name varchar(50) NOT NULL,
    animal varchar(20) NOT NULL,
    breed varchar(30),
    gender varchar(1) NOT NULL,
    rating int NOT NULL,
    size varchar(20) NOT NULL,
    personality varchar(20) NOT NULL,
    age int,
    spayed_neutered boolean NOT NULL,
    registered_date TIMESTAMP NOT NULL,
    description varchar(300),
    favorite_toy varchar(70),
    pet_img varchar(500) NOT NULL,
    CONSTRAINT PK_pet PRIMARY KEY (pet_id),
    CONSTRAINT chk_gender CHECK(gender = 'F' OR gender = 'M')
);

CREATE TABLE location (
    location_id serial,
    park_name varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    gps varchar(150),
    lat decimal(7,5) NOT NULL,
    long decimal(7,5) NOT NULL,
    location_type varchar(20) NOT NULL,
    off_leash boolean NOT NULL,
    site_description varchar(500),
    website varchar(200),
    CONSTRAINT PK_location PRIMARY KEY (location_id)
);

CREATE TABLE review (
    review_id serial,
    pet_id int NOT NULL,
    rating int NOT NULL,
    review varchar(300) NULL,
    CONSTRAINT PK_review PRIMARY KEY (review_id),
    CONSTRAINT chk_rating CHECK(rating > 0 and rating <= 5)
);

CREATE TABLE play_date (
    date_id serial,
    pet_id int NOT NULL,
    scheduled_date varchar(30) NOT NULL,
    location_id int NOT NULL,
    CONSTRAINT PK_date PRIMARY KEY (date_id)
);

-- insert data

-- user info
INSERT INTO users (username, rating, email_address, registered_date, location_id, user_img, password_hash, role)
VALUES ('admin', NULL, 'admin@woogle.com', '2022-12-06', 1, 'https://previews.agefotostock.com/previewimage/medibigoff/efccb1491d9e4d661dce0cd6f42f3fa2/y7s-2370167.jpg', '$2a$10$EPD8H.ZQusHEY3Io8.cZl..rCGLN1MeGqUu/3Z15ekvsVlheglnwW', 'ROLE_ADMIN'),
		('user', NULL, 'user@woogle.com', '2022-12-06', 1, 'nada', '$2a$10$EPD8H.ZQusHEY3Io8.cZl..rCGLN1MeGqUu/3Z15ekvsVlheglnwW', 'ROLE_USER'),
		('Kyle', NULL, 'kl@gmail.com', '2022-12-16', 1, 'https://ca.slack-edge.com/T0GNFLF6D-U03SUKCK1QB-7afd50cb1b57-512', '$2a$10$EPD8H.ZQusHEY3Io8.cZl..rCGLN1MeGqUu/3Z15ekvsVlheglnwW', 'ROLE_ADMIN');

-- pet info
INSERT INTO pet ( user_id, pet_name, animal, breed, gender, rating, size, personality, age, spayed_neutered, registered_date, description, favorite_toy, pet_img) 
VALUES (1, 'Alpha', 'Iguana', NULL, 'F', 3, 'Big', 'friendly', 13, false, '2022-12-06', 'Alpha is a really nice lady. She loves eating all the flies around our food scraps bucket. Proud of her lovely shade of green. Enjoys walks; harness trained!', 'bells', 'https://media.istockphoto.com/id/1303802386/vector/cartoon-iguana-isolated-on-white-background.jpg?s=1024x1024&w=is&k=20&c=jDxNYqK6VmeAYDWbTnfPR-AReRHzrY0Jrz1oSN05Hyg='),
        (2, 'Timbo', 'Dog', 'Poodle', 'M', 2, 'Small', 'Lovely', 10, true, '2022-12-13', 'Timbo is a lovely dog. He likes dog food and loves playing outside. Enjoys company and is a very good with kids. Timbo loves playing with his toys, going to the park', '', 'https://i.imgur.com/QLdDBky.jpeg'),
        (2, 'Frank', 'Cat', 'American Curl', 'M', 1, 'Medium', 'Outgoing', 4, false, '2022-12-11', 'Frank is a very independent cat. He is inquisitive, but not adventurous, and is easily upset by sudden changes of routine.', 'ball', 'https://i.imgur.com/KOfryAf.jpeg'),
        (1, 'Ruffy', 'Dog', 'Labrador', 'F', 2, 'Medium', 'Happy', 2, false, '2022-12-01', 'Ruffy loves playing at the park with other dogs.', 'Car tires', 'https://i.imgur.com/HjTTe8o.png'),
        (1, 'Whiskers', 'Cat', NULL, 'M', 1, 'Small', 'Bossy', 2, false, '2022-12-05', 'Whiskers likes cuddle up on the couch', 'Bird teaser', 'https://i.imgur.com/KKZ1KrM.jpeg'),
        (2, 'Angus', 'Dog', 'German Shepherd', 'M', 2, 'Big', 'Playful', 4, true, '2022-12-07', 'Angus enjoys playing fetch with a frisbee', 'Dog chew toys', 'https://i.imgur.com/gJUeQ8s.jpeg'),
        (1, 'Milo', 'Cat', NULL, 'M', 1, 'Small', 'Mellow', 5, true, '2022-12-11', 'Milo likes mischief and exploring new adventures', 'Tunnels', 'https://i.imgur.com/UQjFrDB.png'),
        (1, 'Bella', 'Dog', 'Yorkshire Terrier', 'F', 4, 'Medium', 'Funny', 2, false, '2022-12-10', 'Bella can fetch, rollover, sit, and easily learns other tricks', 'Stuffed animals', 'https://i.imgur.com/DEQfegU.jpeg'),
        (1, 'Fluffy', 'Cat', NULL, 'M', 1, 'Medium', 'Lovable', 3, false, '2022-12-03', 'Fluffy loves running around and can jump through hoops', 'Floppy fish', 'https://i.imgur.com/ZfCCUOt.jpeg'),
        (1, 'Rocky', 'Dog', 'Poodle', 'M', 1, 'Small', 'Mellow', 4, true, '2022-12-08', 'Rocky is very well behaved and likes other people, loves the outdoors.', 'Interactive toys', 'https://i.imgur.com/3EyzlIT.jpeg'),
        (2, 'CuJo', 'Dog', 'Dachshund', 'M', 5, 'Medium', '', 15, true, '12-13-2022', 'CuJo is a slugbug, always ready for a couch nap. However, lookout when he gets the zoomies; boy can this guy book it! Favorite snacks are baby carrots and string cheese.', 'rope twists', ''),
        (3, 'Aura', 'Dog', 'BullTerrier', 'F', 5, 'Medium', 'Playful', 10, true, '12-16-2022', ' Aura is the sweetest girl. She loves to play tag and lead her friends into the best grassy spots for worm-rolling. She is great with any breed of dog.', 'The stuffing inside of chewies', 'https://i.imgur.com/natttsb.jpeg'),
        (3, 'Bailey', 'Dog', 'Beagle', 'F', 5, 'Small', 'Hilarious', 5, true, '12-16-2022', 'Bailey will always make people laugh. She is shy at first around other animals but once she gets warmed up there is no stopping her. She Will run from her friends and roll in dirt until the sun goes down. ', 'Blankets', 'https://i.imgur.com/QXIlDaX.jpeg'),
        (3, 'Callie', 'Dog', 'Transylvanian Hound', 'F', 5, 'Medium', 'Relaxed', 12, true, '12-16-2022', 'Callie is a very relaxed dog. She loves playing and getting to know other dogs. Although she is normally chill, she will sometimes out of nowhere get her zoomies and make her friends chase her. Despite her age, there aren''t many dogs that can catch her.', 'Anything editable', 'https://i.imgur.com/9RCIsVC.jpeg');



-- review info
INSERT INTO review (pet_id, rating, review)
VALUES (1, 5,'I had such a pleasurable experience with Alpha! She and my garden snake Thumbelina had a great time playing in Edgewater Park!');

-- location info
INSERT INTO location (park_name, city, gps, lat, long, location_type, off_leash, site_description, website)
VALUES ('Edgewater Beach', 'Cleveland', 'https://goo.gl/maps/eGxB4S3Ni5TANdAY8', 41.488, -81.741, 'beach', true, 'The 147-acre Edgewater Park is the westernmost park in Cleveland Metroparks Lakefront Reservation. Edgewater Park features 9000 feet of shoreline, a dog beach, walking trails, picnic areas and grills and a rentable pavilion. Dogs love the park for off-leash swimming in the lake and chasing each other around.', 'https://www.clevelandmetroparks.com/parks/visit/parks/lakefront-reservation/edgewater-beach'),
       ('South Euclid Dog Park', 'South Euclid', 'https://goo.gl/maps/gMKtnZbUhehdWibQ7', 41.538, -81.533, 'park', true, 'This clean, well-maintained off-leash dog park is a great place for your pet to play. The park is fenced with water available during warmer months.', 'https://chparks.com/181/Dog-Park'),
       ('Taps & Tails', 'Cleveland', 'https://goo.gl/maps/aAGPBw6KKiPUnggC8', 41.480, -81.698, 'restaurant', false, 'Taps & Tails Dog Bar is dog-friendly bar and park in Cleveland, OH, with indoor and outdoor areas for dogs to play. Please see their website for a full list of rules before entering the park.', 'https://www.tapsandtailsdogbar.com/'),
       ('Canine Meadow Dog Park', 'Kirtland', 'https://goo.gl/maps/Ym5EKGXrkjGZ11DSA', 41.589, -81.323, 'park', true, 'Canine Meadow, Lake Metroparks new dog park, is a place for dogs to socialize, exercise and play off-leash in a controlled environment under the supervision of their owners. This park has a large dog area (2.57 acres), a swim area (1.84 acres) and a small dog area (1.02 acres).', 'https://www.lakemetroparks.com/parks-trails/canine-meadow-dog-park/'),
       ('Twinsburg Dog Park', 'Twinsburg', 'https://goo.gl/maps/byvDdiLT7wm2GtTu5', 41.319, -81.419, 'park', true, 'Your pet is welcome to play off-leash at this simple, fenced dog run in Twinsburg.', 'https://www.mytwinsburg.com/171/Parks-Trails'),
       ('Columbia Beach Park', 'Bay Village', 'https://goo.gl/maps/t2bm4VR7yTqe1Gsx7', 41.486, -81.902, 'beach', false, 'Located in Bay Village, OH, Columbia Beach is a great place to bring Fido to play in the water. There is a waterfall and a place for a stroll in the grass, too. Please leash and clean up after your pets.', 'https://www.tripadvisor.com/Attraction_Review-g50063-d19402249-Reviews-Columbia_Park-Bay_Village_Ohio.html'),
       ('Nickel Plate Beach', 'Huron', 'https://goo.gl/maps/ZPzs9S3MFjDFngTX9', 41.398, -82.543, 'beach', false, 'Nickel Plate Beach in Huron, OH is a popular dog-friendly area on the shores of Lake Erie. Leashed dogs are welcome on the beach, but are prohibited between the hours of 10am and 6pm from Memorial Day to Labor Day. Please leash and clean up after your pets.', 'https://www.cityofhuron.org/government/departments/parks-and-recreation/parks'),
       ('West Branch State Park', 'Ravenna', 'https://goo.gl/maps/M31Rxc7TdLFRu2ex7', 41.152, -81.146, 'park', true, 'West Branch Dog Park. This huge dog park is separated into a large and small section. Throughout this expansive park you will find a pond, shady areas and grassy spots. The dog park is found within West Branch State Park. Dog owners are reminded to clean up any mess and to keep pups leashed outside of the dog park. The park is open dawn until dark.', 'https://ohiodnr.gov/go-and-do/plan-a-visit/find-a-property/west-branch-state-park'),
       ('Cuyahoga Valley National Park', 'Brecksville', 'https://goo.gl/maps/LDbsoiZZmwRNzhjq7', 41.319, -81.617, 'trail', false, 'Cuyahoga Valley National Park allows leashed dogs on over 110 miles of hiking trails and 20 miles of the Towpath Trail. Pets are also welcome in the Stanford Campground. Pets must be kept on a leash no longer than six feet long at all times. Pets are not allowed in any buildings or on the Cuyahoga Valley Scenic Railroad Train. Bring extra water during the summer months as the area does get hot!', 'https://www.nps.gov/cuva/planyourvisit/pets.htm'),
       ('Horseshoe Lake Park', 'Shaker Heights', 'https://goo.gl/maps/66Wud6VFfNGKHvfj7', 41.483, -81.556, 'trail', false, 'Horseshoe Lake Park is a dog-friendly park in Shaker Heights, OH. Fido is welcome to join you for a stroll on the 1.4 mile trail over the dam. There are paved, gravel, and boardwalk trails at the center of the park that total 1.3 miles. During your walk you will pass the historical marker for Horseshoe Lake. There is a parking area, picnic shelters, and restrooms that are open seasonally.', 'https://www.shakeronline.com/facilities/facility/details/Horseshoe-Lake-6'),
       ('Findley State Park', 'Wellington', 'https://goo.gl/maps/ZnuX1uddiZz73oA6A', 41.134, -82.219, 'trail', false, 'Findley State Park in Wellington, OH is pet-friendly. This forested park is home to trails for hiking and mountain biking, a boating lake and campsites. Leashed dogs are welcome.', 'https://ohiodnr.gov/go-and-do/plan-a-visit/find-a-property/findley-state-park#:~:text=)%20647%2D2870.-,Camping,(866)%20644%2D6727.'),
       ('Rocky River Reservation', 'North Olmstead', 'https://goo.gl/maps/jmW7dER6KhzbC8Qo9', 41.409, -81.882, 'trail', false, 'Dogs are welcome to join their humans on the trails which wind through Rocky River Reservation. Natural features of the park include shale cliffs, meadows, wetlands, and floodplain forests along with wildlife and many bird species. Dogs must be leashed at all times, and you must clean up after your pets.', 'https://www.clevelandmetroparks.com/parks/visit/parks/rocky-river-reservation)');

INSERT INTO play_date (pet_id, scheduled_date, location_id)
VALUES (1, '2023-01-07', 1),
	   (2, '2023-01-07', 1),
	   (12, '2022-12-17', 2),
	   (6, '2022-12-17', 2);



-- foreign keys
ALTER TABLE users ADD CONSTRAINT FK_location_location_id FOREIGN KEY(location_id) REFERENCES location(location_id);
ALTER TABLE pet ADD CONSTRAINT FK_users_user_id FOREIGN KEY(user_id) REFERENCES users(user_id);
ALTER TABLE review ADD CONSTRAINT FK_pet_pet_id FOREIGN KEY(pet_id) REFERENCES pet(pet_id);
ALTER TABLE play_date ADD CONSTRAINT FK_pet_pet_id FOREIGN KEY(pet_id) REFERENCES pet(pet_id);
ALTER TABLE play_date ADD CONSTRAINT FK_location_location_id FOREIGN KEY(location_id) REFERENCES location(location_id);

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_capstone_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_owner;

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;

--rollback;
COMMIT;