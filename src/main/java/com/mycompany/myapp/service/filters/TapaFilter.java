package com.mycompany.myapp.service.filters;

import com.mycompany.myapp.domain.Tapa;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TapaFilter {

    public static List<Tapa> filterTapas(List<Tapa> tapas, Map<String, String> filters) {
        return tapas
            .stream()
            .filter(tapa -> {
                String type = filters.get("type");
                if (type != null && !Objects.toString(tapa.getType(), "").contains(type)) {
                    return false;
                }

                String precedence = filters.get("precedence");
                if (precedence != null && !Objects.toString(tapa.getCountry(), "").contains(precedence)) {
                    return false;
                }

                String country = filters.get("country");
                if (country != null && !Objects.toString(tapa.getEstablishment().getAddress().getCountry(), "").contains(country)) {
                    return false;
                }

                String city = filters.get("city");
                if (city != null && !Objects.toString(tapa.getEstablishment().getAddress().getCity(), "").contains(city)) {
                    return false;
                }
                return true;
            })
            .collect(Collectors.toList());
    }
}
