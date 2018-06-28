package net.hawry.messaging.core;

import com.google.gson.annotations.SerializedName;

public class Content {
  @SerializedName("text") String text;
  // @SerializedName("attachment") Attachment attachment;
  // @SerializedName("quick_replies") QuickReply[] quickReplies;
  @SerializedName("metadata") String metadata;

  /**
   * Sets the text content of the message. Must be UTF-8 and has a 2000 character limit. The text will be concatenated to the first 2000 characters if the length is exceeded.
   * 
   * @param text the text content of the message
   */
  public void setText(String text) {
    if (text.length() > 2000)
      text = text.substring(0, 2000);
    this.text = text;
  }

  /**
   * @return the text content
   */
  public String getText() {
    return this.text;
  }

  /**
   * Sets the optional metadata string. A character limit of 1000 characters. The string will be concatenated to the first 1000 characters if this limit is exceeded.
   * 
   * @param metadata the metadata to set
   */
  public void setMetadata(String metadata) {
    if (metadata.length() > 1000)
      metadata = metadata.substring(0, 1000);

    this.metadata = metadata;
  }

  /**
   * @return the metadata string
   */
  public String getMetadata() {
    return this.metadata;
  }
}