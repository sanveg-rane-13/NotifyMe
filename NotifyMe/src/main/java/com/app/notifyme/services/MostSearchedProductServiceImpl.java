package com.app.notifyme.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.app.notifyme.repositories.ProductRepository;
import com.app.notifyme.repositories.UserCriteriaRepository;

@CrossOrigin(origins = "*")
@Service("mostViewedProductService")
public class MostSearchedProductServiceImpl implements MostSearchedProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserCriteriaRepository userCriteriaRepository;

	@Override
	@Transactional
	public List<String> getMostSearchedProducts() {
		// TODO Auto-generated method stub
		List<ViewedProduct> products = new ArrayList<>();
		List<String> productNames = productRepository.findProductNames();
		System.out.println(productNames);
		for (String productName : productNames) {
			List<Integer> pids = productRepository.findProductIds(productName);
			System.out.println(pids);
			// int sum=0;
			/*
			 * for(Integer pid:pids){ sum+=userCriteriaRepository.getCountOfProduct(pid); }
			 */
			Integer sum = userCriteriaRepository.getSumOfProductCount(pids);
			/*
			 * there are no requests for the product in the given period of 1 week leading
			 * to a sum of null
			 */

			if (sum == null) {
				sum = 0;
			}
			products.add(new ViewedProduct(productName, sum));
			System.out.println(
					"productname: " + productName + "::sum: " + userCriteriaRepository.getSumOfProductCount(pids));
		}
		Collections.sort(products, (o1, o2) -> {
			ViewedProduct v1 = (ViewedProduct) o1;
			ViewedProduct v2 = (ViewedProduct) o2;
			return v2.sum.compareTo(v1.sum);
		});

		List<String> topProducts = new ArrayList<>();
		for (ViewedProduct vp : products) {
			topProducts.add(vp.productName);
		}
		return topProducts;
	}

	private class ViewedProduct {
		String productName;
		Integer sum = 0;

		public ViewedProduct(String productName, int sum) {
			// TODO Auto-generated constructor stub
			this.productName = productName;
			this.sum = sum;

		}
	}

}
