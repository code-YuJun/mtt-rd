# MTT-RD 新闻头条管理系统

## 项目概述

MTT-RD 是一个基于 **Java 原生 Servlet + JDBC** 开发的新闻头条管理后端项目，采用经典的三层架构（Controller - Service - DAO），实现了新闻发布、浏览、用户注册登录、新闻分类管理等核心功能。

项目通过 RESTful 风格的 JSON 接口对外提供服务，支持 JWT Token 认证、跨域处理、MD5 密码加密等安全机制。

---

## 技术栈

| 技术/框架 | 版本 | 说明 |
|-----------|------|------|
| Java | 17+ | 开发语言 |
| Jakarta Servlet | 6.0 | Web 核心规范（Tomcat 10.1+） |
| JDBC | - | 原生数据库访问 |
| MySQL | 8.0+ | 关系型数据库 |
| Druid | 1.1.22 | 数据库连接池 |
| Jackson | 2.13.2 | JSON 序列化/反序列化 |
| JJWT | 0.10.1 | JWT Token 生成与校验 |
| Lombok | 1.18.24 | 简化 POJO 代码 |
| Tomcat | 10.1.52 | 应用服务器 |

---

## 项目结构

```
mtt-rd/
├── src/                              # 源代码目录
│   ├── com/yujun/mtt/
│   │   ├── common/                   # 通用返回结果封装
│   │   │   ├── Result.java           # 统一响应结果对象
│   │   │   └── ResultCodeEnum.java   # 响应状态码枚举
│   │   ├── controller/               # 控制层（Servlet）
│   │   │   ├── BaseController.java   # 基础控制器（反射分发请求）
│   │   │   ├── NewsHeadlineController.java  # 新闻头条管理接口
│   │   │   ├── NewsTypeController.java      # 新闻分类接口
│   │   │   ├── NewsUserController.java      # 用户管理接口
│   │   │   └── PortalController.java        # 门户公开接口
│   │   ├── dao/                      # 数据访问层
│   │   │   ├── BaseDao.java          # 基础 DAO（通用 CRUD）
│   │   │   ├── NewsHeadLineDao.java
│   │   │   ├── NewsTypeDao.java
│   │   │   ├── NewsUserDao.java
│   │   │   └── impl/                 # DAO 实现类
│   │   ├── filters/                  # Servlet 过滤器
│   │   │   ├── CrosFilter.java       # 跨域处理过滤器
│   │   │   └── LoginFilter.java      # 登录认证过滤器
│   │   ├── pojo/                     # 实体类 / VO 对象
│   │   │   ├── NewsHeadline.java     # 新闻头条实体
│   │   │   ├── NewsType.java         # 新闻分类实体
│   │   │   ├── NewsUser.java         # 用户实体
│   │   │   ├── HeadlineQueryVo.java  # 新闻分页查询 VO
│   │   │   ├── HeadlinePageVo.java   # 新闻分页返回 VO
│   │   │   └── HeadlineDetailVo.java # 新闻详情 VO
│   │   ├── service/                  # 业务逻辑层
│   │   │   ├── NewsHeadLineService.java
│   │   │   ├── NewsTypeService.java
│   │   │   ├── NewsUserService.java
│   │   │   └── impl/                 # Service 实现类
│   │   └── util/                     # 工具类
│   │       ├── JDBCUtil.java         # JDBC 连接池工具（Druid）
│   │       ├── JwtHelper.java        # JWT Token 工具
│   │       ├── MD5Util.java          # MD5 加密工具
│   │       └── WebUtil.java          # Web 请求/响应工具
│   └── Main.java                     # 入口类（IDE 模板）
├── resources/
│   └── jdbc.properties               # 数据库连接配置
├── web/                              # Web 应用根目录
│   └── WEB-INF/
│       ├── lib/                      # 第三方依赖 JAR 包
│       └── web.xml                   # Web 应用配置（过滤器配置）
├── out/                              # 编译输出目录
├── mtt-rd.iml                        # IntelliJ IDEA 模块配置
└── readme.md                         # 项目说明文档
```

---

## 数据库设计

项目使用 MySQL 数据库，默认库名为 `mtt`，包含以下核心表：

### 1. news_user（用户表）

| 字段名 | 类型 | 说明 |
|--------|------|------|
| uid | INT | 主键，自增 |
| username | VARCHAR | 用户名（唯一） |
| user_pwd | VARCHAR | 密码（MD5 加密存储） |
| nick_name | VARCHAR | 用户昵称 |

### 2. news_headline（新闻头条表）

| 字段名 | 类型 | 说明 |
|--------|------|------|
| hid | INT | 主键，自增 |
| title | VARCHAR | 新闻标题 |
| article | TEXT | 新闻正文 |
| type | INT | 新闻类型 ID（关联 news_type） |
| publisher | INT | 发布人 ID（关联 news_user） |
| page_views | INT | 浏览量 |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |
| is_deleted | INT | 逻辑删除标志（0-未删除，1-已删除） |

### 3. news_type（新闻分类表）

| 字段名 | 类型 | 说明 |
|--------|------|------|
| tid | INT | 主键，自增 |
| tname | VARCHAR | 分类名称 |

---

## 核心功能模块

### 1. 用户模块（`/user/*`）

| 接口路径 | 请求方式 | 功能说明 |
|----------|----------|----------|
| `/user/login` | POST | 用户登录，返回 JWT Token |
| `/user/getUserInfo` | GET | 根据 Token 获取当前登录用户信息 |
| `/user/checkUserName` | GET | 注册时校验用户名是否已被占用 |
| `/user/regist` | POST | 用户注册（密码 MD5 加密） |

