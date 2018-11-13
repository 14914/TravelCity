package com.travel.cities.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class TravelController {

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/connected", method = RequestMethod.GET)
	public String getTravelCity(@RequestParam String origin, @RequestParam String destination) {

		String[] values = null;
		String response = "NO";
		try {

			InputStream inputStream = TravelController.class.getResourceAsStream("/data/cities.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = br.readLine()) != null) {
				values = line.split(",");
				String o = values[0].trim();
				String d = values[1].trim();
				if (o.equalsIgnoreCase(origin) && d.equalsIgnoreCase(destination)) {
					response = "YES";
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	public static void main(String[] args) {
		SpringApplication.run(TravelController.class, args);
	}
}
