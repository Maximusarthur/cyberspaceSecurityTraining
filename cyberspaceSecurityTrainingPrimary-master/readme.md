# 饿了吗软件的开发模仿

## 介绍
    Time:2023/4 - 2023/6
    author:MaximusCoder
    Introduce:软件开发学习，初步学习了数据库、Java开发（JDBC阶段），以及学习了网页开发的许多细节，如Tailwind CSS，毕竟我之前学过一定的网页设计与开发.
    这个项目是模仿饿了么的软件开发。

## jdbc部分
1.  个人用户界面
2.  商家界面
3.  骑手界面
4.  购物界面
5.  商家管理界面

### 饿了吗项目
#### 个人用户界面：
1.商品分类界面：
为用户展示不同的商品分类，如火锅、烧烤、自助餐、晚餐、午餐等；点击相应分类可以进入相应分类商店列表界面。

不同类型的店铺列表：展示各个店铺的特色菜、店铺评分、店铺优惠；点击店铺进入店铺详情页面。

店铺详情页：展示店铺的联系电话、店铺位置、店铺评分、店铺的特色菜；点击店铺商品可以进入商品详情页。

商品详情页：展示商品价格、商品评价、商品备注等；在此页面可以将商品加入购物车。

2. 个人详情页：管理个人信息（账号昵称、收货地址、账号密码等信息设置）、进行购物、订单管理；点击个人订单管理可以进入订单列表页面。

（1）个人订单管理：展示未支付商品、待收货商品、待评价商品、退款商品；进行订单的支付、删除、评价。

（2）个人信息的更新，账号的注册与注销。

#### 店铺用户界面：
1. 店铺详情管理
   在此界面可以查看和编辑店铺的基本信息（店铺的名字、联系电话、店铺评分、店铺位置），选择商铺的分类。

商品管理：在此界面可以编辑商品信息（商品图片、价格、备注等），设置特色菜，将商品分为各个小类。

2. 订单管理
   查看订单的详细信息：主要是展示订单的详细信息（订单中的菜品、备注信息以及领单骑手）、订单时效信息，其次删除已完成订单等。

#### 骑手用户界面：
1. 订单管理
   （1）订单选择：展示所有的订单供骑手选择，骑手可以选择最近的合适的订单进行配送。

（2）已接订单详情：选择订单后，可以在此界面骑手可以查看订单的手机号、订单详细地址、订单备注信息。

2. 账号信息管理
   个人信息的更新，账号的注册与注销。

#### JDBC阶段运行截图：https://github.com/Maximusarthur/cyberspaceSecurityTraining/blob/main/cyberspaceSecurityTrainingPrimary-master/images/image17.png
![image]([cyberspaceSecurityTrainingPrimary-master/images/image16.png](https://github.com/Maximusarthur/cyberspaceSecurityTraining/blob/main/cyberspaceSecurityTrainingPrimary-master/images/image17.png))
![image](cyberspaceSecurityTrainingPrimary-master/images/image18.png "jdbc")
![image](cyberspaceSecurityTrainingPrimary-master/images/image19.png "jdbc")
![image](cyberspaceSecurityTrainingPrimary-master/images/image20.png "jdbc")
![image](cyberspaceSecurityTrainingPrimary-master/images/image21.png "jdbc")
![image](cyberspaceSecurityTrainingPrimary-master/images/image22.png "jdbc")
![image](images\image23.png "jdbc")
![image](images\image24.png "jdbc")
![image](images\image25.png "jdbc")
![image](images\image26.png "jdbc")
![image](images\image27.png "jdbc")


## Web部分
网页部分设计了首页、商家列表、订单列表、用户登录、用户注册、商家详情、确认订单和订单支付界面

1. 首页显示了点餐分类信息、推荐商家以及一些平台信息，点击分类图片可以跳转到商家列表页面；点击推荐商家可以跳转到商家详情界面；在底部导航栏中，点击发现
   也可以跳转到商家列表；点击订单可以跳转到订单列表；点击我的图标跳转到用户登录页面。在用户登录界面可以填写用户手机号与密码，点击注册按钮可以跳转到注册页面，
   在注册界面可以进行手机号、密码、姓名的填写与性别的选择。

2. 在推荐商家部分与商家列表部分点击商家就可以跳转到商家详情页面，显示商家的一部分信息与商家的商品，每个商品可以选择数量，底部可以显示购物车与已经选择的商品的总金额，
   还有一个“去结算”按钮，点击这个按钮，可以跳转到确认订单页面。在确认订单页面，可以看到订单中的用户信息、商家名字，与商品详情等信息，在确认信息后可以跳转到订单支付界面。
   在订单支付界面可以看到订单的商品单价与商品数量；然后可以选择支付方式（微信支付与支付宝支付），然后点击确认支付就可以支付了。

3. 在开始时使用 HTML+CSS+JS 技术，编写了网页文件；使用 Tailwindcss 编写了 scss 文件，Tailwind CSS 提供了一套丰富的预定义类，可以直接在 HTML元素上应用这些类来实现常见的样式，从而加快开发速度。不需要手动编写大量的 CSS 代码，只需在 HTML 中和添加适当的类名或在 scss 文件添加少量类名的即可。Tailwind CSS 的类名命名方式非常直观和简洁，可以根据需要自由组合和定制样式，而不需要书写额外的 CSS。这种灵活性使得开发者可以更轻松地调整和定制网页的外观和布局。同时在网页中加入了少量的 JavaScript 代码，用来实现商家详情页面中商品数量的增加。
   ![图片](images\image39.png "Tailwind CSS")

#### Web阶段运行截图：
![图片](images\image38.png "web")
![图片](images\image30.png "web")
![图片](images\image31.png "web")
![图片](images\image31.png "web")
![图片](images\image32.png "web")
![图片](images\image33.png "web")
![图片](images\image34.png "web")
![图片](images\image35.png "web")
![图片](images\image36.png "web")
![图片](images\image37.png "web")
