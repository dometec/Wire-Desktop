package tl.storage;

import tl.TL;
import java.nio.ByteBuffer;

public class FileGif extends tl.storage.TFileType {

  
  public FileGif(ByteBuffer buffer) throws Exception {

  }
  
  public FileGif() {

  }
  
  public ByteBuffer writeTo(ByteBuffer buffer, boolean boxed) throws Exception {
    int oldPos = buffer.position();
    if (boxed) {
      buffer.putInt(0xcae1aadf);
    }

    if (oldPos + length() + (boxed ? 4 : 0) != buffer.position()) {
      System.err.println("Invalid length at FileGif: expected " + (length() + (boxed ? 4 : 0)) + " bytes, got " + (buffer.position() - oldPos));
    }
  	return buffer;
  }
  
  public int length() throws Exception {
    return 0;
  }
  
  public String toString() {
    return "(storage.fileGif)";
  }
}
