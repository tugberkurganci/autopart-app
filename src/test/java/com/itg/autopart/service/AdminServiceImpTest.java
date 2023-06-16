package com.itg.autopart.service;

import com.itg.autopart.entities.ListedProduct;
import com.itg.autopart.entities.User;
import com.itg.autopart.entities.enums.UserRole;
import com.itg.autopart.mappers.ModelMapperServiceImp;
import com.itg.autopart.repository.ListedProductRepository;
import com.itg.autopart.repository.UserRepository;
import com.itg.autopart.requests.CreateProductRequestAndAdmin;
import com.itg.autopart.requests.GiveAdminPermitRequest;
import com.itg.autopart.requests.MailToAdminRequest;
import com.itg.autopart.security.email.EmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceImpTest {

    private ModelMapperServiceImp modelMapperService = new ModelMapperServiceImp(new ModelMapper());

    @Mock
    private UserRepository userRepository;

    @Mock
    private ListedProductRepository listedProductRepository;

    @Mock
    private EmailSender emailSender;

    private AdminServiceImp adminService;
    @BeforeEach
    void setUp() {
        adminService = new AdminServiceImp(userRepository,modelMapperService,listedProductRepository,emailSender);
    }

    @Test
    void giveAdminPermit_shouldGiveAdminPermit_whenPasswordIsSpecial(){
        // given
        GiveAdminPermitRequest giveAdminPermitRequest=new GiveAdminPermitRequest();
        giveAdminPermitRequest.setId(1);
        giveAdminPermitRequest.setPassword("special");

        when(userRepository.findById(1)).thenReturn(Optional.of(new User()));
        when(userRepository.save(any())).thenReturn(new User());

        // when
        adminService.giveAdminPermit(giveAdminPermitRequest);

        // then
        verify(userRepository).findById(1);
        verify(userRepository).save(any());
    }
    @Test
    void addListedProduct_shouldAdd_whenInputIsValid(){

        //given
        CreateProductRequestAndAdmin createProductRequestAndAdmin=new CreateProductRequestAndAdmin();

        when(listedProductRepository.save(any())).thenReturn(new ListedProduct());

        //when
        adminService.addListedProduct(createProductRequestAndAdmin);

        //then
        verify(listedProductRepository).save(any());

    }
    @Test
    void mailToAdmin_shouldSendMail_whenInputIsValid(){

        MailToAdminRequest mailToAdminRequest =new MailToAdminRequest();
        mailToAdminRequest.setContent("tutu");
        mailToAdminRequest.setEmail("email");
        User user=new User();
        user.setUserRole(UserRole.valueOf("ADMIN"));
        user.setEmail("tu");
        List<User> userList=new ArrayList<>();
        userList.add(user);
        User user2=new User();
        user2.setEmail(mailToAdminRequest.getEmail());
        user2.setId(2);

        when(userRepository.findByEmail(any())).thenReturn(Optional.of(user2));
        when(userRepository.findAll()).thenReturn(userList);
        doNothing().when(emailSender).sendUserRequest(anyString(),anyString());

        adminService.mailToAdmin(mailToAdminRequest);

        verify(userRepository).findByEmail(any());
        verify(emailSender).sendUserRequest(user.getEmail(),mailToAdminRequest.getContent()+" from " + user2.getEmail()+"user ıd :"+user2.getId());
    }

}