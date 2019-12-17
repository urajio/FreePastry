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
package org.mpisws.p2p.transport.peerreview;

public interface PeerReviewConstants extends StatusConstants {

  short EVT_SEND = 0; // Outgoing message (followed by SENDSIGN entry)
  short EVT_RECV = 1; // Incoming message (followed by SIGN entry)
  short EVT_SIGN = 2;                   /* Signature on incoming message */
  short EVT_ACK = 3;                                  /* Acknowledgment */
  short EVT_CHECKPOINT = 4;                 /* Checkpoint of the state machine */
  short EVT_INIT = 5;                    /* State machine is (re)started */
  short EVT_SENDSIGN = 6;                   /* Signature on outgoing message */
  short EVT_VRF = 7;                         /* New Si value in the VRF */
  short EVT_CHOOSE_Q = 8;                           /* Choose Q array in VRF */
  short EVT_CHOOSE_RAND = 9;                        /* Choose randomness in VRF */
  short EVT_MAX_RESERVED = EVT_CHOOSE_RAND;    /* User defined events start here */

  short EVT_SOCKET_OPEN_INCOMING = 30;
  short EVT_SOCKET_OPEN_OUTGOING = 31;
  short EVT_SOCKET_OPENED_OUTGOING = 32;
  short EVT_SOCKET_EXCEPTION = 33;
  short EVT_SOCKET_CLOSE = 34;
  short EVT_SOCKET_SHUTDOWN_OUTPUT = 35;
  short EVT_SOCKET_CLOSED = 36;
  short EVT_SOCKET_CAN_READ = 37;
  short EVT_SOCKET_CAN_WRITE = 38;
  short EVT_SOCKET_CAN_RW = 39;
  short EVT_SOCKET_READ = 40;
  short EVT_SOCKET_WRITE = 41;
  
  short EVT_MIN_SOCKET_EVT = EVT_SOCKET_OPEN_INCOMING;
  short EVT_MAX_SOCKET_EVT = EVT_SOCKET_SHUTDOWN_OUTPUT;
  
  short EX_TYPE_IO = 1;
  short EX_TYPE_ClosedChannel = 2;
  short EX_TYPE_Unknown = 0;
 
  /* Message types used in PeerReview */

  short MSG_USERDATA = 16;            /* Contains data the application has sent */
  short MSG_ACK = 17;                       /* Acknowledges an USERDATA message */
  short MSG_ACCUSATION = 18;            /* Contains evidence about a third node */
  short MSG_CHALLENGE = 19;            /* Contains evidence about the recipient */
  short MSG_RESPONSE = 20;                      /* Answers a previous CHALLENGE */
  short MSG_AUTHPUSH = 21;        /* Sent to a witness; contains authenticators */
  short MSG_AUTHREQ = 22;    /* Asks a witness to return a recent authenticator */
  short MSG_AUTHRESP = 23;                    /* Responds to a previous AUTHREQ */
  short MSG_USERDGRAM = 24;    /* Contains a datagram from the app (not logged) */

  /* Evidence types (challenges and proofs) */
  byte CHAL_AUDIT = 1;
  byte CHAL_SEND = 2;
  byte RESP_SEND = CHAL_SEND;
  byte RESP_AUDIT = CHAL_AUDIT;
  byte PROOF_INCONSISTENT = 3;
  byte PROOF_NONCONFORMANT = 4;

  /* Constants for reporting the status of a remote node to the application */

  long DEFAULT_AUTH_PUSH_INTERVAL_MILLIS = 5000;
  long DEFAULT_CHECKPOINT_INTERVAL_MILLIS = 10000L;
  long MAINTENANCE_INTERVAL_MILLIS = 10000;
  long DEFAULT_TIME_TOLERANCE_MILLIS = 60000;

  int TI_CHECKPOINT = 99;
  int TI_MAINTENANCE = 6;
  int TI_AUTH_PUSH = 7;
  int TI_MAX_RESERVED = TI_AUTH_PUSH;
  int TI_STATUS_INFO = 101;
  int MAX_STATUS_INFO = 100;
  
  /* Flags for AUDIT challenges */

  byte FLAG_INCLUDE_CHECKPOINT = 1;                /* Include a full checkpoint */
  byte FLAG_FULL_MESSAGES_SENDER = 2; /* Don't hash outgoing messages to sender */
  byte FLAG_FULL_MESSAGES_ALL = 4;          /* Don't hash any outgoing messages */

  /**
   * Enum for EvidenceTool
   */
  int VALID = 1;
  int INVALID = 2;
  int CERT_MISSING = 3;

  long DEFAULT_AUDIT_INTERVAL_MILLIS = 10000;
  int PROGRESS_INTERVAL_MILLIS = 100;
  int INVESTIGATION_INTERVAL_MILLIS = 250;
  int DEFAULT_LOG_DOWNLOAD_TIMEOUT = 2000;
  int MAX_WITNESSED_NODES = 110;
  int MAX_ACTIVE_AUDITS = 500;
  int MAX_ACTIVE_INVESTIGATIONS = 10;
  int MAX_ENTRIES_BETWEEN_CHECKPOINTS = 100;
  int AUTH_CACHE_INTERVAL = 500000;
  
  int TI_START_AUDITS = 3;
  int TI_MAKE_PROGRESS = 4;

  int STATE_SEND_AUDIT = 1;
  int STATE_WAIT_FOR_LOG = 2;

  int NO_CERTIFICATE = -1;
  int SIGNATURE_BAD = 0;
  int SIGNATURE_OK = 1;

}
