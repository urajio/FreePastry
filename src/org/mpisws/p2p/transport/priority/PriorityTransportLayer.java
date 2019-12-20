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
package org.mpisws.p2p.transport.priority;

import org.mpisws.p2p.transport.TransportLayer;
import org.mpisws.p2p.transport.TransportLayerListener;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Does 3 things:
 *  a) Sends messages on a Socket (depending on the options).  
 *  b) Prioritizes messages into queues.
 *  c) calls sendFailed if there is a liveness change
 *  
 * @author Jeff Hoye
 */
public interface PriorityTransportLayer<Identifier> extends TransportLayer<Identifier, ByteBuffer> {
  String OPTION_PRIORITY = "OPTION_PRIORITY";
  
  // different priority levels
  byte MAX_PRIORITY = -15;
  byte HIGH_PRIORITY = -10;
  byte MEDIUM_HIGH_PRIORITY = -5;
  byte MEDIUM_PRIORITY = 0;
  byte MEDIUM_LOW_PRIORITY = 5;
  byte LOW_PRIORITY = 10;
  byte LOWEST_PRIORITY = 15;
  byte DEFAULT_PRIORITY = MEDIUM_PRIORITY;

  int STATUS_NOT_CONNECTED = 0;
  int STATUS_CONNECTING = 1;
  int STATUS_CONNECTED = 2;
  
  
  void addTransportLayerListener(TransportLayerListener<Identifier> listener);
  void removeTransportLayerListener(TransportLayerListener<Identifier> listener);
  void addPriorityTransportLayerListener(PriorityTransportLayerListener<Identifier> listener);
  void removePriorityTransportLayerListener(PriorityTransportLayerListener<Identifier> listener);

  /**
   * Returns if there is a primary connection to the identifier
   * 
   * @param i
   * @return STATUS_NOT_CONNECTED, STATUS_CONNECTING, STATUS_CONNECTED
   */
  int connectionStatus(Identifier i);
  
  /**
   * Returns the options on the primary connection
   * @param i
   * @return
   */
  Map<String, Object> connectionOptions(Identifier i);
  
  /**
   * usually used with bytesPending() or queueLength()
   * @return any Identifier with messages to be sent
   */
  Collection<Identifier> nodesWithPendingMessages();
  
  /**
   * Returns the number of messages pending to be sent
   * @param i
   * @return
   */
  int queueLength(Identifier i);
  
  /**
   * The number of bytes to be sent to the identifier
   * @param i
   * @return
   */
  long bytesPending(Identifier i);
  
  /**
   * The number of bytes to be sent to the identifier
   * @param i
   * @return
   */
  List<MessageInfo> getPendingMessages(Identifier i);
  
  /**
   * open a primary connection
   * @param i
   * @param notifyMe when it is open
   */
  void openPrimaryConnection(Identifier i, Map<String, Object> options);
  
  void addPrimarySocketListener(PrimarySocketListener<Identifier> listener);
  void removePrimarySocketListener(PrimarySocketListener<Identifier> listener);

}
