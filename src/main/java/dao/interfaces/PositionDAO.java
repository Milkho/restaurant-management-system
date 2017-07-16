package dao.interfaces;

import java.util.List;
import model.Position;

public interface PositionDAO {
    public boolean insertPosition(Position position);

    public boolean deletePosition(Position position);

    public boolean updatePosition(Position position);

    public Position findByName(String positionName);
    
    public List<Position> findAll();
}
