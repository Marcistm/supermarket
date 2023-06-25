/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.37-log : Database - market
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`market` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `market`;

/*Table structure for table `tbl_bill` */

DROP TABLE IF EXISTS `tbl_bill`;

CREATE TABLE `tbl_bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `billCode` varchar(255) NOT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `productDesc` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_bill` */

insert  into `tbl_bill`(`id`,`billCode`,`productName`,`productDesc`,`price`) values (1,'BILL2016_001','洗发水、护发素','日用品-洗发、护发',25000),(2,'BILL2016_002','香皂、肥皂、药皂','日用品-皂类',10000),(3,'BILL2016_003','大豆油噢','食品-食用油',100.2),(4,'BILL2016_004','橄榄油噢','食品-进口食用油',9800),(5,'BILL2016_005','橄榄油','日用品-厨房清洁',7000);

/*Table structure for table `tbl_perms` */

DROP TABLE IF EXISTS `tbl_perms`;

CREATE TABLE `tbl_perms` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_perms` */

insert  into `tbl_perms`(`id`,`name`,`url`) values (1,'user:*:*',NULL),(2,'order:*:*',NULL),(3,'user:add:*',NULL);

/*Table structure for table `tbl_provider` */

DROP TABLE IF EXISTS `tbl_provider`;

CREATE TABLE `tbl_provider` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `proCode` varchar(255) DEFAULT NULL COMMENT '供应商编号',
  `proName` varchar(255) NOT NULL COMMENT '供应山姓名',
  `proDesc` varchar(255) DEFAULT NULL COMMENT '供应商描述',
  `proAddress` varchar(255) DEFAULT NULL COMMENT '供应商地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_provider` */

insert  into `tbl_provider`(`id`,`proCode`,`proName`,`proDesc`,`proAddress`) values (1,'BJ_GYS001','北京三木堂商贸有限公司','长期合作伙伴，主营产品:茅台、五粮液、郎酒、酒鬼酒、泸州老窖、赖茅酒、法国红酒等','北京市丰台区育芳园北路'),(2,'GZ_GYS002','深圳市喜来客商贸有限公司四四四四','长期合作伙伴，主营产品：坚果炒货.果脯蜜饯.天然花茶.营养豆豆.特色美食.进口食品.海味零食.肉脯肉','广东省深圳市福龙工业区B2栋3楼西'),(3,'BJ_GUss','北京i夏季赛公司','降低我剑帝哦亲','单位i记得就'),(5,'BJ_jidwj ','kwkd ','odojwo','ojwdow '),(6,'GZ_jdiwjdi','wkodj公司','我记得及','今晚i大家薇旗舰店'),(7,'GX_GYS001','优百商贸有限公司','长期合作伙伴，主营产品：日化产品','广西南宁市秀厢大道42-1号'),(8,'ZJ_GYS001','慈溪市广和绿色食品厂','长期合作伙伴，主营产品：豆瓣酱、黄豆酱、甜面酱，辣椒，大蒜等农产品','浙江省宁波市慈溪周巷小安村');

/*Table structure for table `tbl_role` */

DROP TABLE IF EXISTS `tbl_role`;

CREATE TABLE `tbl_role` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_role` */

insert  into `tbl_role`(`id`,`name`) values (1,'admin'),(2,'user'),(3,'product'),(4,'luser');

/*Table structure for table `tbl_role_perms` */

DROP TABLE IF EXISTS `tbl_role_perms`;

CREATE TABLE `tbl_role_perms` (
  `id` int(6) NOT NULL,
  `roleid` int(6) DEFAULT NULL,
  `permsid` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_role_perms` */

insert  into `tbl_role_perms`(`id`,`roleid`,`permsid`) values (1,1,1),(2,1,2),(3,2,1),(4,3,2),(5,1,3),(6,4,4);

/*Table structure for table `tbl_user` */

DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(50) NOT NULL COMMENT '用户姓名',
  `password` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐值',
  `gender` int(1) DEFAULT NULL COMMENT '性别',
  `birth` date DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_user` */

insert  into `tbl_user`(`id`,`name`,`password`,`salt`,`gender`,`birth`) values (1,'test100','test1pass','111',0,'2022-06-13'),(2,'test2222333','testpoasss','2222',1,'2022-06-03'),(4,'test311','123',NULL,1,'2022-06-09'),(6,'admin','2650939c269e0a2fd3782d1dfe9bb76c','93tC0SvY',0,'2022-06-15'),(7,'mage','99db6a3051e1af661c3880caa823a010','&nXTS4!C',1,'2022-06-09'),(8,'mage1','e92ebdc1e3ae7b531690e8a7536ac297','U(VGz74w',0,'2022-06-16'),(9,'mage2','fcb0b18f22d0cb7a84aa005ad666bf55','@bjaPon)',1,'2022-10-07'),(10,'mage3','eae73dcf6d0c0591e4ef529c81c96f13','4J!2ooiw',1,'2022-06-08'),(11,'test0',NULL,NULL,0,NULL),(15,'test0',NULL,NULL,1,NULL),(16,'system',NULL,NULL,1,'2000-07-04'),(17,'aa',NULL,NULL,1,'2000-07-04'),(18,'aa',NULL,NULL,1,'2000-07-04'),(19,'aa',NULL,NULL,1,'2000-07-04'),(20,'aa',NULL,NULL,1,'2000-07-04'),(21,'aa',NULL,NULL,1,'2000-07-04'),(22,'test6',NULL,NULL,1,'2022-06-15'),(23,'aaaa',NULL,NULL,1,'2022-06-15'),(24,'test4',NULL,NULL,1,'2022-06-15'),(25,'test333',NULL,NULL,0,'2022-06-09'),(27,'tes555',NULL,NULL,1,'2022-06-16'),(28,'test4',NULL,NULL,1,'2022-06-18'),(30,'test3','123',NULL,NULL,NULL),(31,'aaa',NULL,NULL,1,'2022-06-16'),(34,'json','814c35d26442d980f002ed1aca25c09a','JD0Sff@q',NULL,NULL),(35,'guest','e37c8cf3d5dc96d70b807e9f1682f6fa','MQj%X2Ni',NULL,NULL),(41,'aaaa','c6afa5bb2f33e2165ec3779ec61f3d05','2u*dTRlF',NULL,NULL);

/*Table structure for table `tbl_user_role` */

DROP TABLE IF EXISTS `tbl_user_role`;

CREATE TABLE `tbl_user_role` (
  `id` int(6) NOT NULL,
  `userid` int(6) DEFAULT NULL,
  `roleid` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_user_role` */

insert  into `tbl_user_role`(`id`,`userid`,`roleid`) values (1,6,2),(2,6,3),(3,34,1),(4,35,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
