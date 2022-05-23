package com.example.Hugo.s.Couture.services.serviceImpl;

import com.example.Hugo.s.Couture.dto.PostDto;
import com.example.Hugo.s.Couture.dto.RegistrationDto;
import com.example.Hugo.s.Couture.exceptions.InvalidRequestException;
import com.example.Hugo.s.Couture.exceptions.InvalidUserException;
import com.example.Hugo.s.Couture.model.Post;
import com.example.Hugo.s.Couture.model.User;
import com.example.Hugo.s.Couture.repositories.PostRepository;
import com.example.Hugo.s.Couture.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.postgresql.hostchooser.HostRequirement.any;

@SpringBootTest
class PostServicesImplTest {

    @MockBean
    private PostRepository postRepository;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private PostServicesImpl underTest;


    @Autowired
    private ModelMapper mapper;

    private Post postUnderTest;
    private Post post;
    @MockBean
    private Post postMock;
    private User user;

    @BeforeEach
    void setUp() {
        underTest = new PostServicesImpl(postRepository, userRepository, mapper);

        user = User.builder()
                .id(1L)
                .username("love")
                .email("love@gmail.com")
                .role("admin")
                .password("12345")
                .build();

        postUnderTest = Post.builder()
                .productName("zanotti shoe")
                .price(50.00)
                .content("A pair of zanottis")
                .category("Male shoes")
                .build();

        post = Post.builder()
                .postId(1L)
                .productName("zanotti shoe")
                .price(50.00)
                .user(user)
                .content("A pair of zanottis")
                .category("Male shoes")
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(postRepository.save(any())).thenReturn(post);
        when(userRepository.save(any())).thenReturn(user);

    }

    @Test
    void makeNewPost() {
        long userId = 1L;

        PostDto postDto = mapper.map(post, PostDto.class);
        ResponseEntity<PostDto> regDto = underTest.makeNewPost(userId,postDto);
        assertEquals("zanotti shoe", regDto.getBody().getProductName());
    }
    @Test
    void shouldThrowPostException() {

        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        PostDto postDto = mapper.map(post, PostDto.class);
        assertThrows(InvalidUserException.class, () -> underTest.makeNewPost(2L,postDto),"Invalid User");
    }



    @Test
    void editPost() {


    }

    @Test
    void getAllPost() {
        //when
        underTest.getAllPost();
        //then
        verify(postRepository).findAll();
    }

    @Test
    void deletePost() {
        long postId = 1L;
        long userId = 1L;

        postMock = Post.builder()
                .postId(1L)
                .productName("zanotti shoe")
                .price(50.00)
                .user(user)
                .content("A pair of zanottis")
                .category("Male shoes")
                .build();

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        doReturn(user).when(postMock).getUser();
        postRepository.deleteById(postId);
        underTest.deletePost(postId,userId);
        verify(postRepository, times(1)).deleteById(postId);


    }

    @Test
    void getUserPosts() {
    }
}