package ua.com.javajedi.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "statistics")
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stat_id")
    private long statId;
    private Calendar date;
    private long visitors;

    public Statistics (){}

    public long getStatId() {
        return statId;
    }

    public void setStatId(long statId) {
        this.statId = statId;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public long getVisitors() {
        return visitors;
    }

    public void setVisitors(long visitors) {
        this.visitors = visitors;
    }
}
