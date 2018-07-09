# Messaging

Java API for easy serialization and de-serialization of Facebooks Messenger Platform API. Currently being actively developed.

## Supported FB Graph API versions

## Install

### Gradle

Messaging uses the JitPack service, add the following to your `build.gradle`:

```gradle
repositories {
  ...
  maven { 
    name "jitpack"
    url "https://jitpack.io"
  }
  ...
}

dependencies {
  implementation 'com.github.hawry:messaging:0.2.1'
}
```

Note that you can replace the `0.2.1` with a specific release tag or any other short commit hash.

### Maven

```xml
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```

```xml
<dependency>
    <groupId>com.github.hawry</groupId>
    <artifactId>messaging</artifactId>
    <version>0.2.1</version>
</dependency>
```

### Other

## Usage

### Prerequisities

### Simple example

### Mark message as 'seen'

```java
import net.hawry.messaging.core.Message;
import net.hawry.messaging.core.MessageBuilder;

/* ... */

MessageBuilder builder = new MessageBuilder();
Message msg = builder.setRecipient(new Participant("123"))
                .setSenderAction(SenderActionType.MARK_SEEN)
                .create();

String json;

try {
  json = msg.toJson();
} catch (MissingRequiredFieldsException e) {
  e.printStackTrace();
}

System.out.println(json); /* results in {"recipient": {"id": "123"}, "sender_action": "mark_seen"}

/* ... */
```

## License

MIT

## Contributions

Contributions welcome, fork the project and create a pull request.