package be.company.fca.repository;

import org.springframework.data.repository.CrudRepository;

//@RepositoryRestResource(collectionResourceRel = "pointCarto", path = "pointCarto")
public interface PointCartoRepository { //extends CrudRepository<PointCarto, Long> {
//        @Query(value = "Select id, name from #{#entityName} t"
//                + " where ST_DWithin(t.geom,ST_GeomFromText('POINT(0 0)', 26910),1000)"
//        )
}
