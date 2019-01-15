-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.1.30-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para gameker
CREATE DATABASE IF NOT EXISTS `gameker` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `gameker`;

-- Copiando estrutura para tabela gameker.tb_game
CREATE TABLE IF NOT EXISTS `tb_game` (
  `id_game` int(11) NOT NULL AUTO_INCREMENT,
  `ds_nome` varchar(50) NOT NULL DEFAULT '0',
  `nr_nota` char(1) NOT NULL DEFAULT '0',
  `nr_status` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_game`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela gameker.tb_game: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_game` DISABLE KEYS */;
INSERT INTO `tb_game` (`id_game`, `ds_nome`, `nr_nota`, `nr_status`) VALUES
	(2, 'Crysis', '9', '1'),
	(8, 'Mirror\'s Edge 2', '6', '0');
/*!40000 ALTER TABLE `tb_game` ENABLE KEYS */;

-- Copiando estrutura para tabela gameker.tb_game_x_plataforma
CREATE TABLE IF NOT EXISTS `tb_game_x_plataforma` (
  `id_game` int(11) NOT NULL,
  `id_plataforma` int(11) NOT NULL,
  PRIMARY KEY (`id_game`,`id_plataforma`),
  KEY `id_plataforma` (`id_plataforma`),
  CONSTRAINT `tb_game_x_plataforma_ibfk_1` FOREIGN KEY (`id_game`) REFERENCES `tb_game` (`id_game`),
  CONSTRAINT `tb_game_x_plataforma_ibfk_2` FOREIGN KEY (`id_plataforma`) REFERENCES `tb_plataforma` (`id_plataforma`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela gameker.tb_game_x_plataforma: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_game_x_plataforma` DISABLE KEYS */;
INSERT INTO `tb_game_x_plataforma` (`id_game`, `id_plataforma`) VALUES
	(2, 1),
	(8, 1),
	(8, 2),
	(8, 3);
/*!40000 ALTER TABLE `tb_game_x_plataforma` ENABLE KEYS */;

-- Copiando estrutura para tabela gameker.tb_grupo
CREATE TABLE IF NOT EXISTS `tb_grupo` (
  `id_grupo` int(11) NOT NULL AUTO_INCREMENT,
  `ds_nome` varchar(50) NOT NULL,
  `ds_descricao` varchar(200) NOT NULL,
  PRIMARY KEY (`id_grupo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela gameker.tb_grupo: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_grupo` DISABLE KEYS */;
INSERT INTO `tb_grupo` (`id_grupo`, `ds_nome`, `ds_descricao`) VALUES
	(1, 'ADMINISTRADORES', 'Adminitrador'),
	(2, 'USUARIOS', 'Usuários Comum'),
	(3, 'BACKOFFICE', 'Backoffice - Cadastros');
/*!40000 ALTER TABLE `tb_grupo` ENABLE KEYS */;

-- Copiando estrutura para tabela gameker.tb_permissao
CREATE TABLE IF NOT EXISTS `tb_permissao` (
  `id_permissao` int(11) NOT NULL AUTO_INCREMENT,
  `ds_permissao` varchar(50) NOT NULL,
  `ds_descricao` varchar(200) NOT NULL,
  PRIMARY KEY (`id_permissao`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela gameker.tb_permissao: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_permissao` DISABLE KEYS */;
INSERT INTO `tb_permissao` (`id_permissao`, `ds_permissao`, `ds_descricao`) VALUES
	(1, 'ROLE_CADASTROUSUARIO', 'CADASTRO DE NOVOS USUÁRIOS'),
	(2, 'ROLE_CONSULTAUSUARIO', 'CONSULTA DE USUÁRIOS'),
	(3, 'ROLE_ADMIN', 'ADMINISTRAÇÂO COMPLETA');
/*!40000 ALTER TABLE `tb_permissao` ENABLE KEYS */;

-- Copiando estrutura para tabela gameker.tb_permissao_x_grupo
CREATE TABLE IF NOT EXISTS `tb_permissao_x_grupo` (
  `id_permissao` int(11) NOT NULL,
  `id_grupo` int(11) NOT NULL,
  PRIMARY KEY (`id_permissao`,`id_grupo`),
  KEY `FK_GRUP_1` (`id_grupo`),
  CONSTRAINT `FK_GRUP_1` FOREIGN KEY (`id_grupo`) REFERENCES `tb_grupo` (`id_grupo`),
  CONSTRAINT `FK_PERM_1` FOREIGN KEY (`id_permissao`) REFERENCES `tb_permissao` (`id_permissao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela gameker.tb_permissao_x_grupo: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_permissao_x_grupo` DISABLE KEYS */;
INSERT INTO `tb_permissao_x_grupo` (`id_permissao`, `id_grupo`) VALUES
	(1, 3),
	(2, 2),
	(3, 1);
/*!40000 ALTER TABLE `tb_permissao_x_grupo` ENABLE KEYS */;

-- Copiando estrutura para tabela gameker.tb_plataforma
CREATE TABLE IF NOT EXISTS `tb_plataforma` (
  `id_plataforma` int(11) NOT NULL AUTO_INCREMENT,
  `ds_nome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_plataforma`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela gameker.tb_plataforma: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_plataforma` DISABLE KEYS */;
INSERT INTO `tb_plataforma` (`id_plataforma`, `ds_nome`) VALUES
	(1, 'PC'),
	(2, 'PS4'),
	(3, 'Xbox One');
/*!40000 ALTER TABLE `tb_plataforma` ENABLE KEYS */;

-- Copiando estrutura para tabela gameker.tb_usuario
CREATE TABLE IF NOT EXISTS `tb_usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `ds_nome` varchar(60) NOT NULL,
  `ds_login` varchar(60) NOT NULL,
  `ds_senha` varchar(400) NOT NULL,
  `fl_ativo` char(1) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela gameker.tb_usuario: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` (`id_usuario`, `ds_nome`, `ds_login`, `ds_senha`, `fl_ativo`) VALUES
	(12, 'Gabriel', 'gabriel', '$2a$10$hxFw6MK3485ylN8IyerxuOavrybUft1jwqWDCFDfY3D9cAZpICQLq', '1'),
	(17, '123', '123', '', '1');
/*!40000 ALTER TABLE `tb_usuario` ENABLE KEYS */;

-- Copiando estrutura para tabela gameker.tb_usuario_x_grupo
CREATE TABLE IF NOT EXISTS `tb_usuario_x_grupo` (
  `id_usuario` int(11) NOT NULL,
  `id_grupo` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_grupo`),
  KEY `id_grupo` (`id_grupo`),
  CONSTRAINT `tb_usuario_x_grupo_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`),
  CONSTRAINT `tb_usuario_x_grupo_ibfk_2` FOREIGN KEY (`id_grupo`) REFERENCES `tb_grupo` (`id_grupo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela gameker.tb_usuario_x_grupo: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_usuario_x_grupo` DISABLE KEYS */;
INSERT INTO `tb_usuario_x_grupo` (`id_usuario`, `id_grupo`) VALUES
	(12, 1);
/*!40000 ALTER TABLE `tb_usuario_x_grupo` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
