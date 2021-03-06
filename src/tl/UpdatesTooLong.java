package tl;

import java.nio.ByteBuffer;

public class UpdatesTooLong extends tl.TUpdates {

  
  public UpdatesTooLong(ByteBuffer buffer) throws Exception {

  }
  
  public UpdatesTooLong() {

  }
  
  public ByteBuffer writeTo(ByteBuffer buffer, boolean boxed) throws Exception {
    int oldPos = buffer.position();
    if (boxed) {
      buffer.putInt(0xe317af7e);
    }

    if (oldPos + length() + (boxed ? 4 : 0) != buffer.position()) {
      System.err.println("Invalid length at UpdatesTooLong: expected " + (length() + (boxed ? 4 : 0)) + " bytes, got " + (buffer.position() - oldPos));
    }
  	return buffer;
  }
  
  public int length() throws Exception {
    return 0;
  }
  
  public String toString() {
    return "(updatesTooLong)";
  }
}
