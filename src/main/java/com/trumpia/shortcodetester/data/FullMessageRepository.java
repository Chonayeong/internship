package com.trumpia.shortcodetester.data;

import org.springframework.data.repository.CrudRepository;

import com.trumpia.shortcodetester.model.FullMessage;
import com.trumpia.shortcodetester.model.MessageContext;

public interface FullMessageRepository extends CrudRepository<FullMessage,Long>{

}
