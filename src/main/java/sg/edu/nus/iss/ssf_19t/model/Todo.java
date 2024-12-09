package sg.edu.nus.iss.ssf_19t.model;

import java.io.StringReader;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;
import sg.edu.nus.iss.ssf_19t.utilities.Helper;

public class Todo {
    @Size(max=50)
    private String id;

    @Size(min=10, max=50)
    private String name;

    @Size(max=255)
    private String description;

    @FutureOrPresent
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date due_date;

    private String priority_level;
    private String status;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date created_at;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updated_at;
    
    public Todo() {
        this.id = UUID.randomUUID().toString();
    }

    public Todo(String id, String name, String description, Date due_date, String priority, String status,
            Date created, Date updated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.due_date = due_date;
        this.priority_level = priority;
        this.status = status;
        this.created_at = created;
        this.updated_at = updated;
    }

    public static Todo readData(String rawJson) throws ParseException {
        JsonReader reader = Json.createReader(new StringReader(rawJson));
        JsonObject obj = reader.readObject();
        
        return new Todo(
            obj.getString("id"),
            obj.getString("name"),
            obj.getString("description"),
            Helper.strToDate(obj.getString("due_date")),
            obj.getString("priority_level"),
            obj.getString("status"),
            Helper.strToDate(obj.getString("created_at")),
            Helper.strToDate(obj.getString("updated_at"))
        );
    }

    public static Todo serialize(String rawJson) throws ParseException {
        JsonReader reader = Json.createReader(new StringReader(rawJson));
        JsonObject obj = reader.readObject();

        // System.out.println(obj.toString());
        
        return new Todo(
            obj.getString("id"),
            obj.getString("name"),
            obj.getString("description"),
            Helper.epochToDate(obj.getJsonNumber("due_date").longValue()),
            obj.getString("priority_level"),
            obj.getString("status"),
            Helper.epochToDate(obj.getJsonNumber("created_at").longValue()),
            Helper.epochToDate(obj.getJsonNumber("updated_at").longValue())
        );
    }

    public String deserialize() {
        JsonObject jsonObj = Json.createObjectBuilder()
            .add("id", id)
            .add("name", name)
            .add("description", description)
            .add("due_date", Helper.dateToEpoch(due_date))
            .add("priority_level", priority_level)
            .add("status", status)
            .add("created_at", Helper.dateToEpoch(created_at))
            .add("updated_at", Helper.dateToEpoch(updated_at))
            .build();
        
        return jsonObj.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public String getPriority_level() {
        return priority_level;
    }

    public void setPriority_level(String priority) {
        this.priority_level = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created) {
        this.created_at = created;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated) {
        this.updated_at = updated;
    }

    @Override
    public String toString() {
        return "Todo [id=" + id + ", name=" + name + ", description=" + description + ", due_date=" + due_date
                + ", priority_level=" + priority_level + ", status=" + status + ", created_at=" + created_at
                + ", updated_at=" + updated_at + "]";
    }

    
}
