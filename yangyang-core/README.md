# 1springboot应用中异常体系的构建

1.1spring框架Assert抛出的异常处理

通过源码追踪，可以看到Assert最终抛出的是IllegalArgumentException

```
public static void notNull(@Nullable Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}
```



