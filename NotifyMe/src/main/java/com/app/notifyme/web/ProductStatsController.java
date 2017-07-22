package com.app.notifyme.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.notifyme.models.Previousstat;
import com.app.notifyme.models.Productstat;
import com.app.notifyme.models.StatisticsData;
import com.app.notifyme.repositories.PreviousStatRepository;
import com.app.notifyme.repositories.ProductRepository;
import com.app.notifyme.repositories.ProductStatRepository;
import com.app.notifyme.repositories.UserCriteriaRepository;

@RestController
public class ProductStatsController {

	@Autowired
	ProductStatRepository productStatRepository;

	@Autowired
	PreviousStatRepository previousStatRepository;

	@Autowired
	UserCriteriaRepository userCriteriaRepository;

	@Autowired
	ProductRepository productRepository;

	@RequestMapping(value = "/productStats", method = RequestMethod.GET)
	List<StatisticsData> productCurrentStats(@RequestParam String userId) {

		int productId = userCriteriaRepository.getLastProductFromUser(userId);
		List<Productstat> statsList = productStatRepository.getUserData(productId);
		List<StatisticsData> statData = new ArrayList<>();

		for (Productstat p : statsList) {
			StatisticsData sd = new StatisticsData(p.getPrice(), p.getTime(),
					productRepository.getProductName(productId));
			statData.add(sd);
		}
		return statData;
	}

	@RequestMapping(value = "/productPrevStats", method = RequestMethod.GET)
	List<StatisticsData> productPreviousStats(@RequestParam String userId) {

		List<Integer> trackedProductsList = userCriteriaRepository.getAllProductsOfUser(userId);
		List<StatisticsData> statData = new ArrayList<>();

		for (Integer productId : trackedProductsList) {
			List<Previousstat> statsList = previousStatRepository.getUserData(productId);

			for (Previousstat p : statsList) {
				StatisticsData sd = new StatisticsData(p.getMinimumPrice(), p.getDate(),
						productRepository.getProductName(productId));
				statData.add(sd);
			}
		}
		return statData;
	}

}
