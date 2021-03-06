package tl.upload;

import tl.TL;
import java.nio.ByteBuffer;

public class SaveFilePart extends tl.TLFunction {
  public long file_id;
  public int file_part;
  public byte[] bytes;
  
  public SaveFilePart(ByteBuffer buffer) throws Exception {
    file_id = buffer.getLong();
    file_part = buffer.getInt();
    bytes = TL.readString(buffer);
  }
  
  public SaveFilePart(long file_id, int file_part, byte[] bytes) {
    this.file_id = file_id;
    this.file_part = file_part;
    this.bytes = bytes;
  }
  
  public ByteBuffer writeTo(ByteBuffer buffer, boolean boxed) throws Exception {
    int oldPos = buffer.position();
    if (boxed) {
      buffer.putInt(0xb304a621);
    }
    buffer.putLong(file_id);
    buffer.putInt(file_part);
    TL.writeString(buffer, bytes, false);
    if (oldPos + length() + (boxed ? 4 : 0) != buffer.position()) {
      System.err.println("Invalid length at SaveFilePart: expected " + (length() + (boxed ? 4 : 0)) + " bytes, got " + (buffer.position() - oldPos));
    }
  	return buffer;
  }
  
  public int length() throws Exception {
    return 12 + TL.length(bytes);
  }
  
  public String toString() {
    return "(upload.saveFilePart file_id:" + String.format("0x%016x", file_id) + " file_part:" + file_part + " bytes:" + TL.toString(bytes) + ")";
  }
}
