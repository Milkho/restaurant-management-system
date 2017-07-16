package controller;

import dao.concrete.DAOFactoryImpl;
import dao.interfaces.PositionDAO;
import java.util.ArrayList;
import model.Position;

public final class PositionController {

    private final PositionDAO positionDao;

    public PositionController() {
        positionDao = new DAOFactoryImpl().getPositionDAO();
    }

    public ArrayList<Position> getPositions() {
        return new ArrayList(positionDao.findAll());
    }

}
