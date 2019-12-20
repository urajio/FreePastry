/*******************************************************************************

"FreePastry" Peer-to-Peer Application Development Substrate

Copyright 2002-2007, Rice University. Copyright 2006-2007, Max Planck Institute 
for Software Systems.  All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

- Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.

- Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimer in the
documentation and/or other materials provided with the distribution.

- Neither the name of Rice  University (RICE), Max Planck Institute for Software 
Systems (MPI-SWS) nor the names of its contributors may be used to endorse or 
promote products derived from this software without specific prior written 
permission.

This software is provided by RICE, MPI-SWS and the contributors on an "as is" 
basis, without any representations or warranties of any kind, express or implied 
including, but not limited to, representations or warranties of 
non-infringement, merchantability or fitness for a particular purpose. In no 
event shall RICE, MPI-SWS or contributors be liable for any direct, indirect, 
incidental, special, exemplary, or consequential damages (including, but not 
limited to, procurement of substitute goods or services; loss of use, data, or 
profits; or business interruption) however caused and on any theory of 
liability, whether in contract, strict liability, or tort (including negligence
or otherwise) arising in any way out of the use of this software, even if 
advised of the possibility of such damage.

*******************************************************************************/ 
package rice.p2p.glacier.v2.messaging;

import rice.p2p.commonapi.Endpoint;
import rice.p2p.commonapi.Id;
import rice.p2p.commonapi.IdRange;
import rice.p2p.commonapi.NodeHandle;
import rice.p2p.commonapi.rawserialization.InputBuffer;
import rice.p2p.commonapi.rawserialization.OutputBuffer;
import rice.p2p.glacier.v2.BloomFilter;

import java.io.IOException;

public class GlacierSyncMessage extends GlacierMessage {
  public static final short TYPE = 14;

  protected IdRange range;
  protected int offsetFID;
  protected BloomFilter BloomFilter;

  public GlacierSyncMessage(int uid, IdRange range, int offsetFID, BloomFilter BloomFilter, NodeHandle source, Id dest, char tag) {
    super(uid, source, dest, false, tag);

    this.range = range;
    this.offsetFID = offsetFID;
    this.BloomFilter = BloomFilter;
  }

  public int getOffsetFID() {
    return offsetFID;
  }

  public IdRange getRange() {
    return range;
  }

  public BloomFilter getBloomFilter() {
    return BloomFilter;
  }

  public String toString() {
    return "[GlacierSync for range "+range+", offset "+offsetFID+"]";
  }
  
  /***************** Raw Serialization ***************************************/
  public short getType() {
    return TYPE; 
  }
  
  public void serialize(OutputBuffer buf) throws IOException {
    buf.writeByte((byte)0); // version    
    super.serialize(buf);
    buf.writeInt(offsetFID);
    range.serialize(buf);
    BloomFilter.serialize(buf);
  }
  
  public static GlacierSyncMessage build(InputBuffer buf, Endpoint endpoint) throws IOException {
    byte version = buf.readByte();
      if (version == 0) {
          return new GlacierSyncMessage(buf, endpoint);
      }
      throw new IOException("Unknown Version: " + version);
  }
    
  private GlacierSyncMessage(InputBuffer buf, Endpoint endpoint) throws IOException {
    super(buf, endpoint);
    offsetFID = buf.readInt();
    range = endpoint.readIdRange(buf);
    BloomFilter = new BloomFilter(buf);
  }
}

