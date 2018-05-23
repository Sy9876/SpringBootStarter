package cn.sy.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class MyKafkaListener {
	private static Logger logger = LoggerFactory.getLogger(KafkaListener.class);

	
//	@Autowired
//	private KafkaTemplate<String, String> kafkaTemplate;
	
	@KafkaListener(topics = "myTopic")
	public void listen(ConsumerRecord<String, String> cr) throws Exception { 
	
		logger.info("myTopic receive: " + cr.toString());

	}
    
    
}
