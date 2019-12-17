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
package rice.p2p.aggregation.messaging;

import rice.p2p.commonapi.Id;
import rice.p2p.commonapi.Message;
import rice.p2p.commonapi.NodeHandle;

public abstract class AggregationMessage implements Message {

  // the unique id for this message
  protected int id;

  // the source Id of this message
  protected NodeHandle source;

  // the destination of this message
  protected Id dest;
  
  /**
   * Constructor which takes a unique integer Id
   *
   * @param id The unique id
   * @param source The source address
   * @param dest The destination address
   */
  protected AggregationMessage(int id, NodeHandle source, Id dest) {
    this.id = id;
    this.source = source;
    this.dest = dest;
  }
  
  /**
   * Method which should return the priority level of this message.  The messages
   * can range in priority from 0 (highest priority) to Integer.MAX_VALUE (lowest) -
   * when sending messages across the wire, the queue is sorted by message priority.
   * If the queue reaches its limit, the lowest priority messages are discarded.  Thus,
   * applications which are very verbose should have LOW_PRIORITY or lower, and
   * applications which are somewhat quiet are allowed to have MEDIUM_PRIORITY or
   * possibly even HIGH_PRIORITY.
   *
   * @return This message's priority
   */
  public int getPriority() {
    return MEDIUM_LOW_PRIORITY;
  }
  
  /**
   * Method which returns this messages' unique id
   *
   * @return The id of this message
   */
  public int getUID() {
    return id;
  }

  /**
   * Method which returns this messages' source address
   *
   * @return The source of this message
   */
  public NodeHandle getSource() {
    return source;
  }

  /**
   * Method which returns this messages' destination address
   *
   * @return The dest of this message
   */
  public Id getDestination() {
    return dest;
  }
  
//  public AggregationMessage(InputBuffer buf, Endpoint endpoint) throws IOException {
//    id = buf.readInt();
//    dest = endpoint.readId(buf, buf.readShort());
//    source = endpoint.readNodeHandle(buf);
//  }
//  
//  public void serialize(OutputBuffer buf) throws IOException {
//    buf.writeInt(id);
//    buf.writeShort(dest.getType());
//    dest.serialize(buf);
//    source.serialize(buf);
//  }  
}

