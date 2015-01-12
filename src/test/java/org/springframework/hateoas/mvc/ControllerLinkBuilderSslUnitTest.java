package org.springframework.hateoas.mvc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.Link;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Alfonso on 13/01/2015.
 */
public class ControllerLinkBuilderSslUnitTest {


    protected MockHttpServletRequest request;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        request.setProtocol("https");
        request.setScheme("https");
        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(requestAttributes);
    }

    @Test
    public void usesForwardedSslIfHttpsRequestAndNoForwardedHeadersSet() {
        Link link = linkTo(PersonControllerImpl.class).withSelfRel();
        assertThat(link.getHref(), startsWith("https://"));
    }

    @RequestMapping("/people")
    interface PersonController {}

    class PersonControllerImpl implements PersonController {}

}
