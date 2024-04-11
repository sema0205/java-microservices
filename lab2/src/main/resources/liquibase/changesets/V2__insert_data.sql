INSERT INTO owner (name, birthdate)
VALUES ('John Doe', '1975-07-20 08:00:00+00'),
       ('Alice Johnson', '1981-03-10 08:00:00+00'),
       ('Robert Smith', '1990-11-15 08:00:00+00'),
       ('Patricia Brown', '1985-06-05 08:00:00+00'),
       ('Jennifer Garcia', '1992-12-25 08:00:00+00');

INSERT INTO cat (name, birthdate, breed, color)
VALUES ('Whiskers', '2018-03-01 08:00:00+00', 'SIAMESE', 'WHITE'),
       ('Shadow', '2019-07-15 08:00:00+00', 'MAINE_COON', 'BLACK'),
       ('Misty', '2017-06-09 08:00:00+00', 'PERSIAN', 'GRAY'),
       ('Oscar', '2020-01-25 08:00:00+00', 'SPHINX', 'RED'),
       ('Luna', '2018-11-05 08:00:00+00', 'MAINE_COON', 'BLUE');

INSERT INTO cat_owner_item (owner_id, cat_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5);

INSERT INTO cat_friend_item (cat_id, friend_cat_id)
VALUES (1, 2),
       (1, 3),
       (2, 3),
       (2, 4),
       (3, 5);
