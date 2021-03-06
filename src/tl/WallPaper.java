package tl;

import java.nio.ByteBuffer;

public class WallPaper extends tl.TWallPaper {

  
  public WallPaper(ByteBuffer buffer) throws Exception {
    id = buffer.getInt();
    title = new String(TL.readString(buffer), "UTF8");
    sizes = TL.readVector(buffer, true, new tl.TPhotoSize[0]);
    color = buffer.getInt();
  }
  
  public WallPaper(int id, String title, tl.TPhotoSize[] sizes, int color) {
    this.id = id;
    this.title = title;
    this.sizes = sizes;
    this.color = color;
  }
  
  public ByteBuffer writeTo(ByteBuffer buffer, boolean boxed) throws Exception {
    int oldPos = buffer.position();
    if (boxed) {
      buffer.putInt(0xccb03657);
    }
    buffer.putInt(id);
    TL.writeString(buffer, title.getBytes("UTF8"), false);
    TL.writeVector(buffer, sizes, true, true);
    buffer.putInt(color);
    if (oldPos + length() + (boxed ? 4 : 0) != buffer.position()) {
      System.err.println("Invalid length at WallPaper: expected " + (length() + (boxed ? 4 : 0)) + " bytes, got " + (buffer.position() - oldPos));
    }
  	return buffer;
  }
  
  public int length() throws Exception {
    return 16 + TL.length(title.getBytes("UTF8")) + TL.length(sizes);
  }
  
  public String toString() {
    return "(wallPaper id:" + id + " title:" + "title" + " sizes:" + TL.toString(sizes) + " color:" + color + ")";
  }
}
