## 测试服务器心跳

本程序用于定时校验现场考试服务器的生存状态，现行只有检验登录和获取静态试卷是否正常。后面可以再度添加。

### 检验项目说明
每个校验的方法都存放在`com.css.check`目录下。现有的校验类说明：

- `TryLogin` 用于校验登录。
- `ReadStatucPaper` 用于校验静态文件，现行的规则是：如果获取的静态文件少于约定的文件大小，则判断为异常。

### 参数配置说明
程序的控制参数都放在`configure.properties`文件中。现有参数说明：

- `IpAndPort` 需要检查的服务器地址与端口号，如果有多个，使用英文符号逗号分隔。有无空格都可以。
- `LoginPath` 校验登录的请求地址（不带IP地址与端口号，“/”开头）。
- `LoginKey` 请求校验登录表单的key。
- `LoginValue`  请求校验登录表单的值value。
- `LoginCtrl` 校验登录的Ctrl
- `StaticPaperPath` 请求获取静态文件的地址（不带IP地址与端口号，“/”开头）。
- `PaperMinSize` 允许最小的静态文件大小，如果获取的静态文件少于此值，则判定为异常试卷。

### 日志打印
本程序分为info级别日志与error级别日志，没有debug级别日志是为了把框架的日志排除。

配置文件为`log4.properties`。

`log4j.appender.D.File = E://logs/info.log` 设置info信息打印路径。
`log4j.appender.E.File =E://logs/error.log` 设置error信息打印路径。


### 打包

在resources文件中，有dev和pro两个文件，用于存放生产环境与开发环境两个不同环境的配置。

打开发环境的jar包命令行：
```shell
 mvn clean package
```

打生产环境的jar包命令行：
```shell
 mvn clean package -Ppro
```


