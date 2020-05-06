## 快速开发系列（IOC框架）

   
IOC框架：参照xUtils整理、开发的一款快速的开发的IOC框架，方便用户快速的进行控件的绑定功能。

### 一、绑定控件

在需要绑定的Activity进行初始化：

```
ViewUtils.inject(this); // 初始化IOC框架
```

对需要绑定的控件通过ViewBind注解进行绑定：

```
@ViewBind(R.id.xxx) //  xxx代表xml中配置的控件id
```
相同的控件也可以使用：

```
@ViewBind({R.id.xxx,R.id.yyy})
```

### 二、绑定点击事件

对需要进行点击的控件通过OnClick注解进行绑定、编写点击的方法：

```
@OnClick(R.id.xxx) 
private void onClick(View view) {
// view可以为空  也可以指定view的具体类型（如：TextView等）
}
```

#### 补充：

通常对点击的按钮有延迟点击、进行网络时会判断网络等操作，因此在本框架中加入了对延迟点击、网络判断的注解操作:主要通过 @OnClickCheck 注解进行，OnClickCheck包含 “type”（代表判断的类型） 、“time”（延迟点击的延迟时间，默认500ms）、“msg”（无网络时的提示语，默认：请检查网络连接！）

##### 1、默认情况，无需任何配置：

```
@OnClickCheck  // 只设置延迟点击,延迟时间500ms
```

##### 2、在需要判断网络的时候使用：

```
@OnClickCheck(type = OnClickCheckUtils.CHECK_NET)
```
如果无网络则吐司 “ 请检查网络连接！”，需要自定义提示语的可以设置提示语：

```
@OnClickCheck(type = 
OnClickCheckUtils.CHECK_NET,msg = "亲，您的网络错误呦！！")
```

##### 3、设置延迟点击以及延迟时间：

```
@OnClickCheck(type =
OnClickCheckUtils.CHECK_CLICKFAST,time = 1000)
```










