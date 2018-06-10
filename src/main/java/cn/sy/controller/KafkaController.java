package cn.sy.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cn.sy.dto.DemoKafkaDto;


@RestController
public class KafkaController {
	private static Logger logger = LoggerFactory.getLogger(KafkaController.class);
	
	
	// 使用@PostConstruct在stringRedisTemplate被注入之后执行redisRoutingMap的初始化
	@PostConstruct
	public void init() {
		logger.info("PostConstruct. init ");
	}
	
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, Integer> intKafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, DemoKafkaDto> dtoKafkaTemplate;
	
	    
    
    @RequestMapping("/public/sendMsg.do")
    public void sendMsg() {

    	kafkaTemplate.send("myTopic", "foo1");
    	
    	logger.info("sendMsg.do end");
    }
    
    @RequestMapping("/public/sendInt.do")
    public void sendInt(int count) {

    	intKafkaTemplate.send("myTopic", count);
    	
    	logger.info("sendInt.do end");
    }
    
    
    @RequestMapping("/public/sendDto.do")
    public void sendDto() {

    	DemoKafkaDto dto = new DemoKafkaDto();
    	dto.setV1(UUID.randomUUID().toString());
    	dto.setV2(null);
    	dto.setV3(0);
    	dto.setV4(new Date());
    	
    	Map<String, Object> headers = new HashMap<String, Object>();
    	headers.put(KafkaHeaders.TOPIC, "myTopic");
//    	headers.put("myCustomHeader", "abc");
    	headers.put("PayLoadType", DemoKafkaDto.class.getName());
    	Message<DemoKafkaDto> msg = new GenericMessage<DemoKafkaDto>(dto, headers);

		logger.info("sendDto.do msg: " + msg);
    	dtoKafkaTemplate.send(msg);
    	
    	logger.info("sendDto.do end");
    }
    
    @RequestMapping("/public/sendRecord.do")
    public void sendRecord() {

    	DemoKafkaDto dto = new DemoKafkaDto();
    	dto.setV1(UUID.randomUUID().toString());
    	dto.setV2("sendRecord");
    	dto.setV3(0);
    	dto.setV4(new Date());
    	
//    	Map<String, Object> headers = new HashMap<String, Object>();
//    	headers.put(KafkaHeaders.TOPIC, "myTopic");
////    	headers.put("myCustomHeader", "abc");
//    	headers.put("PayLoadType", DemoKafkaDto.class.getName());
//    	Message<DemoKafkaDto> msg = new GenericMessage<DemoKafkaDto>(dto, headers);

    	RecordHeaders headers = new RecordHeaders();
    	headers.add("PayLoadType", org.apache.kafka.common.utils.Utils.utf8(DemoKafkaDto.class.getName()));
    	// String topic, Integer partition, K key, V value,  Iterable<Header> headers
    	ProducerRecord<String, DemoKafkaDto> record = new ProducerRecord<String, DemoKafkaDto>(
    			"myTopic", null, null, dto, headers);
    	
		logger.info("sendDto.do record: " + record);
    	dtoKafkaTemplate.send(record);
    	
    	logger.info("sendDto.do end");
    }

    
    @ExceptionHandler(value=Exception.class)
    @ResponseStatus
    public ErrorRespBody handleError(Exception ex) {
    	return new ErrorRespBody(1001, "ERROR: " + ex.getMessage());
    }
    
    private class ErrorRespBody {
    	private int code;
    	private String message;
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public ErrorRespBody() {
			
		}
		public ErrorRespBody(int code, String message) {
			super();
			this.code = code;
			this.message = message;
		}
    	
    	
    }
}
