package com.aclti.academy.repositories;

import com.aclti.academy.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITopicRepository extends JpaRepository<Topic, Long> {
}
