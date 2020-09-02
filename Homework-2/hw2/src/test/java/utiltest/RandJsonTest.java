package utiltest;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import factory.TicketFactory;
import factory.ITicket;
import factory.Locations;
import util.RandJson;

import static org.junit.Assert.assertEquals;

public class RandJsonTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void create() {
    JSONObject obj = RandJson.create();
    ITicket ticket = TicketFactory.create(obj);

    assertEquals(Locations.find(obj.getString("arrival location")).getName(),
            ticket.getArrivalLocation());
  }
}