package com.geekster.Restaurant.management.service.services;

import com.geekster.Restaurant.management.service.models.*;
import com.geekster.Restaurant.management.service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    IOrderRepository iOrderRepository;
    @Autowired
    private IFoodRepository iFoodRepository;
    @Autowired
    private IRestaurantRepository iRestaurantRepository;

    @Autowired
    private IAuthenticationRepository authenticationRepo;
    @Autowired
    private ICustomerRepository customerRepo;
    public String addOrder(OrderFood orderFood) {
        Long foodId = orderFood.getFoods().getFoodsID();
        Long restaurantId = orderFood.getRestaurant().getRestaurantID();
        Foods foodsObj = iFoodRepository.findById(foodId).get();
        Restaurant restuarentObj = iRestaurantRepository.findById(restaurantId).get();

        orderFood.setFoods(foodsObj);
        orderFood.setRestaurant(restuarentObj);

        iOrderRepository.save(orderFood);

        return "Orderd Successfully!!";
    }

    public boolean check(String emailId, String token) {
        if(emailId == null || token == null){
            throw new IllegalStateException("Wrong EmailId And Password!!");
        }
        try {
            String customerEmail = customerRepo.findFirstByCustomerEmailId(emailId).getCustomerEmailId();
            String authenticationToken = authenticationRepo.findFirstByAuthenticationToken(token).getAuthenticationToken();

            return customerEmail.equals(emailId) && authenticationToken.equals((token));
        }catch (Exception e){
            throw new IllegalStateException("Worong EmailId Or Password!!");
        }

    }


    public Iterable<OrderFood> getAllOrderdFood() {
        return iOrderRepository.findAll();
    }
}
