CREATE TABLE `scoring_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(2000) NOT NULL,
  `preview_url` varchar(2000) NOT NULL,
  `score` int(3) NOT NULL,
  PRIMARY KEY (`id`)
)