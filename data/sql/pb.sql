-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        11.8.2-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- pb 데이터베이스 구조 내보내기
DROP DATABASE IF EXISTS `pb`;
CREATE DATABASE IF NOT EXISTS `pb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;
USE `pb`;

-- 프로시저 pb.ON_FN_SPOT_ORD_MAIN 구조 내보내기
DROP PROCEDURE IF EXISTS `ON_FN_SPOT_ORD_MAIN`;
DELIMITER //
CREATE PROCEDURE `ON_FN_SPOT_ORD_MAIN`(
	IN `I_ORD_DT` VARCHAR(8),
	IN `I_ORD_MKT_CODE` VARCHAR(2),
	IN `I_ACNT_NO` VARCHAR(20),
	IN `I_ISU_NO` VARCHAR(12),
	IN `I_ORD_QTY` BIGINT,
	IN `I_ORD_PRC` DECIMAL(20,6),
	IN `I_BNS_TP` VARCHAR(2),
	IN `I_ORD_TIME` VARCHAR(9),
	OUT `O_ORD_NO` BIGINT,
	OUT `O_SQL_CODE` BIGINT,
	OUT `O_ERR_MSG_CODE` VARCHAR(4)
)
    COMMENT '현물주문'
BEGIN
	DECLARE V_ORD_NO BIGINT;
	
	SET V_ORD_NO       := NEXT VALUE FOR SEQ_ON_TBL_SPOT_ORD;
	
	INSERT INTO PB.ON_TBL_SPOT_ORD (  ORD_DT,   ORD_NO,   ORD_MKT_CODE,   ACNT_NO,   ISU_NO,   ORD_QTY,   ORD_PRC,   BNS_TP,   ORD_TIME)
	VALUES                         (I_ORD_DT, V_ORD_NO, I_ORD_MKT_CODE, I_ACNT_NO, I_ISU_NO, I_ORD_QTY, I_ORD_PRC, I_BNS_TP, I_ORD_TIME);
	
	SET O_ORD_NO       := V_ORD_NO;
	SET O_SQL_CODE     := 0;
	SET O_ERR_MSG_CODE := '0000';
	
END//
DELIMITER ;

-- 테이블 pb.on_tbl_spot_ord 구조 내보내기
DROP TABLE IF EXISTS `on_tbl_spot_ord`;
CREATE TABLE IF NOT EXISTS `on_tbl_spot_ord` (
  `ORD_DT` varchar(8) NOT NULL,
  `ORD_NO` bigint(20) NOT NULL,
  `ORD_MKT_CODE` varchar(2) DEFAULT NULL,
  `ACNT_NO` varchar(20) DEFAULT NULL,
  `ISU_NO` varchar(12) DEFAULT NULL,
  `ORD_QTY` bigint(20) DEFAULT NULL,
  `ORD_PRC` decimal(20,6) DEFAULT NULL,
  `ORD_TIME` varchar(9) DEFAULT NULL,
  `BNS_TP` varchar(1) DEFAULT NULL,
  `EXEC_QTY` bigint(20) DEFAULT NULL,
  `EXEC_AMT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ORD_DT`,`ORD_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- 테이블 데이터 pb.on_tbl_spot_ord:~29 rows (대략적) 내보내기
