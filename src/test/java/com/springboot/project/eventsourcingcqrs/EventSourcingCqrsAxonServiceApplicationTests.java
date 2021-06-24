package com.springboot.project.eventsourcingcqrs;

import com.springboot.project.eventsourcingcqrs.aggregates.PaymentAggregate;
import com.springboot.project.eventsourcingcqrs.aggregates.PaymentStatus;
import com.springboot.project.eventsourcingcqrs.commands.CreateAccountCommand;
import com.springboot.project.eventsourcingcqrs.commands.CreditMoneyCommand;
import com.springboot.project.eventsourcingcqrs.commands.DebitMoneyCommand;
import com.springboot.project.eventsourcingcqrs.events.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
class EventSourcingCqrsAxonServiceApplicationTests {

	private FixtureConfiguration<PaymentAggregate> fixture;

	@BeforeEach
	public void setUp() {
		fixture = new AggregateTestFixture<>(PaymentAggregate.class);
	}

	@Test
	void testCreateAccountCommandEventHandler(){
		String transationId = UUID.randomUUID().toString();
		fixture.givenNoPriorActivity()
				.when(new CreateAccountCommand(transationId, 1000L,"INR"))
				.expectEvents(new AccountCreatedEvent(transationId, 1000L,"INR"),
						new PaymentSuccessEvent(transationId, PaymentStatus.SUCCESSFUL));
	}

	@Test
	void testCreditMoneyCommandEventHandler(){
		String transationId = UUID.randomUUID().toString();
		fixture.given(new AccountCreatedEvent(transationId, -20L,"INR"))
				.when(new CreditMoneyCommand(transationId, 1000L,"INR"))
				.expectEvents(new MoneyCreditedEvent(transationId, 1000L,"INR"),
						new PaymentSuccessEvent(transationId, PaymentStatus.SUCCESSFUL));

	}

	@Test
	void testDebitMoneyCommandEventHandler(){
		String transationId = UUID.randomUUID().toString();
		fixture.given(new AccountCreatedEvent(transationId, 0L,"INR"))
				.when(new DebitMoneyCommand(transationId, 1500L,"INR"))
				.expectEvents(new MoneyDebitedEvent(transationId, 1500L,"INR"),
						new PaymentOnHoldEvent(transationId, PaymentStatus.ONHOLD));
	}
}
