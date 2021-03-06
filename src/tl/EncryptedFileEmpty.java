package tl;

import java.nio.ByteBuffer;

public class EncryptedFileEmpty extends tl.TEncryptedFile {

  
  public EncryptedFileEmpty(ByteBuffer buffer) throws Exception {

  }
  
  public EncryptedFileEmpty() {

  }
  
  public ByteBuffer writeTo(ByteBuffer buffer, boolean boxed) throws Exception {
    int oldPos = buffer.position();
    if (boxed) {
      buffer.putInt(0xc21f497e);
    }

    if (oldPos + length() + (boxed ? 4 : 0) != buffer.position()) {
      System.err.println("Invalid length at EncryptedFileEmpty: expected " + (length() + (boxed ? 4 : 0)) + " bytes, got " + (buffer.position() - oldPos));
    }
  	return buffer;
  }
  
  public int length() throws Exception {
    return 0;
  }
  
  public String toString() {
    return "(encryptedFileEmpty)";
  }
}
