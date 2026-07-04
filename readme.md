# 微头条JavaWeb版本 - 后端

## 📁 项目概述
这是一个基于Java Servlet的新闻管理系统（mtt-rd），采用传统MVC架构，使用Druid连接池、JWT认证、MySQL数据库。

## 📂 目录结构

```
mtt-rd/
├── src/                          # 源代码目录
│   ├── Main.java                 # 程序入口（示例代码）
│   └── com/yujun/mtt/           # 主包
│       ├── common/              # 公共类
│       ├── controller/          # 控制器层
│       ├── dao/                 # 数据访问层
│       ├── filters/             # 过滤器
│       ├── pojo/                # 实体类
│       ├── service/             # 业务逻辑层
│       └── util/                # 工具类
├── resources/                   # 资源文件
│   └── jdbc.properties         # 数据库配置
├── web/                        # Web配置
│   └── WEB-INF/
│       ├── lib/                # 第三方依赖
│       └── web.xml             # Web配置文件
├── out/                        # 编译输出目录
├── .gitignore                  # Git忽略配置
└── mtt-rd.iml                  # IntelliJ IDEA项目文件
```

## 📄 文件作用详解

### 1. 配置文件

| 文件路径 | 作用 |
|---------|------|
| [resources/jdbc.properties](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/resources/jdbc.properties) | 数据库连接配置，包含MySQL连接信息、连接池参数 |
| [web/WEB-INF/web.xml](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/web/WEB-INF/web.xml) | Web应用配置文件（当前为空配置） |
| [.gitignore](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/.gitignore) | Git版本控制忽略规则配置 |

### 2. 公共类

| 文件路径 | 作用 |
|---------|------|
| [common/Result.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/common/Result.java) | 全局统一返回结果类，封装API响应格式 |
| [common/ResultCodeEnum.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/common/ResultCodeEnum.java) | 返回码枚举类，定义系统状态码 |

### 3. 控制器层

| 文件路径 | 作用 |
|---------|------|
| [controller/BaseController.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/controller/BaseController.java) | 基础控制器，通过反射实现RESTful路由分发 |
| [controller/NewsHeadlineController.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/controller/NewsHeadlineController.java) | 新闻头条控制器，处理头条相关请求 |
| [controller/NewsTypeController.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/controller/NewsTypeController.java) | 新闻类型控制器，处理新闻分类请求 |
| [controller/NewsUserController.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/controller/NewsUserController.java) | 用户控制器，处理用户相关请求 |
| [controller/PortalController.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/controller/PortalController.java) | 门户控制器，处理门户页面请求 |

### 4. 数据访问层

| 文件路径 | 作用 |
|---------|------|
| [dao/BaseDao.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/dao/BaseDao.java) | 基础DAO接口，提供通用数据访问方法 |
| [dao/NewsHeadLineDao.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/dao/NewsHeadLineDao.java) | 新闻头条DAO接口 |
| [dao/NewsTypeDao.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/dao/NewsTypeDao.java) | 新闻类型DAO接口 |
| [dao/NewsUserDao.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/dao/NewsUserDao.java) | 用户DAO接口 |
| [dao/impl/NewsHeadLineDaoImpl.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/dao/impl/NewsHeadLineDaoImpl.java) | 新闻头条DAO实现类 |
| [dao/impl/NewsTypeDaoImpl.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/dao/impl/NewsTypeDaoImpl.java) | 新闻类型DAO实现类 |
| [dao/impl/NewsUserDaoImpl.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/dao/impl/NewsUserDaoImpl.java) | 用户DAO实现类 |

### 5. 实体类

| 文件路径 | 作用 |
|---------|------|
| [pojo/NewsHeadline.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/pojo/NewsHeadline.java) | 新闻头条实体类，包含新闻标题、内容、类型等字段 |
| [pojo/NewsType.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/pojo/NewsType.java) | 新闻类型实体类，包含新闻分类信息 |
| [pojo/NewsUser.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/pojo/NewsUser.java) | 用户实体类，包含用户基本信息 |
| [pojo/HeadlineDetailVo.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/pojo/HeadlineDetailVo.java) | 新闻详情视图对象 |
| [pojo/HeadlinePageVo.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/pojo/HeadlinePageVo.java) | 新闻分页视图对象 |
| [pojo/HeadlineQueryVo.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/pojo/HeadlineQueryVo.java) | 新闻查询视图对象 |

### 6. 业务逻辑层

