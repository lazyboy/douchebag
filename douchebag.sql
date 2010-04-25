CREATE DATABASE `douchebag` DEFAULT CHARSET=utf8;

CREATE TABLE  `path` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `p` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  `sin` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `desc` text NOT NULL,
  `start` int(11) NOT NULL default '-1',
  `end` int(11) NOT NULL default '-1',
  `length` int(11) NOT NULL default '-1',
  `rate` int(11) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  `droid` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `type` int(11) NOT NULL default '0',
  `name` text NOT NULL,
  `desc` text NOT NULL,
  `tags` text NOT NULL,
  `url` text NOT NULL,
  `localurl` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  `sin_path` (
  `fk_sin_id` int(10) unsigned NOT NULL,
  `fk_path_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`fk_sin_id`,`fk_path_id`),
  KEY `fk_sin_path_path_id` (`fk_path_id`),
  CONSTRAINT `fk_sin_path_sin_id` FOREIGN KEY (`fk_sin_id`) REFERENCES `sin` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_sin_path_path_id` FOREIGN KEY (`fk_path_id`) REFERENCES `path` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  `droid_sin` (
  `fk_droid_id` int(10) unsigned NOT NULL,
  `fk_sin_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`fk_droid_id`,`fk_sin_id`),
  KEY `fk_droid_sin_sin_id` (`fk_sin_id`),
  CONSTRAINT `fk_droid_sin_droid_id` FOREIGN KEY (`fk_droid_id`) REFERENCES `droid` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_droid_sin_sin_id` FOREIGN KEY (`fk_sin_id`) REFERENCES `sin` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
