package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import rewards.jms.client.DiningBatchProcessor;
import rewards.jms.client.JmsDiningBatchProcessor;
import rewards.jms.client.RewardConfirmationLogger;

import javax.jms.ConnectionFactory;

@Configuration
public class ClientConfig {

	@Autowired ConnectionFactory connectionFactory;
	
	@Bean
	public DiningBatchProcessor diningBatchProcessor(JmsTemplate jmsTemplate) {
		JmsDiningBatchProcessor processor = new JmsDiningBatchProcessor();
		return processor;
	}

	//	TODO-05: Define a JmsTemplate.
	//	Provide it with a reference to the ConnectionFactory and the dining destination. 
	//	Inject it into the batch processor bean (above). 
	@Bean
	public JmsTemplate jmsTemplate(
			ConnectionFactory connectionFactory ){
		JmsTemplate template = new JmsTemplate(connectionFactory);
		template.setDefaultDestinationName("rewards.queue.dining");
		return template;
	}

	/**
	 *	Create an object that knows how to log dining confirmations: 
	 */
	@Bean
	public RewardConfirmationLogger logger() {
		return new RewardConfirmationLogger();
	}
	
}
