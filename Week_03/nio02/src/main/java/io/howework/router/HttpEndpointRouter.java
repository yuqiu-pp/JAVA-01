package io.howework.router;

import java.util.List;

public interface HttpEndpointRouter {

    String router(List<String> endpoints);
}
