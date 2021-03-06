package tl;

import java.nio.ByteBuffer;

public class Video extends tl.TVideo {

  
  public Video(ByteBuffer buffer) throws Exception {
    id = buffer.getLong();
    access_hash = buffer.getLong();
    user_id = buffer.getInt();
    date = buffer.getInt();
    caption = new String(TL.readString(buffer), "UTF8");
    duration = buffer.getInt();
    size = buffer.getInt();
    thumb = (tl.TPhotoSize) TL.read(buffer);
    dc_id = buffer.getInt();
    w = buffer.getInt();
    h = buffer.getInt();
  }
  
  public Video(long id, long access_hash, int user_id, int date, String caption, int duration, int size, tl.TPhotoSize thumb, int dc_id, int w, int h) {
    this.id = id;
    this.access_hash = access_hash;
    this.user_id = user_id;
    this.date = date;
    this.caption = caption;
    this.duration = duration;
    this.size = size;
    this.thumb = thumb;
    this.dc_id = dc_id;
    this.w = w;
    this.h = h;
  }
  
  public ByteBuffer writeTo(ByteBuffer buffer, boolean boxed) throws Exception {
    int oldPos = buffer.position();
    if (boxed) {
      buffer.putInt(0x5a04a49f);
    }
    buffer.putLong(id);
    buffer.putLong(access_hash);
    buffer.putInt(user_id);
    buffer.putInt(date);
    TL.writeString(buffer, caption.getBytes("UTF8"), false);
    buffer.putInt(duration);
    buffer.putInt(size);
    thumb.writeTo(buffer, true);
    buffer.putInt(dc_id);
    buffer.putInt(w);
    buffer.putInt(h);
    if (oldPos + length() + (boxed ? 4 : 0) != buffer.position()) {
      System.err.println("Invalid length at Video: expected " + (length() + (boxed ? 4 : 0)) + " bytes, got " + (buffer.position() - oldPos));
    }
  	return buffer;
  }
  
  public int length() throws Exception {
    return 48 + TL.length(caption.getBytes("UTF8")) + thumb.length();
  }
  
  public String toString() {
    return "(video id:" + String.format("0x%016x", id) + " access_hash:" + String.format("0x%016x", access_hash) + " user_id:" + user_id + " date:" + date + " caption:" + "caption" + " duration:" + duration + " size:" + size + " thumb:" + thumb + " dc_id:" + dc_id + " w:" + w + " h:" + h + ")";
  }
}
