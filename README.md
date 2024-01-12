# fmise_back_end 后端使用指南

最终版：2024.1.12

## 数据类型

### 1. Category类（枚举类）

+ 共5种类型，在传递参数时需要使用枚举名（即==全部大写==）

```java
//所有枚举类型
FRUIT("Fruit"),
VEGETABLE("Vegetable"),
MEAT("Meat"),
SEAFOOD("Seafood"),
SNACK("Snack"),
OTHER("Other");
```

### 2. Ingredient类

```java
//所有属性
Long id;
String name;
String quantity;
//用于标志该用料是否是主要原材料，如西红柿炒鸡蛋中，西红柿和鸡蛋为主要原料，其余如盐、酱油等不是主要原料（不在冰箱中存储
boolean mainIngredient; 
```

### 3. Food类

```java
//所有属性
Long id;
String name;
String userId;
//Date格式必须为 YYYY-MM-DD
Date freshDate;
String quantity;
//Category格式字符串必须全部大写
Category category
String address
```

json格式举例（id项可写可不写，写了也没用....）：

```json
{
    "id": 1,
    "name": "鸡肉",
    "userId":"1",
    "freshDate": "2023-02-12",
    "quantity": "3块",
    "category": "MEAT",
    "address": "冷冻二层"
}
```

### 4. Menu类

````java
Long id;
String image;
String name;
List<Ingredient> ingredients;
List<String> steps;
````

json格式举例：

```json
{
    "id": 1,
    "image": "https://example.com/images/menu1.jpg",
    "name": "水煮牛肉",
    "ingredients": [
        {
            "id": 13,
            "name": "牛肉",
            "quantity": "300克",
            "mainIngredient": true
        },
        {
            "id": 14,
            "name": "豆芽",
            "quantity": "100克",
            "mainIngredient": false
        },
        {
            "id": 15,
            "name": "姜",
            "quantity": "适量",
            "mainIngredient": false
        },
        {
            "id": 16,
            "name": "辣椒",
            "quantity": "6个",
            "mainIngredient": false
        }
    ],
    "steps": [
        "牛肉切片，用料酒、盐、生粉腌制10分钟",
        "锅中放油，爆香葱姜蒜和干红辣椒",
        "放入豆芽和泡姜煸炒",
        "加入适量清水，大火煮开",
        "放入牛肉片，煮至变色捞出",
        "最后倒入烧开的油浇在牛肉片上即可"
    ]
}
```

### 5. User类

````java
Long id;
private String username;
private String password;
private List<Menu> collectedMenus;
````

json格式举例（同样，id可写可不写,菜谱list略）：

```json
{
    "id": 1,
    "username": "13012341234",
    "password":"12345"
}
```

### 



## 接口说明

### 1. FoodController

#### 1.1 GET:查询所有食物（按保质期排序）

路径：http://localhost:8080/food/foods-by-date/{userId}

返回类型：List\<Food>

#### 1.2 GET:查询特定种类的食物

路径：http://localhost:8080/food/foods-by-category/{category}/{userId}

返回类型：List\<Food>

示例：

```java
http://localhost:8080/food/foods-by-category/FRUIT/1
```

#### 1.3 GET:输入字符串查询相关食物

路径：http://localhost:8080/food/foods-by-input/{input}/{userId}

返回类型：List\<Food>

示例：

```java
//会查出含“鸡”的所有食物，鸡肉、鸡蛋等
http://localhost:8080/food/foods-by-input/鸡/1
```

#### 1.4 GET:根据ID查找食物

路径：http://localhost:8080/food/food-by/{foodId}

返回类型：Food

示例：

```java
//查询id为1的食物
http://localhost:8080/food/foods-by-category/1
```

#### 1.5 POST:添加食物

路径：http://localhost:8080/food/add-food

返回类型：

```json
//成功
{
    "message": "Food successfully add",
    "code": 201,
    "error": false
}
```

示例：

```java
http://localhost:8080/food/add-food
```

消息体类型见food类型的json示例

#### 1.6 PUT:编辑食物信息

路径：http://localhost:8080/food/edit-food/{foodId}

示例：

```java
http://localhost:8080/food/edit-food/1
```

消息体类型见food类型的json示例

返回类型：

```json
//成功
{
    "message": "Food successfully edit",
    "code": 201,
    "error": false
}
```



#### 1.7 DELETE:删除食物

路径：http://localhost:8080/food/delete-food/{foodId}

示例：

```java
http://localhost:8080/food/delete-food/1
```

返回类型：

```json
//成功
{
    "message": "Food successfully delete",
    "code": 204,
    "error": false
}
```



### 2. MenuController

#### 2.1 GET:查询所有菜谱

路径：http://localhost:8080/menu/all-menu

#### 2.2 GET:查询推荐菜谱

路径：http://localhost:8080/menu/recommend-menu

#### 2.4 GET:根据ID查找菜谱

路径：http://localhost:8080/menu/menu-by/{menuId}
示例：

```java
http://localhost:8080/menu/menu-by/1
```

#### 2.5 GET:根据用户输入查找菜谱

路径：http://localhost:8080/menu/menus-by-input/{input}/{userId}

示例：

```java
//会检索出所有名字中带“鸡”的菜谱以及用料中含“鸡”的菜谱
http://localhost:8080/menu/menus-by-input/鸡/1
```

#### 2.6 POST:添加菜谱（后端自用）

路径：http://localhost:8080/menu/create-menu

示例：

```java
http://localhost:8080/menu/create-menu
```

