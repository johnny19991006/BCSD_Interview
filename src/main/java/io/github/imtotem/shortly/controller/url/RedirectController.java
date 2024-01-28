package io.github.imtotem.shortly.controller.url;

import io.github.imtotem.shortly.service.url.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class RedirectController {
    private final UrlService service;

    @GetMapping("/{shorten}")
    public void redirect(@PathVariable String shorten,
                         HttpServletResponse httpServletResponse) throws IOException {
        String originUrl = service.restoreUrl(shorten);
        httpServletResponse.sendRedirect(originUrl);
    }
}
