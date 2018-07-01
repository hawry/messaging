package net.hawry.messaging.core.webhook;

import com.google.gson.annotations.SerializedName;

import net.hawry.messaging.core.Participant;

public class Messaging {
  @SerializedName("sender") Participant sender;
  @SerializedName("recipient") Participant recipient;
  @SerializedName("timestamp") long timestamp;
  @SerializedName("message") WebhookMessage message;
  @SerializedName("seq") int seq; // part of examples, but not in the spec - unsure if it's something that will be received

  public Participant getSender() {
    return this.sender;
  }

  public Participant getRecipient() {
    return this.recipient;
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public WebhookMessage getMessage() {
    return this.message;
  }

  public int getSeq() {
    return this.seq;
  }
}