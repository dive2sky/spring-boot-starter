package net.madroach.spring.model;

import lombok.Data;
import net.madroach.spring.code.StatusCd;

import java.util.Date;

/**
 * Created by sampark on 2016. 12. 17..
 */
@Data
public class Model {
    private Date createdAt;
    private Date updatedAt;
    private StatusCd status;

    public Model(){
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.status = StatusCd.OK;
    }

}
