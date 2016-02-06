package objects;

public interface DocumentDao {

    public void saveDocument(Document document);

    public void saveDocument(Document document, Employee employee);

}
