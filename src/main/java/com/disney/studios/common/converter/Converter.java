package com.disney.studios.common.converter;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public interface Converter<S, T> {

    Collection<T> convert(Collection<S> sources);

    default T convert(S source) {
        return source == null ? null :
                Optional.ofNullable(convert(Collections.singletonList(source)))
                        .flatMap(c -> c.stream().findFirst())
                        .orElse(null);
    }

}
