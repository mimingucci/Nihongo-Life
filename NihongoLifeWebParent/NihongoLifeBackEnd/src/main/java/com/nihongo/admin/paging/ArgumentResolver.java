package com.nihongo.admin.paging;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(PagingAndSortingParam.class) != null;

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request
                = (HttpServletRequest) webRequest.getNativeRequest();

        PagingAndSortingParam annotation = parameter.getParameterAnnotation(PagingAndSortingParam.class);
        String sortDir = request.getParameter("sortDir");
        String sortField = request.getParameter("sortField");
        String keyword = request.getParameter("keyword");
        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";

        return new PagingAndSortingHelper(
                sortField, sortDir, keyword);


    }
}
