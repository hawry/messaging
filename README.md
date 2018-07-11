[![Build Status](https://travis-ci.org/Hawry/messaging.svg?branch=master)](https://travis-ci.org/Hawry/messaging)

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
  implementation 'com.github.hawry:messaging:0.3.0'
}
```

Note that you can replace the `0.3.0` with a specific release tag or any other short commit hash.

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
    <version>0.3.0</version>
</dependency>
```

### Other

## Usage

### Usage example

#### Receive a webhook event

```java
import net.hawry.messaging.core.webhook;

/* ... */
// the following is linebreaked for brevity and won't compile
String payload = "{
  'object':'page',
  'entry':[
    {
      'id':'<PAGE_ID>',
      'time':1458692752478,
      'messaging':[
        {
          'sender':{
            'id':'<PSID>'
          },
          'recipient':{
            'id':'<PAGE_ID>'
          },
          'message':{
            'mid':'mid.1457764197618:41d102a3e1ae206a38',
            'text':'hello, world!',
          }
        }
      ]
    }
  ]
}";

try {
  Event event = Event.fromJson(payload);

  System.out.println(event.getObject()); // results in 'page'
  for (Entry entry : event.getEntries()) {
    System.out.println(entry.getId()); // results in <PAGE_ID>
    Messaging m = entry.getMessaging(); // even though it's an array in the JSON-string, according to the docs there will only ever be a single messaging-object
    System.out.println(m.getSender().getId()); // <PSID>
    System.out.println(m.getRecipient().getId()); // <PAGE_ID>
    
    WebhookMessage msg = m.getMessage();
    System.out.println(msg.getMessageId()); // $mid.1457764197618:41d102a3e1ae206a38
    System.out.println(msg.getText()); // hello, world

  }
} catch (InvalidJsonException e) {
  e.printStackTrace();
}

```

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