| 文件路径 | 作用 |
|---------|------|
| [service/NewsHeadLineService.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/service/NewsHeadLineService.java) | 新闻头条业务接口 |
| [service/NewsTypeService.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/service/NewsTypeService.java) | 新闻类型业务接口 |
| [service/NewsUserService.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/service/NewsUserService.java) | 用户业务接口 |
| [service/impl/NewsHeadLineServiceImpl.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/service/impl/NewsHeadLineServiceImpl.java) | 新闻头条业务实现类 |
| [service/impl/NewsTypeServiceImpl.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/service/impl/NewsTypeServiceImpl.java) | 新闻类型业务实现类 |
| [service/impl/NewsUserServiceImpl.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/service/impl/NewsUserServiceImpl.java) | 用户业务实现类 |

### 7. 工具类

| 文件路径 | 作用 |
|---------|------|
| [util/JDBCUtil.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/util/JDBCUtil.java) | 数据库连接工具类，基于Druid连接池和ThreadLocal |
| [util/JwtHelper.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/util/JwtHelper.java) | JWT令牌工具类，用于用户认证和授权 |
| [util/MD5Util.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/util/MD5Util.java) | MD5加密工具类 |
| [util/WebUtil.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/util/WebUtil.java) | Web请求处理工具类 |

### 8. 过滤器

| 文件路径 | 作用 |
|---------|------|
| [filters/CrosFilter.java](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/filters/CrosFilter.java) | 跨域请求过滤器，处理CORS跨域问题 |

### 9. 依赖库

| 文件路径 | 作用 |
|---------|------|
| web/WEB-INF/lib/druid-1.1.22.jar | Druid数据库连接池 |
| web/WEB-INF/lib/jackson-*.jar | JSON处理库 |
| web/WEB-INF/lib/jjwt-api-0.10.1.jar | JWT令牌处理 |
| web/WEB-INF/lib/lombok-1.18.24.jar | 简化Java代码 |
| web/WEB-INF/lib/mysql-connector-java-8.0.25.jar | MySQL数据库驱动 |

## 🔗 文件调用关系

### 1. 请求处理流程

```
HTTP请求
  ↓
CrosFilter (跨域处理)
  ↓
Controller (通过BaseController反射路由)
  ↓
Service (业务逻辑处理)
  ↓
Dao (数据访问)
  ↓
JDBCUtil (数据库连接)
  ↓
MySQL数据库
```

### 2. 核心调用关系

#### Controller层调用关系
- 所有Controller继承 [BaseController](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/controller/BaseController.java)
- [BaseController](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/controller/BaseController.java) 使用反射动态调用子类方法
- Controller调用Service层处理业务逻辑
- Controller使用 [Result](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/common/Result.java) 封装返回结果

#### Service层调用关系
- Service接口定义业务方法
- ServiceImpl实现类调用对应的Dao接口
- Service可能使用 [JwtHelper](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/util/JwtHelper.java) 进行用户认证
- Service可能使用 [MD5Util](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/util/MD5Util.java) 进行密码加密

#### Dao层调用关系
- Dao接口定义数据访问方法
- DaoImpl实现类使用 [JDBCUtil](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/util/JDBCUtil.java) 获取数据库连接
- Dao操作对应的Pojo实体类

#### 工具类调用关系
- [JDBCUtil](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/util/JDBCUtil.java) 读取 [jdbc.properties](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/resources/jdbc.properties) 配置
- [JDBCUtil](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/util/JDBCUtil.java) 使用Druid连接池管理连接
- [JwtHelper](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/util/JwtHelper.java) 使用jjwt库生成和解析JWT令牌

### 3. 数据流向

```
前端请求 → Controller → Service → Dao → JDBCUtil → MySQL
                    ↓           ↓        ↓
                  Result      Pojo    Connection
                    ↓           ↓        ↓
                JSON响应    实体对象   数据库操作
```

### 4. 配置文件依赖

- [jdbc.properties](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/resources/jdbc.properties) 被 [JDBCUtil](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/src/com/yujun/mtt/util/JDBCUtil.java) 读取
- [web.xml](file:///Users/qiangyujun/Desktop/study/admin/mtt-rd/web/WEB-INF/web.xml) 配置Servlet和Filter

## 🎯 架构特点

1. **分层架构**：清晰的MVC分层，Controller-Service-Dao三层分离
2. **RESTful设计**：通过BaseController实现基于URL方法名的路由
3. **统一响应**：使用Result类统一API响应格式
4. **连接池管理**：使用Druid连接池和ThreadLocal管理数据库连接
5. **JWT认证**：基于JWT的无状态用户认证
6. **跨域支持**：通过CrosFilter处理CORS跨域问题

这是一个典型的Java Web项目结构，适合中小型应用开发，代码结构清晰，职责分明。
        