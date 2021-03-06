package tl.account;

import tl.TL;
import java.nio.ByteBuffer;

public class UnregisterDevice extends tl.TLFunction {
  public int token_type;
  public String token;
  
  public UnregisterDevice(ByteBuffer buffer) throws Exception {
    token_type = buffer.getInt();
    token = new String(TL.readString(buffer), "UTF8");
  }
  
  public UnregisterDevice(int token_type, String token) {
    this.token_type = token_type;
    this.token = token;
  }
  
  public ByteBuffer writeTo(ByteBuffer buffer, boolean boxed) throws Exception {
    int oldPos = buffer.position();
    if (boxed) {
      buffer.putInt(0x65c55b40);
    }
    buffer.putInt(token_type);
    TL.writeString(buffer, token.getBytes("UTF8"), false);
    if (oldPos + length() + (boxed ? 4 : 0) != buffer.position()) {
      System.err.println("Invalid length at UnregisterDevice: expected " + (length() + (boxed ? 4 : 0)) + " bytes, got " + (buffer.position() - oldPos));
    }
  	return buffer;
  }
  
  public int length() throws Exception {
    return 4 + TL.length(token.getBytes("UTF8"));
  }
  
  public String toString() {
    return "(account.unregisterDevice token_type:" + token_type + " token:" + "token" + ")";
  }
}
