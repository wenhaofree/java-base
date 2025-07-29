# fwh-object 模块

## 模块介绍
本模块专注于 Java 基础对象的测试和调用方法，包括 String、Object、包装类等基础对象的使用方法和最佳实践。

## 主要内容

### 1. String 字符串测试 (`StringTest.java`)
- **基本操作**: 字符串创建、拼接、比较
- **常用方法**: 长度获取、大小写转换、查找替换、截取分割
- **工具类使用**: Apache Commons Lang StringUtils 工具类
- **连接方法对比**: String.join、StringJoiner、StringBuilder 等不同方式
- **性能测试**: String vs StringBuilder 性能对比
- **编码处理**: UTF-8、GBK 等不同编码的处理
- **正则表达式**: 字符串的正则匹配和替换

### 2. Object 基础方法测试 (`ObjectMethodsTest.java`)
- **Object 基础方法**: equals、hashCode、toString、getClass
- **Objects 工具类**: 空值判断、比较、字符串转换
- **自定义类实现**: equals 和 hashCode 的正确实现
- **Lombok 注解**: 使用 @Data 等注解简化代码
- **对象克隆**: Cloneable 接口的实现和使用

### 3. 包装类测试 (`WrapperTypesTest.java`)
- **Integer 包装类**: 创建方式、缓存池、类型转换、静态方法
- **Double 包装类**: 特殊值处理、精度问题、BigDecimal 替代方案
- **Boolean 包装类**: 字符串解析、逻辑运算
- **Character 包装类**: 字符判断、转换、Unicode 处理
- **自动装箱拆箱**: 装箱拆箱机制和注意事项
- **BigInteger/BigDecimal**: 大数运算和精确计算

### 4. 综合测试 (`ObjectTest.java`)
- 基础对象方法的简单示例和验证

## 技术特点

### Maven 依赖
- 继承父级项目的 JUnit 和 Lombok 依赖
- 额外添加了 Apache Commons Lang3 和 Google Guava 工具库
- 支持 Java 1.8+ 版本

### 编码规范
- 遵循项目统一的包命名规范：`club.fuwenhao.object`
- 使用详细的注释和文档说明
- 测试方法按功能分组，便于理解和维护
- 输出格式规范，方便查看测试结果

### 最佳实践
- **字符串操作**: 推荐使用 StringBuilder 进行大量字符串拼接
- **空值处理**: 使用 Objects.equals() 等工具方法安全处理空值
- **精度计算**: 金融计算使用 BigDecimal 避免精度丢失
- **对象比较**: 正确实现 equals 和 hashCode 方法
- **性能考虑**: 了解自动装箱拆箱的性能影响

## 使用方法

### 运行测试
```bash
# 运行所有测试
mvn test

# 运行特定测试类
mvn test -Dtest=StringTest
mvn test -Dtest=ObjectMethodsTest
mvn test -Dtest=WrapperTypesTest
```

### 查看测试结果
测试运行后会在控制台输出详细的测试结果，包括：
- 各种方法的执行结果
- 性能对比数据
- 异常处理演示
- 最佳实践示例

## 扩展建议
- 可以添加更多日期时间类的测试（LocalDateTime、Date 等）
- 增加枚举类型的使用示例
- 添加泛型相关的测试用例
- 补充序列化和反序列化的测试

## 注意事项
1. 字符串拼接大量数据时避免使用 + 操作符
2. 比较对象时优先使用 Objects.equals() 方法
3. 浮点数计算涉及金钱时必须使用 BigDecimal
4. 自动装箱可能导致 NullPointerException，需要注意空值检查
5. Integer 缓存池范围是 -128 到 127，超出范围需注意 == 比较结果
