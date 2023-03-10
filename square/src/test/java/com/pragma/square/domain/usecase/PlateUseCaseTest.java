package com.pragma.square.domain.usecase;

import com.pragma.square.application.request.PlateUpdateRequestDto;
import com.pragma.square.domain.exception.DomainException;
import com.pragma.square.domain.models.PlateModel;
import com.pragma.square.domain.models.RestaurantModel;
import com.pragma.square.domain.spi.IPlatePersistencePort;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;


@ExtendWith(MockitoExtension.class)
class PlateUseCaseTest {
    @Mock
    IPlatePersistencePort platePersistencePort;

    @InjectMocks
    PlateUseCase plateUseCase;
    ////////////////////////////////<--- SAVE PLATE --->///////////////////////////////////////////
    @Test
    void savePlateShouldReturnAPlate() {
        //given
        PlateModel expected = new PlateModel();
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setUserId(1L);
        Long restaurantId = 1L;
        Long categoryId = 1L;
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        //when
        Mockito.when(auth.getCredentials()).thenReturn("1");
        Mockito.when(securityContext.getAuthentication()).thenReturn(auth);
        Mockito.when(platePersistencePort.getRestaurant(restaurantId))
                .thenReturn(restaurantModel);
        Mockito.when(platePersistencePort.savePlate(expected,restaurantId,categoryId))
                .thenReturn(expected);
        PlateModel result = plateUseCase.savePlate(expected,restaurantId,categoryId);

        //then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void savePlateShouldThrowException() {
        //given
        PlateModel expected = new PlateModel();
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setUserId(1L);
        Long restaurantId = 1L;
        Long categoryId = 1L;
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        //when
        Mockito.when(auth.getCredentials()).thenReturn("2");
        Mockito.when(securityContext.getAuthentication()).thenReturn(auth);
        Mockito.when(platePersistencePort.getRestaurant(restaurantId))
                .thenReturn(restaurantModel);
        final Throwable raisedException = catchThrowable(() -> plateUseCase.savePlate(expected,restaurantId,categoryId));

        //then
        assertThat(raisedException).isInstanceOf(DomainException.class)
                .hasMessageContaining("You are not allowed to save this plate");

    }
    ////////////////////////////////<--- UPDATE PLATE --->///////////////////////////////////////////

    @Test
    void updatePlate() {
        //given
        PlateUpdateRequestDto plateUpdate = new PlateUpdateRequestDto();
        plateUpdate.setPrice("1000");
        plateUpdate.setDescription("description");
        Long plateId = 1L;
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setUserId(1L);
        PlateModel plateModel = new PlateModel();
        plateModel.setIdRestaurant(restaurantModel);


        //when
        Mockito.when(auth.getCredentials()).thenReturn("1");
        Mockito.when(securityContext.getAuthentication()).thenReturn(auth);
        Mockito.when(platePersistencePort.getPlate(plateId))
                .thenReturn(plateModel);
        Mockito.when(platePersistencePort.updatePlate(Mockito.any(PlateModel.class)))
                .thenReturn(plateModel);
        PlateModel result = plateUseCase.UpdatePlate(plateUpdate,plateId);

        //then
        Assertions.assertEquals(plateModel,result);
    }

    @Test
    void updatePlateShouldThrowException() {
        //given
        PlateUpdateRequestDto plateUpdate = new PlateUpdateRequestDto();
        plateUpdate.setPrice("1000");
        plateUpdate.setDescription("description");
        Long plateId = 1L;
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setUserId(2L);
        PlateModel plateModel = new PlateModel();
        plateModel.setIdRestaurant(restaurantModel);

        //when
        Mockito.when(auth.getCredentials()).thenReturn("1");
        Mockito.when(securityContext.getAuthentication()).thenReturn(auth);
        Mockito.when(platePersistencePort.getPlate(plateId))
                .thenReturn(plateModel);
        final Throwable raisedException = catchThrowable(() -> plateUseCase.UpdatePlate(plateUpdate,plateId));

        //then
        assertThat(raisedException).isInstanceOf(DomainException.class)
                .hasMessageContaining("You are not allowed to update this plate");


    }
    ////////////////////////////////<--- DEACTIVATE PLATE --->///////////////////////////////////////////
    @Test
    void deactivatePlate() {
        //given
        Long plateId = 1L;
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setUserId(1L);
        PlateModel plateModel = new PlateModel();
        plateModel.setIdRestaurant(restaurantModel);

        //when
        Mockito.when(auth.getCredentials()).thenReturn("1");
        Mockito.when(securityContext.getAuthentication()).thenReturn(auth);
        Mockito.when(platePersistencePort.getPlate(plateId))
                .thenReturn(plateModel);
        Mockito.when(platePersistencePort.updatePlate(Mockito.any(PlateModel.class)))
                .thenReturn(plateModel);
        PlateModel result = plateUseCase.deactivatePlate(plateId);

        //then
        Assertions.assertEquals(plateModel,result);
    }

    @Test
    void deactivatePlateShouldThrowException() {
        //given
        Long plateId = 1L;
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setUserId(2L);
        PlateModel plateModel = new PlateModel();
        plateModel.setIdRestaurant(restaurantModel);

        //when
        Mockito.when(auth.getCredentials()).thenReturn("1");
        Mockito.when(securityContext.getAuthentication()).thenReturn(auth);
        Mockito.when(platePersistencePort.getPlate(plateId))
                .thenReturn(plateModel);
        final Throwable raisedException = catchThrowable(() -> plateUseCase.deactivatePlate(plateId));

        //then
        assertThat(raisedException).isInstanceOf(DomainException.class)
                .hasMessageContaining("You are not allowed to deactivate this plate");

    }


    ////////////////////////////////<--- GET PLATERESPONSEDTO BY PAGE --->///////////////////////////////////////////
    @Test
    void getPlateResponseDtoByPage() {
        //given
        Long categoryId = 1L;
        Long restaurantId = 1L;
        int page = 1;
        int size = 10;
        String property = "price";
        String sort = "ascending";
        Page<PlateModel> pageModel = new PageImpl<>(new ArrayList<>());

        //when
        Mockito.when(platePersistencePort.getPlatesByPage(categoryId,restaurantId,page,size,property,sort))
                .thenReturn(pageModel);
        Page<PlateModel> result = plateUseCase.getPlateResponseDtoByPage(categoryId,restaurantId,page,size,property,sort);

        //then
        Assertions.assertEquals(pageModel,result);
    }

    @Test
    void getPlateResponseDtoByPageShouldThrowExceptionWhenPropertyIsWrong() {
        //given
        Long categoryId = 1L;
        Long restaurantId = 1L;
        int page = 1;
        int size = 10;
        String property = "id";
        String sort = "ascending";

        //when
        final Throwable raisedException = catchThrowable(() -> plateUseCase.getPlateResponseDtoByPage(categoryId,restaurantId,page,size,property,sort));

        //then
        assertThat(raisedException).isInstanceOf(DomainException.class)
                .hasMessageContaining("Invalid property, must be price o name");


    }

    @Test
    void getPlateResponseDtoByPageShouldThrowExceptionWhenSortIsWrong() {
        //given
        Long categoryId = 1L;
        Long restaurantId = 1L;
        int page = 1;
        int size = 10;
        String property = "price";
        String sort = "asc";

        //when
        final Throwable raisedException = catchThrowable(() -> plateUseCase.getPlateResponseDtoByPage(categoryId,restaurantId,page,size,property,sort));

        //then
        assertThat(raisedException).isInstanceOf(DomainException.class)
                .hasMessageContaining("Invalid sort, must be ascending or descending");
    }

}