# CREATE
INSERT INTO `users` (`email`, `password`)
VALUES ('admin@test.com', 'password'),
       ('admin2@test.com', 'password2'),
       ('user@test.com', 'user'),
       ('user2@test.com', 'user2');

INSERT INTO `admins` (`id`) SELECT `id` FROM `users` WHERE id in (1, 2);

# UPDATE
UPDATE `users` SET `password` = 'updatePassword2' WHERE id = 2;
UPDATE `users` SET `password` = 'updateUser2' WHERE id = 4;

# READ
SELECT * FROM `users`;
SELECT * FROM `admins`;

# DELETE
# DELETE FROM `users` WHERE id = 2;
# DELETE FROM `users` WHERE id = 4;

# READ
# SELECT * FROM `users`;
# SELECT * FROM `admins`;