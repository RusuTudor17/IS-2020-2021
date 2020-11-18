package repository.cont;


public abstract class ContRepositoryDecorator implements ContRepository {
    protected ContRepository decoratedRepository;
    public ContRepositoryDecorator(ContRepository contRepository) {
        this.decoratedRepository = contRepository;
    }
}
