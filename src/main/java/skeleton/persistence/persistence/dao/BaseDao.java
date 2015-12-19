package skeleton.persistence.persistence.dao;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.ibatis.exceptions.PersistenceException;
//import org.springframework.util.ReflectionUtils;

import skeleton.persistence.util.Regulation;

import skeleton.persistence.exception.RepositoryException;
import skeleton.persistence.model.BaseModel;
import skeleton.persistence.persistence.mapper.BaseMapper;


public abstract class BaseDao <M extends BaseModel> implements BaseMapper<M> {
    protected static long nextId() {
        long id = 0;
        Calendar c = Calendar.getInstance();
        id = c.getTimeInMillis();
        Random rnd = new Random(id);
        return id * 1000 + rnd.nextInt(999);
    }
    
    private BaseMapper getMapper() {
//        Field field = ReflectionUtils.findField(this.getClass(), "mapper");
//        if (field != null) {
//            try {
//                field.setAccessible(true);
//                return (BaseMapper) field.get(this);
//            } catch (IllegalArgumentException | IllegalAccessException e) {
//                return null;
//            }
//        }
        return null;
    }
    
//    @Override
    public int insert(M model) throws RepositoryException {
        // automatically set id
    	if (model.getId() == 0) {
        	model.setId(nextId());
        }
    	
    	try{
            return getMapper().insert(model);
        }
        catch (PersistenceException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

//    @Override
    public int update(M model) throws RepositoryException {
        try {
            return getMapper().update(model);
        }
        catch (PersistenceException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

//    @Override
    public int delete(M model) throws RepositoryException {
        try {
            return getMapper().delete(model);
        }
        catch (PersistenceException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

//    @Override
    public M get(long id) throws RepositoryException {
        try {
            return (M) getMapper().get(id);
        }
        catch (PersistenceException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

//    @Override
    public int count(Map criteria) throws RepositoryException {
        try {
            return getMapper().count(criteria);
        }
        catch (PersistenceException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

//    @Override
    public List<M> select(Map criteria, Regulation regulation) throws RepositoryException {
    	try {
            if (regulation != null && regulation.pagination != null) {
            	regulation.pagination.setRowCount(this.count(criteria));
            }
            return getMapper().select(criteria, regulation);
        }
        catch (PersistenceException e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}
