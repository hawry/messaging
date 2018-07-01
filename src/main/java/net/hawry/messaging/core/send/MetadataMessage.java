package net.hawry.messaging.core.send;

import com.google.gson.annotations.SerializedName;

import net.hawry.messaging.core.Content;

public class MetadataMessage extends Content {
  @SerializedName("metadata") String metadata;

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