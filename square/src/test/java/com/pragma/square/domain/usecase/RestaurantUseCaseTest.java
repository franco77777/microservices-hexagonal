package com.pragma.square.domain.usecase;

import com.pragma.square.domain.exception.DomainException;
import com.pragma.square.domain.models.RestaurantModel;
import com.pragma.square.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantUseCaseTest {
    @Mock
    IRestaurantPersistencePort restaurantPersistencePort;
    @InjectMocks
    RestaurantUseCase restaurantUseCase;
    ////////////////////////////////<--- SAVE RESTAURANT --->///////////////////////////////////////////
    @Test
    void saveRestaurant() {
        //given
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setUserId(1L);

        //when
        when(restaurantPersistencePort.saveRestaurant(any(RestaurantModel.class)))
                .thenReturn(restaurantModel);
        when(restaurantPersistencePort.getRoleUser(anyLong()))
                .thenReturn("true");
        RestaurantModel result = restaurantUseCase.saveRestaurant(restaurantModel);

        //given
        Assertions.assertEquals(restaurantModel, result);
    }
    ////////////////////////////////<--- GET ALL RESTAURANTS --->///////////////////////////////////////////
    @Test
    void getAllRestaurants() {
        //given
        List<RestaurantModel> restaurantModels = List.of(new RestaurantModel());
        //when
        when(restaurantPersistencePort.getAllRestaurants())
                .thenReturn(restaurantModels);
        List<RestaurantModel> result = restaurantUseCase.getAllRestaurants();

        //then
        Assertions.assertEquals(restaurantModels, result);
    }
    ////////////////////////////////<--- GET RESTAURANT BY USER ID --->///////////////////////////////////////////
    @Test
    void getRestaurantsByUserId() {
        //given
        Long userId = 1L;
        List<RestaurantModel> restaurantModels = List.of(new RestaurantModel());

        //when
        when(restaurantPersistencePort.getRestaurantsByUserId(userId))
                .thenReturn(restaurantModels);
        List<RestaurantModel> result = restaurantUseCase.getRestaurantsByUserId(userId);

        //then
        Assertions.assertEquals(restaurantModels, result);
    }
    ////////////////////////////////<--- GET RESTAURANT BY PAGE --->///////////////////////////////////////////
    @Test
    void getRestaurantsByPage() {
        //given
        int page = 1;
        int size = 10;
        String sort = "ascending";
        Page<RestaurantModel> restaurantModels = new PageImpl<>(new ArrayList<>());

        //when
        when(restaurantPersistencePort.getRestaurantsByPage(page, size, sort))
                .thenReturn(restaurantModels);
        Page<RestaurantModel> result = restaurantUseCase.getRestaurantsByPage(page, size, sort);

        //then
        Assertions.assertEquals(restaurantModels, result);
}

    @Test
    void getRestaurantByPageShouldThrowException() {
        //given
        int page = 1;
        int size = 10;
        String sort = "asc";

        //when
        final Throwable raisedException = catchThrowable(() -> restaurantUseCase.getRestaurantsByPage(page, size, sort));

        //then
        assertThat(raisedException).isInstanceOf(DomainException.class)
                .hasMessageContaining("invalid sort, must be ascending or descending");
}}