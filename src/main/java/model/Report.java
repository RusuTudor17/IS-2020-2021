package model;

import java.sql.Date;

public class Report {
    private Long id;
    private Long userId;
    private String activitate;
    private Date dataActivitate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getActivitate() {
        return activitate;
    }

    public void setActivitate(String activitate) {
        this.activitate = activitate;
    }

    public Date getDataActivitate() {
        return dataActivitate;
    }

    public void setDataActivitate(Date dataActivitate) {
        this.dataActivitate = dataActivitate;
    }
}
