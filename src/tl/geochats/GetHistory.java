package tl.geochats;

import tl.TL;
import java.nio.ByteBuffer;

public class GetHistory extends tl.TLFunction {
  public tl.TInputGeoChat peer;
  public int offset;
  public int max_id;
  public int limit;
  
  public GetHistory(ByteBuffer buffer) throws Exception {
    peer = (tl.TInputGeoChat) TL.read(buffer);
    offset = buffer.getInt();
    max_id = buffer.getInt();
    limit = buffer.getInt();
  }
  
  public GetHistory(tl.TInputGeoChat peer, int offset, int max_id, int limit) {
    this.peer = peer;
    this.offset = offset;
    this.max_id = max_id;
    this.limit = limit;
  }
  
  public ByteBuffer writeTo(ByteBuffer buffer, boolean boxed) throws Exception {
    int oldPos = buffer.position();
    if (boxed) {
      buffer.putInt(0xb53f7a68);
    }
    peer.writeTo(buffer, true);
    buffer.putInt(offset);
    buffer.putInt(max_id);
    buffer.putInt(limit);
    if (oldPos + length() + (boxed ? 4 : 0) != buffer.position()) {
      System.err.println("Invalid length at GetHistory: expected " + (length() + (boxed ? 4 : 0)) + " bytes, got " + (buffer.position() - oldPos));
    }
  	return buffer;
  }
  
  public int length() throws Exception {
    return 16 + peer.length();
  }
  
  public String toString() {
    return "(geochats.getHistory peer:" + peer + " offset:" + offset + " max_id:" + max_id + " limit:" + limit + ")";
  }
}
