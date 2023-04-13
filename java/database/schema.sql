BEGIN TRANSACTION;
DROP TABLE IF EXISTS users;
CREATE TABLE users (
    user_id SERIAL,
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    role varchar(50) NOT NULL,
    CONSTRAINT PK_user PRIMARY KEY (user_id)
    rating int,
    email_address varchar(50) NOT NULL,
    registered_date TIMESTAMP NOT NULL,
    location_id int NOT NULL,
    user_img varchar(150) NOT NULL,
    role varchar(50) NOT NULL,
    CONSTRAINT chk_rating CHECK (rating >= 1 AND rating <= 5)
);
COMMIT TRANSACTION;