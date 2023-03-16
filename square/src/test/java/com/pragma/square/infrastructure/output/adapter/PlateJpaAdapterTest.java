package com.pragma.square.infrastructure.output.adapter;

import com.pragma.square.domain.models.PlateModel;
import com.pragma.square.domain.models.RestaurantModel;
import com.pragma.square.infrastructure.output.entity.CategoryEntity;
import com.pragma.square.infrastructure.output.entity.PlateEntity;
import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
import com.pragma.square.infrastructure.output.mapper.IPlateEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IRestaurantEntityMapper;
import com.pragma.square.infrastructure.output.repository.ICategoryRepository;
import com.pragma.square.infrastructure.output.repository.IPlateRepository;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlateJpaAdapterTest {
    @Mock
 IPlateRepository plateRepository;
    @Mock
 IPlateEntityMapper plateEntityMapper;
    @Mock
IRestaurantRepository restaurantRepository;
    @Mock
 IRestaurantEntityMapper restaurantEntityMapper;
    @Mock
 ICategoryRepository categoryRepository;
    @InjectMocks
    PlateJpaAdapter plateJpaAdapter;


    @Test
    void savePlateShouldReturnPlatModel() {
        //given
        CategoryEntity categoryEntity = new CategoryEntity();
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        PlateEntity plateEntity = new PlateEntity();
        PlateEntity plateEntity2 = new PlateEntity();
        PlateModel plateModel = new PlateModel();
        PlateModel expected = new PlateModel();

        //when
        when(categoryRepository.findById(anyLong()))
                .thenReturn(Optional.of(categoryEntity));
        when(restaurantRepository.findById(anyLong()))
                .thenReturn(Optional.of(restaurantEntity));
        when(plateEntityMapper.toPlateEntity(plateModel))
                .thenReturn(plateEntity);
        when(plateEntityMapper.toPlateModel(any(PlateEntity.class)))
                .thenReturn(expected);
        when(plateRepository.save(any(PlateEntity.class)))
                .thenReturn(plateEntity2);
        PlateModel result = plateJpaAdapter.savePlate(plateModel,1L,1L);

        //then
        assertEquals(result, expected);
    }

    @Test
    void getRestaurantShouldReturnRestaurantModel() {
        //given
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        RestaurantModel expected = new RestaurantModel();

        //when
        when(restaurantRepository.findById(anyLong()))
                .thenReturn(Optional.of(restaurantEntity));
        when(restaurantEntityMapper.toRestaurantModel(any(RestaurantEntity.class)))
                .thenReturn(expected);
        RestaurantModel result = plateJpaAdapter.getRestaurant(anyLong());

        //then
        assertEquals(result, expected);
    }

    @Test
    void getPlateShouldReturnPlateModel() {
        //given
        PlateEntity plateEntity = new PlateEntity();
        PlateModel expected = new PlateModel();

        //when
        when(plateRepository.findById(anyLong()))
                .thenReturn(Optional.of(plateEntity));
        when(plateEntityMapper.toPlateModel(any(PlateEntity.class)))
                .thenReturn(expected);
        PlateModel result = plateJpaAdapter.getPlate(anyLong());

        //then
        assertEquals(result,expected);
    }

    @Test
    void updatePlateShouldReturnPlateModel() {
        //given
        PlateEntity plateEntity = new PlateEntity();
        PlateEntity plateEntity2 = new PlateEntity();
        PlateModel plateModel = new PlateModel();
        PlateModel expected = new PlateModel();

        //when
        when(plateEntityMapper.toPlateEntity(plateModel))
                .thenReturn(plateEntity);
        when(plateEntityMapper.toPlateModel(any(PlateEntity.class)))
                .thenReturn(expected);
        when(plateRepository.save(any(PlateEntity.class)))
                .thenReturn(plateEntity2);
        PlateModel result = plateJpaAdapter.updatePlate(plateModel);

        //then
        assertEquals(result,expected);
    }

    @Test
    void getPlatesByPageShouldReturnPagePlateModel() {
        //given
        List<PlateEntity> plateEntityList = new ArrayList<>();
        Page<PlateEntity> plateEntityPage = new PageImpl<>(plateEntityList) ;
        List<PlateModel> plateModelList = new ArrayList<>();
        Page<PlateModel> expected = new PageImpl<>(plateModelList);

        //when
        when(plateRepository.findAllByIdCategory_IdAndIdRestaurant_Id(anyLong(),anyLong(),any()))
                .thenReturn(Optional.of(plateEntityPage));
        Page<PlateModel> result = plateJpaAdapter.getPlatesByPage(1L,1L,1,1,"price","ascending");

        //
        assertEquals(result,expected);
    }

    @Test
    void findCurrentUserIdShouldReturnString() {
        //given
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        String expected = "1";

        //when
        when(securityContext.getAuthentication()).thenReturn(auth);
        when(auth.getCredentials()).thenReturn("1");
        String result = plateJpaAdapter.findCurrentUserId();

        //then
        assertEquals(expected,result);
    }
}