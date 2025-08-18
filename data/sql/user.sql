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


-- mysql 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `mysql` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;
USE `mysql`;

-- 뷰 mysql.user 구조 내보내기
-- VIEW 종속성 오류를 극복하기 위해 임시 테이블을 생성합니다.
CREATE TABLE `user` (
	`Host` CHAR(255) NOT NULL COLLATE 'utf8mb3_bin',
	`User` CHAR(128) NOT NULL COLLATE 'utf8mb3_bin',
	`Password` LONGTEXT NULL COLLATE 'utf8mb4_bin',
	`Select_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Insert_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Update_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Delete_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Create_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Drop_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Reload_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Shutdown_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Process_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`File_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Grant_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`References_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Index_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Alter_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Show_db_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Super_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Create_tmp_table_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Lock_tables_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Execute_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Repl_slave_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Repl_client_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Create_view_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Show_view_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Create_routine_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Alter_routine_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Create_user_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Event_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Trigger_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Create_tablespace_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`Delete_history_priv` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`ssl_type` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`ssl_cipher` LONGTEXT NOT NULL COLLATE 'utf8mb4_bin',
	`x509_issuer` LONGTEXT NOT NULL COLLATE 'utf8mb4_bin',
	`x509_subject` LONGTEXT NOT NULL COLLATE 'utf8mb4_bin',
	`max_questions` BIGINT(20) UNSIGNED NOT NULL,
	`max_updates` BIGINT(20) UNSIGNED NOT NULL,
	`max_connections` BIGINT(20) UNSIGNED NOT NULL,
	`max_user_connections` BIGINT(21) NOT NULL,
	`plugin` LONGTEXT NOT NULL COLLATE 'utf8mb4_bin',
	`authentication_string` LONGTEXT NOT NULL COLLATE 'utf8mb4_bin',
	`password_expired` VARCHAR(1) NOT NULL COLLATE 'latin1_swedish_ci',
	`is_role` VARCHAR(1) NULL COLLATE 'latin1_swedish_ci',
	`default_role` LONGTEXT NOT NULL COLLATE 'utf8mb4_bin',
	`max_statement_time` DECIMAL(12,6) NOT NULL
) ENGINE=MyISAM;

-- 임시 테이블을 제거하고 최종 VIEW 구조를 생성
DROP TABLE IF EXISTS `user`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `user` AS SELECT
  Host,
  User,
  IF(JSON_VALUE(Priv, '$.plugin') IN ('mysql_native_password', 'mysql_old_password'), IFNULL(JSON_VALUE(Priv, '$.authentication_string'), ''), '') AS Password,
  IF(JSON_VALUE(Priv, '$.access') &         1, 'Y', 'N') AS Select_priv,
  IF(JSON_VALUE(Priv, '$.access') &         2, 'Y', 'N') AS Insert_priv,
  IF(JSON_VALUE(Priv, '$.access') &         4, 'Y', 'N') AS Update_priv,
  IF(JSON_VALUE(Priv, '$.access') &         8, 'Y', 'N') AS Delete_priv,
  IF(JSON_VALUE(Priv, '$.access') &        16, 'Y', 'N') AS Create_priv,
  IF(JSON_VALUE(Priv, '$.access') &        32, 'Y', 'N') AS Drop_priv,
  IF(JSON_VALUE(Priv, '$.access') &        64, 'Y', 'N') AS Reload_priv,
  IF(JSON_VALUE(Priv, '$.access') &       128, 'Y', 'N') AS Shutdown_priv,
  IF(JSON_VALUE(Priv, '$.access') &       256, 'Y', 'N') AS Process_priv,
  IF(JSON_VALUE(Priv, '$.access') &       512, 'Y', 'N') AS File_priv,
  IF(JSON_VALUE(Priv, '$.access') &      1024, 'Y', 'N') AS Grant_priv,
  IF(JSON_VALUE(Priv, '$.access') &      2048, 'Y', 'N') AS References_priv,
  IF(JSON_VALUE(Priv, '$.access') &      4096, 'Y', 'N') AS Index_priv,
  IF(JSON_VALUE(Priv, '$.access') &      8192, 'Y', 'N') AS Alter_priv,
  IF(JSON_VALUE(Priv, '$.access') &     16384, 'Y', 'N') AS Show_db_priv,
  IF(JSON_VALUE(Priv, '$.access') &     32768, 'Y', 'N') AS Super_priv,
  IF(JSON_VALUE(Priv, '$.access') &     65536, 'Y', 'N') AS Create_tmp_table_priv,
  IF(JSON_VALUE(Priv, '$.access') &    131072, 'Y', 'N') AS Lock_tables_priv,
  IF(JSON_VALUE(Priv, '$.access') &    262144, 'Y', 'N') AS Execute_priv,
  IF(JSON_VALUE(Priv, '$.access') &    524288, 'Y', 'N') AS Repl_slave_priv,
  IF(JSON_VALUE(Priv, '$.access') &   1048576, 'Y', 'N') AS Repl_client_priv,
  IF(JSON_VALUE(Priv, '$.access') &   2097152, 'Y', 'N') AS Create_view_priv,
  IF(JSON_VALUE(Priv, '$.access') &   4194304, 'Y', 'N') AS Show_view_priv,
  IF(JSON_VALUE(Priv, '$.access') &   8388608, 'Y', 'N') AS Create_routine_priv,
  IF(JSON_VALUE(Priv, '$.access') &  16777216, 'Y', 'N') AS Alter_routine_priv,
  IF(JSON_VALUE(Priv, '$.access') &  33554432, 'Y', 'N') AS Create_user_priv,
  IF(JSON_VALUE(Priv, '$.access') &  67108864, 'Y', 'N') AS Event_priv,
  IF(JSON_VALUE(Priv, '$.access') & 134217728, 'Y', 'N') AS Trigger_priv,
  IF(JSON_VALUE(Priv, '$.access') & 268435456, 'Y', 'N') AS Create_tablespace_priv,
  IF(JSON_VALUE(Priv, '$.access') & 536870912, 'Y', 'N') AS Delete_history_priv,
  ELT(IFNULL(JSON_VALUE(Priv, '$.ssl_type'), 0) + 1, '', 'ANY','X509', 'SPECIFIED') AS ssl_type,
  IFNULL(JSON_VALUE(Priv, '$.ssl_cipher'), '') AS ssl_cipher,
  IFNULL(JSON_VALUE(Priv, '$.x509_issuer'), '') AS x509_issuer,
  IFNULL(JSON_VALUE(Priv, '$.x509_subject'), '') AS x509_subject,
  CAST(IFNULL(JSON_VALUE(Priv, '$.max_questions'), 0) AS UNSIGNED) AS max_questions,
  CAST(IFNULL(JSON_VALUE(Priv, '$.max_updates'), 0) AS UNSIGNED) AS max_updates,
  CAST(IFNULL(JSON_VALUE(Priv, '$.max_connections'), 0) AS UNSIGNED) AS max_connections,
  CAST(IFNULL(JSON_VALUE(Priv, '$.max_user_connections'), 0) AS SIGNED) AS max_user_connections,
  IFNULL(JSON_VALUE(Priv, '$.plugin'), '') AS plugin,
  IFNULL(JSON_VALUE(Priv, '$.authentication_string'), '') AS authentication_string,
  IF(IFNULL(JSON_VALUE(Priv, '$.password_last_changed'), 1) = 0, 'Y', 'N') AS password_expired,
  ELT(IFNULL(JSON_VALUE(Priv, '$.is_role'), 0) + 1, 'N', 'Y') AS is_role,
  IFNULL(JSON_VALUE(Priv, '$.default_role'), '') AS default_role,
  CAST(IFNULL(JSON_VALUE(Priv, '$.max_statement_time'), 0.0) AS DECIMAL(12,6)) AS max_statement_time
  FROM global_priv; 
;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
