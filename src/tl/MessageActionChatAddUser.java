package tl;

import java.nio.ByteBuffer;

public class MessageActionChatAddUser extends tl.TMessageAction {

  
  public MessageActionChatAddUser(ByteBuffer buffer) throws Exception {
    user_id = buffer.getInt();
  }
  
  public MessageActionChatAddUser(int user_id) {
    this.user_id = user_id;
  }
  
  public ByteBuffer writeTo(ByteBuffer buffer, boolean boxed) throws Exception {
    int oldPos = buffer.position();
    if (boxed) {
      buffer.putInt(0x5e3cfc4b);
    }
    buffer.putInt(user_id);
    if (oldPos + length() + (boxed ? 4 : 0) != buffer.position()) {
      System.err.println("Invalid length at MessageActionChatAddUser: expected " + (length() + (boxed ? 4 : 0)) + " bytes, got " + (buffer.position() - oldPos));
    }
  	return buffer;
  }
  
  public int length() throws Exception {
    return 4;
  }
  
  public String toString() {
    return "(messageActionChatAddUser user_id:" + user_id + ")";
  }
}
