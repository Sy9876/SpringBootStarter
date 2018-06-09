package cn.sy.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sy.dto.DemoKafkaDto;

@Component
@KafkaListener(topics = "myTopic", groupId="g1")
// ,containerFactory = "kafkaJsonListenerContainerFactory"
public class MyKafkaListener {
	private static Logger logger = LoggerFactory.getLogger(KafkaListener.class);

	
//	@Autowired
//	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	/**
	 * 使用@KafkaListener(topics = "myTopic") 声明多个方法，只有一个能够接收信息（因为group相同），
	 * 但，如果方法参数类型不同，则根据消息的类型调用相应的方法。
	 * 例如
	 *  @KafkaListener(id = "multi", topics = "myTopic")
	 *  static class MultiListenerBean {
	 *  	@KafkaHandler
	 *  	public void listen(String foo) {
	 *  		...
	 *  	}
	 *  	@KafkaHandler
	 *  	public void listen(Integer bar) {
	 *  		...
	 *  	}
	 *  }
	 * 
	 * 使用@KafkaListener(topics = "myTopic", groupId="xx") 声明多个方法，
	 * 只要groupId不同，则都能够接收信息
	 * 
	 * 
	 */
	
//	@KafkaListener(topics = "myTopic", groupId="g1")
	@KafkaHandler()
	public void listen3(String payload) throws Exception { 
	
		logger.info("listen3 myTopic payload: " + payload);

	}
    
//	@KafkaListener(topics = "myTopic", groupId="g1")
//	@KafkaHandler()
//	public void listen(Integer payload) throws Exception { 
//	
//		logger.info("listen4 myTopic payload: " + payload);
//
//	}
    

//	@KafkaHandler()
//	public void listen(DemoKafkaDto msg) throws Exception { 
//	
//		logger.info("listen myTopic DemoKafkaDto msg: " + msg.toString());
//
//	}

	@KafkaHandler()
	public void listen(ConsumerRecord<String, String> cr) throws Exception { 
	
		logger.info("listen myTopic receive: " + cr.toString());

	}
    
//	@KafkaListener(topics = "myTopic", groupId="g2")
//	public void listen2(ConsumerRecord<String, String> cr) throws Exception { 
//	
//		logger.info("listen2 myTopic receive: " + cr.toString());
//
//	}
    
    
}
