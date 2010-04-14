CREATE DATABASE `douchebag` DEFAULT CHARSET=utf8;

CREATE TABLE  `douchebag`.`path` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `p` varchar(3000) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
