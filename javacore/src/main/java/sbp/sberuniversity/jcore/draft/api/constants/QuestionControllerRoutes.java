package sbp.sberuniversity.jcore.draft.api.constants;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface QuestionControllerRoutes {

    Map<String, Map<AllowedMethods, List<AllowedQueryParams>>> SUPPORTED_ROUTES = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("GET", Map.ofEntries(
                    new AbstractMap.SimpleEntry<>(AllowedMethods.LIST, Collections.emptyList()),
                    new AbstractMap.SimpleEntry<>(AllowedMethods.FIND_BY_CRITERIA, List.of(AllowedQueryParams.FIND_BY_ID, AllowedQueryParams.FIND_BY_LEVEL, AllowedQueryParams.FIND_BY_MULTIPLE_CHOICE))
            )),
            new AbstractMap.SimpleEntry<>("PUT", Map.ofEntries(
                    new AbstractMap.SimpleEntry<>(AllowedMethods.SAVE, Collections.emptyList())
            )),
            new AbstractMap.SimpleEntry<>("DELETE", Map.ofEntries(
                    new AbstractMap.SimpleEntry<>(AllowedMethods.DELETE, List.of(AllowedQueryParams.FIND_BY_ID))
            ))
    );

}
