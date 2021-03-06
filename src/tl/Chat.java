package tl;

import java.nio.ByteBuffer;

public class Chat extends tl.TChat {

  
  public Chat(ByteBuffer buffer) throws Exception {
    id = buffer.getInt();
    title = new String(TL.readString(buffer), "UTF8");
    photo = (tl.TChatPhoto) TL.read(buffer);
    participants_count = buffer.getInt();
    date = buffer.getInt();
    left = (buffer.getInt() == 0x997275b5);
    version = buffer.getInt();
  }
  
  public Chat(int id, String title, tl.TChatPhoto photo, int participants_count, int date, boolean left, int version) {
    this.id = id;
    this.title = title;
    this.photo = photo;
    this.participants_count = participants_count;
    this.date = date;
    this.left = left;
    this.version = version;
  }
  
  public ByteBuffer writeTo(ByteBuffer buffer, boolean boxed) throws Exception {
    int oldPos = buffer.position();
    if (boxed) {
      buffer.putInt(0x6e9c9bc7);
    }
    buffer.putInt(id);
    TL.writeString(buffer, title.getBytes("UTF8"), false);
    photo.writeTo(buffer, true);
    buffer.putInt(participants_count);
    buffer.putInt(date);
    buffer.putInt(left ? 0x997275b5 : 0xbc799737);
    buffer.putInt(version);
    if (oldPos + length() + (boxed ? 4 : 0) != buffer.position()) {
      System.err.println("Invalid length at Chat: expected " + (length() + (boxed ? 4 : 0)) + " bytes, got " + (buffer.position() - oldPos));
    }
  	return buffer;
  }
  
  public int length() throws Exception {
    return 24 + TL.length(title.getBytes("UTF8")) + photo.length();
  }
  
  public String toString() {
    return "(chat id:" + id + " title:" + "title" + " photo:" + photo + " participants_count:" + participants_count + " date:" + date + " left:" + (left ? "true" : "false") + " version:" + version + ")";
  }
}
