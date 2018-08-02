package rest.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CacheControlFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        if (request.getMethod().equals("GET")) {
            CacheControl cacheControl = new CacheControl();
            cacheControl.setNoStore(true);
            cacheControl.setNoCache(true);
            response.getHeaders().add("Cache-Control", cacheControl);
        }

    }
}
