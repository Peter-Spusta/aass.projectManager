package com.fiit.aass.soap;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.Currency;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CountryRepository {
	private static final Map<String, Country> countries = new HashMap<>();

	@PostConstruct
	public void initData() {
		Country slovakia = new Country();
		slovakia.setName("Slovakia");
		slovakia.setCapital("Bratislava");
		slovakia.setCurrency(Currency.EUR);
		slovakia.setPopulation(6758320);
		slovakia.setId(1);

		countries.put(slovakia.getName(), slovakia);
		
		Country austria = new Country();
		austria.setName("Austria");
		austria.setCapital("Wien");
		austria.setCurrency(Currency.EUR);
		austria.setPopulation(11253911);
		austria.setId(2);

		countries.put(austria.getName(), austria);

		Country spain = new Country();
		spain.setName("Spain");
		spain.setCapital("Madrid");
		spain.setCurrency(Currency.EUR);
		spain.setPopulation(46704314);
		spain.setId(4);

		countries.put(spain.getName(), spain);

		Country poland = new Country();
		poland.setName("Poland");
		poland.setCapital("Warsaw");
		poland.setCurrency(Currency.PLN);
		poland.setPopulation(38186860);
		spain.setId(5);

		countries.put(poland.getName(), poland);

		Country uk = new Country();
		uk.setName("United Kingdom");
		uk.setCapital("London");
		uk.setCurrency(Currency.GBP);
		uk.setPopulation(63705000);
		spain.setId(6);
		
		countries.put(uk.getName(), uk);
	}

	public Country findCountry(String name) {
		Assert.notNull(name, "The country's name must not be null");
		return countries.get(name);
	}
}
