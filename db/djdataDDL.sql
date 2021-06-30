/*
 Navicat Premium Data Transfer

 Source Server         : 103.48.171.42
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 103.48.171.42:3306
 Source Schema         : djdata

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 30/11/2019 18:14:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for competition_area
-- ----------------------------
DROP TABLE IF EXISTS `competition_area`;
CREATE TABLE `competition_area`  (
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT '赛区ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '赛区名称',
  `area` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '赛区区域',
  `game_id` bigint(32) NOT NULL COMMENT '游戏ID',
  `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '描述简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '赛区' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '赛事ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '赛事名称',
  `bonus` bigint(32) NOT NULL COMMENT '奖金',
  `bonus_type` bigint(32) NOT NULL COMMENT '奖金单位',
  `game_id` bigint(32) NOT NULL COMMENT '游戏ID',
  `league_id` bigint(32) NOT NULL COMMENT '联赛ID',
  `area_id` bigint(32) NOT NULL COMMENT '赛区ID',
  `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述简介',
  `start_time` bigint(32) NOT NULL COMMENT '开始时间',
  `end_time` bigint(32) NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75637354608937857 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_aov
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_aov`;
CREATE TABLE `finish_match_aov`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛主键',
  `series_id` bigint(20) NOT NULL COMMENT '系列赛ID',
  `match_id` bigint(20) NOT NULL COMMENT '比赛ID',
  `game_id` bigint(20) NOT NULL COMMENT '游戏ID',
  `game_no` int(10) NULL DEFAULT NULL COMMENT '游戏场次',
  `first_blood` int(10) NULL DEFAULT NULL COMMENT '一血',
  `first_dominator` int(10) NULL DEFAULT NULL COMMENT '主宰',
  `first_tower` int(10) NULL DEFAULT NULL COMMENT '一塔',
  `first_tyrant` int(10) NULL DEFAULT NULL COMMENT '暴君',
  `five_kill` int(10) NULL DEFAULT NULL COMMENT '五杀',
  `ten_kill` int(10) NULL DEFAULT NULL COMMENT '十杀',
  `win` int(10) NULL DEFAULT NULL COMMENT '获胜',
  `team_a_site` bigint(10) NULL DEFAULT NULL COMMENT '战队A的站点',
  `team_a_id` bigint(20) NULL DEFAULT NULL COMMENT '战队A的ID',
  `team_a_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '战队A的logo',
  `team_a_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '战队A的名称',
  `team_b_site` bigint(10) NULL DEFAULT NULL COMMENT '战队B的站点',
  `team_b_id` bigint(20) NULL DEFAULT NULL COMMENT '战队B的ID',
  `team_b_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '战队B的logo',
  `team_b_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '战队B的名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 753 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_csgo
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_csgo`;
CREATE TABLE `finish_match_csgo`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'csgo赛果主键',
  `series_id` bigint(20) NOT NULL COMMENT '系列赛ID',
  `match_id` bigint(20) NOT NULL COMMENT '比赛ID',
  `game_id` bigint(20) NOT NULL COMMENT '游戏ID',
  `course_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '赛事名称',
  `match_index` int(10) NULL DEFAULT NULL COMMENT '第几局',
  `start_time` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '开始时间',
  `finished` tinyint(1) NULL DEFAULT NULL COMMENT '已经结束',
  `map` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '使用地图',
  `left_score` int(10) NULL DEFAULT NULL COMMENT '左队得分',
  `right_score` int(10) NULL DEFAULT NULL COMMENT '右队得分',
  `flag_r1` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手枪局获胜队伍（左、右）',
  `flag_w5` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '领先5回合的队伍（左、右）',
  `flag_r16` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '第16局获胜队伍（左、右）',
  `left_round_history` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '左队回合历史',
  `right_round_history` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '右队回合历史',
  `overtime_left_score` int(10) NULL DEFAULT NULL COMMENT '加时赛左队得分',
  `overtime_right_score` int(10) NULL DEFAULT NULL COMMENT '加时赛右队得分',
  `left_team_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '左队名字',
  `left_team_tag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '左队标识',
  `left_team_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '左队logo',
  `left_team_id` bigint(20) NULL DEFAULT NULL COMMENT '左队ID',
  `left_team_score` bigint(20) NULL DEFAULT NULL COMMENT '左队得分',
  `right_team_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '右队名字',
  `right_team_tag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '右队标识',
  `right_team_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '右队logo',
  `right_team_id` bigint(20) NULL DEFAULT NULL COMMENT '右队ID',
  `right_team_score` bigint(20) NULL DEFAULT NULL COMMENT '右队得分',
  `first_half_left_score` bigint(20) NULL DEFAULT NULL COMMENT '上半场左队得分',
  `first_half_left_side` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上半场左队角色（ct反恐精英，t恐怖份子）',
  `first_half_right_score` bigint(20) NULL DEFAULT NULL COMMENT '上半场右队得分',
  `first_half_right_side` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上半场右队角色（ct反恐精英，t恐怖份子）',
  `second_half_left_score` bigint(20) NULL DEFAULT NULL COMMENT '下半场左队得分',
  `second_half_left_side` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '下半场左队角色（ct反恐精英，t恐怖份子）',
  `second_half_right_score` bigint(20) NULL DEFAULT NULL COMMENT '下半场右队得分',
  `second_half_right_side` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '下半场右队角色（ct反恐精英，t恐怖份子）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 791 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_csgo_left_players
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_csgo_left_players`;
CREATE TABLE `finish_match_csgo_left_players`  (
  `id0` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'csgo赛果左玩家主键',
  `id` bigint(20) NOT NULL COMMENT 'csgo赛果左玩家ID',
  `finish_match_csgo_id` bigint(20) NOT NULL COMMENT 'csgo赛果ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名字',
  `kills` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '击杀',
  `hs` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '爆头',
  `assists` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '助攻',
  `deaths` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '死亡',
  `kdratio` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '击杀/死亡比',
  `kddiff` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '击杀死亡差',
  `adr` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '全场平均生命值',
  `fkdiff` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '浮动比',
  `rating` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '比率',
  PRIMARY KEY (`id0`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3333 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_csgo_right_players
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_csgo_right_players`;
CREATE TABLE `finish_match_csgo_right_players`  (
  `id0` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'csgo赛果右玩家主键',
  `id` bigint(20) NOT NULL COMMENT 'csgo赛果右玩家ID',
  `finish_match_csgo_id` bigint(20) NOT NULL COMMENT 'csgo赛果ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名字',
  `kills` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '击杀',
  `hs` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '爆头',
  `assists` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '助攻',
  `deaths` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '死亡',
  `kdratio` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '击杀/死亡比',
  `kddiff` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '击杀死亡差',
  `adr` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '全场平均生命值',
  `fkdiff` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '浮动比',
  `rating` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '比率',
  PRIMARY KEY (`id0`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3332 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_dota2
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_dota2`;
CREATE TABLE `finish_match_dota2`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'csgo赛果主键',
  `series_id` bigint(20) NOT NULL COMMENT '系列赛ID',
  `match_id` bigint(20) NOT NULL COMMENT '比赛ID',
  `game_id` bigint(20) NOT NULL COMMENT '游戏ID',
  `course_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '赛事名称',
  `start_time` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '开始时间',
  `finished` tinyint(1) NULL DEFAULT NULL COMMENT '是否结束',
  `valid` tinyint(1) NULL DEFAULT NULL COMMENT '是否有效',
  `game_time` bigint(20) NULL DEFAULT NULL COMMENT '比赛总时长',
  `game_mode` bigint(20) NULL DEFAULT NULL COMMENT '游戏模式（0为正规比赛）',
  `first_pick` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '优先bp队伍',
  `first_blood_time` int(10) NULL DEFAULT NULL COMMENT '一血发生时间（秒）',
  `win_side` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '获胜队伍',
  `first_blood` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '一血队伍',
  `five_kills` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '首先五杀队伍',
  `ten_kills` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '首先十杀队伍',
  `ten_kills_score` int(10) NULL DEFAULT NULL COMMENT '十杀时领先人头数',
  `first_tower` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '一塔队伍',
  `radiant_gold_lead` int(10) NULL DEFAULT NULL COMMENT '天辉经济领先（可为负数）',
  `radiant_picks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '天辉pick英雄',
  `radiant_bans` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '天辉ban英雄',
  `dire_picks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '夜魇pick英雄',
  `dire_bans` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '夜魇ban英雄',
  `stream_delay_s` bigint(20) NULL DEFAULT NULL COMMENT '数据延迟（单位毫秒）',
  `roshan_respawn_timer` bigint(20) NULL DEFAULT NULL COMMENT '肉山复活倒计时（秒）',
  `radiant_score` bigint(20) NULL DEFAULT NULL COMMENT '天辉击杀数',
  `radiant_invalid_score` bigint(20) NULL DEFAULT NULL COMMENT '天辉无效击杀数（自杀）',
  `radiant_xp` bigint(64) NULL DEFAULT NULL COMMENT '天辉经验值',
  `radiant_gold` bigint(20) NULL DEFAULT NULL COMMENT '天辉经济值',
  `radiant_towers` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '天辉塔状态',
  `radiant_barracks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '天辉兵营状态',
  `radiant_last_hits` int(10) NULL DEFAULT NULL COMMENT '天辉补刀',
  `radiant_damage` int(10) NULL DEFAULT NULL COMMENT '天辉总伤害输出',
  `dire_score` bigint(255) NULL DEFAULT NULL COMMENT '夜魇击杀数',
  `dire_invalid_score` bigint(255) NULL DEFAULT NULL COMMENT '夜魇无效击杀数（自杀）',
  `dire_xp` bigint(255) NULL DEFAULT NULL COMMENT '夜魇经验值',
  `dire_gold` bigint(255) NULL DEFAULT NULL COMMENT '夜魇经济值',
  `dire_towers` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '夜魇塔状态',
  `dire_barracks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '夜魇兵营状态',
  `dire_last_hits` int(10) NULL DEFAULT NULL COMMENT '夜魇补刀',
  `dire_damage` int(10) NULL DEFAULT NULL COMMENT '夜魇总伤害输出',
  `radiant_team_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '天辉名称',
  `radiant_team_tag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '天辉编写',
  `radiant_team_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '天辉logo',
  `radiant_team_id` bigint(20) NULL DEFAULT NULL COMMENT '天辉队伍ID',
  `radiant_team_score` bigint(20) NULL DEFAULT NULL COMMENT '天辉得分',
  `dire_team_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '夜魇名称',
  `dire_team_tag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '夜魇编写',
  `dire_team_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '夜魇logo',
  `dire_team_id` bigint(20) NULL DEFAULT NULL COMMENT '夜魇队伍ID',
  `dire_team_score` bigint(20) NULL DEFAULT NULL COMMENT '夜魇得分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 267 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_dota2_dire_bans
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_dota2_dire_bans`;
CREATE TABLE `finish_match_dota2_dire_bans`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛夜魇禁用英雄主键',
  `finish_match_id` bigint(20) NOT NULL COMMENT '赛果ID',
  `role_id` bigint(20) NOT NULL COMMENT '英雄ID',
  `role_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄logo',
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄名称',
  `role_name_english` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄英文名',
  `role_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄别名',
  `role_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄信息',
  `role_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 559 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_dota2_dire_picks
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_dota2_dire_picks`;
CREATE TABLE `finish_match_dota2_dire_picks`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛夜魇选择英雄主键',
  `finish_match_id` bigint(20) NOT NULL COMMENT '赛果ID',
  `role_id` bigint(20) NOT NULL COMMENT '英雄ID',
  `role_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄logo',
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄名称',
  `role_name_english` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄英文名',
  `role_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄别名',
  `role_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄信息',
  `role_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 469 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_dota2_players
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_dota2_players`;
CREATE TABLE `finish_match_dota2_players`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'dota2赛果玩家主键',
  `finish_match_dota2_id` bigint(20) NOT NULL COMMENT 'dota2赛果ID',
  `account` bigint(20) NOT NULL COMMENT '账号',
  `team` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '战队',
  `hero` bigint(20) NULL DEFAULT NULL COMMENT '英雄',
  `kills` int(10) NULL DEFAULT NULL COMMENT '技能',
  `death` int(10) NULL DEFAULT NULL COMMENT '死亡数',
  `assists` int(10) NULL DEFAULT NULL COMMENT '助攻',
  `last_hits` int(10) NULL DEFAULT NULL COMMENT '最后一击',
  `denies` int(10) NULL DEFAULT NULL COMMENT '拒绝',
  `gold` int(10) NULL DEFAULT NULL COMMENT '金币',
  `level` int(10) NULL DEFAULT NULL COMMENT '等级',
  `gold_per_min` int(10) NULL DEFAULT NULL COMMENT '每分钟金币',
  `xp_per_min` int(10) NULL DEFAULT NULL COMMENT '每分钟经验',
  `ultimate_state` int(10) NULL DEFAULT NULL COMMENT '极限状态',
  `item_0` bigint(20) NULL DEFAULT NULL COMMENT '战队0',
  `item_1` bigint(20) NULL DEFAULT NULL COMMENT '战队1',
  `item_2` bigint(20) NULL DEFAULT NULL COMMENT '战队2',
  `item_3` bigint(20) NULL DEFAULT NULL COMMENT '战队3',
  `item_4` bigint(20) NULL DEFAULT NULL COMMENT '战队4',
  `item_5` bigint(20) NULL DEFAULT NULL COMMENT '战队5',
  `hero_damage` int(10) NULL DEFAULT NULL COMMENT '英雄的伤害',
  `tower_damage` int(10) NULL DEFAULT NULL COMMENT '塔的伤害',
  `hero_healing` int(10) NULL DEFAULT NULL COMMENT '英雄愈合',
  `respawn_timer` int(10) NULL DEFAULT NULL COMMENT '复位定时器',
  `position_x` decimal(20, 0) NULL DEFAULT NULL COMMENT '位置x',
  `position_y` decimal(20, 0) NULL DEFAULT NULL COMMENT '位置y',
  `net_worth` int(10) NULL DEFAULT NULL COMMENT '净值',
  `ability_upgrades` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '升级的能力',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2659 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_dota2_players_equipment
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_dota2_players_equipment`;
CREATE TABLE `finish_match_dota2_players_equipment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛玩家装备主键',
  `finish_match_players_id` bigint(20) NOT NULL COMMENT '赛果战队玩家ID',
  `finish_match_players_account` bigint(20) NOT NULL COMMENT '赛果战队玩家账号',
  `equipment_id` bigint(20) NULL DEFAULT NULL COMMENT '装备ID',
  `equipment_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备logo',
  `equipment_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备名称',
  `equipment_name_english` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备英文名',
  `equipment_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备别名',
  `equipment_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备信息',
  `equipment_description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备描述',
  `equipment_sort` int(8) NULL DEFAULT NULL COMMENT '装备排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5291 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_dota2_radiant_bans
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_dota2_radiant_bans`;
CREATE TABLE `finish_match_dota2_radiant_bans`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛天辉禁用英雄主键',
  `finish_match_id` bigint(20) NOT NULL COMMENT '赛果ID',
  `role_id` bigint(20) NOT NULL COMMENT '英雄ID',
  `role_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄logo',
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄名称',
  `role_name_english` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄英文名',
  `role_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄别名',
  `role_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄信息',
  `role_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 565 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_dota2_radiant_picks
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_dota2_radiant_picks`;
CREATE TABLE `finish_match_dota2_radiant_picks`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛天辉选择英雄主键',
  `finish_match_id` bigint(20) NOT NULL COMMENT '赛果ID',
  `role_id` bigint(20) NOT NULL COMMENT '英雄ID',
  `role_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄logo',
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄名称',
  `role_name_english` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄英文名',
  `role_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄别名',
  `role_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄信息',
  `role_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 471 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_lol
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_lol`;
CREATE TABLE `finish_match_lol`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛主键',
  `series_id` bigint(20) NOT NULL COMMENT '系列赛ID',
  `match_id` bigint(20) NOT NULL COMMENT '比赛ID',
  `game_id` bigint(20) NOT NULL COMMENT '游戏ID',
  `game_no` int(10) NULL DEFAULT NULL COMMENT '游戏场次',
  `course_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '赛程名称',
  `blue_gold_diff` int(10) NULL DEFAULT NULL COMMENT '蓝方黄金比分',
  `start_time` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '开始时间',
  `createdAt` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建时间',
  `game_time` int(1) NULL DEFAULT NULL COMMENT '游戏时间',
  `finished` tinyint(1) NULL DEFAULT NULL COMMENT '比赛是否完成',
  `valid` tinyint(1) NULL DEFAULT NULL COMMENT '比赛是否有效',
  `winner` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '获胜队伍',
  `first_blood` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '一血',
  `first_tower` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '一塔',
  `five_kills` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '杀五个',
  `ten_kills` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '杀十个',
  `first_drakes` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '首杀小龙',
  `first_nashors` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '首杀男爵',
  `first_inhibitor` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '首个水晶',
  `herald` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '先锋',
  `blue_team_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '蓝色方战队名称',
  `blue_team_tag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '蓝色方战队标识',
  `blue_team_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '蓝色方战队logo',
  `blue_team_id` bigint(20) NULL DEFAULT NULL COMMENT '蓝色方战队ID',
  `blue_team_score` bigint(20) NULL DEFAULT NULL COMMENT '蓝色方战队得分',
  `red_team_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '红色方战队名称',
  `red_team_tag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '红色方战队标识',
  `red_team_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '红色方战队logo',
  `red_team_id` bigint(20) NULL DEFAULT NULL COMMENT '红色方战队ID',
  `red_team_score` bigint(20) NULL DEFAULT NULL COMMENT '红色方战队得分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 220 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_lol_blue
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_lol_blue`;
CREATE TABLE `finish_match_lol_blue`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛主键',
  `finish_match_lol_id` bigint(20) NOT NULL COMMENT 'lol赛果ID',
  `team_id` bigint(20) NOT NULL COMMENT '战队ID',
  `kills` int(10) NULL DEFAULT NULL COMMENT '击杀',
  `deaths` int(10) NULL DEFAULT NULL COMMENT '死亡',
  `assists` int(10) NULL DEFAULT NULL COMMENT '助攻',
  `gold` int(10) NULL DEFAULT NULL COMMENT '经济',
  `cs` int(10) NULL DEFAULT NULL COMMENT '补刀',
  `drakes` int(10) NULL DEFAULT NULL COMMENT '小龙',
  `level` int(10) NULL DEFAULT NULL COMMENT '等级',
  `damage_taken` int(10) NULL DEFAULT NULL COMMENT '伤害',
  `damage` int(10) NULL DEFAULT NULL COMMENT '造成伤害',
  `nahsor_barons` int(10) NULL DEFAULT NULL COMMENT '男爵',
  `inhibitors` int(10) NULL DEFAULT NULL COMMENT '水晶',
  `towers` int(10) NULL DEFAULT NULL COMMENT '塔',
  `picks` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选择',
  `bans` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 220 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_lol_blue_bans
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_lol_blue_bans`;
CREATE TABLE `finish_match_lol_blue_bans`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛主键',
  `finish_match_lol_id` bigint(20) NOT NULL COMMENT 'lol赛果ID',
  `role_id` bigint(20) NOT NULL COMMENT '英雄ID',
  `role_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄logo',
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄名称',
  `role_name_english` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄英文名',
  `role_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄别名',
  `role_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄信息',
  `role_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1050 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_lol_blue_picks
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_lol_blue_picks`;
CREATE TABLE `finish_match_lol_blue_picks`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛主键',
  `finish_match_lol_id` bigint(20) NOT NULL COMMENT 'lol赛果ID',
  `role_id` bigint(20) NOT NULL COMMENT '英雄ID',
  `role_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄logo',
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄名称',
  `role_name_english` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄英文名',
  `role_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄别名',
  `role_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄信息',
  `role_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1096 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_lol_blue_players
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_lol_blue_players`;
CREATE TABLE `finish_match_lol_blue_players`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛主键',
  `finish_match_lol_team_id` bigint(20) NOT NULL COMMENT '赛果战队ID',
  `kda` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '自动',
  `cs` int(10) NULL DEFAULT NULL COMMENT '补刀',
  `level` int(10) NULL DEFAULT NULL COMMENT '等级',
  `kills` int(10) NULL DEFAULT NULL COMMENT '击杀',
  `deaths` int(10) NULL DEFAULT NULL COMMENT '死亡',
  `assists` int(10) NULL DEFAULT NULL COMMENT '助攻',
  `gold` int(10) NULL DEFAULT NULL COMMENT '经济',
  `damage_taken` int(10) NULL DEFAULT NULL COMMENT '伤害',
  `damage` int(10) NULL DEFAULT NULL COMMENT '造成伤害',
  `heal` int(10) NULL DEFAULT NULL COMMENT '治疗',
  `hp` int(10) NULL DEFAULT NULL COMMENT '血量',
  `role` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '位置',
  `player` bigint(20) NULL DEFAULT NULL COMMENT '队员ID',
  `champion` bigint(20) NULL DEFAULT NULL COMMENT '英雄ID',
  `gold_rate` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '金率',
  `relative_gold_diff` int(10) NULL DEFAULT NULL COMMENT '相对黄金比例',
  `damage_taken_rate` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '伤害率',
  `damage_taken_min` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '伤害最小',
  `damage_taken_death` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '伤害死亡',
  `damage_rate` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '伤害率',
  `damage_min` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '伤害最小',
  `damage_kill` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '伤害杀死',
  `summoner_spells` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '召唤者法术',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1096 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_lol_blue_players_equipment
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_lol_blue_players_equipment`;
CREATE TABLE `finish_match_lol_blue_players_equipment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛主键',
  `finish_match_id` bigint(20) NOT NULL COMMENT '赛果系列ID',
  `finish_match_team_id` bigint(20) NOT NULL COMMENT '赛果战队ID',
  `finish_match_players_id` bigint(20) NOT NULL COMMENT '赛果战队玩家ID',
  `equipment_id` bigint(20) NULL DEFAULT NULL COMMENT '装备ID',
  `equipment_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备logo',
  `equipment_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备名称',
  `equipment_name_english` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备英文名',
  `equipment_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备别名',
  `equipment_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备信息',
  `equipment_description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7046 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_lol_red
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_lol_red`;
CREATE TABLE `finish_match_lol_red`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛主键',
  `finish_match_lol_id` bigint(20) NOT NULL COMMENT 'lol赛果ID',
  `team_id` bigint(20) NOT NULL COMMENT '战队ID',
  `kills` int(10) NULL DEFAULT NULL COMMENT '击杀',
  `deaths` int(10) NULL DEFAULT NULL COMMENT '死亡',
  `assists` int(10) NULL DEFAULT NULL COMMENT '助攻',
  `gold` int(10) NULL DEFAULT NULL COMMENT '经济',
  `cs` int(10) NULL DEFAULT NULL COMMENT '补刀',
  `drakes` int(10) NULL DEFAULT NULL COMMENT '小龙',
  `level` int(10) NULL DEFAULT NULL COMMENT '等级',
  `damage_taken` int(10) NULL DEFAULT NULL COMMENT '男爵',
  `damage` int(10) NULL DEFAULT NULL COMMENT '造成伤害',
  `nahsor_barons` int(10) NULL DEFAULT NULL COMMENT '男爵',
  `inhibitors` int(10) NULL DEFAULT NULL COMMENT '水晶',
  `towers` int(10) NULL DEFAULT NULL COMMENT '塔',
  `picks` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选择',
  `bans` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 220 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_lol_red_bans
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_lol_red_bans`;
CREATE TABLE `finish_match_lol_red_bans`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛主键',
  `finish_match_lol_id` bigint(20) NOT NULL COMMENT 'lol赛果ID',
  `role_id` bigint(20) NOT NULL COMMENT '英雄ID',
  `role_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄logo',
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄名称',
  `role_name_english` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄英文名',
  `role_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄别名',
  `role_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄信息',
  `role_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1046 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_lol_red_picks
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_lol_red_picks`;
CREATE TABLE `finish_match_lol_red_picks`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛主键',
  `finish_match_lol_id` bigint(20) NOT NULL COMMENT 'lol赛果ID',
  `role_id` bigint(20) NOT NULL COMMENT '英雄ID',
  `role_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄logo',
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄名称',
  `role_name_english` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄英文名',
  `role_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄别名',
  `role_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄信息',
  `role_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英雄描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1096 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_lol_red_players
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_lol_red_players`;
CREATE TABLE `finish_match_lol_red_players`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛主键',
  `finish_match_lol_team_id` bigint(20) NOT NULL COMMENT 'lol赛果蓝队ID',
  `kda` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '自动',
  `cs` int(10) NULL DEFAULT NULL COMMENT '补刀',
  `level` int(10) NULL DEFAULT NULL COMMENT '等级',
  `kills` int(10) NULL DEFAULT NULL COMMENT '击杀',
  `deaths` int(10) NULL DEFAULT NULL COMMENT '死亡',
  `assists` int(10) NULL DEFAULT NULL COMMENT '助攻',
  `gold` int(10) NULL DEFAULT NULL COMMENT '经济',
  `damage_taken` int(10) NULL DEFAULT NULL COMMENT '伤害',
  `damage` int(10) NULL DEFAULT NULL COMMENT '造成伤害',
  `heal` int(10) NULL DEFAULT NULL COMMENT '治疗',
  `hp` int(10) NULL DEFAULT NULL COMMENT '血量',
  `role` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '位置',
  `player` bigint(20) NULL DEFAULT NULL COMMENT '队员ID',
  `champion` bigint(20) NULL DEFAULT NULL COMMENT '英雄ID',
  `gold_rate` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '金率',
  `relative_gold_diff` int(10) NULL DEFAULT NULL COMMENT '相对黄金比例',
  `damage_taken_rate` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '伤害率',
  `damage_taken_min` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '伤害最小',
  `damage_taken_death` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '伤害死亡',
  `damage_rate` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '伤害率',
  `damage_min` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '伤害最小',
  `damage_kill` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '伤害杀死',
  `summoner_spells` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '召唤者法术',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1096 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_match_lol_red_players_equipment
-- ----------------------------
DROP TABLE IF EXISTS `finish_match_lol_red_players_equipment`;
CREATE TABLE `finish_match_lol_red_players_equipment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛主键',
  `finish_match_id` bigint(20) NOT NULL COMMENT '赛果系列ID',
  `finish_match_team_id` bigint(20) NOT NULL COMMENT '赛果战队ID',
  `finish_match_players_id` bigint(20) NOT NULL COMMENT '赛果战队玩家ID',
  `equipment_id` bigint(20) NULL DEFAULT NULL COMMENT '装备ID',
  `equipment_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备logo',
  `equipment_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备名称',
  `equipment_name_english` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备英文名',
  `equipment_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备别名',
  `equipment_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备信息',
  `equipment_description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '装备描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7058 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_series_aov
-- ----------------------------
DROP TABLE IF EXISTS `finish_series_aov`;
CREATE TABLE `finish_series_aov`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '赛果系列赛ID',
  `series_id` bigint(20) NOT NULL COMMENT '系列赛ID',
  `team_id` bigint(20) NOT NULL COMMENT '战队ID',
  `score` int(11) NULL DEFAULT NULL COMMENT '比分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 124 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_series_csgo
-- ----------------------------
DROP TABLE IF EXISTS `finish_series_csgo`;
CREATE TABLE `finish_series_csgo`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '赛果系列赛ID',
  `series_id` bigint(20) NOT NULL COMMENT '系列赛ID',
  `team_id` bigint(20) NOT NULL COMMENT '战队ID',
  `score` int(11) NULL DEFAULT NULL COMMENT '比分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_series_dota2
-- ----------------------------
DROP TABLE IF EXISTS `finish_series_dota2`;
CREATE TABLE `finish_series_dota2`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '赛果系列赛ID',
  `series_id` bigint(20) NOT NULL COMMENT '系列赛ID',
  `team_id` bigint(20) NOT NULL COMMENT '战队ID',
  `score` int(11) NULL DEFAULT NULL COMMENT '比分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 150 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finish_series_lol
-- ----------------------------
DROP TABLE IF EXISTS `finish_series_lol`;
CREATE TABLE `finish_series_lol`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '赛果系列赛ID',
  `series_id` bigint(20) NOT NULL COMMENT '系列赛ID',
  `team_id` bigint(20) NOT NULL COMMENT '战队ID',
  `score` int(11) NULL DEFAULT NULL COMMENT '比分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 775 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for game_equipment
-- ----------------------------
DROP TABLE IF EXISTS `game_equipment`;
CREATE TABLE `game_equipment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色装备ID',
  `game_id` bigint(20) NULL DEFAULT NULL COMMENT '游戏ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `name_english` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '英文名称',
  `alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '别名',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '信息',
  `description` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 579899996159451137 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for game_role
-- ----------------------------
DROP TABLE IF EXISTS `game_role`;
CREATE TABLE `game_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '游戏英雄ID',
  `game_id` bigint(20) NULL DEFAULT NULL COMMENT '游戏ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `name_english` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '英文名',
  `alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '别名',
  `info` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '信息',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `icon` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 579899999040151553 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for game_skill
-- ----------------------------
DROP TABLE IF EXISTS `game_skill`;
CREATE TABLE `game_skill`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色技能ID',
  `game_id` bigint(20) NOT NULL COMMENT '游戏ID',
  `game_role_id` bigint(20) NOT NULL COMMENT '游戏角色ID',
  `game_equipment_id` bigint(20) NOT NULL COMMENT '游戏装备ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `name_english` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '英文名称',
  `info` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '信息',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '描述',
  `icon` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 579899954244329473 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for game_skill_alias
-- ----------------------------
DROP TABLE IF EXISTS `game_skill_alias`;
CREATE TABLE `game_skill_alias`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '技能别名ID',
  `game_skill_id` bigint(20) NOT NULL COMMENT '游戏技能ID',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '技能名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '技能编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1026 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for games
-- ----------------------------
DROP TABLE IF EXISTS `games`;
CREATE TABLE `games`  (
  `id` bigint(18) NOT NULL COMMENT '游戏ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `short_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '短名称',
  `code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for league
-- ----------------------------
DROP TABLE IF EXISTS `league`;
CREATE TABLE `league`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '联赛ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `short_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '短名称',
  `logo` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'logo',
  `organizer` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '组织者',
  `level` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '登记',
  `local` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地区',
  `game_id` bigint(32) NOT NULL COMMENT '游戏ID',
  `area_id` bigint(32) NOT NULL COMMENT '赛区ID',
  `limit_team` int(11) NULL DEFAULT NULL COMMENT '队伍数量',
  `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for series
-- ----------------------------
DROP TABLE IF EXISTS `series`;
CREATE TABLE `series`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '系列赛ID',
  `game_id` bigint(32) NOT NULL COMMENT '游戏ID',
  `league_id` bigint(11) NOT NULL COMMENT '联赛ID',
  `area_id` bigint(32) NOT NULL COMMENT '赛区ID',
  `course_id` bigint(11) NOT NULL COMMENT '赛事ID',
  `course_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '赛事名称',
  `status` int(11) NOT NULL COMMENT '	比赛状态（1未开始 2进行中 3结束）',
  `season_info` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '赛季信息',
  `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述简介',
  `game_no` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '游戏局数',
  `game_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '游戏时间',
  `start_time` bigint(20) NOT NULL COMMENT '开始时间',
  `end_time` bigint(20) NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75802344562932097 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for series_detail
-- ----------------------------
DROP TABLE IF EXISTS `series_detail`;
CREATE TABLE `series_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系列赛详情ID',
  `series_id` bigint(20) NOT NULL COMMENT '系列赛ID',
  `game_id` bigint(20) NOT NULL COMMENT '游戏ID',
  `league_id` bigint(11) NOT NULL COMMENT '联赛ID',
  `area_id` bigint(20) NOT NULL COMMENT '赛区ID',
  `course_id` bigint(11) NOT NULL COMMENT '赛事ID',
  `status` int(11) NOT NULL COMMENT '	比赛状态（1未开始 2进行中 3结束）',
  `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述简介',
  `start_time` bigint(20) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` bigint(20) NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75732200347938817 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for series_detail_info
-- ----------------------------
DROP TABLE IF EXISTS `series_detail_info`;
CREATE TABLE `series_detail_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系列赛详情信息ID',
  `series_detail_id` bigint(20) NOT NULL COMMENT '系列赛详情ID',
  `score` bigint(20) NOT NULL COMMENT '得分',
  `team_id` bigint(11) NOT NULL COMMENT '团队ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75642566213586695 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for series_team
-- ----------------------------
DROP TABLE IF EXISTS `series_team`;
CREATE TABLE `series_team`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系列赛团队主键',
  `series_id` bigint(20) NOT NULL COMMENT '系列赛ID',
  `team_id` bigint(20) NULL DEFAULT NULL COMMENT '系列赛战队ID',
  `team_logo` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '系列赛战队图标',
  `team_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '系列赛战队名称',
  `team_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '系列赛战队类型',
  `team_score` int(10) NULL DEFAULT NULL COMMENT '系列赛战队比分',
  `first_blood` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `first_tower` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `five_kills` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ten_kills` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `first_drakes` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `first_nashors` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `first_inhibitor` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `herald` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `first_bloods` int(10) NULL DEFAULT NULL,
  `first_dominator` int(10) NULL DEFAULT NULL,
  `first_towers` int(10) NULL DEFAULT NULL,
  `first_tyrant` int(10) NULL DEFAULT NULL,
  `five_kill` int(10) NULL DEFAULT NULL,
  `ten_kill` int(10) NULL DEFAULT NULL,
  `win` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1179 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '战队ID',
  `game_id` bigint(20) NOT NULL COMMENT '游戏ID',
  `game_type` tinyint(1) NULL DEFAULT NULL COMMENT '游戏类型',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `short_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '简称',
  `name_english` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '英文名称',
  `local` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '归属地',
  `country` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '国家编码',
  `area` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地区',
  `logo` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 652200810913002753 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_alias
-- ----------------------------
DROP TABLE IF EXISTS `team_alias`;
CREATE TABLE `team_alias`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '战队别名ID',
  `team_id` bigint(20) NOT NULL COMMENT '战队ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1928 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_detail
-- ----------------------------
DROP TABLE IF EXISTS `team_detail`;
CREATE TABLE `team_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '战队ID',
  `game_id` bigint(20) NOT NULL COMMENT '游戏ID',
  `game_type` int(11) NULL DEFAULT 0 COMMENT '游戏类型',
  `alias` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '别名',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `short_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '简称',
  `name_english` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '英文名称',
  `logo` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '图标',
  `local` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '归属地',
  `country` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '国家编码',
  `area` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地区',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 652200810913002753 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_detail_player
-- ----------------------------
DROP TABLE IF EXISTS `team_detail_player`;
CREATE TABLE `team_detail_player`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '战队详情玩家ID',
  `team_detail_id` bigint(20) NULL DEFAULT NULL COMMENT '战队详情ID',
  `alias` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '战队详情玩家别名',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '玩家名称',
  `name_english` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '玩家英文名',
  `country` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '玩家国家编码',
  `team_player_position` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '玩家位置',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 652199393299324289 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_detail_player_alias
-- ----------------------------
DROP TABLE IF EXISTS `team_detail_player_alias`;
CREATE TABLE `team_detail_player_alias`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '战队玩家别名ID',
  `team_detail_players_id` int(11) NOT NULL COMMENT '战队详情玩家ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '战队玩家别名名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_rank
-- ----------------------------
DROP TABLE IF EXISTS `team_rank`;
CREATE TABLE `team_rank`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '战队排名ID',
  `team_id` bigint(20) NOT NULL COMMENT '战队ID',
  `game_id` bigint(20) NOT NULL COMMENT '游戏ID',
  `short_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '短名称',
  `ranks` int(11) NULL DEFAULT 0 COMMENT '排名',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
