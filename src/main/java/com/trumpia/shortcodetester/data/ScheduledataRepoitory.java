package com.trumpia.shortcodetester.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trumpia.shortcodetester.model.MessageContext;
import com.trumpia.shortcodetester.model.Schedule;

@Repository
public interface ScheduledataRepoitory extends CrudRepository<Schedule, Long>{
	 
}
