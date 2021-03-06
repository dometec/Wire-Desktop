package tl;

import java.nio.ByteBuffer;

public class ResPQ extends tl.TResPQ {

  
  public ResPQ(ByteBuffer buffer) throws Exception {
    nonce = TL.readInt128(buffer);
    server_nonce = TL.readInt128(buffer);
    pq = new java.math.BigInteger(1, TL.readString(buffer));
    server_public_key_fingerprints = TL.readVectorLong(buffer, true);
  }
  
  public ResPQ(byte[] nonce, byte[] server_nonce, java.math.BigInteger pq, long[] server_public_key_fingerprints) {
    this.nonce = nonce;
    this.server_nonce = server_nonce;
    this.pq = pq;
    this.server_public_key_fingerprints = server_public_key_fingerprints;
  }
  
  public ByteBuffer writeTo(ByteBuffer buffer, boolean boxed) throws Exception {
    int oldPos = buffer.position();
    if (boxed) {
      buffer.putInt(0x05162463);
    }
    buffer.put(nonce);
    buffer.put(server_nonce);
    TL.writeString(buffer, pq.toByteArray(), false);
    TL.writeVector(buffer, server_public_key_fingerprints, true, false);
    if (oldPos + length() + (boxed ? 4 : 0) != buffer.position()) {
      System.err.println("Invalid length at ResPQ: expected " + (length() + (boxed ? 4 : 0)) + " bytes, got " + (buffer.position() - oldPos));
    }
  	return buffer;
  }
  
  public int length() throws Exception {
    return 40 + TL.length(pq.toByteArray()) + server_public_key_fingerprints.length * 8;
  }
  
  public String toString() {
    return "(resPQ nonce:" + new java.math.BigInteger(nonce) + " server_nonce:" + new java.math.BigInteger(server_nonce) + " pq:" + "pq" + " server_public_key_fingerprints:" + TL.toString(server_public_key_fingerprints) + ")";
  }
}
