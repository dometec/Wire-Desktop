package tl;

import java.nio.ByteBuffer;

public class DecryptedMessage extends tl.TDecryptedMessage {

  
  public DecryptedMessage(ByteBuffer buffer) throws Exception {
    random_id = buffer.getLong();
    random_bytes = TL.readString(buffer);
    message = new String(TL.readString(buffer), "UTF8");
    media = (tl.TDecryptedMessageMedia) TL.read(buffer);
  }
  
  public DecryptedMessage(long random_id, byte[] random_bytes, String message, tl.TDecryptedMessageMedia media) {
    this.random_id = random_id;
    this.random_bytes = random_bytes;
    this.message = message;
    this.media = media;
  }
  
  public ByteBuffer writeTo(ByteBuffer buffer, boolean boxed) throws Exception {
    int oldPos = buffer.position();
    if (boxed) {
      buffer.putInt(0x1f814f1f);
    }
    buffer.putLong(random_id);
    TL.writeString(buffer, random_bytes, false);
    TL.writeString(buffer, message.getBytes("UTF8"), false);
    media.writeTo(buffer, true);
    if (oldPos + length() + (boxed ? 4 : 0) != buffer.position()) {
      System.err.println("Invalid length at DecryptedMessage: expected " + (length() + (boxed ? 4 : 0)) + " bytes, got " + (buffer.position() - oldPos));
    }
  	return buffer;
  }
  
  public int length() throws Exception {
    return 12 + TL.length(random_bytes) + TL.length(message.getBytes("UTF8")) + media.length();
  }
  
  public String toString() {
    return "(decryptedMessage random_id:" + String.format("0x%016x", random_id) + " random_bytes:" + TL.toString(random_bytes) + " message:" + "message" + " media:" + media + ")";
  }
}
