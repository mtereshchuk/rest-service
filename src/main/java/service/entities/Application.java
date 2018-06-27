package service.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Application {

    @ManyToOne
    private Customer customer;
    @Id
    private int id;
    private String name;
    private Date date;

    Application() {}

    public Application(Customer customer, int id, String name) {
        this.customer = customer;
        this.id = id;
        this.name = name;
        date = new Date();
    }

    public int getCustomerId() {
		return customer.getId();
	}

    public int getId() {
        return id;
    }

	public String getName() {
		return name;
    }
    
    public Date getDate() {
		return date;
    }
}
