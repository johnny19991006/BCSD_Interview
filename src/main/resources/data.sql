# CREATE
INSERT INTO `users` (`email`, `password`, `role`, `created_at`)
VALUES ('admin@test.com', 'password', 1, NOW(6)),
       ('admin2@test.com', 'password2', 1, NOW(6)),
       ('user@test.com', 'user', 0, NOW(6)),
       ('user2@test.com', 'user2', 0, NOW(6));

# ADMIN UPDATE
# INSERT INTO `role` (`id`) SELECT `id` FROM `users` WHERE id in (1, 2);

SELECT * FROM `users`;