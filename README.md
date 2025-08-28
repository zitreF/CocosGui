# CocosGui
The comfortable way of creating and managing bukkit inventories.

# Setup
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.zitreF</groupId>
    <artifactId>CocosGui</artifactId>
    <version>1.7</version>
</dependency>
```


```gradle
repositories {
  maven { url 'https://jitpack.io' }
}

dependencies {
  implementation 'com.github.zitreF:CocosGui:1.7'
}
```

# Start
```java
@Override
public void onEnable() {
  // Registers all listeners for gui
  CocosGui.initialize();
}
```