消息体类型见menu类型的json示例

#### 2.7 PUT:编辑菜谱（后端自用）

路径：http://localhost:8080/menu/update-menu/{id}

示例：

```java
http://localhost:8080/menu/update-menu/1
```

消息体类型见menu类型的json示例

#### 2.8 DELETE:删除菜谱（后端自用）

路径：http://localhost:8080/menu/delete-menu/{id}

```java
http://localhost:8080/menu/delete-menu/1
```
#### 2.9 PUT:收藏菜谱

路径：http://localhost:8080/menu/collect-menu/{id}/{userId}

```java
http://localhost:8080/menu/collect-menu/1/1
```
#### 2.10 PUT:取消菜谱收藏

路径：http://localhost:8080/menu/uncollect-menu/{id}/{userId}

```java
http://localhost:8080/menu/uncollect-menu/1/1
```
#### 2.11 GET:查询收藏菜谱

路径：http://localhost:8080/menu/menus-by-collect/{userId}

```java
http://localhost:8080/menu/menus-by-collect/1
```

### 3. UserController

#### 3.1 POST: 用户登录

路径：http://localhost:8080/user/login

消息体内容举例：

```json
{
    "username": "13012341234",
    "password":"12345"
}
```

如果登录成功，返回的消息体内容如下（返回的就是userId），状态码为200：

```json
3
```

如果用户名不存在，返回-2，状态码为404；

如果密码错误，返回-1，状态码为403；

#### 3.2 POST: 用户注册

路径：http://localhost:8080/user/registry

消息体内容同3.1。

如果注册成功，返回的消息体内容如下：

```json
{
    "message": "User successfully add",
    "code": 201,
    "error": false
}
```

如果名称已存在，则返回：

```json
{
    "message": "Username already exists",
    "code": 409,
    "error": false
}
```

#### 3.3 DELETE: 删除用户（后端自用）

路径：http://localhost:8080/user/delete-user/{userId}



## 部署说明

### 0. 打包

进入每一个子模块，进行package操作，并根据生成的jar包的名字，动态修改dockerfile中的指令

（生成的jar包在target目录下）

```dockerfile
#如gateway子模块的dockerfile
COPY {生成jar包的名字} gateway-service-0.0.1-SNAPSHOT.jar
```

### 1. nacos

#### docker启动:

```
docker pull nacos/nacos-server
docker network create nacos_network
docker run --name nacos -d -p 8848:8848 --network nacos_network -e MODE=standalone nacos/nacos-server
```

#### 访问控制台：

```
localhost:8848/nacos
```



### 2. Sentinel

#### Sentinel下载:

下载地址：https://github.com/alibaba/Sentinel/releases

下载版本：1.6.3

（下载sentinel-dashboard-1.6.3.jar）

#### Sentinal启动：

##### 1. 创建image

进入fmise_back_end文件夹

```
docker build -t Sentinal .
```

如果报错 java.lang.IllegalStateException: Cannot load configuration class: com.alibaba.csp.sentinel.dashboard，可能是因为java版本过高。修改当前文件夹（fmise_back_end）下的DockerFile：

**（基本不可能出现，因为镜像已经指定了java版本）**

```
ENTRYPOINT ["java", "--add-opens", "java.base/java.lang=ALL-UNNAMED", "-jar", "/sentinel-dashboard-1.6.3.jar", "-Dserver.port=8080", "-Dcsp.sentinel.app.type=1", "-Dcsp.sentinel.dashboard.server=localhost:8080", "-Dproject.name=sentinel-dashboard"]
```

##### 2. 创建container并启动

```
docker run -p 8080:8080 -d --name sentinal-service Sentinal:latest
```

#### 访问控制台

```
localhost:8080
```

### 3. 服务启动

#### 3.1 register

进入：\fmise_back_end\register

##### 构建image

`````
docker build -t register .
`````

##### 创建container并启动

```
docker run -p 8090:8090 -d --name register-service register:latest
```

#### 3.2 gateway

进入：\fmise_back_end\gateway

##### 构建image

`````
docker build -t gateway .
`````

##### 创建container并启动

```
docker run -p 8083:8083 -d --name gateway-service gateway:latest
```

#### 3.3 food-service

进入：\fmise_back_end\food-service

##### 构建image

`````
docker build -t food-service .
`````

##### 创建container并启动

```
docker run -p 8085:8085 -d --name food-service food-service:latest
```



进入：\fmise_back_end\food-service2

##### 构建image

`````
docker build -t food-service2 .
`````

##### 创建container并启动

```
docker run -p 8082:8082 -d --name food-service2 food-service2:latest
```

#### 

#### 3.4 menu-service

进入：\fmise_back_end\menu-service

##### 构建image

`````
docker build -t menu-service .
`````

##### 创建container并启动

```
docker run -p 8086:8086 -d --name menu-service menu-service:latest
```



进入：\fmise_back_end\menu-service2

##### 构建image

`````
docker build -t menu-service2 .
`````

##### 创建container并启动

```
docker run -p 8087:8087 -d --name menu-service2 menu-service2:latest
```



#### 3.5 user-service

进入：\fmise_back_end\user-service

##### 构建image

`````
docker build -t user-service .
`````

##### 创建container并启动

```
docker run -p 8188:8188 -d --name user-service user-service:latest
```



#### 3.6 chat-service

进入：\fmise_back_end\chat-service

##### 构建image

`````
docker build -t chat-service .
`````

##### 创建container并启动

```
docker run -p 8180:8180 -d --name chat-service chat-service:latest
```



