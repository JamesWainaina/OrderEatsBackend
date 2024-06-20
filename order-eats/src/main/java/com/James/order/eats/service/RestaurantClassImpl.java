package com.James.order.eats.service;

import com.James.order.eats.Dto.RestaurantDto;
import com.James.order.eats.model.Address;
import com.James.order.eats.model.Restaurant;
import com.James.order.eats.model.User;
import com.James.order.eats.repository.*;
import com.James.order.eats.requests.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantClassImpl  implements RestaurantService {

    @Autowired
    private RestaurantRespository restaurantRespository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user)throws Exception{


        Address address = new Address();
        addressRepository.save(address);

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);
        return restaurantRespository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if (restaurant.getCuisineType() != null){
            restaurant.setCuisineType(updateRestaurant.getCuisineType());
        }
        if(restaurant.getDescription() != null){
            restaurant.setDescription(updateRestaurant.getDescription());
        }
        if (restaurant.getImages() != null){
            restaurant.setImages(updateRestaurant.getImages());
        }
        if(restaurant.getName() != null){
            restaurant.setName(updateRestaurant.getName());
        }

        return restaurantRespository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant= findRestaurantById(restaurantId);

        restaurantRespository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRespository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurants(String keyword) {
        return restaurantRespository.findbyRestaurantName(keyword);
    }


    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws Exception {
        Optional<Restaurant> opt = restaurantRespository.findById(restaurantId);

        if (opt.isEmpty()){
            throw new Exception("Restaurant not found with id: " + restaurantId);
        }
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRespository.findByOwnerId(userId);
        if(restaurant== null){
            throw new Exception("Restaurant not found with user id: " + userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addTOFavourites(Long restaurantId, User user) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);
        RestaurantDto dto = new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);

        boolean isFavourites = false;
        List<RestaurantDto> favourites = user.getFavourites();
        for (RestaurantDto favourite : favourites){
            if (favourite.getId().equals(restaurantId)){
                isFavourites = true;
                break;
            }
        }

        if(isFavourites){
            favourites.removeIf(favourite -> favourite.getId().equals(restaurantId));
        }else {
            favourites.add(dto);
        }

        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(restaurant.isOpen());
        return restaurantRespository.save(restaurant);
    }
}
