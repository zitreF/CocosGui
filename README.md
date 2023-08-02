# CocosGui
The comfortable way of creating and managing bukkit inventories.

# Setup
```gradle
repositories {
  maven { url 'https://jitpack.io' }
}

dependencies {
  implementation 'com.github.zitreF:CocosGui:1.1'
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
