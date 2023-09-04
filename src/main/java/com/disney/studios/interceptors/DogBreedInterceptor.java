package com.disney.studios.interceptors;

import com.disney.studios.services.ClientValidationService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@RequiredArgsConstructor
public class DogBreedInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(HandlerInterceptor.class);

    private final ClientValidationService clientValidationService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler ) {
        if(StringUtils.isBlank(request.getHeader("x-client-id"))) {
            logger.info("No client id provided!");
            return false;
        }
        try{
            return clientValidationService.validateClientId(request.getHeader("x-client-id"));
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }
}
