DROP TABLE IF EXISTS `tht_brand`;
CREATE TABLE `tht_brand` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `letter` bigint(32) DEFAULT NULL COMMENT '字母序号',
  `logo` char(1) DEFAULT NULL COMMENT 'logo',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '品牌介绍',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '最新修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最新修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`) COMMENT '品牌'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_account`
--

DROP TABLE IF EXISTS `tstd_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_account` (
  `account_number` varchar(32) NOT NULL DEFAULT '' COMMENT '账号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `real_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '真实姓名',
  `type` varchar(4) DEFAULT NULL COMMENT '类别（B端账号，C端账号，平台账号）',
  `status` varchar(2) DEFAULT NULL COMMENT '状态（正常/程序冻结/人工冻结）',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '余额',
  `frozen_amount` decimal(64,0) DEFAULT NULL COMMENT '冻结金额',
  `md5` varchar(32) DEFAULT NULL COMMENT 'MD5',
  `add_amount` decimal(64,0) DEFAULT '0' COMMENT '累计增加金额',
  `in_amount` decimal(64,0) DEFAULT '0' COMMENT '入金',
  `out_amount` decimal(64,0) DEFAULT '0' COMMENT '出金',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `last_order` varchar(32) DEFAULT NULL COMMENT '最近一次变动对应的流水编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_charge`
--

DROP TABLE IF EXISTS `tstd_charge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_charge` (
  `code` varchar(32) NOT NULL COMMENT '针对编号',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `ref_no` varchar(255) DEFAULT NULL COMMENT '流水分组组号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '针对账号',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '充值金额',
  `account_name` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '针对户名',
  `type` varchar(4) DEFAULT NULL COMMENT '账户类型',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `biz_type` varchar(32) DEFAULT NULL,
  `biz_note` varchar(255) DEFAULT NULL,
  `pay_card_info` varchar(255) DEFAULT NULL COMMENT '支付渠道账号信息',
  `pay_card_no` varchar(255) DEFAULT NULL COMMENT '支付渠道账号',
  `status` varchar(4) NOT NULL COMMENT '状态',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `pay_user` varchar(32) DEFAULT NULL COMMENT '支付回录人',
  `pay_note` varchar(255) DEFAULT NULL COMMENT '支付渠道说明',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`) COMMENT '充值订单'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_cnavigate`
--

DROP TABLE IF EXISTS `tstd_cnavigate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_cnavigate` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `url` varchar(255) DEFAULT NULL COMMENT '访问Url',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片',
  `status` varchar(4) DEFAULT NULL COMMENT '状态(1 显示 0 不显示)',
  `location` varchar(32) DEFAULT NULL COMMENT '位置',
  `order_no` int(11) DEFAULT NULL COMMENT '相对位置编号',
  `belong` varchar(32) DEFAULT NULL COMMENT '属于(1 全局 2 地方默认 3 地方默认编号)',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父编号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `content_type` varchar(32) DEFAULT NULL COMMENT '内容源类型',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_exchange_currency`
--

DROP TABLE IF EXISTS `tstd_exchange_currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_exchange_currency` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `to_user_id` varchar(32) DEFAULT NULL COMMENT '去方用户',
  `to_amount` decimal(64,0) DEFAULT NULL COMMENT '去方金额',
  `to_currency` varchar(32) DEFAULT NULL COMMENT '去方币种',
  `from_user_id` varchar(32) DEFAULT NULL COMMENT '来方用户',
  `from_amount` decimal(64,0) DEFAULT NULL COMMENT '来方金额',
  `from_currency` varchar(32) DEFAULT NULL COMMENT '来方币种',
  `create_datetime` datetime DEFAULT NULL COMMENT '产生时间',
  `status` varchar(4) NOT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `pay_type` varchar(4) DEFAULT NULL COMMENT '支付方式',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付编号',
  `pay_amount` bigint(20) DEFAULT NULL COMMENT '支付人民币',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`) COMMENT '币种兑换'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_field_times`
--

DROP TABLE IF EXISTS `tstd_field_times`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_field_times` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) DEFAULT NULL COMMENT '类型(1 登录名 2 昵称)',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `times` int(11) DEFAULT NULL COMMENT '修改次数',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_hlorder`
--

DROP TABLE IF EXISTS `tstd_hlorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_hlorder` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `account_number` varchar(32) NOT NULL COMMENT '账号',
  `account_name` varchar(32) DEFAULT NULL COMMENT '针对户名',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `jour_code` varchar(32) DEFAULT NULL COMMENT '流水号',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `direction` char(1) NOT NULL COMMENT '方向：1=蓝补；0=红冲',
  `amount` bigint(20) NOT NULL COMMENT '金额（精确到厘）',
  `status` varchar(4) NOT NULL COMMENT '状态',
  `apply_user` varchar(32) NOT NULL COMMENT '申请人',
  `apply_note` varchar(255) NOT NULL COMMENT '申请说明',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审批人（li为程序）',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审批说明',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审批时间',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_jour`
