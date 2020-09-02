package logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import util.RandJson;

/**
 * Driver class to run Ticket Factory
 */
public class Main {
  public static void main(String[] args) throws IOException {
    String className = TicketFactory.class.getName();
    String method = Thread.currentThread().getStackTrace()[1].getMethodName();
    Logger logger = Logger.getLogger(className);

    FileHandler handler;
    try {
      handler = new FileHandler("log.txt", true);
    } catch (SecurityException | IOException e) {
      throw new IOException("Could not create file.");
    }
    handler.setFormatter(new SimpleFormatter());
    logger.addHandler(handler);
    logger.entering(className, method);

    List<ITicket> listTickets = new ArrayList<>();
    for (int i = 0; i < 100; i++){
      listTickets.add(TicketFactory.create(RandJson.create()));
    }

    for (ITicket ticket : listTickets) {
      logger.log(Level.INFO,
              ticket.getClass().getSimpleName() +  " created for "
                      + ticket.getPassengerName() + " departing from "
                      + ticket.getDepartureLocation() + " and arriving at "
                      + ticket.getArrivalLocation());
    }

    Counter.report();
    Counter.print();
  }
}
