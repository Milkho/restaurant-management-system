package dao.concrete;

import dao.interfaces.PositionDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Position;

public final class PositionDAOImpl implements PositionDAO{

    @Override
    public boolean insertPosition(Position position) {
       //TODO
        return false;
    }

    @Override
    public boolean deletePosition(Position position) {
        //TODO
        return false;
    }

    @Override
    public boolean updatePosition(Position position) {
        //TODO
        return false;
    }

   
    @Override
    public Position findByName(String name) {
      //TODO
      return null;
    }

    @Override
    public List<Position> findAll() {
        ArrayList<Position> result = new ArrayList<>();
        try {
            Statement statement = DAOFactoryImpl.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from position");

            while (resultSet.next()) {
                Position position = new Position();
                position.setPositionName(resultSet.getString("positionName"));
                position.setSalary(resultSet.getDouble("salary"));
                result.add(position);
            }
        } catch (SQLException ex) {
            return null;
        }
        return result;
    }

    
}