--

DROP TABLE IF EXISTS `tstd_jour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_jour` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `kind` varchar(32) DEFAULT NULL COMMENT '流水类型（余额流水、冻结流水）',
  `pay_group` varchar(255) DEFAULT NULL COMMENT '订单分组组号',
  `ref_no` varchar(255) DEFAULT NULL COMMENT '参考订单号',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道类型',
  `channel_order` varchar(255) DEFAULT NULL COMMENT '支付渠道单号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '账号',
  `trans_amount` decimal(64,0) DEFAULT NULL COMMENT '变动金额',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `real_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '真实姓名',
  `type` varchar(4) DEFAULT NULL COMMENT '账户类型',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '业务类型',
  `biz_note` varchar(255) DEFAULT NULL COMMENT '业务类型',
  `pre_amount` decimal(64,0) DEFAULT NULL COMMENT '变动前金额',
  `post_amount` decimal(64,0) DEFAULT NULL COMMENT '变动后金额',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `work_date` varchar(8) DEFAULT NULL COMMENT '拟对账时间',
  `check_user` varchar(32) DEFAULT NULL COMMENT '对账人',
  `check_note` varchar(255) DEFAULT NULL COMMENT '对账说明',
  `check_datetime` datetime DEFAULT NULL COMMENT '对账时间',
  `adjust_user` varchar(32) DEFAULT NULL COMMENT '调账人',
  `adjust_note` varchar(255) DEFAULT NULL COMMENT '调账说明',
  `adjust_datetime` datetime DEFAULT NULL COMMENT '调账时间',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_user`
--

DROP TABLE IF EXISTS `tstd_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登陆名',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `login_pwd` varchar(32) DEFAULT NULL COMMENT '登陆密码',
  `login_pwd_strength` char(1) DEFAULT NULL COMMENT '登陆密码强度',
  `kind` varchar(4) DEFAULT NULL COMMENT '用户类型',
  `level` varchar(4) DEFAULT NULL COMMENT '用户等级',
  `user_referee` varchar(32) DEFAULT NULL COMMENT '推荐人',
  `user_referee_level` varchar(4) DEFAULT NULL COMMENT '推荐人等级',
  `id_kind` char(1) DEFAULT NULL COMMENT '证件类型',
  `id_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `trade_pwd` varchar(32) DEFAULT NULL COMMENT '安全密码',
  `trade_pwd_strength` char(1) DEFAULT NULL COMMENT '安全密码强度',
  `google_secret` varchar(64) DEFAULT NULL COMMENT '谷歌验证秘钥',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编号',
  `div_rate1` decimal(18,8) DEFAULT NULL COMMENT '分成比例1',
  `div_rate2` decimal(18,8) DEFAULT NULL COMMENT '分成比例2',
  `trade_rate` decimal(18,8) DEFAULT NULL COMMENT '交易手续费率',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `union_id` varchar(255) DEFAULT NULL COMMENT '联合编号',
  `h5_open_id` varchar(255) DEFAULT NULL COMMENT '公众号开放编号',
  `app_open_id` varchar(255) DEFAULT NULL COMMENT 'app开放编号',
  `gender` char(1) DEFAULT NULL COMMENT '性别(1 男 0 女)',
  `introduce` varchar(255) DEFAULT NULL COMMENT '自我介绍',
  `birthday` varchar(16) DEFAULT NULL COMMENT '生日',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `diploma` varchar(4) DEFAULT NULL COMMENT '学位',
  `occupation` varchar(64) DEFAULT NULL COMMENT '职业',
  `work_time` varchar(4) DEFAULT NULL COMMENT '工作年限',
  `pdf` varchar(255) DEFAULT NULL COMMENT '用户资料',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `longitude` varchar(255) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(255) DEFAULT NULL COMMENT '维度',
  `create_datetime` datetime DEFAULT NULL COMMENT '注册时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `last_login` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_user_relation`
