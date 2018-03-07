package org.coop.api.domain.contribution;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionRepository extends MongoRepository<Contribution, String> {

}