INSERT INTO `on_tbl_spot_ord` (`ORD_DT`, `ORD_NO`, `ORD_MKT_CODE`, `ACNT_NO`, `ISU_NO`, `ORD_QTY`, `ORD_PRC`, `ORD_TIME`, `BNS_TP`, `EXEC_QTY`, `EXEC_AMT`) VALUES
	('20010101', 2, '01', '032240222331', 'APPL', 0, 0.000000, NULL, NULL, 0, 0),
	('20010101', 3, '02', '032240222332', 'APPL', 0, 0.000000, NULL, NULL, 0, 0),
	('20010101', 4, '01', '032240222333', 'APPL', 0, 0.000000, NULL, NULL, 0, 0),
	('20010101', 5, '02', '032240222331', 'APPL', 0, 0.000000, NULL, NULL, 0, 0),
	('20010101', 6, '01', '032240222332', 'APPL', 0, 0.000000, NULL, NULL, 0, 0),
	('20010101', 2006, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('20121212', 8, '02', '032240222333', 'APPL', 0, 0.000000, NULL, NULL, 0, 0),
	('20121212', 9, '01', '032240222331', 'APPL', 0, 0.000000, NULL, NULL, 0, 0),
	('20121212', 10, '02', '032240222332', 'APPL', 0, 0.000000, NULL, NULL, 0, 0),
	('20121212', 11, '01', '032240222333', 'APPL', 0, 0.000000, NULL, NULL, 0, 0),
	('20121212', 12, '02', '032240222331', 'APPL', 0, 0.000000, NULL, NULL, 0, 0),
	('20260101', 2007, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('20260101', 2008, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('20260101', 2009, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('20260101', 2010, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('20260101', 2011, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('20260101', 2012, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('20260101', 2013, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('20260101', 2014, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('20260101', 2015, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('20260101', 2016, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('20260101', 2017, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('20260101', 2018, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('20260101', 2019, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('20270101', 3001, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('20270101', 3002, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	('30', 3003, '', '00000', '', 0, NULL, '', '', NULL, NULL),
	('30', 3004, '', '00000', '', 0, NULL, '', '', NULL, NULL),
	('dt', 1055, '0', 'a', 's', 0, 0.000000, 't', 'b', NULL, NULL);

-- 테이블 pb.seq_on_tbl_spot_ord 구조 내보내기
DROP TABLE IF EXISTS `seq_on_tbl_spot_ord`;
CREATE TABLE IF NOT EXISTS `seq_on_tbl_spot_ord` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;

-- 테이블 데이터 pb.seq_on_tbl_spot_ord:~1 rows (대략적) 내보내기
INSERT INTO `seq_on_tbl_spot_ord` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
	(4001, 1, 9223372036854775806, 1, 1, 1000, 0, 0);

-- 테이블 pb.tenant 구조 내보내기
DROP TABLE IF EXISTS `tenant`;
CREATE TABLE IF NOT EXISTS `tenant` (
  `no` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenant_id` varchar(20) NOT NULL,
  `tenant_name` varchar(50) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- 테이블 데이터 pb.tenant:~0 rows (대략적) 내보내기

-- 테이블 pb.test_001 구조 내보내기
DROP TABLE IF EXISTS `test_001`;
CREATE TABLE IF NOT EXISTS `test_001` (
  `no1` bigint(20) unsigned NOT NULL,
  `no2` bigint(20) unsigned NOT NULL,
  `no3` bigint(20) unsigned NOT NULL,
  `id1` varchar(120) NOT NULL,
  `id2` varchar(120) NOT NULL,
  `id3` varchar(120) NOT NULL,
  `name1` varchar(50) DEFAULT NULL,
  `name2` varchar(50) DEFAULT NULL,
  `name3` varchar(50) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`no1`,`no2`,`no3`,`id1`,`id2`,`id3`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- 테이블 데이터 pb.test_001:~64 rows (대략적) 내보내기
INSERT INTO `test_001` (`no1`, `no2`, `no3`, `id1`, `id2`, `id3`, `name1`, `name2`, `name3`, `created`) VALUES
	(0, 0, 0, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:38:33'),
	(0, 0, 14, 'Orange', 'purple', 'selani', NULL, NULL, NULL, NULL),
	(0, 0, 15, 'Orange', 'purple', 'selani', NULL, NULL, NULL, NULL),
	(0, 0, 17, 'Orange', 'purple', '코스콤', NULL, NULL, NULL, NULL),
	(0, 0, 18, 'Orange', 'purple', '코스콤', NULL, NULL, NULL, NULL),
	(0, 0, 1001, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 19:50:09'),
	(0, 0, 1002, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 19:50:09'),
	(0, 0, 1003, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 19:50:09'),
	(0, 0, 1010, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:31:41'),
	(0, 0, 1011, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:31:41'),
	(0, 0, 1012, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:31:41'),
	(0, 0, 1013, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1014, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1015, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1016, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1017, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1018, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1019, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1020, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1021, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1022, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1023, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1024, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1025, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1026, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1027, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1028, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1029, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1030, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1031, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1032, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1033, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1034, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1035, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1036, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1037, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1038, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1039, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1040, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1041, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1042, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1043, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1044, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1045, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1046, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1047, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1048, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1049, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1050, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1051, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1052, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1053, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(0, 0, 1054, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 20:37:48'),
	(1, 0, 0, 'string', 'string', 'string', 'string', 'string', 'string', NULL),
	(1, 0, 1004, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 19:50:09'),
	(1, 1, 1, 'string', 'string', 'string', 'string', 'string', 'string', NULL),
	(1, 2, 0, 'string', 'string', 'string', 'string', 'string', 'string', NULL),
	(1, 2, 19, 'Orange', 'purple', '코스콤', NULL, NULL, NULL, NULL),
	(1, 2, 20, 'Orange', 'purple', '코스콤', NULL, NULL, NULL, NULL),
	(2, 0, 1005, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 19:50:09'),
	(3, 0, 1006, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 19:50:09'),
	(4, 0, 1007, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 19:50:09'),
	(4, 0, 1008, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 19:50:09'),
	(4, 0, 1009, 'string', 'string', 'string', 'string', 'string', 'string', '2025-08-13 19:50:09');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
