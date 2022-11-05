package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class QueriesRepo {

    @Autowired
    private JdbcTemplate t;

    public User selectUser(String u) {

        try {

            String x = "SELECT * FROM Users WHERE userEmail = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(User.class),u);

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public Event selectEvent(int v) {

        try {

            String x = "SELECT * FROM Event WHERE eventId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Event.class), new Object[] { v });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public Event selectEventFromOrganiser(String v) {

        try {

            String x = "SELECT * FROM Event WHERE headEmail = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Event.class), new Object[] { v });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public List<Event> getEventFromSubEvent(List<SubEvent> s) {

        List<Event>e=new ArrayList<Event>();

        for (int i = 0; i < s.size(); i++) {

            e.add(selectEvent(s.get(i).getEventId()));
        }

        return e;
    }

    public Fund selectFund(int f) {

        try {

            String x = "SELECT * FROM Fund WHERE fundId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Fund.class), new Object[] { f });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public Organiser selectOrganiser(String o) {

        try {

            String x = "SELECT * FROM Organiser WHERE organiserEmail = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Organiser.class), new Object[] { o });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public Participant selectParticipant(String p) {

        try {

            String x = "SELECT * FROM Participant WHERE participantEmail = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Participant.class), new Object[] { p });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public Sponsor selectSponsor(int s) {

        try {

            String x = "SELECT * FROM Sponsor WHERE sponsorId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Sponsor.class), new Object[] { s });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public List<SubEvent> getSubEventByDate(Date d) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String dd = df.format(d);

        String x = "SELECT * FROM SubEvent WHERE startDate = '"+dd+"'";

        return t.query(x, new BeanPropertyRowMapper<>(SubEvent.class));

    }

    public List<SubEvent> getSubEventsByEvent(int i) {

        String x = "SELECT * FROM SubEvent WHERE eventId = '"+i+"'";

        return t.query(x, new BeanPropertyRowMapper<>(SubEvent.class));

    }

    public SubEvent getSubEventById(int i, int ee){

        try {

            String x = "SELECT * FROM SubEvent WHERE subEventId = ? AND eventId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(SubEvent.class), new Object[] {i, ee});

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }
    }

    public List<Organiser> getOrganiserByEvent(int i){

        String x="SELECT * FROM Organiser WHERE eventId =?";

        return t.query(x, new BeanPropertyRowMapper<>(Organiser.class), i);

    }

    public Competition getCompetitionById(int c, int s, int ee) {

        try {

            String x = "SELECT * FROM Competition WHERE competitionId=? AND subEventId =? AND eventId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Competition.class), new Object[] { c, s, ee });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public List<Participation> getAllParticipations(int c, int s, int ee) {

        try {

            String x = "SELECT * FROM Participation WHERE competitionId = ? AND subEventId = ? AND eventId = ?";

            return t.query(x, new BeanPropertyRowMapper<>(Participation.class), c, s, ee);

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public Cart activeCart(String currentUser) {

        try {

            String x = "SELECT * FROM Cart WHERE cartId NOT IN (SELECT cartId FROM Transaction) AND participantEmail = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Cart.class), currentUser);

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void addNewEvent(Event e, Organiser o) {
        String x = "WITH new_event AS (INSERT INTO Event (eventId, eventName, headEmail) values (?,?,?))INSERT INTO Organiser (organiserEmail, firstName, lastName, mobileNumber, eventId) values (?,?,?,?,?)";
        t.update(x,e.getEventId(),e.getEventName(),o.getOrganiserEmail(),o.getOrganiserEmail(),o.getFirstName(),o.getLastName(),o.getMobileNumber(),e.getEventId());
    }

    public List<Event> getAllEvents() {
        try {

            String x = "SELECT * FROM Event";

            return t.query(x, new BeanPropertyRowMapper<>(Event.class));

        }

        catch (EmptyResultDataAccessException e) {
            System.out.println("ono");

            return null;

        }

    }

    public List<Item> getAllItems() {
        try {

            String x = "SELECT * FROM Item";

            return t.query(x, new BeanPropertyRowMapper<>(Item.class));

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }
    }

    public List<Sponsor> getAllSponsors() {

        try {

            String x = "SELECT * FROM Sponsor";

            return t.query(x, new BeanPropertyRowMapper<>(Sponsor.class));

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }
    }

    public Transaction getTransaction(Cart c) {

        try {

            String x = "SELECT * FROM Transaction WHERE cartId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Transaction.class), c.getCartId());

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public Participant getParticipant(String currentUser) {

        try {

            String x = "SELECT * FROM Participant WHERE participantEmail = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Participant.class), currentUser);

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public int getAmount(CartItemDetails c) {

        String x = "SELECT * FROM Item WHERE itemId = ?";

        Item str = t.queryForObject(x, new BeanPropertyRowMapper<>(Item.class), c.getItemId());

        int temp = c.getQuantity()*str.getSellingPrice();

        return temp;

    }

    public List<Participation> getLeaderboard(int ee, int s, int c) {

        String x = "SELECT * FROM Participation WHERE eventId = ? AND subEventId = ? AND competitionId = ? ORDER BY leaderBoardPosition";

        return t.query(x, new BeanPropertyRowMapper<>(Participation.class), ee, s, c);

    }

    public Boolean checkParticipation(Competition c, String u) {

        try {

            String x = "SELECT * FROM Participation WHERE participantEmail = ? AND eventId = ? AND subEventId = ? AND competitionId = ?";

            Participation temp = t.queryForObject(x, new BeanPropertyRowMapper<>(Participation.class), u, c.getEventId(), c.getSubEventId(), c.getCompetitionId());

            return true;

        }

        catch (EmptyResultDataAccessException e) {

            return false;

        }

    }

    public List<Competition> getCompetitions(int s, int ee) {

        try {

            String x = "SELECT * FROM Competition WHERE eventId = ? AND subEventId = ?";

            return t.query(x, new BeanPropertyRowMapper<>(Competition.class), ee, s);

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public List<Cart> getPreviousCarts(String u) {

        try {

            String x = "SELECT * FROM Cart WHERE participantEmail = ?";

            return t.query(x, new BeanPropertyRowMapper<>(Cart.class), u);

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public List<CartItemDetails> getCartItems(Cart c) {

        try {

            String x = "SELECT * FROM CartItemDetails WHERE cartId = ?";

            return t.query(x, new BeanPropertyRowMapper<>(CartItemDetails.class), c.getCartId());

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public Boolean checkAdded(Item i, Cart c) {

        try {

            String x = "SELECT * FROM CartItemDetails WHERE cartId = ? AND itemId = ?";

            CartItemDetails temp = t.queryForObject(x, new BeanPropertyRowMapper<>(CartItemDetails.class), c.getCartId(), i.getItemId());

            return true;

        }

        catch (EmptyResultDataAccessException e) {

            return false;

        }

    }

    public Event getEvent(int eventId) {

        try {

            String x = "SELECT * FROM Event WHERE eventId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Event.class), eventId);

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public Item getItembyId(int itemId) {

        try {

            String x = "SELECT * FROM Item WHERE itemId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Item.class), itemId);

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }
    }
}