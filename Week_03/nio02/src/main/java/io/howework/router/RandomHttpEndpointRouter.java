package io.howework.router;

import java.util.List;
import java.util.Random;

public class RandomHttpEndpointRouter implements HttpEndpointRouter {

    @Override
    public String router(List<String> endpoints) {
        Random random = new Random(System.currentTimeMillis());
        int index = random.nextInt(endpoints.size());
        return endpoints.get(index);
    }
}
