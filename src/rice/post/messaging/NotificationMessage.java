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
package rice.post.messaging;

import rice.p2p.commonapi.Endpoint;
import rice.p2p.commonapi.rawserialization.InputBuffer;
import rice.p2p.commonapi.rawserialization.OutputBuffer;
import rice.post.PostClientAddress;
import rice.post.PostEntityAddress;

import java.io.IOException;

/**
 * This class represents an abstract message in the Post system
 * which serves as a notification.  Each Post application should
 * extend this class with each type of relevant notification 
 * message.
 */
public abstract class NotificationMessage /*extends PostMessage*/ {
  private PostEntityAddress sender;  
  private PostClientAddress clientAddress;
  private PostEntityAddress destination;
  
  /**
   * Constructs a NotificationMessage for the given Email.
   *
   * @param clientAddress The address of the service to which this message
   *      should be delivered.
   * @param destination The address of the user or group to which this
   *        message should be delivered. 
   */
  public NotificationMessage(PostClientAddress clientAddress, PostEntityAddress sender, PostEntityAddress destination) {
    if (sender == null) {
      throw new IllegalArgumentException("Attempt to build PostMessage with null sender!");
    }
    
    this.sender = sender;
    this.clientAddress = clientAddress;
    this.destination = destination;
  }
  
  /**
   * Returns the sender of this message.
   *
   * @return The sender
   */
  public final PostEntityAddress getSender() {
    return sender;
  }

  /**
   * Returns the PostEntityAddress of the user or group 
   * to which this noticiation should be delivered.
   *
   * @return The address of the user or group to which this should be delivered
   */
  public PostEntityAddress getDestination() {
    return destination;
  }
  
  /**
   * Returns the PostClientAddress of the application to 
   * which this message should be delievered
   *
   * @return The address of the service to which this message
   *         should be delivered 
   */
   public PostClientAddress getClientAddress(){
     return clientAddress;
   }
   
   public NotificationMessage(InputBuffer buf, Endpoint endpoint) throws IOException {
     clientAddress = new PostClientAddress(buf);
     destination = PostEntityAddress.build(buf, endpoint, buf.readShort());
     sender = PostEntityAddress.build(buf, endpoint, buf.readShort());
//     System.out.println("NotificationMessage.deserialize("+clientAddress+","+destination+","+sender+")");
   }
   
   public void serialize(OutputBuffer buf) throws IOException {
//     System.out.println("NotificationMessage.serialize("+clientAddress+","+destination+","+sender+")");
     clientAddress.serialize(buf);

     buf.writeShort(destination.getType());
     destination.serialize(buf);

     buf.writeShort(sender.getType());
     sender.serialize(buf);
   }    
}
