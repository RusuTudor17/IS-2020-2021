package repository;

public class EntityNotFoundException extends Exception {

    private String entityClass;

    public EntityNotFoundException(String entityClass) {
        this.entityClass = entityClass;
    }
    

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }
}
