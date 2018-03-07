package org.coop.api.domain.cycle;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CycleRepository extends MongoRepository<Cycle, String> {

}
