# 1. 医生信息管理系统-API


- [1. 医生信息管理系统-API](#1-医生信息管理系统-API)
   - [1.1. tips](#11-tips)
   - [1.2. 登陆](#12-登陆)
   - [1.3. 市场人员系统](#13-市场人员系统)
     - [1.3.1. 添加医生信息](#131-添加医生信息)
     - [1.3.2. 修改医生信息](#132-修改医生信息)
     - [1.3.3. 删除医生信息](#133-删除医生信息)
     - [1.3.4. 查询自己的医生信息](#134-查询自己的医生信息)
     - [1.3.5. 下载医生的文件](#135-下载医生的文件)
   - [1.4. 管理员系统](#14-管理员系统)
     - [1.4.1. 查询所有医生信息](#141-查询所有医生信息)
     - [1.4.2. 添加市场人员信息](#142-添加市场人员信息)
     - [1.4.3. 修改市场人员信息](#143-修改市场人员信息)
     - [1.4.4. 删除市场人员信息](#144-删除市场人员信息)
     - [1.4.5. 查询市场人员信息](#145-查询市场人员信息)
     - [1.4.6. 维护总金额，患者总数](#146-维护总金额，患者总数)
     - [1.4.7. 增加省、市、医院、科室](#147-增加省、市、医院、科室)
     - [1.4.8. 删除省、市、医院、科室](#148-删除省、市、医院、科室)
     - [1.4.9. 修改省、市、医院、科室](#149-修改省、市、医院、科室)
     - [1.4.10. 查询省、市、医院、科室](#1410-查询省、市、医院、科室)
   - [1.5. 超级管理员系统](#15-超级管理员系统)
     - [1.5.2. 条件查询总金额，月增金额，医生总数，患者总数](#152-条件查询总金额，月增金额，医生总数，患者总数)
     - [1.5.3. 添加管理员信息](#153-添加管理员信息)
     - [1.5.4. 修改管理员信息](#154-修改管理员信息)
     - [1.5.5. 删除管理员信息](#155-删除管理员信息)
     - [1.5.6. 查询所有管理员](#156-查询所有管理员)
     - [1.5.7. 查询管理员信息](#157-查询管理员信息)
   

##  1.1. tips

- 用户类别 : 
  - 1 ：超级管理员
  - 2 ：管理员
  - 3 ：市场人员
- code :
  - 0 : 正常
  - 1 : 添加 message 字段展示相关信息。（例如：删除，更新，插入。会有 “删除成功/失败” 信息）
  - 2 : 登陆失败
- data : 返回数据
  
- url : host + uri
  - example :
    - host : <http://hospital.softlab.com>
    - uri : /login
    - url : <http://hospital.softlab.com/login>
    
---

## 1.2. 登陆

- POST  /login
- payload : 

```json
{
    "userId" : "admin",
    "userPassword" : "123456"
}
```


- return :

```json
{
  "code": 0,
  "data": {
    "type": 1,
    "systemId": 1,
    "token": "8cad118283564b4581e0cf5cd69ad9c6"
  }
}
```
- 登录获得的token，在以后的每个接口传值的时候都要带上，把token设置在request header里面
## 1.3. 市场人员系统

### 1.3.1 添加医生信息

- POST /user
- payload :
- docTag表示所属的市场人员编号，市场人员在添加医生时需要标明哪个医生是自己的添加的。所以加个docTag
```json
{
   "docProvince" : "黑龙江省",
   "docCity" : "哈尔滨市",
   "docHospital" : "医大二院",
   "docRoom" : "内科",
   "docName" : "江小白",
   "docDate" : "2019/7/3",
   "docPhone" : "18888888888",
   "docTag" : "100001"
}
```
- 除了以上信息外，还要加上文件，多个文件。
- file : {files}

- return : 

```json
{
  "code": 0,
  "message": "添加成功"
}
```

### 1.3.2 修改医生信息

- PUT  /doctor
- payload : 
- 也就是添加时候传的信息都能修改，systemId是查询的时候返给你的，对应数据库的自增主键，不用管。
```json
{
   "systemId" : 2,
   "docProvince" : "黑龙江省",
   "docCity" : "哈尔滨市",
   "docHospital" : "医大二院",
   "docRoom" : "内科",
   "docName" : "江小白",
   "docDate" : "2019/7/3",
   "docPhone" : "18888888888",
   "docTag" : "100001"
}
```

- 除了以上信息外，还要加上文件，多个文件。
- file : {files}

- return : 

```json
{
  "code": 0,
  "message": "修改成功"
}
```

### 1.3.3 删除医生信息

- DELETE  /doctor/{systemId}

- return : 

```json
{
  "code": 0,
  "message": "删除成功"
}
```

### 1.3.4 查询自己的医生信息

- GET  /doctor?userId=。。。。
- userId 为当前市场人员的编号 == 查询里面的docTag 。
- return : 

```json
{
    "code": 0,
    "data": [
        {
            "date": "2019-07-12",
            "incPatient": 1,
            "systemId": 21,
            "sumMoney": 1.0,
            "city": "省2市3",
            "sumPatient": 1,
            "incMoney": 1.0,
            "room": "1",
            "province": "省2",
            "phone": "1",
            "name": "1",
            "tag": "1",
            "hospital": "1"
        }
    ]
}
```

### 1.3.5 下载医生的文件

- GET /downFile?fileName=.....

- return 
  - 成功 ： 无返回值
  - 失败 ： 返回一个字符串：未查询到此文件/下载失败



---

---

---

##  1.4. 管理员系统 


### 加上市场人员的所有功能。
- 注意 查询某个医生信息时，需要加上userId,查询的时候已经返过来了

### 1.4.1 查询所有医生信息

- GET  /doctor-all

- return : 

```json
{
  "code": 0,
  "data": [
    {
      "date": "7",
      "incPatient": 0,
      "systemId": 1,
      "sumMoney": 200,
      "city": "3",
      "sumPatient": 300,
      "incMoney": 0,
      "room": "5",
      "file": "WWW",
      "province": "2",
      "phone": "8",
      "name": "6",
      "tag": "1",
      "hospital": "4"
    },
    {
      "date": "2019/7/3",
      "incPatient": 0,
      "systemId": 2,
      "sumMoney": 0,
      "city": "哈尔滨市",
      "sumPatient": 0,
      "incMoney": 0,
      "room": "内科",
      "file": "aaa",
      "province": "黑龙江省",
      "phone": "13091896371",
      "name": "江小白",
      "tag": "100001",
      "hospital": "医大二院"
    }
  ]
}
```

### 1.4.2. 添加市场人员信息

- POST /user
- payload :

```json
{
    "userName" : "007",
    "userId" : "007",
    "userPassword" : "123456"
}
```

- return : 

```json
{
  "code": 0,
  "message": "添加成功"
}
```

### 1.4.3. 修改市场人员信息

- PUT  /user

- payload : 

```json
{
   "systemId" : 2,
   "userName" : "qwe"
   "userId" : "007",
   "userPassword" : "123456"
}
```

- return : 

```json
{
  "code": 0,
  "message": "修改成功"
}
```

### 1.4.4. 删除市场人员信息

- DELETE  /doctor/{systemId}

- return : 

```json
{
  "code": 0,
  "message": "删除成功"
}
```

### 1.4.5. 查询市场人员信息

- GET  /user

- return : 

```json
{
  "code": 0,
  "data": [
    {
      "systemId": 2,
      "userPassword": "123456",
      "userType": 3,
      "userName": "asd",
      "userId": "asd"
    }
  ]
}
```

### 1.4.6. 维护总金额，患者总数

- PUT  /update 
- 只需要勾选你要更新的医生信息，然后把 systemId  以下面的形式传给我就行了
- payload : 

```json
{
    "al" : [1, 2]
}
```

- return : 

```json
{
  "code": 0,
  "message": "批量维护成功"
}
```

### 1.4.7. 增加省、市、医院、科室
- POST /info-add
- payload : 
```json
{
  "province" : "省三",
  "city" : "驻马店市",
  "hospital" : "aaa",
  "room" : "lala"
}
```

- return 
```json
{
  "code": 0,
  "message": "插入成功"
}
```
### 1.4.8. 删除省、市、医院、科室
- DELETE   /info/{systemId}
- return 
```json
{
  "code": 0,
  "message": "删除成功"
}
```
### 1.4.9. 修改省、市、医院、科室
- PUT  /info-update
- payload : 
```json
{
  "province" : "省三",
  "city" : "驻马店市",
  "hospital" : "aaa",
  "room" : "lala"
}
```
- return 
```json
{
  "code": 0,
  "message": "更新成功"
}
```

### 1.4.10 查询省
- GET /info-province

```json
{
  "code": 0,
    "data": [
        "省2",
        "省4",
        "11",
        "1"
    ]
}
```

### 1.4.11 查询市
- POST /info-city

- payload :
```json
{
  "province" : "省2"
}
```

- return : 

```json
{
    "code": 0,
    "data": [
        "市2",
        "市4",
        "市3",
        "11",
        "1"
    ]
}
```


### 1.4.12 查询医院
- POST /info-hospital

- payload :
```json
{
  "province" : "省2",
  "city" "市2"
}
```
- return :

```json
{
    "code": 0,
    "data": [
        "哈医大二院",
        "哈医大四院",
        "5医院",
        "11",
        "1"
    ]
}
```

### 1.4.13 查询科室
- POST /info-room

- payload :
```json
{
  "province" : "省2",
  "city" "市2",
  "hospital" "哈医大二院"
}
```
- return :

```json
{
    "code": 0,
    "data": [
        "ke3",
        "科四",
        "科5",
        "11",
        "1"
    ]
}
```

---


## 1.5. 超级管理员系统

### 拥有管理员和市场人员的功能

### 1.5.2 条件查询总金额，月增金额，医生总数，患者总数

- POST /super-select

- payload :

```json
{
  "docProvince" : "aa",
  "docCity" : "aa",
  "docHospital" : "aa",
  "docRoom" : "aa"
}
```

- return :

```json
{
    "code": 0,
    "data": {
        "totalMoney": 0.0,
        "totalIncMoney": 0.0,
        "totalPeople": 0,
        "totalIncPeople": 0
    }
}
```
### 1.5.3. 添加管理员信息

- POST /manager
- payload :

```json
{
    "userName" : "008",
    "userId" : "008",
    "userPassword" : "123456"
}
```
- return : 

```json
{
  "code": 0,
  "message": "插入成功"
}
```

### 1.5.4. 修改管理员信息

- PUT  /manager
- systemId 是查询返回的，修改的时候带上，这个不能改，所有的修改都不能改systemId,
- 因为是从查询获取的
- payload : 

```json
{
   "systemId" : 4,
   "userName" : "009"
   "userId" : "008",
   "userPassword" : "123456"
}
```

- return : 

```json
{
  "code": 0,
  "message": "修改成功"
}
```

### 1.5.5. 删除管理员信息

- DELETE  /manager/{systemId}

- return : 

```json
{
  "code": 0,
  "message": "删除成功"
}
```

### 1.5.6. 查询所有管理员

- GET  /manager-all

- return :

```json
{
  "code": 0,
  "data": [
    {
      "systemId": 5,
      "userPassword": "123456",
      "userType": 2,
      "userName": "008",
      "userId": "008"
    },
    {
      "systemId": 6,
      "userPassword": "123456",
      "userType": 2,
      "userName": "009",
      "userId": "009"
    }
  ]
}
```

### 1.5.7. 查询管理员信息

- POST  /manager-part
- 类似这种查询均是条件查询
- payload : 

```json
{
  "userPassword": "123456",
  "userType": 2,
  "userName": "009",
  "userId": "009"
}
```

- return : 

```json
{
  "code": 0,
  "data": [
    {
      "systemId": 6,
      "userPassword": "123456",
      "userType": 2,
      "userName": "009",
      "userId": "009"
    }
  ]
}
``` 

