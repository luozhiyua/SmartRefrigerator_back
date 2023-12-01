# fmise_back_end 后端使用指南



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
//Date格式必须为 YYYY-MM-DD
Date freshDate;
String quantity;
//Category格式字符串必须全部大写
Category category
String address
```

json格式举例：

```json
{
    "id": 1,
    "name": "鸡肉",
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

### 



## 接口说明

### 1. FoodController

#### 1.1 GET:查询所有食物（按保质期排序）

路径：http://localhost:8080/food/foods-by-date

返回类型：List\<Food>

#### 1.2 GET:查询特定种类的食物

路径：http://localhost:8080/food/foods-by-category/{category}

返回类型：List\<Food>

示例：

```java
http://localhost:8080/food/foods-by-category/FRUIT
```

#### 1.3 GET:输入字符串查询相关食物

路径：http://localhost:8080/food/foods-by-input/{input}

返回类型：List\<Food>

示例：

```java
//会查出含“鸡”的所有食物，鸡肉、鸡蛋等
http://localhost:8080/food/foods-by-input/鸡
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
    "code": 200,
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

路径：http://localhost:8080/menu/menus-by-input/{input}

示例：

```java
//会检索出所有名字中带“鸡”的菜谱以及用料中含“鸡”的菜谱
http://localhost:8080/menu/menus-by-input/鸡
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

