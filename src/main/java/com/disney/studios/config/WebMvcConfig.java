package com.disney.studios.config;

import com.disney.studios.interceptors.DogBreedInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final DogBreedInterceptor dogBreedInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(dogBreedInterceptor).addPathPatterns("/**");

    }

}
