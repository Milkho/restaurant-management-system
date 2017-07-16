package dao.interfaces;

import java.util.List;
import model.Ordering;

public interface OrderingDAO {

    public boolean insertOrdering(Ordering Ordering);

    public boolean deleteOrdering(Ordering Ordering);

    public boolean updateOrdering(Ordering Ordering);

    public Ordering findByID(int orderID);
    
    public List<Ordering> findAll();
    
    public List<Ordering> findByExample(Ordering Ordering);
    
}


