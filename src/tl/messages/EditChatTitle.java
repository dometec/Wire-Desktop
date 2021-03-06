package tl.messages;

import tl.TL;
import java.nio.ByteBuffer;

public class EditChatTitle extends tl.TLFunction {
  public int chat_id;
  public String title;
  
  public EditChatTitle(ByteBuffer buffer) throws Exception {
    chat_id = buffer.getInt();
    title = new String(TL.readString(buffer), "UTF8");
  }
  
  public EditChatTitle(int chat_id, String title) {
    this.chat_id = chat_id;
    this.title = title;
  }
  
  public ByteBuffer writeTo(ByteBuffer buffer, boolean boxed) throws Exception {
    int oldPos = buffer.position();
    if (boxed) {
      buffer.putInt(0xb4bc68b5);
    }
    buffer.putInt(chat_id);
    TL.writeString(buffer, title.getBytes("UTF8"), false);
    if (oldPos + length() + (boxed ? 4 : 0) != buffer.position()) {
      System.err.println("Invalid length at EditChatTitle: expected " + (length() + (boxed ? 4 : 0)) + " bytes, got " + (buffer.position() - oldPos));
    }
  	return buffer;
  }
  
  public int length() throws Exception {
    return 4 + TL.length(title.getBytes("UTF8"));
  }
  
  public String toString() {
    return "(messages.editChatTitle chat_id:" + chat_id + " title:" + "title" + ")";
  }
}
