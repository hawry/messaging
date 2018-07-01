package net.hawry.messaging.core;

import com.google.gson.annotations.SerializedName;

import net.hawry.messaging.exceptions.MissingRequiredFieldException;

public class Content {
  @SerializedName("text") protected String text;
  // @SerializedName("attachment") protected Attachment attachment;
  // @SerializedName("quick_replies") protected QuickReply[] quickReplies;

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

 void validate() throws MissingRequiredFieldException {
    if (this.text==null)
      throw new MissingRequiredFieldException("text");
  }
}