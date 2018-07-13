package com.john.websockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MarcoController {

  private static final Logger logger = LoggerFactory
      .getLogger(MarcoController.class);

  /**
   * Listens to messages sent to /app/marco and sends back messages to /topic/marco.
   * @param incoming
   * @return
   */
  @MessageMapping("/marco")
  public Shout handleShout(Shout incoming) {
    logger.info("Received message: " + incoming.getMessage());

    try { Thread.sleep(2000); } catch (InterruptedException e) {}
    
    Shout outgoing = new Shout();
    outgoing.setMessage("Polo!");
    
    return outgoing;
  }

  /**
   * Listens to messages sent to /app/broadcastMarco and sends messages to /topic/broadcastPolo.
   * @param incoming
   * @return
   */
  @SubscribeMapping("/broadcastMarco")
  @SendTo("/topic/broadcastPolo")
  public Shout handleBroadcastShout(Shout incoming) {
    logger.info("Received  broadcast message: " + incoming.getMessage());

    Shout outgoing = new Shout();
    outgoing.setMessage("Broadcast Polo!");

    return outgoing;
  }


}
