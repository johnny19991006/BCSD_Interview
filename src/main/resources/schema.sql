# DROP DATABASE IF EXISTS `shortly`;
#
# CREATE DATABASE IF NOT EXISTS `shortly` DEFAULT CHARACTER SET utf8mb4;
# USE `shortly`;
#
# CREATE TABLE `users` (
#     `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
#     `email` VARCHAR(255) NOT NULL UNIQUE,
#     `password` VARCHAR(255) NOT NULL,
#     `role` INT NOT NULL,
#     `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
#     FOREIGN KEY (`role`) REFERENCES `role`(`role`)
# );
#
# CREATE TABLE `role` (
#     `id` INT PRIMARY KEY AUTO_INCREMENT,
#     `role` VARCHAR(128) NOT NULL
# );
#
# CREATE TABLE `short_url` (
#     `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
#     `short_url` VARCHAR(255) NOT NULL,
#     `origin_url` MEDIUMTEXT NOT NULL,
#     `cnt` INT NOT NULL DEFAULT 1
# );
#
# ALTER TABLE `short_url` AUTO_INCREMENT = 10000;
#
# CREATE TABLE `users_url` (
#     `user_id` BIGINT,
#     `url_id` BIGINT,
#     `description` VARCHAR(255),
#     `toggle` BOOLEAN DEFAULT TRUE,
#     `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
#     FOREIGN KEY (`user_id`) REFERENCES users(`id`) ON DELETE SET NULL,
#     FOREIGN KEY (`url_id`) REFERENCES short_url(`id`) ON DELETE CASCADE
# ); # TODO 탈퇴시 삭제 제외 처리 좀 더 생각해봐야할듯.Trigger 잘 쓰면 되지 않을까
#
# CREATE TABLE `notice` (
#     `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
#     `user_id` BIGINT NOT NULL,
#     `title` VARCHAR(255) NOT NULL,
#     `content` TEXT NOT NULL,
#     `origin_url` MEDIUMTEXT NOT NULL,
#     `description` VARCHAR(255),
#     `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
#     FOREIGN KEY (`user_id`) REFERENCES users(`id`) ON DELETE CASCADE
# );
