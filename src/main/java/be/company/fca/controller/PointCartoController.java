package be.company.fca.controller;

import be.company.fca.repository.PointCartoRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@RestController
public class PointCartoController {

//    @Autowired
//    private PointCartoRepository pointCartoRepository;
//
//    @RequestMapping("/api/pointsCarto")
//    public List<PointCarto> getAllPointsCarto(){
//        return (List<PointCarto>) pointCartoRepository.findAll();
//    }
//
//    @RequestMapping(value = "/api/pointsCarto", method = RequestMethod.POST)
//    public void savePoint(@RequestParam(value="x") String x,@RequestParam(value="y") String y,@RequestParam(value="srid") String srid){
//        System.err.println("Double x : " + x);
//        System.err.println("Double y : " + y);
//        System.err.println("Double srid : " + srid);
//
//        GeometryFactory factory = new GeometryFactory();
//        PointCarto pointCarto = new PointCarto();
////        pointCarto.setId(1L);
//        pointCarto.setName("Point saved via Geoviewer");
//        Point point = factory.createPoint(new Coordinate(Double.parseDouble(x),Double.parseDouble(y)));
//        point.setSRID(Integer.valueOf(srid));
//        pointCarto.setGeometry(point);
//        pointCartoRepository.save(pointCarto);
//
//        System.err.println("Point saved");
//    }

}
