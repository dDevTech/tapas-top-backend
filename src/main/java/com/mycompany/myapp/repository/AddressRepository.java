package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Address;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByAddressAndCityAndCountry(String address, String city, String country);
}
