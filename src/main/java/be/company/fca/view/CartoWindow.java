package be.company.fca.view;

import be.company.fca.model.PointCarto;
import be.company.fca.repository.PointCartoRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.springframework.data.domain.Pageable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.util.Iterator;
import java.util.List;

public class CartoWindow extends Window {

    public void testCarto(){
        PointCartoRepository pointCartoRepository = (PointCartoRepository) SpringUtil.getBean("pointCartoRepository");
        Iterator<PointCarto> pointCartoIterator = pointCartoRepository.findAll().iterator();
        while (pointCartoIterator.hasNext()){
            System.err.println("Name : " + pointCartoIterator.next().getName());
        }
        Messagebox.show("Test carto");

        // Tentative d'insertion

        GeometryFactory factory = new GeometryFactory();
        PointCarto pointCarto = new PointCarto();
        pointCarto.setId(1L);
        pointCarto.setName("firstPointSaved");
        Point point = factory.createPoint(new Coordinate(25, 24));
        point.setSRID(31300);
        pointCarto.setGeometry(point);
        pointCartoRepository.save(pointCarto);

    }
}
