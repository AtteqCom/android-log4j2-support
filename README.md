# android-log4j2-support

This library adds the basic support for log4j-2.3 for Android. The libraries relying on log4j work on Android and Logcat appender is used to log their output.

## Usage in Android applications

### Setting up Gradle build

```
repositories {
    maven {
        url 'http://dev.atteq.com/maven2/'
    }
}

dependencies {
    compile 'com.atteq:android-log4j2-support:2.3'
}
```

### Initialization of log4j

In the `onCreate` method of your application you may use the following code

```
AndroidLog4jHelper.initialise(getApplicationContext(), R.raw.log4j);
```

The contents of `src/main/res/raw/log4j.xml` then may look as follows.

```
<?xml version="1.0" encoding="UTF-8"?>

<Configuration status="debug">
    <Appenders>
        <Logcat name="Logcat">
            <ThresholdFilter
                level="ALL"
                onMatch="ACCEPT"
                onMismatch="DENY" />
            <PatternLayout pattern="%m" />
        </Logcat>
    </Appenders>

    <Loggers>
        <Root level="DEBUG">
            <AppenderRef ref="Logcat" />
        </Root>
    </Loggers>
</Configuration>
```

### Using log4j

Finally you may use log4j as usual.

```
Logger.getLogger("org.apache").setLevel(Level.ERROR); // You would set this via config.
Logger.getLogger("my.fancy.logger").error("blaaah"); // You would normally use Log.e(...)
```

## Credits

The code is highly inspired by https://loune.net/2016/05/using-log4j2-2-3-with-android/ and https://github.com/loune/log4j2-android. The main advantage is that the library can be simply added to Gradle and initialized in the application `onCreate` method.
