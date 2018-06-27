package service.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;
import java.util.ArrayList;

@Entity
public class Customer {

    @Id
    private int id;
    @OneToMany(mappedBy = "customer")
    private List<Application> applications;

    Customer() {}

    public Customer(int id) {
        this.id = id;
        this.applications = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    
    public List<Application> getApplications() {
        return applications;
    }
}