--

DROP TABLE IF EXISTS `tstd_user_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_user_relation` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `to_user` varchar(32) DEFAULT NULL COMMENT '关系人编号',
  `type` varchar(4) DEFAULT NULL COMMENT '关系类型',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `update_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_withdraw`
--

DROP TABLE IF EXISTS `tstd_withdraw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_withdraw` (
  `code` varchar(32) NOT NULL COMMENT '针对编号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '针对账号',
  `account_name` varchar(32) DEFAULT NULL COMMENT '针对户名',
  `type` varchar(4) DEFAULT NULL COMMENT '类别（B端账号，C端账号，平台账号）',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '取现金额',
  `fee` decimal(64,0) DEFAULT NULL COMMENT '手续费',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `channel_bank` varchar(32) DEFAULT NULL COMMENT '渠道银行代号',
  `channel_order` varchar(255) DEFAULT NULL COMMENT '支付渠道编号',
  `pay_card_info` varchar(255) DEFAULT NULL COMMENT '支付渠道账号信息',
  `pay_card_no` varchar(255) DEFAULT NULL COMMENT '支付渠道账号',
  `status` varchar(4) NOT NULL COMMENT '状态',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '申请说明',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审批人',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审批说明',
  `approve_datetime` varchar(32) DEFAULT NULL COMMENT '审批时间',
  `pay_user` varchar(255) DEFAULT NULL COMMENT '支付回录人',
  `pay_note` varchar(255) DEFAULT NULL COMMENT '支付回录说明',
  `pay_group` varchar(255) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(255) DEFAULT NULL COMMENT '支付渠道订单编号',
  `pay_fee` decimal(64,0) DEFAULT NULL COMMENT '支付渠道手续费（矿工费）',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付回录时间',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`) COMMENT '取现订单'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_config`
--

DROP TABLE IF EXISTS `tsys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `ckey` varchar(32) DEFAULT NULL COMMENT 'key值',
  `cvalue` TEXT DEFAULT NULL COMMENT '值',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_dict`
--

DROP TABLE IF EXISTS `tsys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号（自增长）',
  `type` char(1) NOT NULL COMMENT '类型（0=下拉框意义 1=下拉框选项）',
  `parent_key` varchar(32) DEFAULT NULL COMMENT '父key',
  `dkey` varchar(32) NOT NULL COMMENT 'key',
  `dvalue` varchar(64) NOT NULL COMMENT '值',
  `updater` varchar(32) NOT NULL COMMENT '更新人',
  `update_datetime` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`) COMMENT '数据字典'
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_menu`
--

DROP TABLE IF EXISTS `tsys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_menu` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `type` varchar(2) DEFAULT NULL COMMENT '类型',
  `url` varchar(64) DEFAULT NULL COMMENT '请求url',
  `order_no` varchar(8) DEFAULT NULL COMMENT '序号',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父亲节点',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_menu_role`
--

DROP TABLE IF EXISTS `tsys_menu_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_menu_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) DEFAULT NULL,
  `menu_code` varchar(32) DEFAULT NULL,
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=857 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_role`
--

DROP TABLE IF EXISTS `tsys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsys_role` (
  `code` varchar(32) NOT NULL COMMENT '角色编号',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `level` varchar(2) DEFAULT NULL,
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `tstd_blacklist` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `type` varchar(32) DEFAULT NULL COMMENT '拉黑类型',
  `status` varchar(4) DEFAULT NULL COMMENT '状态 0-已删除 1-已生效',
  `create_datetime` datetime DEFAULT NULL COMMENT '拉黑时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` varchar(45) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tstd_user_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `type` varchar(4) NOT NULL COMMENT '设置类型',
  `value` varchar(10) NOT NULL COMMENT '预留值',
  `create_datetime` datetime NOT NULL,
  `update_datetime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_type` (`user_id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