**登录流程：**
1. 前端提交用户名和密码
2. 后端校验用户名是否存在
3. 校验密码（MD5 加密后比对）
4. 生成 JWT Token 返回给前端
5. 前端后续请求携带 Token 进行身份认证

### 2. 门户模块（`/portal/*`）—— 无需登录

| 接口路径 | 请求方式 | 功能说明 |
|----------|----------|----------|
| `/portal/findAllTypes` | GET | 查询所有新闻分类 |
| `/portal/findNewsPage` | POST | 分页条件查询新闻列表 |
| `/portal/showHeadlineDetail` | GET | 查询单个新闻详情（浏览量+1） |

**新闻分页查询支持条件：**
- `keyWords`：按标题关键词模糊搜索
- `type`：按新闻类型筛选
- `pageNum` / `pageSize`：分页参数

### 3. 新闻头条管理模块（`/headline/*`）—— 需登录

| 接口路径 | 请求方式 | 功能说明 |
|----------|----------|----------|
| `/headline/publish` | POST | 发布新闻 |
| `/headline/findHeadlineByHid` | GET | 根据 ID 查询新闻（修改回显） |
| `/headline/update` | POST | 更新新闻信息 |
| `/headline/removeByHid` | GET | 删除新闻（逻辑删除） |

> 该模块所有接口受 `LoginFilter` 保护，必须在请求头中携带有效的 `token`。

---

## 架构设计

### 三层架构

```
┌─────────────────────────────────────┐
│           Controller 层              │  ← Servlet 接收请求，返回 JSON
│  (NewsUserController / PortalController ...) │
├─────────────────────────────────────┤
│           Service 层                 │  ← 业务逻辑处理
│  (NewsUserServiceImpl / NewsHeadLineServiceImpl ...) │
├─────────────────────────────────────┤
│           DAO 层                     │  ← 数据库访问
│  (NewsUserDaoImpl / NewsHeadLineDaoImpl ...) │
├─────────────────────────────────────┤
│           BaseDao                    │  ← 通用 JDBC 封装（反射映射实体）
└─────────────────────────────────────┘
```

### 请求分发机制

项目没有使用 Spring MVC，而是通过自定义的 [BaseController](src/com/yujun/mtt/controller/BaseController.java) 实现请求分发：

- 利用 Servlet 的 `service()` 方法拦截所有请求
- 通过反射解析 URL 最后一个路径段作为方法名
- 动态调用对应的业务方法，实现类似 Spring MVC 的 `@RequestMapping` 效果

### 安全机制

1. **JWT 认证**
   - 登录成功后生成 Token，有效期 24 小时
   - 受保护接口通过请求头 `token` 校验身份
   - Token 解析用户 ID，用于关联操作（如发布新闻时自动设置发布人）

2. **跨域处理（CORS）**
   - [CrosFilter](src/com/yujun/mtt/filters/CrosFilter.java) 拦截所有请求
   - 设置 `Access-Control-Allow-Origin: *`
   - 自动处理 OPTIONS 预检请求

3. **密码加密**
   - 用户密码使用 MD5 单向加密存储
   - 注册和登录时均进行加密比对

---

## 配置文件说明

### jdbc.properties

```properties
driverClassName=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/mtt
username=root
password=你的密码
initialSize=5
maxActive=10
maxWait=1000
```

> 请根据本地数据库环境修改 `url`、`username`、`password`。

### web.xml

配置了 `LoginFilter`，拦截 `/headline/*` 路径，对新闻管理接口进行登录校验：

```xml
<filter-mapping>
    <filter-name>loginFilter</filter-name>
    <url-pattern>/headline/*</url-pattern>
</filter-mapping>
```

---

## 快速启动

### 环境要求

- JDK 17+
- MySQL 8.0+
- Tomcat 10.1+
- IntelliJ IDEA（推荐）

### 部署步骤

1. **克隆/导入项目**
   - 使用 IntelliJ IDEA 打开项目目录

2. **初始化数据库**
   - 创建数据库 `mtt`
   - 执行建表 SQL（`news_user`、`news_headline`、`news_type`）

3. **修改数据库配置**
   - 编辑 `resources/jdbc.properties`，填写正确的数据库连接信息

4. **配置 Tomcat**
   - 在 IDEA 中添加 Tomcat 10.1+ 本地服务器
   - 部署 `mtt-rd` 模块（注意使用 Jakarta EE 版本）

5. **启动运行**
   - 启动 Tomcat 服务
   - 访问 `http://localhost:8080/`（具体端口根据配置）

---

## 接口响应格式

所有接口统一返回 JSON 格式：

```json
{
  "code": 200,
  "message": "success",
  "data": { ... }
}
```

### 状态码说明

| 状态码 | 含义 |
|--------|------|
| 200 | 操作成功 |
| 501 | 用户名错误 |
| 503 | 密码错误 |
| 504 | 未登录 / Token 过期 |
| 505 | 用户名已被占用 |

---

## 项目亮点

- **纯原生实现**：不依赖 Spring/SpringBoot，深入理解 Java Web 底层原理
- **反射通用 DAO**：[BaseDao](src/com/yujun/mtt/dao/BaseDao.java) 通过反射自动将 ResultSet 映射为实体对象，大幅减少重复代码
- **动态 SQL 拼接**：新闻分页查询支持多条件动态组合
- **ThreadLocal 连接管理**：[JDBCUtil](src/com/yujun/mtt/util/JDBCUtil.java) 使用 ThreadLocal 保证同线程内数据库连接唯一性
- **逻辑删除**：新闻删除采用 `is_deleted` 标志位，保留数据可追溯

---

## 作者

- **开发者**：yujun
- **包名**：`com.yujun.mtt`

---

## 许可证

本项目为学习演示用途，仅供参考。