tips
- 数据库doctor表doc_tag字段改成了char(6), 和user表user_id对应
- 添加医生信息中的文件未完成
- 增删改查省、市、医院、科室功能未完成
- ？ 添加修改删除咱们的结果是
```json
{
  "code": 0,
  "message": "删除成功"
}
```
别人的是
```json
{
    "code" : 0,
    "data" : true
}
```



# 1. 医生信息管理系统-API

"

- [1. 医生信息管理系统-API](#1-医生信息管理系统-API)
   - [1.1. tips](#11-tips)
   - [1.2. 登陆](#12-登陆)
   - [1.3. 市场人员系统](#13-市场人员系统)
     - [1.3.1. 添加医生信息](#131-添加医生信息)
     - [1.3.2. 修改医生信息](#132-修改医生信息)
     - [1.3.3. 删除医生信息](#133-删除医生信息)
     - [1.3.4. 查询自己的医生信息](#134-查询自己的医生信息)
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
     - [1.5.3. 添加管理员信息](#153-添加管理员信息)
     - [1.5.4. 修改管理员信息](#154-修改管理员信息)
     - [1.5.5. 删除管理员信息](#155-删除管理员信息)
     - [1.5.6. 查询管理员信息](#156-查询管理员信息)
   

##  1.1. tips

- 用户类别 : 
  - 市场人员
  - 管理员
  - 超级管理员
  
- code :
  - 0 : 正常
  - 1 : 添加 message 字段展示错误
  - 2 : 登陆失败
  
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
    "type": 2,
    "systemId": 1,
    "token": "8cad118283564b4581e0cf5cd69ad9c6"
  }
}
```

## 1.3. 市场人员系统

### 1.3.1 添加医生信息

- POST /user
- payload :

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

- return : 

```json
{
  "code": 0,
  "message": "添加成功"
}
```

---

### 1.3.2 修改医生信息

- PUT  /doctor
- payload : 

```json
{
    "systemId" : 2,
   "docName" : "江小白"
}
```

- return : 

```json
{
  "code": 0,
  "message": "修改成功"
}
```

---

### 1.3.3 删除医生信息

- DELETE  /doctor/{systemId}

- return : 

```json
{
  "code": 0,
  "message": "删除成功"
}
```

---

### 1.3.4 查询自己的医生信息

- GET  /doctor?userId={userId}

- return : 

```json
{
  "code": 0,
  "data": [
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

---

##  1.4. 管理员系统 

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

---

### 1.4.2. 添加市场人员信息

- POST /user
- payload :

```json
{
    "userName" : "007",
    "userId" : "007",
    "userType" : 3,
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

---

### 1.4.3. 修改市场人员信息

- PUT  /doctor

- payload : 

```json
{
   "systemId" : 2,
   "userName" : "qwe"
}
```

- return : 

```json
{
  "code": 0,
  "message": "修改成功"
}
```

---

### 1.4.4. 删除市场人员信息

- DELETE  /doctor/{systemId}

- return : 

```json
{
  "code": 0,
  "message": "删除成功"
}
```

---

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

---

### 1.4.6. 维护总金额，患者总数

- PUT  /update 

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

---

### 1.4.7. 增加省、市、医院、科室
### 1.4.8. 删除省、市、医院、科室
### 1.4.9. 修改省、市、医院、科室
### 1.4.10. 查询省、市、医院、科室
## 1.5. 超级管理员系统
### 1.5.3. 添加管理员信息
### 1.5.4. 修改管理员信息
### 1.5.5. 删除管理员信息
### 1.5.6. 查询管理员信息