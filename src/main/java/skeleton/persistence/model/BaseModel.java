package skeleton.persistence.model;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 100000000000L;
    
    long id;
    Date insertTime;
    Date updateTime;
    String insertBy;
    String updateBy;
    
    public boolean hasId() {
    	return this.id != 0;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(String insertBy) {
        this.insertBy = insertBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    
    
}
