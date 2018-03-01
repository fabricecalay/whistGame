package be.company.fca.repository;

import be.company.fca.model.PointCarto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "pointCarto", path = "pointCarto")
public interface PointCartoRepository extends CrudRepository<PointCarto, Long> {
//        @Query(value = "Select id, name from #{#entityName} t"
//                + " where ST_DWithin(t.geom,ST_GeomFromText('POINT(0 0)', 26910),1000)"
//        )
}
